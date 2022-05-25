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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class MidFightTasks {
    private final double SEARCH_DIST = 160.0;
    private final double ANGER_DIST = 100.0;

    private final WeaponMasterMain plugin;
    private final EnderDragon currentlyActiveDragon;
    private final UUID cachedDragonId;
    public MidFightTasks(WeaponMasterMain plugin, EnderDragon currentlyActiveDragon) {
        this.plugin = plugin;
        this.currentlyActiveDragon = currentlyActiveDragon;
        this.cachedDragonId = currentlyActiveDragon.getUniqueId();
    }

    /*
     * Helper function to randomly execute mid-fight tasks.
     * Use it like this: if (rejectWithChance(30.0)) { return; }
     */
    public static boolean rejectWithChance(double averageSeconds) {
        averageSeconds = averageSeconds / 5; // testing
        return Math.random() >= 0.05 / averageSeconds;
    }

    public void startFightTasks() {
        new BukkitRunnable() {
            public void run() {
                if ((!currentlyActiveDragon.getUniqueId().equals(cachedDragonId)) || currentlyActiveDragon.isDead()) {
                    cancel();
                    return;
                }
                hiveAnger();
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

    public void hiveAnger() {
        if (rejectWithChance(30.0)) { return; }
        // Pick a random player nearby
        int i = 1;
        Player target = null;
        for (Entity entity : currentlyActiveDragon.getNearbyEntities(SEARCH_DIST, SEARCH_DIST, SEARCH_DIST)) {
            if (!(entity instanceof Player)) { continue; }
            if (Math.random() < 1.0/(i++)) {
                target = (Player) entity;
            }
        }
        if (target == null) { return; }
        // Make endermen angry at them
        int angeredCount = 0;
        for (Entity entity : target.getNearbyEntities(ANGER_DIST, ANGER_DIST, ANGER_DIST)) {
            if (!(entity instanceof Enderman)) { continue; }
            Enderman enderman = (Enderman) entity;
            if (enderman.getTarget() != null) { continue; } // we don't want to reassign their anger
            if (Math.random() < 0.1) {
                // TODO: maybe increase the chance when less endermen are angered, because more need to be angered instead of 2-7
                //       and its also very variant; maybe make this fire less but in exchange anger more endermen (8-10 i guess)
                enderman.setTarget(target);
                enderman.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, true));
                angeredCount++;
            }
        }
        target.sendMessage(String.format("§5Ender Dragon §cused §3Hive Anger §con you and angered §b%d §cendermen.", angeredCount));
    }


}
