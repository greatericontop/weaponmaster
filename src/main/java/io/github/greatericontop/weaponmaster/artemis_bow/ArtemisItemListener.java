package io.github.greatericontop.weaponmaster.artemis_bow;

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
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum ArtemisMode {
    DISABLED, WEAK, NORMAL, STRONGEST
}

public class ArtemisItemListener implements Listener {

    private final double SEEKING_DISTANCE = 3.8;

    private Map<String, ArtemisMode> artemisModes = new HashMap<String, ArtemisMode>();

    private final WeaponMasterMain plugin;
    private final Util util;
    public ArtemisItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    private double doCompensation(int runNumber, ArtemisMode mode) {
        switch (mode) {
            case WEAK:
                if (runNumber <= 9)  return 0.01;
                if (runNumber <= 19)  return 0.25 * SEEKING_DISTANCE;
                if (runNumber <= 29)  return 0.5 * SEEKING_DISTANCE;
                if (runNumber <= 39)  return 0.75 * SEEKING_DISTANCE;
                return SEEKING_DISTANCE;
            case STRONGEST:
                return SEEKING_DISTANCE;
            default: // NORMAL
                if (runNumber <= 2)  return 0.01;
                if (runNumber <= 7)  return 0.5 * SEEKING_DISTANCE;
                return SEEKING_DISTANCE;
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBowShoot(EntityShootBowEvent event) {
        Entity arrow = event.getProjectile();
        if (!(event.getEntity() instanceof Player)) { return; }
        Player player = (Player) event.getEntity();
        if (!util.checkForArtemisBow(event.getBow())) { return; }
        if (!player.hasPermission("weaponmaster.artemisbow.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.artemisbow.use§3.");
            return;
        }
        if (event.getForce() < 0.99) { // only allow fully charged shots
            player.sendMessage("§7Heat seeking only functions for fully charged shots!");
            return;
        }
        if (artemisModes.get(player.getUniqueId().toString()) == ArtemisMode.DISABLED) {
            player.sendMessage("§7Heat seeking was disabled, LEFT CLICK with Artemis to toggle.");
            return;
        }
        // player.sendMessage("§7velo: "+arrow.getVelocity().length());
        new BukkitRunnable() {
            ArtemisMode mode = artemisModes.getOrDefault(player.getUniqueId().toString(), ArtemisMode.NORMAL);
            int runs = 0;
            int maxCurves = 9;
            int curveCoolDown = 0;
            public void run() {
                runs++;
                if (arrow.isOnGround() || arrow.isDead()) {
                    cancel();
                } else if (maxCurves <= 0) {
                    cancel();
                } else if (curveCoolDown != 0) {
                    curveCoolDown--;
                } else {
                    Location arrowLoc = arrow.getLocation();
                    arrow.getWorld().spawnParticle(Particle.FLAME, arrowLoc, 2);
                    double seekingDistance = doCompensation(runs, mode);
                    List<Entity> nearEntities = arrow.getNearbyEntities(seekingDistance, seekingDistance, seekingDistance);
                    nearEntities.sort(
                            (Entity a, Entity b) -> (int) (1000.0 * (a.getLocation().distanceSquared(arrowLoc)-b.getLocation().distanceSquared(arrowLoc)))
                    );
                    for (Entity target : nearEntities) {

                        // Ignore entites that are immune to arrows
                        if (target instanceof Enderman) { continue; }
                        if (target instanceof Player) {
                            GameMode gm = ((Player) target).getGameMode();
                            if (gm == GameMode.CREATIVE || gm == GameMode.SPECTATOR) { continue; }
                        }


                        // Curve code
                        if (player.hasLineOfSight(target) && target instanceof LivingEntity && (!target.isDead()) && target.getEntityId() != player.getEntityId()) {
                            Vector velo = arrow.getVelocity();
                            double speed = Math.sqrt(velo.getX()*velo.getX() + velo.getY()*velo.getY() + velo.getZ()*velo.getZ());
                            arrow.setVelocity(target.getLocation().toVector().subtract(arrow.getLocation().toVector())
                                    .normalize().multiply(speed));
                            arrow.getWorld().spawnParticle(Particle.FLAME, arrowLoc, 150);
                            maxCurves--;
                            curveCoolDown = 4;
                            break;
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLeftClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) { return; }
        Player player = event.getPlayer();
        if (!util.checkForArtemisBow(player.getInventory().getItemInMainHand())) { return; }
        ArtemisMode currentMode = artemisModes.getOrDefault(player.getUniqueId().toString(), ArtemisMode.NORMAL);
        if (currentMode == ArtemisMode.DISABLED) {
            artemisModes.put(player.getUniqueId().toString(), ArtemisMode.WEAK);
            player.sendMessage("§3Artemis Bow set to §4weak§3.");
        } else if (currentMode == ArtemisMode.WEAK) {
            artemisModes.put(player.getUniqueId().toString(), ArtemisMode.NORMAL);
            player.sendMessage("§3Artemis Bow set to §4normal§3.");
        } else if (currentMode == ArtemisMode.NORMAL) {
            artemisModes.put(player.getUniqueId().toString(), ArtemisMode.STRONGEST);
            player.sendMessage("§3Artemis Bow set to §4strongest§3.");
        } else if (currentMode == ArtemisMode.STRONGEST) {
            artemisModes.put(player.getUniqueId().toString(), ArtemisMode.DISABLED);
            player.sendMessage("§3Artemis Bow set to §4disabled§3.");
        }
    }

}
