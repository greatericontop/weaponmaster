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
import org.bukkit.GameMode;
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
    private final float BLOCK_EXPLOSION_POWER = 6.0F;
    private final float ENTITY_EXPLOSION_POWER = 19.0F;
    private final float WEAK_EXPLOSION_POWER = 5.0F;
    public static double PROXIMITY_DISTANCE_SQUARED = 6.5 * 6.5;
    // higher acceleration = faster missile
    public static double ACCELERATION = 0.63;
    // higher air resistance (lower number) = slower missile, but more maneuverable
    public static double AIR_RESISTANCE = 0.73;
    // when air resistance is applied before acceleration, terminal velocity is given by: a / (1-d)

    // There are cases where missiles can orbit the target
    // (if the missile gets the right amount of centripetal acceleration)
    // with a radius larger than the proximity distance.
    // This unfortunately isn't really avoidable, but it doesn't happen very often at the current settings.
    // A lower acceleration would decrease the turn radius making it more likely to go inside proximity distance and
    // explode (since r = v^2/a = a / (1-d)^2), and the same is true for more air resistance (lower d).
    // (That formula isn't perfect since some acceleration is wasted on centripetal force instead of all being used
    //  for keeping the missile at top speed, but it's close. Also, it can also do that in 3D, such as when
    //  a player is moving and the missile is moves in a corkscrew shape. Here the cross-sectional acceleration
    //  would be even lower.)
    // We can prevent this, in theory, by having r be less than the proximity distance, but that would be extremely
    // excessive.

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
        fireball.setVelocity(player.getVelocity());
        fireball.setYield(WEAK_EXPLOSION_POWER);
        fireball.setShooter(player);

        targetSelector.clearLock(player);
        new BukkitRunnable() {
            int tickNumber = 0;
            public void run() {
                tickNumber++;
                if (fireball.isDead()) {
                    cancel();
                    return;
                }
                Location fireballLoc = fireball.getLocation();
                // smoke trail: we can't do this perfectly but let's try our best
                final int NUMBER_PARTICLES = 10;
                Vector displacement = fireball.getVelocity(); // the velocity was the displacement this tick
                for (int i = 0; i < NUMBER_PARTICLES; i++) {
                    // TODO: is the BukkitRunnable called BEFORE or AFTER the fireball is moved?
                    Location loc = fireballLoc.subtract(displacement.clone().multiply(i / (double) NUMBER_PARTICLES));
                    fireball.getWorld().spawnParticle(Particle.FLAME, loc, 5, 0.0, 0.0, 0.0, 0.05);
                    fireball.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 2, 0.0, 0.0, 0.0, 0.025);
                }
                // proximity fuse
                if (fireballLoc.distanceSquared(target.getLocation()) < PROXIMITY_DISTANCE_SQUARED) {
                    fireball.getWorld().createExplosion(fireballLoc, BLOCK_EXPLOSION_POWER, true, true, fireball);
                    fireball.getWorld().createExplosion(fireballLoc, ENTITY_EXPLOSION_POWER, false, false, fireball);
                    target.damage(1_000_000.0, fireball);
                    fireball.remove();
                    cancel();
                    return;
                }
                // if target too far away, quit
                if (fireballLoc.distanceSquared(target.getLocation()) > 300.0 * 300.0) {
                    fireball.remove();
                    cancel();
                    return;
                }
                // update fireball's velocity
                Vector desiredDirection = target.getLocation().subtract(fireballLoc).toVector().normalize();
                double realAcceleration = tickNumber >= 20 ? ACCELERATION : 0.05*tickNumber*ACCELERATION; // slower at the beginning
                Vector newVelocity = fireball.getVelocity().multiply(AIR_RESISTANCE).add(desiredDirection.multiply(realAcceleration));
                fireball.setVelocity(newVelocity);
            }
        }.runTaskTimer(plugin, 1L, 1L);

        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
        }
    }

    public void regGuidedMissileRunnable() {
        targetSelector.runTaskTimer(plugin, 1L, 1L);
    }

}