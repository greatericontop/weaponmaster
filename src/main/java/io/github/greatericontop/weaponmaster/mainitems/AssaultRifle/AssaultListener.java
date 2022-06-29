package io.github.greatericontop.weaponmaster.mainitems.AssaultRifle;

/*
 * WeaponMaster Copyright (C) 2021-present greateric.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty  of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

enum AssaultRifleMode {
    SINGLE_SHOT,
    BURST,
    AUTO // not implemented
}

public class AssaultListener implements Listener {
    private final Map<UUID, AssaultRifleMode> modes = new HashMap<>();

    private final WeaponMasterMain plugin;
    private final Util util;
    public AssaultListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    private void fireOneRound(Player player, ItemStack rifle) {
        Location eyeLoc = player.getEyeLocation();
        Vector velocityVector = eyeLoc.getDirection().multiply(46.3); // 46.3 block/tick is 926 meter/s is 3038 feet/s
        Arrow arrow = (Arrow) player.getWorld().spawnEntity(eyeLoc, EntityType.ARROW);
        arrow.setDamage(7.0);
        arrow.setKnockbackStrength(3);
        arrow.setVelocity(velocityVector);
        arrow.setShooter(player);
        player.getWorld().spawnParticle(Particle.SMALL_FLAME, eyeLoc, 20, 0.0, 0.0, 0.0, 0.001);
        player.playSound(player, Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
        setBulletsRemaining(rifle, getBulletsRemaining(rifle) - 1);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBowShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) { return; }
        if (util.checkForAssaultRifle(event.getBow())) {
            event.setCancelled(true);
        }
    }

    public int getBulletsRemaining(ItemStack rifle) {
        PersistentDataContainer pdc = rifle.getItemMeta().getPersistentDataContainer();
        Integer amount = pdc.get(new NamespacedKey(plugin, "loaded"), PersistentDataType.INTEGER);
        if (amount == null) {
            return 0;
        }
        return amount;
    }
    public void setBulletsRemaining(ItemStack rifle, int amount) {
        ItemMeta im = rifle.getItemMeta();
        PersistentDataContainer pdc = im.getPersistentDataContainer();
        pdc.set(new NamespacedKey(plugin, "loaded"), PersistentDataType.INTEGER, amount);
        rifle.setItemMeta(im);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        Player player = event.getPlayer();
        if (!util.checkForAssaultRifle(player.getInventory().getItemInMainHand())) {
            return;
        }
        if (!player.hasPermission("weaponmaster.assaultrifle.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.assaultrifle.use§3.");
            return;
        }
        ItemStack rifle = player.getInventory().getItemInMainHand();

        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            switch (modes.getOrDefault(player.getUniqueId(), AssaultRifleMode.SINGLE_SHOT)) {
                case SINGLE_SHOT:
                    if (getBulletsRemaining(rifle) >= 1) {
                        player.setVelocity(player.getVelocity().subtract(player.getEyeLocation().getDirection().multiply(0.03)));
                        fireOneRound(player, rifle);
                    } else {
                        player.sendMessage("§7No rounds loaded!");
                        break;
                    }
                    break;

                case BURST:
                    new BukkitRunnable() {
                        int i = 0;
                        public void run() {
                            if (i >= 5) {
                                cancel();
                                return;
                            }
                            if (getBulletsRemaining(rifle) >= 1) {
                                player.setVelocity(player.getVelocity().subtract(player.getEyeLocation().getDirection().multiply(0.03)));
                                fireOneRound(player, rifle);
                            } else {
                                player.sendMessage("§7No rounds loaded!");
                                cancel();
                                return;
                            }
                            i++;
                        }
                    }.runTaskTimer(plugin, 1L, 2L);
                    break;

                case AUTO:
                default:
                    player.sendMessage("§7not implemented");
                    break;
            }

        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {
            if (player.isSneaking()) {
                player.sendMessage("§7Reloading!");
                if (getBulletsRemaining(rifle) == -1) {
                    player.sendMessage("§cCan't reload while another reload is taking place!");
                } else {
                    setBulletsRemaining(rifle, -1);
                    new BukkitRunnable() {
                        public void run() {
                            int amount = 64; // TODO: this costs arrows
                            setBulletsRemaining(rifle, amount);
                            player.sendMessage("§cReloaded!");
                        }
                    }.runTaskLater(plugin, 20L);
                }
            } else {
                AssaultRifleMode currentMode = modes.getOrDefault(player.getUniqueId(), AssaultRifleMode.SINGLE_SHOT);
                if (currentMode == AssaultRifleMode.SINGLE_SHOT) {
                    modes.put(player.getUniqueId(), AssaultRifleMode.BURST);
                    player.sendMessage("§3Set to burst!");
                } else if (currentMode == AssaultRifleMode.BURST) {
                    modes.put(player.getUniqueId(), AssaultRifleMode.SINGLE_SHOT);
                    player.sendMessage("§3Set to single shot!");
                }
            }
        }
    }

}