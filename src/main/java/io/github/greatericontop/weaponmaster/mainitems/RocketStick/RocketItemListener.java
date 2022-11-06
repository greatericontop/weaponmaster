package io.github.greatericontop.weaponmaster.mainitems.RocketStick;

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
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

public class RocketItemListener implements Listener {

    private final double KNOCKBACK_OTHER = 8.3;
    private final double KNOCKBACK_SELF = 8.8;
    private final double TELEPORT_DISTANCE = 6.5;

    private final WeaponMasterMain plugin;
    private final Util util;
    public RocketItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        Player player = (Player)event.getDamager();
        if (!util.checkForRocketStick(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.rocketstick.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.rocketstick.use§3.");
            return;
        }

        Vector knockback = player.getEyeLocation().getDirection().multiply(KNOCKBACK_OTHER);
        event.getEntity().setVelocity(event.getEntity().getVelocity().add(knockback));
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        Player player = event.getPlayer();
        if (!util.checkForRocketStick(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.rocketstick.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.rocketstick.use§3.");
            return;
        }

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Util.checkForInteractableBlock(event)) { return; }
            if (player.isSneaking()) {
                // check raytrace
                RayTraceResult rtxResult = player.getWorld().rayTraceBlocks(
                        player.getEyeLocation(), player.getEyeLocation().getDirection(),
                        TELEPORT_DISTANCE + 0.1, FluidCollisionMode.NEVER, true);
                if (rtxResult != null && rtxResult.getHitBlock() != null) {
                    Location loc = rtxResult.getHitBlock().getLocation();
                    player.sendMessage(String.format("§7Canceled due to: %s %s %s", loc.getX(), loc.getY(), loc.getZ()));
                    player.sendMessage("§7You can't teleport through blocks!");
                    return;
                }
                Vector tp = player.getEyeLocation().getDirection().multiply(TELEPORT_DISTANCE);
                player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
                player.teleport(player.getEyeLocation().add(tp));
            } else {
                Vector knockback = player.getEyeLocation().getDirection().multiply(KNOCKBACK_SELF);
                player.setVelocity(player.getVelocity().add(knockback));
            }
        }
    }

}