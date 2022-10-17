package io.github.greatericontop.weaponmaster.mainitems.GuidedMissile;

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
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class GuidedMissileManager implements Listener {
    private final float EXPLOSION_POWER = 11.0F;
    private final float WEAK_EXPLOSION_POWER = 3.0F;
    private final double PROXIMITY_DISTANCE_SQUARED = 3.5 * 3.5;
    private final double INITIAL_VELOCITY = 0.6;
    private final double ACCELERATION = 0.28; // in blocks per tick^2
    private final double AIR_RESISTANCE = 0.85; // decrease terminal velocity

    private final WeaponMasterMain plugin;
    private final Util util;
    private final GuidedMissileTargetSelector targetSelector;
    public GuidedMissileManager(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.util = new Util(plugin);
        this.targetSelector = new GuidedMissileTargetSelector(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        Player player = event.getPlayer();
        if (!util.checkForGuidedMissile(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.guidedmissile.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.guidedmissile.use§3.");
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            event.setCancelled(true);
        }
        if (Util.checkForInteractableBlock(event)) { return; }
        if (targetSelector.getLockState(player) != GuidedMissileTargetSelector.LockState.LOCKED) {
            player.sendMessage("§3No target selected!");
            return;
        }

        LivingEntity target = targetSelector.getTarget(player);
        Location eyeLocation = player.getEyeLocation();
        Location spawnLoc = eyeLocation.add(eyeLocation.getDirection().multiply(0.9));
        World world = player.getWorld();
        Fireball fireball = (Fireball) world.spawnEntity(spawnLoc, EntityType.FIREBALL);
        fireball.setVelocity(eyeLocation.getDirection().multiply(INITIAL_VELOCITY));
        fireball.setYield(WEAK_EXPLOSION_POWER);
        fireball.setShooter(player);

        player.sendMessage(String.format("§7Debug: target = %s", target));

        targetSelector.clearLock(player);
        new BukkitRunnable() {
            public void run() {
                if (fireball.isDead()) {
                    cancel();
                    return;
                }
                Location fireballLoc = fireball.getLocation();
                fireball.getWorld().spawnParticle(Particle.FLAME, fireballLoc, 20, 0.0, 0.0, 0.0, 0.001);
                // proximity fuse
                if (fireballLoc.distanceSquared(target.getLocation()) < PROXIMITY_DISTANCE_SQUARED) {
                    fireball.remove();
                    fireball.getWorld().createExplosion(fireballLoc, EXPLOSION_POWER, true, true, player);
                    cancel();
                    return;
                }
                // update fireball's velocity
                Vector desiredDirection = target.getLocation().subtract(fireballLoc).toVector().normalize();
                Vector newVelocity = fireball.getVelocity().multiply(AIR_RESISTANCE).add(desiredDirection.multiply(ACCELERATION));
                fireball.setVelocity(newVelocity);
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

    public void regGuidedMissileRunnable() {
        targetSelector.runTaskTimer(plugin, 1L, 1L);
    }

}