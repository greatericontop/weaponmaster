package io.github.greatericontop.weaponmaster.mainitems.AssassinsBlade;

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
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AssassinsBladeListener implements Listener {
    private final float REQUIRED_ANGLE = 40.0F;

    private final Util util;
    public AssassinsBladeListener(WeaponMasterMain plugin) {
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        if (!util.checkForAssassinsBlade(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.assassinsblade.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.assassinsblade.use§3.");
            return;
        }
        Entity target = event.getEntity();

        // find difference of the angles the player and target
        // if both directions point in the same direction (same angle), then the attacker is behind the defender
        // if they point in opposite directions (difference of 180 degrees), then they are facing each other
        float playerYaw = (player.getLocation().getYaw() + 360) % 360;
        float targetYaw = (target.getLocation().getYaw() + 360) % 360;
        float angle = Math.abs(playerYaw - targetYaw); // 0 ~ 360
        player.sendMessage(String.format("§7[Debug] angle=%.1f playerYaw=%.1f targetYaw=%.1f", angle, playerYaw, targetYaw));
        if (angle < REQUIRED_ANGLE || 360-REQUIRED_ANGLE < angle) {
            event.setDamage(event.getDamage() * 1.5);
            player.getWorld().spawnParticle(Particle.SMOKE_LARGE, target.getLocation(), 40);
        }
    }

}