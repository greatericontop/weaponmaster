package io.github.greatericontop.weaponmaster.SniperRifle;

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
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
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
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class SniperItemListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;

    public SniperItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    private void fireOneRound(Player player) {
        Location eyeLoc = player.getEyeLocation();
        Vector velocityVector = eyeLoc.getDirection().multiply(50.95); // 50.95 block/tick is 1019 meter/s is 3343 feet/s
        Arrow arrow = (Arrow) player.getWorld().spawnEntity(eyeLoc, EntityType.ARROW);
        arrow.setDamage(13.0);
        arrow.setKnockbackStrength(3);
        arrow.setVelocity(velocityVector);
        arrow.setShooter(player);
        player.getWorld().spawnParticle(Particle.SMALL_FLAME, eyeLoc, 20);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBowShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        if (util.checkForSniperRifle(event.getBow())) {
            ((Player) event.getEntity()).sendMessage("§cYou need to use LEFT CLICK to shoot this.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) { return; }
        Player player = event.getPlayer();
        if (!util.checkForSniperRifle(player.getInventory().getItemInMainHand())) {
            return;
        }
        if (!player.hasPermission("weaponmaster.sniperrifle.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.sniperrifle.use§3.");
            return;
        }
        ItemStack sniper = player.getInventory().getItemInMainHand();
        Damageable durability = (Damageable) sniper.getItemMeta();
        if (durability.getDamage() > 1) {
            if (player.getGameMode() == GameMode.CREATIVE) {
                durability.setDamage(0);
                sniper.setItemMeta(durability);
                player.sendMessage("§cFixed durability!");
            } else {
                plugin.paperUtils.sendActionBar(player, "§cThis weapon is reloading! This takes 1.5 seconds.", true);
                return;
            }
        }

        if (player.getGameMode() == GameMode.CREATIVE || player.getInventory().containsAtLeast(new ItemStack(Material.ARROW), 1)) {
            player.setVelocity(player.getVelocity().subtract(player.getEyeLocation().getDirection().multiply(0.06)));
            fireOneRound(player);
            if (player.getGameMode() != GameMode.CREATIVE) {
                player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
            }
            new BukkitRunnable() {
                int ticksLeft = 29;
                public void run() {
                    if (ticksLeft == 0) {
                        durability.setDamage(0);
                        sniper.setItemMeta(durability);
                        cancel();
                        return;
                    }
                    int remainingDurability = (int) ( 384.0/30.0 * (30-ticksLeft) );
                    // player.sendMessage("§7test " + remainingDurability);
                    durability.setDamage(384 - remainingDurability);
                    sniper.setItemMeta(durability);
                    ticksLeft--;
                }
            }.runTaskTimer(plugin, 1L, 1L);
        }
    }

}