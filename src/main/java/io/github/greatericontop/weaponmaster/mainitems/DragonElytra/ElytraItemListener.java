package io.github.greatericontop.weaponmaster.mainitems.DragonElytra;

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
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ElytraItemListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public ElytraItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler()
    public void onClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        if (Util.checkForInteractableBlock(event)) { return; }
        Player player = event.getPlayer();
        if (!util.checkForDragonElytra(player.getInventory().getChestplate())) { return; }
        if (!player.hasPermission("weaponmaster.dragonelytra.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.dragonelytra.use§3.");
            return;
        }
        if (player.getInventory().getItemInMainHand().getType() == Material.ENDER_EYE) {
            Vector bonus = player.getEyeLocation().getDirection().multiply(7.5);
            player.setVelocity(player.getVelocity().add(bonus));
            player.getInventory().removeItem(new ItemStack(Material.ENDER_EYE, 1));
            event.setCancelled(true);
        }
    }

    public ElytraItemListener regDragonElytraRunnable() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.hasPermission("weaponmaster.dragonelytra.use")) { continue; }
                    if (!(util.checkForDragonElytra(player.getInventory().getChestplate()))) { continue; }
                    if (!player.isGliding()) { continue; }
                    double playerVelocity = player.getVelocity().length();
                    double bonusMagnitude;
                    if (playerVelocity <= 1.25) { // 25 m/s
                        bonusMagnitude = 0.01;
                    } else if (playerVelocity <= 1.75) { // 35 m/s
                        // linear decrease from 0.01 to 0.005
                        bonusMagnitude = 0.005 + 0.005 * (1 - 2.0*(playerVelocity-1.25));
                    } else {
                        bonusMagnitude = 0.005;
                    }
                    Vector bonus = player.getEyeLocation().getDirection().multiply(bonusMagnitude);
                    player.setVelocity(player.getVelocity().add(bonus));
                    player.spawnParticle(Particle.SMALL_FLAME, player.getLocation(), 3, 0.0, 0.0, 0.0, 0.001);
                }
            }
        }.runTaskTimer(plugin, 200L, 1L);
        return this;
    }

}