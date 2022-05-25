package io.github.greatericontop.weaponmaster.dragon_manager;

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
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class FightManager implements Listener {

    public EnderDragon currentlyActiveDragon = null;
    private final WeaponMasterMain plugin;
    public FightManager(WeaponMasterMain plugin) {
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.NORMAL)
    public void onDragonSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof EnderDragon)) { return; }
        // TODO: only trigger sometimes, maybe 20%
        this.currentlyActiveDragon = (EnderDragon) entity;
        startFightTasks();
    }

    private final double DISTANCE = 150.0;
    /*
     * This gets executed every tick while the fight lasts.
     */
    public void midFightTasks() {
        if (Math.random() < 0.005) { // average 10s
            // Pick a random player nearby
            int i = 1;
            Player target = null;
            for (Entity entity : currentlyActiveDragon.getNearbyEntities(DISTANCE, DISTANCE, DISTANCE)) {
                if (!(entity instanceof Player)) { continue; }
                if (Math.random() < 1.0/(i++)) {
                    target = (Player) entity;
                }
            }
            if (target == null) { return; }
            // Make endermen angry at them
            for (Entity entity : currentlyActiveDragon.getNearbyEntities(DISTANCE, DISTANCE, DISTANCE)) {
                if (!(entity instanceof Enderman)) { continue; }
                Enderman enderman = (Enderman) entity;
                if (enderman.getTarget() == null) { return; } // we don't want to reassign their anger
                if (Math.random() < 0.2) {
                    enderman.setTarget(target);
                }
            }

        }
    }

    public void startFightTasks() {
        UUID cachedDragon = currentlyActiveDragon.getUniqueId();
        new BukkitRunnable() {
            public void run() {
                if (currentlyActiveDragon == null || !currentlyActiveDragon.getUniqueId().equals(cachedDragon)) {
                    cancel();
                    return;
                }
                midFightTasks();
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

}
