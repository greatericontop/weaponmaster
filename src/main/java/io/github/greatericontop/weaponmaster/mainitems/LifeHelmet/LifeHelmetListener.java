package io.github.greatericontop.weaponmaster.mainitems.LifeHelmet;

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
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class LifeHelmetListener implements Listener {

    public Map<UUID, Integer> cooldown = new HashMap<UUID, Integer>();
    private final WeaponMasterMain plugin;
    private final Util util;

    public LifeHelmetListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.HIGHEST) // run last to query the correct final damage
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getEntity();
        if (!util.checkForLifeHelmet(player.getInventory().getHelmet())) { return; }
        if (!player.hasPermission("weaponmaster.lifehelmet.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.lifehelmet.use§3.");
            return;
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) { return; } // don't interact with void damage or /kill
        if (cooldown.getOrDefault(player.getUniqueId(), 0) == 0) {
            // EntityDamageEvent and check if fatal, because spigot doesn't let you cancel EntityDeathEvent
            if (player.getHealth() + player.getAbsorptionAmount() - event.getFinalDamage() <= 0) {
                player.setHealth(1.0);
                player.setAbsorptionAmount(0.0);
                event.setDamage(0.000_001);
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 1, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 300, 4, true));
                player.sendTitle("§cYou were saved from death!", "", 0, 100, 100);
                player.sendMessage("§9------------------------------");
                player.sendMessage("§eYou were saved from death!");
                player.sendMessage("§9------------------------------");
                player.getWorld().playSound(player, Sound.ITEM_TOTEM_USE, 4.0F, 1.0F);
                int cooldownNonce = (int) (1 + (Math.random() * 2147483645));
                cooldown.put(player.getUniqueId(), cooldownNonce);
                new BukkitRunnable() {
                    public void run() {
                        // If the cooldown was forcibly reset by the command and was used again, the nonce will be
                        // different, and the original cooldown will not reset it again.
                        if (cooldown.get(player.getUniqueId()) == cooldownNonce) {
                            cooldown.put(player.getUniqueId(), 0);
                            player.sendMessage("§eYour Helmet of Life cooldown is over!");
                        } else {
                            player.sendMessage("§7[Debug] refusing to reset! expected nonce: "+cooldownNonce
                                    + " actual nonce: "+cooldown.get(player.getUniqueId()));
                        }
                    }
                }.runTaskLater(plugin, 12_000L); // after 10 minutes

            }
        }
    }

}