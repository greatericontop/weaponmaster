package io.github.greatericontop.weaponmaster.mainitems.EndSword;

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
import org.bukkit.FluidCollisionMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class EndSwordListener implements Listener {
    private static final double TELEPORT_DISTANCE = 7.0;
    private static final double RAYTRACE_EXTRA_PADDING = 0.4;
    private static final double RAYTRACE_TOLERANCE = 0.05;

    private final WeaponMasterMain plugin;
    private final EndPowerManager powerManager;
    private final Util util;
    public EndSwordListener(WeaponMasterMain plugin, EndPowerManager powerManager) {
        this.plugin = plugin;
        this.powerManager = powerManager;
        this.util = new Util(null);
    }

    private static double findMaxRaytraceDistance(Player player, double min, double max, boolean isFirstRun) {
        // Binary search
        if (max - min < RAYTRACE_TOLERANCE) {
            return (min + max) / 2.0;
        }
        double test = isFirstRun ? max : (min + max) / 2.0;
        RayTraceResult rtxResult = player.getWorld().rayTraceBlocks(
                player.getEyeLocation(), player.getEyeLocation().getDirection(),
                test, FluidCollisionMode.NEVER, true);
        if (rtxResult != null && rtxResult.getHitBlock() != null) {
            return findMaxRaytraceDistance(player, min, test, false);
        } else {
            return findMaxRaytraceDistance(player, test, max, false);
        }
    }

    @EventHandler()
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND)  return;
        Player player = event.getPlayer();
        if (!util.checkForEndSword(player.getInventory().getItemInMainHand()))  return;
        if (!player.hasPermission("weaponmaster.endsword.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.endsword.use§3.");
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)  return;
        if (Util.checkForInteractableBlock(event))  return;
        if (powerManager.getPower(player) < 10) {
            player.sendMessage("§7You don't have enough power to teleport!");
            return;
        }

        double teleportDistance = findMaxRaytraceDistance(player, 0.0, TELEPORT_DISTANCE+RAYTRACE_EXTRA_PADDING, true) - RAYTRACE_EXTRA_PADDING;
        if (teleportDistance < 3.0) {
            player.sendMessage("§7You can't teleport through blocks!");
            return;
        }
        player.sendMessage(""+teleportDistance);
        Vector tp = player.getEyeLocation().getDirection().multiply(teleportDistance);
        player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
        player.teleport(player.getEyeLocation().add(tp));
        powerManager.incrementPower(player, -10);
    }

}
