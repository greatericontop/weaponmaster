package io.github.greatericontop.weaponmaster.SniperRifle;

/*
    Copyright (C) 2021 greateric.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.InaccuracyAdder;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
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
        Vector velocityVector = eyeLoc.getDirection().multiply(50.95); // 46.3 block/tick is 1019 meter/s is 3343 feet/s
        Arrow arrow = (Arrow) player.getWorld().spawnEntity(eyeLoc, EntityType.ARROW);
        arrow.setDamage(13.0);
        arrow.setKnockbackStrength(3);
        arrow.setVelocity(velocityVector);
        arrow.setShooter(player);
        player.getWorld().spawnParticle(Particle.SMALL_FLAME, eyeLoc, 20);
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

        if (player.getInventory().containsAtLeast(new ItemStack(Material.ARROW), 1)) {
            player.setVelocity(player.getVelocity().subtract(player.getEyeLocation().getDirection().multiply(0.02)));
            fireOneRound(player);
            if (player.getGameMode() != GameMode.CREATIVE) {
                player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
            }
        }
    }

}