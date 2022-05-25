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
import io.github.greatericontop.weaponmaster.utils.TrueDamageHelper;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class MidFightTasks {
    private final double SEARCH_DIST = 160.0;
    private final double ANGER_DIST = 100.0;
    private final double GUARD_MAX_HP = 120.0; // 3x their default of 40

    private final Random rnd = new Random();
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
        return Math.random() >= 0.05 / averageSeconds;
    }

    /*
     * Helper function to get a random nearby player within SEARCH_DIST of the dragon.
     */
    @Nullable
    public Player getRandomNearbyPlayer() {
        int i = 1;
        Player target = null;
        for (Entity entity : currentlyActiveDragon.getNearbyEntities(SEARCH_DIST, SEARCH_DIST, SEARCH_DIST)) {
            if (!(entity instanceof Player)) { continue; }
            if (Math.random() < 1.0/(i++)) { // knuth algorithm: #i has a 1/i chance to overwrite the current selection
                target = (Player) entity;
            }
        }
        return target;
    }

    public void startFightTasks() {
        new BukkitRunnable() {
            public void run() {
                if ((!currentlyActiveDragon.getUniqueId().equals(cachedDragonId)) || currentlyActiveDragon.isDead()) {
                    cancel();
                    return;
                }
                doHiveAnger();
                spawnEndGuard();
                doLightningAttack();
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

    public void doHiveAnger() {
        if (rejectWithChance(35.0)) { return; }
        Player target = getRandomNearbyPlayer();
        if (target == null) { return; }
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

    public void spawnEndGuard() {
        if (rejectWithChance(80.0)) { return; }
        Player target = getRandomNearbyPlayer();
        if (target == null) { return; }
        Enderman endGuard = (Enderman) currentlyActiveDragon.getWorld().spawnEntity(target.getLocation(), EntityType.ENDERMAN);
        endGuard.setTarget(target);
        endGuard.setCustomName("§dEnd Guard");
        endGuard.setCustomNameVisible(true);
        endGuard.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(GUARD_MAX_HP);
        endGuard.setHealth(GUARD_MAX_HP);
        new BukkitRunnable() {
            int amplifier = 0;
            public void run() {
                amplifier++;
                endGuard.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1073741823, amplifier, true));
                endGuard.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1073741823, Math.min(amplifier/2, 2), true));
                if (amplifier >= 7) {
                    cancel();
                    return;
                }
            }
        }.runTaskTimer(plugin, 100L, 200L);
        target.sendMessage("§5Ender Dragon §cused §3Call Help §con you. Kill the guards before they get too powerful!");
    }

    public void doLightningAttack() {
        // TODO: add tick counter in main runner and limit to at most once every 10 seconds
        if (rejectWithChance(30.0)) { return; }
        for (Entity entity : currentlyActiveDragon.getNearbyEntities(SEARCH_DIST, SEARCH_DIST, SEARCH_DIST)) {
            if (!(entity instanceof Player)) { continue; }
            Player target = (Player) entity;
            double damage = 9.0 + rnd.nextInt(8); // 9 ~ 16 in true damage
            TrueDamageHelper.dealTrueDamage(target, damage, target);
            target.getWorld().strikeLightningEffect(target.getLocation());
            target.sendMessage(String.format("§5Ender Dragon §cused §3Lightning §con you for §4%.1f §cdamage.", damage));
        }
    }

}
