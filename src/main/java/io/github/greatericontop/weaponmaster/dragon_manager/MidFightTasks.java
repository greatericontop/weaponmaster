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
import org.bukkit.Bukkit;
import org.bukkit.EntityEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.*;

public class MidFightTasks {
    private final double SEARCH_DIST = 160.0;
    private final double ANGER_DIST = 100.0;
    private final double GUARD_MAX_HP = 120.0; // 3x their default of 40
    private final int STORM_SIZE = 4;
    private final double FIREBALL_SEEK = 11.0;

    private int hiveAnger_lastTickRan = -1000;
    private int endGuard_lastTickRan = -1000;
    private int lightningAttack_lastTickRan = -1000;
    private int fireballStorm_lastTickRan = -1000;
    private int toxicStorm_lastTickRan = -1000;

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
            int tickNumber = 0;
            public void run() {
                tickNumber++;
                if ((!currentlyActiveDragon.getUniqueId().equals(cachedDragonId)) || currentlyActiveDragon.isDead()) {
                    cancel();
                    return;
                }
                doHiveAnger(tickNumber);
                spawnEndGuard(tickNumber);
                doLightningAttack(tickNumber);
                doFireballStorm(tickNumber);
                doToxicStorm(tickNumber);
                regenerateOnLowHealth(tickNumber);
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

    public void doHiveAnger(int tickNumber) {
        if (rejectWithChance(60.0)) { return; }
        if (tickNumber < hiveAnger_lastTickRan + 700) { return; }
        hiveAnger_lastTickRan = tickNumber;
        Player target = getRandomNearbyPlayer();
        if (target == null) { return; }
        int angeredCount = 0;
        for (Entity entity : target.getNearbyEntities(ANGER_DIST, ANGER_DIST, ANGER_DIST)) {
            if (!(entity instanceof Enderman)) { continue; }
            Enderman enderman = (Enderman) entity;
            if (enderman.getTarget() != null) { continue; } // we don't want to reassign their anger
            if (Math.random() < 0.13) {
                // TODO: maybe increase the chance when less endermen are angered, because more need to be angered instead of 2-7
                //       and its also very variant; maybe make this fire less but in exchange anger more endermen (8-10 i guess)
                enderman.setTarget(target);
                enderman.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, true));
                angeredCount++;
            }
        }
        target.sendMessage(String.format("§5Ender Dragon §7used §3Hive Anger §7on you and angered §b%d §7endermen.", angeredCount));
    }

    public void spawnEndGuard(int tickNumber) {
        if (rejectWithChance(75.0)) { return; }
        if (tickNumber < endGuard_lastTickRan + 500) { return; }
        endGuard_lastTickRan = tickNumber;
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
        new BukkitRunnable() {
            public void run() {
                if (endGuard.isDead()) {
                    cancel();
                    return;
                }
                if (endGuard.getTarget() == null || !endGuard.getTarget().equals(target)) {
                    endGuard.setTarget(target);
                }
            }
        }.runTaskTimer(plugin, 1L, 1L);
        target.sendMessage("§5Ender Dragon §7used §3Call Help §7on you. Kill the guards before they get too powerful!");
    }

    public void doLightningAttack(int tickNumber) {
        if (rejectWithChance(45.0)) { return; }
        if (tickNumber < lightningAttack_lastTickRan + 160) { return; }
        lightningAttack_lastTickRan = tickNumber;
        for (Entity entity : currentlyActiveDragon.getNearbyEntities(SEARCH_DIST, SEARCH_DIST, SEARCH_DIST)) {
            if (!(entity instanceof Player)) { continue; }
            Player target = (Player) entity;
            double damage = 7.0 + rnd.nextInt(12); // 7 ~ 18 in true damage
            if (rnd.nextFloat() < 0.5F) { damage += 0.5; } // 7.5 ~ 18.5 uniform
            TrueDamageHelper.dealTrueDamage(target, damage);
            target.getWorld().strikeLightningEffect(target.getLocation());
            target.sendMessage(String.format("§5Ender Dragon §7used §3Lightning §7on you for §4%.1f §7damage.", damage));
        }
    }

    public void doFireballStorm(int tickNumber) {
        if (rejectWithChance(40.0)) { return; }
        if (tickNumber < fireballStorm_lastTickRan + 200) { return; }
        fireballStorm_lastTickRan = tickNumber;
        Location loc = currentlyActiveDragon.getLocation();
        // Spawn fireballs below the dragon as some kind of protection
        for (int x = -STORM_SIZE; x <= STORM_SIZE; x++) {
            for (int z = -STORM_SIZE; z <= STORM_SIZE; z++) {
                Vector ray = new Vector(x, -STORM_SIZE*0.35, z).normalize().multiply(1.9);
                Location spawnLoc = loc.clone().add(ray.multiply(4.0));
                DragonFireball fireball = (DragonFireball) loc.getWorld().spawnEntity(spawnLoc, EntityType.DRAGON_FIREBALL);
                fireball.setVelocity(ray);
            }
        }
        // Spew out many fireballs in the direction of players
        new BukkitRunnable() {
            int attacksLeft = 16;
            public void run() {
                if (attacksLeft <= 0) {
                    cancel();
                    return;
                }
                for (Entity entity : currentlyActiveDragon.getNearbyEntities(SEARCH_DIST, SEARCH_DIST, SEARCH_DIST)) {
                    if (!(entity instanceof Player)) { continue; }
                    Player target = (Player) entity;
                    Vector direction = target.getLocation().subtract(currentlyActiveDragon.getLocation()).toVector();
                    Vector velocity = direction.normalize().multiply(3.8);
                    Location spawnLoc = currentlyActiveDragon.getLocation().add(velocity);
                    DragonFireball fireball = (DragonFireball) loc.getWorld().spawnEntity(spawnLoc, EntityType.DRAGON_FIREBALL);
                    fireball.setVelocity(velocity);
                }
                attacksLeft--;
            }
        }.runTaskTimer(plugin, 1L, 4L);
        // Message everyone in the end
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld().equals(loc.getWorld())) {
                player.sendMessage("§5Ender Dragon §7used §3Fireball Storm§7.");
            }
        }
    }

    public void doToxicStorm(int tickNumber) {
        if (rejectWithChance(120.0)) { return; }
        if (tickNumber < toxicStorm_lastTickRan + 300) { return; }
        toxicStorm_lastTickRan = tickNumber;
        for (Entity entity : currentlyActiveDragon.getNearbyEntities(SEARCH_DIST, SEARCH_DIST, SEARCH_DIST)) {
            if (!(entity instanceof Player)) { continue; }
            Player target = (Player) entity;
            target.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0, true));
            target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0, true));
            target.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 200, 0, true));
            target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 200, 0, true));
            target.playSound(target.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0F, 1.0F);
            target.sendMessage("§5Ender Dragon §7used §3Toxic Storm §7and gave you §cWeakness§7, §cPoison§7, §cHunger§7, and §cMining Fatigue §7for §c10 §7seconds.");
        }
    }

    public void regenerateOnLowHealth(int tickNumber) {
        // TODO: add multiple tiers to the regen, when the dragon is lower it regenerates faster
        if (currentlyActiveDragon.getHealth() <= 150.0 && tickNumber % 60 == 0) {
            currentlyActiveDragon.setHealth(currentlyActiveDragon.getHealth() + 1.0);
        }
    }

}
