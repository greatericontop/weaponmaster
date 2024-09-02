package io.github.greatericontop.weaponmaster.dragonmanager;

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
import io.github.greatericontop.weaponmaster.utils.TrueDamageHelper;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Illager;
import org.bukkit.entity.IronGolem;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.WitherSkeleton;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import javax.annotation.Nullable;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class MidFightTasks {
    private final double SEARCH_DIST = 160.0;
    private final double ANGER_DIST = 100.0;
    private final double GUARD_MAX_HP = 150.0;
    private final int STORM_SIZE = 4;
    private final double DEFENDER_MAX_HEALTH = 65.0;
    private final double AGENT_HEALTH = 90.0;

    private int hiveAnger_lastTickRan = -1000;
    private int endGuard_lastTickRan = -1000;
    private int lightningAttack_lastTickRan = -1000;
    private int fireballStorm_lastTickRan = -1000;
    private int toxicStorm_lastTickRan = -1000;
    private int endDweller_lastTickRan = -1000;
    private int endstoneDefender_lastTickRan = -1000;
    private int sniper_lastTickRan = -1000;
    private int ghost_lastTickRan = -1000;
    private int agents_lastTickRan = -1000;

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
                Player player = (Player) entity;
                if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) { continue; }
                target = player;
            }
        }
        return target;
    }

    /*
     * Helper function to lock the target of a mob to another.
     */
    public void lockTarget(Mob source, LivingEntity target) {
        new BukkitRunnable() {
            public void run() {
                if (source.isDead()) {
                    cancel();
                    return;
                }
                if (source.getTarget() == null || !source.getTarget().equals(target)) {
                    source.setTarget(target);
                }
            }
        }.runTaskTimer(plugin, 1L, 1L);
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
                spawnEndDweller(tickNumber);
                spawnEndstoneDefender(tickNumber);
                summonSniper(tickNumber);
                summonGhosts(tickNumber);
                callAgents(tickNumber);
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

    public void doHiveAnger(int tickNumber) {
        if (rejectWithChance(110.0)) { return; }
        if (tickNumber < hiveAnger_lastTickRan + 700) { return; }
        hiveAnger_lastTickRan = tickNumber;
        Player target = getRandomNearbyPlayer();
        if (target == null) { return; }
        int angeredCount = 0;
        for (Entity entity : target.getNearbyEntities(ANGER_DIST, ANGER_DIST, ANGER_DIST)) {
            if (!(entity instanceof Enderman)) { continue; }
            Enderman enderman = (Enderman) entity;
            if (enderman.getTarget() != null) { continue; } // we don't want to reassign their anger
            if (Math.random() < 0.1 + (angeredCount < 10 ? 0.05 : 0)) {
                enderman.setTarget(target);
                enderman.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 0, true));
                angeredCount++;
            }
        }
        target.sendMessage(String.format("§5WeaponMaster Dragon §7used §3Hive Anger §7on you and angered §b%d §7endermen.", angeredCount));
    }

    public void spawnEndGuard(int tickNumber) {
        if (rejectWithChance(85.0)) { return; }
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
                endGuard.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 1073741823, amplifier, true));
                if (amplifier == 2) { // add resistance 1 at the same time we add strength 3
                    endGuard.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 1073741823, 0, true));
                }
                if (amplifier >= 6) { // maximum strength 7
                    cancel();
                    return;
                }
            }
        }.runTaskTimer(plugin, 200L, 200L);
        lockTarget(endGuard, target);
        target.sendMessage("§5WeaponMaster Dragon §7used §3Call Help §7on you. Kill the guards before they get too powerful!");
    }

    public void doLightningAttack(int tickNumber) {
        if (rejectWithChance(45.0)) { return; }
        if (tickNumber < lightningAttack_lastTickRan + 300) { return; }
        lightningAttack_lastTickRan = tickNumber;
        for (Entity entity : currentlyActiveDragon.getNearbyEntities(SEARCH_DIST, SEARCH_DIST, SEARCH_DIST)) {
            if (!(entity instanceof Player)) { continue; }
            Player target = (Player) entity;
            double damage = 7.0 + rnd.nextInt(12); // 7 ~ 18 in true damage
            if (rnd.nextFloat() < 0.5F) { damage += 0.5; } // 7.5 ~ 18.5 uniform
            TrueDamageHelper.dealTrueDamage(target, damage);
            target.getWorld().strikeLightningEffect(target.getLocation());
            target.sendMessage(String.format("§5WeaponMaster Dragon §7used §3Lightning §7on you for §4%.1f §7damage.", damage));
        }
    }

    public void doFireballStorm(int tickNumber) {
        if (rejectWithChance(60.0)) { return; }
        if (tickNumber < fireballStorm_lastTickRan + 400) { return; }
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
            int attacksLeft = 15;
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
                    Location spawnLoc = currentlyActiveDragon.getLocation().add(velocity.multiply(2.5));
                    DragonFireball fireball = (DragonFireball) loc.getWorld().spawnEntity(spawnLoc, EntityType.DRAGON_FIREBALL);
                    fireball.setVelocity(velocity);
                }
                attacksLeft--;
            }
        }.runTaskTimer(plugin, 1L, 8L);
        // Message everyone in the end
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getWorld().equals(loc.getWorld())) {
                player.sendMessage("§5WeaponMaster Dragon §7used §3Fireball Storm§7.");
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
            target.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 200, 0, true));
            target.playSound(target.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1.0F, 1.0F);
            target.sendMessage("§5WeaponMaster Dragon §7used §3Toxic Storm §7and gave you §cWeakness§7, §cPoison§7, §cHunger§7, and §cMining Fatigue §7for §c10 §7seconds.");
        }
    }

    public void regenerateOnLowHealth(int tickNumber) {
        if (currentlyActiveDragon.getHealth() <= 150.0 && tickNumber % 60 == 0) {
            currentlyActiveDragon.setHealth(currentlyActiveDragon.getHealth() + 1.0);
        } else if (currentlyActiveDragon.getHealth() <= 250.0 && tickNumber % 100 == 0) {
            currentlyActiveDragon.setHealth(currentlyActiveDragon.getHealth() + 1.0);
        } else if (tickNumber % 180 == 0) {
            currentlyActiveDragon.setHealth(Math.min(currentlyActiveDragon.getHealth() + 1.0, currentlyActiveDragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()));
        }
    }

    public void spawnEndDweller(int tickNumber) {
        if (rejectWithChance(70.0)) { return; }
        if (tickNumber < endDweller_lastTickRan + 200) { return; }
        endDweller_lastTickRan = tickNumber;
        Player target = getRandomNearbyPlayer();
        if (target == null) { return; }
        IronGolem endDweller = (IronGolem) currentlyActiveDragon.getWorld().spawnEntity(target.getLocation(), EntityType.IRON_GOLEM);
        endDweller.setTarget(target);
        endDweller.setCustomName("§7End Dweller");
        endDweller.setCustomNameVisible(true);
        endDweller.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.35); // up from 0.25
        PersistentDataContainer pdc = endDweller.getPersistentDataContainer();
        pdc.set(new NamespacedKey(plugin, "WM_DRAGON_NODROPS"), PersistentDataType.INTEGER, 1);
        lockTarget(endDweller, target);
        target.sendMessage("§5WeaponMaster Dragon §7used §3Summon End Dweller §7on you.");
    }

    public void spawnEndstoneDefender(int tickNumber) {
        if (rejectWithChance(100.0)) { return; }
        if (tickNumber < endstoneDefender_lastTickRan + 300) { return; }
        endstoneDefender_lastTickRan = tickNumber;
        Player target = getRandomNearbyPlayer();
        if (target == null) { return; }
        WitherSkeleton defender = (WitherSkeleton) currentlyActiveDragon.getWorld().spawnEntity(target.getLocation(), EntityType.WITHER_SKELETON);
        defender.setTarget(target);
        defender.setCustomName("§6§lEndstone Defender");
        defender.setCustomNameVisible(true);
        defender.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(DEFENDER_MAX_HEALTH); // up from 20
        defender.setHealth(DEFENDER_MAX_HEALTH);
        ItemStack endStone = new ItemStack(Material.END_STONE, 1);
        endStone.addUnsafeEnchantment(Enchantment.LUCK_OF_THE_SEA, 1);
        defender.getEquipment().setHelmet(endStone);
        defender.getEquipment().setChestplate(new ItemStack(Material.NETHERITE_CHESTPLATE, 1));
        defender.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS, 1));
        defender.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS, 1));
        PersistentDataContainer pdc = defender.getPersistentDataContainer();
        pdc.set(new NamespacedKey(plugin, "WM_DRAGON_NODROPS"), PersistentDataType.INTEGER, 1);
        target.sendMessage("§5WeaponMaster Dragon §7used §3Endstone Defense §7on you.");
            new BukkitRunnable() {
            public void run() {
                defender.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(100.0);
            }
        }.runTaskLater(plugin, 80L); // spawned on top of the player, so don't immediately kill them
    }

    public void summonSniper(int tickNumber) {
        if (rejectWithChance(85.0)) { return; }
        if (tickNumber < sniper_lastTickRan + 300) { return; }
        sniper_lastTickRan = tickNumber;
        Player target = getRandomNearbyPlayer();
        if (target == null) { return; }
        // TODO: don't spawn it on top of the player, spawn it at a random end stone block somewhere on the island
        Skeleton sniper = (Skeleton) currentlyActiveDragon.getWorld().spawnEntity(target.getLocation(), EntityType.SKELETON);
        sniper.setTarget(target);
        sniper.setCustomName("§bEnder Sniper");
        ItemStack sniperItem = sniper.getEquipment().getItemInMainHand();
        sniperItem.addUnsafeEnchantment(Enchantment.POWER, 10);
        sniperItem.addUnsafeEnchantment(Enchantment.PUNCH, 3);
        sniperItem.addEnchantment(Enchantment.FLAME, 1);
        sniper.getEquipment().setItemInMainHand(sniperItem);
        sniper.getAttribute(Attribute.GENERIC_FOLLOW_RANGE).setBaseValue(40.0);
        PersistentDataContainer pdc = sniper.getPersistentDataContainer(); // no overpowered bows!
        pdc.set(new NamespacedKey(plugin, "WM_DRAGON_NODROPS"), PersistentDataType.INTEGER, 1);
        target.sendMessage("§5WeaponMaster Dragon §7used §3Summon Sniper §7on you.");
        lockTarget(sniper, target);
    }

    public void summonGhosts(int tickNumber) {
        if (rejectWithChance(165.0)) { return; }
        if (tickNumber < ghost_lastTickRan + 600) { return; }
        ghost_lastTickRan = tickNumber;
        Player target = getRandomNearbyPlayer();
        if (target == null) { return; }
        int number = 1 + rnd.nextInt(3); // 1~3
        for (int i = 0; i < number; i++) {
            double x = target.getLocation().getX() + ThreadLocalRandom.current().nextDouble(-5.0, 5.0);
            double y = target.getLocation().getY() + rnd.nextInt(64);
            double z = target.getLocation().getZ() + ThreadLocalRandom.current().nextDouble(-5.0, 5.0);
            Location spawnLoc = new Location(currentlyActiveDragon.getWorld(), x, y, z);
            Phantom ghost = (Phantom) currentlyActiveDragon.getWorld().spawnEntity(spawnLoc, EntityType.PHANTOM);
            ghost.setTarget(target);
            ghost.setCustomName("§4Ghost");
            ghost.setCustomNameVisible(true);
            double health = currentlyActiveDragon.getHealth() / currentlyActiveDragon.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            double multi = (1 - health) * 8; // up to 9x damage if dragon is low, or 27 damage on hard mode
            ghost.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).addModifier(new AttributeModifier(UUID.randomUUID(), "weaponmaster", multi, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
            PersistentDataContainer pdc = ghost.getPersistentDataContainer();
            pdc.set(new NamespacedKey(plugin, "WM_DRAGON_NODROPS"), PersistentDataType.INTEGER, 1);
            lockTarget(ghost, target);
        }
        target.sendMessage("§5WeaponMaster Dragon §7used §3Summon Ghosts §7on you.");
    }

    public void callAgents(int tickNumber) {
        if (rejectWithChance(125.0)) { return; }
        if (tickNumber < agents_lastTickRan + 500) { return; }
        agents_lastTickRan = tickNumber;
        Player target = getRandomNearbyPlayer();
        if (target == null) { return; }
        final String[] names = {"§2Brown", "§2Smith", "§2Jones"};
        final EntityType[] types = {EntityType.VINDICATOR, EntityType.EVOKER, EntityType.PILLAGER};
        for (int i = 0; i < 3; i++) {
            Illager agent = (Illager) currentlyActiveDragon.getWorld().spawnEntity(target.getLocation(), types[i]);
            agent.setTarget(target);
            agent.setCustomName(names[i]);
            agent.setCustomNameVisible(true);
            agent.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(AGENT_HEALTH);
            agent.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 1073741823, 2, true));
            agent.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1073741823, 0, true));
            PersistentDataContainer pdc = agent.getPersistentDataContainer();
            pdc.set(new NamespacedKey(plugin, "WM_DRAGON_NODROPS"), PersistentDataType.INTEGER, 1);
            lockTarget(agent, target);
        }
        target.sendMessage("§5WeaponMaster Dragon §7used §3Call Agents §7on you.");
    }

}
