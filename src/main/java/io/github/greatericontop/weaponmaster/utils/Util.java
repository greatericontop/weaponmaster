package io.github.greatericontop.weaponmaster.utils;

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
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public final String RPG_LAUNCHER_NAME = "§2§lRPG Launcher §6⚝⚝⚝⚝⚝";
    public final String VAMP_AXE_NAME = "§d§lVampire's Axe §6⚝⚝⚝⚝⚝";
    public final String DEATH_SCYTHE_NAME = "§c§lDeath's Scythe §6⚝⚝⚝⚝⚝";
    public final String DRAGON_SWORD_NAME = "§aDragon Sword §6⚝⚝⚝⚝⚝";
    public final String ARTEMIS_BOW_NAME = "§5Artemis Bow §6⚝⚝⚝⚝⚝";
    public final String ANDURIL_NAME = "§f§lAnduril §6⚝⚝⚝⚝⚝";
    public final String EXCALIBUR_NAME = "§e§lExcalibur §6⚝⚝⚝⚝⚝";
    public final String EXODUS_NAME = "§b§lExodus §6⚝⚝⚝⚝⚝";
    public final String ROCKET_STICK_NAME = "§9Rocket Stick §6⚝⚝⚝⚝⚝";
    public final String HELIOS_NAME = "§f§lHelios §6⚝⚝⚝⚝⚝";
    public final String SCYLLA_CHESTPLATE_NAME = "§7Scylla's Chestplate §6⚝⚝⚝⚝⚝";
    public final String HERMES_BOOTS_NAME = "§eHermes' Boots §6⚝⚝⚝⚝⚝";
    public final String LIFE_HELMET_NAME = "§9§lHelmet of Life §6⚝⚝⚝⚝⚝";
    public final String CAVEMAN_SWORD_NAME = "§8§lCaveman Sword §6⚝⚝⚝⚝⚝";
    public final String WARLOCK_PANTS_NAME = "§8Warlock Pants §6⚝⚝⚝⚝⚝";
    public final String FIREBALL_NAME = "§9§lFireball §6⚝⚝⚝⚝⚝";
    public final String ATOM_BOMB_NAME = "§cC§4a§cs§4t§cl§4e §cB§4r§ca§4v§co §6⚝⚝⚝⚝⚝";
    public final String NETHERITE_STAFF_NAME = "§bNetherite Staff §6⚝⚝⚝⚝⚝";
    public final String SNIPER_RIFLE_NAME = "§3Sniper Rifle §6⚝⚝⚝⚝⚝";
    public final String MINERS_BLESSING_NAME = "§a§lMiner's Blessing §6⚝⚝⚝⚝⚝";
    public final String PILOT_SWORD_NAME = "§3Pilot's Sword §6⚝⚝⚝⚝⚝";
    public final String SHREDDED_AXE_NAME = "§2Shredded Axe §6⚝⚝⚝⚝⚝";
    public final String NAPALM_MISSILE_NAME = "§6§l§oNapalm Missile §6⚝⚝⚝⚝⚝";
    public final String NINJA_BOW_NAME = "§7§lNinja Bow §6⚝⚝⚝⚝⚝";
    public final String DRAGON_ARMOR_NAME = "§aDragon Armor §6⚝⚝⚝⚝⚝";
    public final String COPPER_SWORD_NAME = "§9Copper Sword §6⚝⚝⚝⚝⚝";
    public final String POSEIDON_TRIDENT_NAME = "§bPoseidon's Trident §6⚝⚝⚝⚝⚝";
    public final String VALKYRIE_AXE_NAME = "§2Valkyrie Axe §6⚝⚝⚝⚝⚝";
    public final String ASSAULT_RIFLE_NAME = "§cAssault Rifle §6⚝⚝⚝⚝⚝";
    public final String DRAGON_ELYTRA_NAME = "§dDragon Elytra §6⚝⚝⚝⚝⚝";
    public final String GUIDED_MISSILE_NAME = "§cHeat Seeking Guided Missile §6⚝⚝⚝⚝⚝";
    public final String ASSASSINS_BLADE_NAME = "§eAssassin's Blade §6⚝⚝⚝⚝⚝";
    public final String WITHER_STAFF_NAME = "§bWither Staff §6⚝⚝⚝⚝⚝";
    public final String BOMB_CANNON_NAME = "§5Bomb Cannon §6⚝⚝⚝⚝⚝";
    public final String THROWING_KNIFE_NAME = "§cThrowing Knife §6⚝⚝⚝⚝⚝";
    public final String WITCH_SWORD_NAME = "§dWitch Sword §6⚝⚝⚝⚝⚝";
    public final String END_SWORD_NAME = "§5End Sword §6⚝⚝⚝⚝⚝";
    public final String END_ARMOR_NAME = "§5End Armor §6⚝⚝⚝⚝⚝";
    public final String SCORPION_BOW_NAME = "§2Scorpion Bow §6⚝⚝⚝⚝⚝";
    public final String ARES_NAME = "§eAres §6⚝⚝⚝⚝⚝";
    public final String NINJA_MASTERS_BOW_NAME = "§7§lNinja Master's Bow §6⚝⚝⚝⚝⚝";
    public final String DEATH_ROD_NAME = "§cDeath Rod §6⚝⚝⚝⚝⚝";
    public final String PLUTONIUM_BLADE_NAME = "§8§lPlutonium Blade §6⚝⚝⚝⚝⚝";

    public List<String> RPG_LAUNCHER_LORE = new ArrayList<>();
    public List<String> VAMP_AXE_LORE = new ArrayList<>();
    public List<String> DEATH_SCYTHE_LORE = new ArrayList<>();
    public List<String> DRAGON_SWORD_LORE = new ArrayList<>();
    public List<String> ARTEMIS_BOW_LORE = new ArrayList<>();
    public List<String> ANDURIL_LORE = new ArrayList<>();
    public List<String> EXCALIBUR_LORE = new ArrayList<>();
    public List<String> EXODUS_LORE = new ArrayList<>();
    public List<String> ROCKET_STICK_LORE = new ArrayList<>();
    public List<String> HELIOS_LORE = new ArrayList<>();
    public List<String> SCYLLA_CHESTPLATE_LORE = new ArrayList<>();
    public List<String> HERMES_BOOTS_LORE = new ArrayList<>();
    public List<String> LIFE_HELMET_LORE = new ArrayList<>();
    public List<String> CAVEMAN_SWORD_LORE = new ArrayList<>();
    public List<String> WARLOCK_PANTS_LORE = new ArrayList<>();
    public List<String> FIREBALL_LORE = new ArrayList<>();
    public List<String> ATOM_BOMB_LORE = new ArrayList<>();
    public List<String> NETHERITE_STAFF_LORE = new ArrayList<>();
    public List<String> SNIPER_RIFLE_LORE = new ArrayList<>();
    public List<String> MINERS_BLESSING_LORE = new ArrayList<>();
    public List<String> PILOT_SWORD_LORE = new ArrayList<>();
    public List<String> SHREDDED_AXE_LORE = new ArrayList<>();
    public List<String> NAPALM_MISSILE_LORE = new ArrayList<>();
    public List<String> NINJA_BOW_LORE = new ArrayList<>();
    public List<String> DRAGON_ARMOR_LORE = new ArrayList<>();
    public List<String> COPPER_SWORD_LORE = new ArrayList<>();
    public List<String> POSEIDON_TRIDENT_LORE = new ArrayList<>();
    public List<String> VALKYRIE_AXE_LORE = new ArrayList<>();
    public List<String> ASSAULT_RIFLE_LORE = new ArrayList<>();
    public List<String> DRAGON_ELYTRA_LORE = new ArrayList<>();
    public List<String> GUIDED_MISSILE_LORE = new ArrayList<>();
    public List<String> ASSASSINS_BLADE_LORE = new ArrayList<>();
    public List<String> WITHER_STAFF_LORE = new ArrayList<>();
    public List<String> BOMB_CANNON_LORE = new ArrayList<>();
    public List<String> THROWING_KNIFE_LORE = new ArrayList<>();
    public List<String> WITCH_SWORD_LORE = new ArrayList<>();
    public List<String> END_SWORD_LORE = new ArrayList<>();
    public List<String> END_ARMOR_LORE = new ArrayList<>();
    public List<String> SCORPION_BOW_LORE = new ArrayList<>();
    public List<String> ARES_LORE = new ArrayList<>();
    public List<String> NINJA_MASTERS_BOW_LORE = new ArrayList<>();
    public List<String> DEATH_ROD_LORE = new ArrayList<>();
    public List<String> PLUTONIUM_BLADE_LORE = new ArrayList<>();

    public final int CAVEMAN_EXP = 3;
    public final int CAVEMAN_REQ = 4;
    public final int CAVEMAN_LVL = 5;
    public final int MINER_EXP = 3;
    public final int MINER_REQ = 4;
    public final int MINER_LVL = 5;
    public final int MINER_INSERTION = 7;
    public final int DRAGON_UPGRADE = 8;

    public final WeaponMasterMain plugin;
    public Util(WeaponMasterMain plugin) {
        this.plugin = plugin;
        // RPG Launcher
        RPG_LAUNCHER_LORE.add("id: RPG_LAUNCHER");
        RPG_LAUNCHER_LORE.add("§6---------------");
        RPG_LAUNCHER_LORE.add("");
        RPG_LAUNCHER_LORE.add("§eAbility: §4Fire §eLEFT CLICK");
        RPG_LAUNCHER_LORE.add("§3Fire a rocket propelled grenade.");
        RPG_LAUNCHER_LORE.add("");
        RPG_LAUNCHER_LORE.add("§eMuzzle Velocity: §3290 m/s");
        RPG_LAUNCHER_LORE.add("§eMax Spread: §33.1m §eat §3100 §eblocks + drop");
        RPG_LAUNCHER_LORE.add("§eImpact Strength: §3Similar to bed or anchor");
        RPG_LAUNCHER_LORE.add("§7Major recoil");
        RPG_LAUNCHER_LORE.add("");
        RPG_LAUNCHER_LORE.add("§cSUPREME");
        RPG_LAUNCHER_LORE.add("§6---------------");
        // Vampire's Axe
        VAMP_AXE_LORE.add("id: VAMP_AXE");
        VAMP_AXE_LORE.add("§6---------------");
        VAMP_AXE_LORE.add("");
        VAMP_AXE_LORE.add("§eAbility: §4Vampiric");
        VAMP_AXE_LORE.add("§3Life steal §b§l16% §3of dealt damage.");
        VAMP_AXE_LORE.add("");
        VAMP_AXE_LORE.add("§6LEGENDARY");
        VAMP_AXE_LORE.add("§6---------------");
        // Death's Scythe
        DEATH_SCYTHE_LORE.add("id: DEATH_SCYTHE");
        DEATH_SCYTHE_LORE.add("§6---------------");
        DEATH_SCYTHE_LORE.add("");
        DEATH_SCYTHE_LORE.add("§eAbility: §4Death's Power");
        DEATH_SCYTHE_LORE.add("§3Each hit deals §b§l30% §3of your target's");
        DEATH_SCYTHE_LORE.add("§3health as true damage and gain increasing");
        DEATH_SCYTHE_LORE.add("§3levels of §cStrength §3depending on the");
        DEATH_SCYTHE_LORE.add("§3damage dealt!");
        DEATH_SCYTHE_LORE.add("");
        DEATH_SCYTHE_LORE.add("§6LEGENDARY");
        DEATH_SCYTHE_LORE.add("§6---------------");
        // Dragon Sword
        DRAGON_SWORD_LORE.add("id: DRAGON_SWORD");
        DRAGON_SWORD_LORE.add("§6---------------");
        DRAGON_SWORD_LORE.add("");
        DRAGON_SWORD_LORE.add("§6Crafted from a dragon's head, it carries power.");
        DRAGON_SWORD_LORE.add("");
        DRAGON_SWORD_LORE.add("§eAbility: §4One With The Dragon");
        DRAGON_SWORD_LORE.add("§3Chance to deal up to §c80% §3more damage!");
        DRAGON_SWORD_LORE.add("");
        // a blank line will be inserted here if legendary is detected; index 8 this will carry upgrades data in the future
        DRAGON_SWORD_LORE.add("§6LEGENDARY");
        DRAGON_SWORD_LORE.add("§6---------------");
        // Artemis Bow
        ARTEMIS_BOW_LORE.add("id: ARTEMIS_BOW");
        ARTEMIS_BOW_LORE.add("§6---------------");
        ARTEMIS_BOW_LORE.add("");
        ARTEMIS_BOW_LORE.add("§eAbility: §4Heat Seeking Arrows");
        ARTEMIS_BOW_LORE.add("§3Living entities within §b3.8 §3blocks of an arrow");
        ARTEMIS_BOW_LORE.add("§3will be targeted by fully charged shots.");
        ARTEMIS_BOW_LORE.add("");
        ARTEMIS_BOW_LORE.add("§7LEFT CLICK to cycle ability");
        ARTEMIS_BOW_LORE.add("");
        ARTEMIS_BOW_LORE.add("§6LEGENDARY");
        ARTEMIS_BOW_LORE.add("§6---------------");
        // Anduril
        ANDURIL_LORE.add("id: ANDURIL");
        ANDURIL_LORE.add("§6---------------");
        ANDURIL_LORE.add("");
        ANDURIL_LORE.add("§3Grants permanent effects while holding.");
        ANDURIL_LORE.add("§9Speed I");
        ANDURIL_LORE.add("§9Resistance I");
        ANDURIL_LORE.add("");
        ANDURIL_LORE.add("§dEPIC");
        ANDURIL_LORE.add("§6---------------");
        // Excalibur
        EXCALIBUR_LORE.add("id: EXCALIBUR");
        EXCALIBUR_LORE.add("§6---------------");
        EXCALIBUR_LORE.add("");
        EXCALIBUR_LORE.add("§eAbility: §4Fury");
        EXCALIBUR_LORE.add("§3Violently explode dealing §b3");
        EXCALIBUR_LORE.add("§3damage. (6 second cooldown)");
        EXCALIBUR_LORE.add("");
        EXCALIBUR_LORE.add("§6LEGENDARY");
        EXCALIBUR_LORE.add("§6---------------");
        // Exodus
        EXODUS_LORE.add("id: EXODUS");
        EXODUS_LORE.add("§6---------------");
        EXODUS_LORE.add("");
        EXODUS_LORE.add("§3Regenerate a small portion");
        EXODUS_LORE.add("§3of health when you hit any");
        EXODUS_LORE.add("§3entity!");
        EXODUS_LORE.add("§74 second cooldown");
        EXODUS_LORE.add("");
        EXODUS_LORE.add("§6LEGENDARY");
        EXODUS_LORE.add("§6---------------");
        // Rocket Stick
        ROCKET_STICK_LORE.add("id: ROCKET_STICK");
        ROCKET_STICK_LORE.add("§6---------------");
        ROCKET_STICK_LORE.add("");
        ROCKET_STICK_LORE.add("§6Launch your enemies into the air!");
        ROCKET_STICK_LORE.add("§7Hit any entity with the stick.");
        ROCKET_STICK_LORE.add("");
        ROCKET_STICK_LORE.add("§6Teleport forward! §3§lSHIFT RIGHT CLICK");
        ROCKET_STICK_LORE.add("");
        ROCKET_STICK_LORE.add("§6Launch yourself in the direction");
        ROCKET_STICK_LORE.add("§6you are facing! §3§lRIGHT CLICK");
        ROCKET_STICK_LORE.add("");
        ROCKET_STICK_LORE.add("§cSUPREME");
        ROCKET_STICK_LORE.add("§6---------------");
        // Helios
        HELIOS_LORE.add("id: HELIOS");
        HELIOS_LORE.add("§6---------------");
        HELIOS_LORE.add("");
        HELIOS_LORE.add("§aThis sword increases in power as you");
        HELIOS_LORE.add("§again more experience levels.");
        HELIOS_LORE.add("§aYou can increase damage by up to §b50% §aat §b80 §alevels.");
        HELIOS_LORE.add("");
        HELIOS_LORE.add("§eAbility: §4Godly Swarm §e§lRIGHT CLICK");
        HELIOS_LORE.add("§3Summon fire and deal damage to any");
        HELIOS_LORE.add("§3targets within 5 blocks!");
        HELIOS_LORE.add("§7This costs some hunger to use.");
        HELIOS_LORE.add("");
        HELIOS_LORE.add("§6LEGENDARY");
        HELIOS_LORE.add("§6---------------");
        // Scylla's Chestplate
        SCYLLA_CHESTPLATE_LORE.add("id: SCYLLA_CHESTPLATE");
        SCYLLA_CHESTPLATE_LORE.add("§6---------------");
        SCYLLA_CHESTPLATE_LORE.add("");
        SCYLLA_CHESTPLATE_LORE.add("§eAbility: §4Last Wind");
        SCYLLA_CHESTPLATE_LORE.add("§3Gain more resistance when your");
        SCYLLA_CHESTPLATE_LORE.add("§3health is low.");
        SCYLLA_CHESTPLATE_LORE.add("");
        SCYLLA_CHESTPLATE_LORE.add("§6LEGENDARY");
        SCYLLA_CHESTPLATE_LORE.add("§6---------------");
        // Hermes' Boots
        HERMES_BOOTS_LORE.add("id: HERMES_BOOTS");
        HERMES_BOOTS_LORE.add("§6---------------");
        HERMES_BOOTS_LORE.add("");
        HERMES_BOOTS_LORE.add("§aProvides §b+5 §aarmor toughness.");
        HERMES_BOOTS_LORE.add("");
        HERMES_BOOTS_LORE.add("§aGain §b+10% §aspeed while wearing.");
        HERMES_BOOTS_LORE.add("");
        HERMES_BOOTS_LORE.add("§eAbility: §4Mythic Speed");
        HERMES_BOOTS_LORE.add("§3Gain a §c12% §3chance to dodge an attack.");
        HERMES_BOOTS_LORE.add("§716 second cooldown");
        HERMES_BOOTS_LORE.add("");
        HERMES_BOOTS_LORE.add("§6LEGENDARY");
        HERMES_BOOTS_LORE.add("§6---------------");
        // Helmet of Life
        LIFE_HELMET_LORE.add("id: LIFE_HELMET");
        LIFE_HELMET_LORE.add("§6---------------");
        LIFE_HELMET_LORE.add("");
        LIFE_HELMET_LORE.add("§eAbility: §4Rekindle");
        LIFE_HELMET_LORE.add("§3On the brink of death, gain §cResistance §3and");
        LIFE_HELMET_LORE.add("§cRegeneration §3for §b15 §3seconds.");
        LIFE_HELMET_LORE.add("§710 minute cooldown");
        LIFE_HELMET_LORE.add("");
        LIFE_HELMET_LORE.add("§6LEGENDARY");
        LIFE_HELMET_LORE.add("§6---------------");
        // Caveman Sword
        CAVEMAN_SWORD_LORE.add("id: CAVEMAN_SWORD");
        CAVEMAN_SWORD_LORE.add("§6---------------");
        CAVEMAN_SWORD_LORE.add("");
        CAVEMAN_SWORD_LORE.add("§6Experience: §70");
        CAVEMAN_SWORD_LORE.add("§6Required: §7Hit Something!");
        CAVEMAN_SWORD_LORE.add("§6Sharpness Level: §70");
        CAVEMAN_SWORD_LORE.add("");
        CAVEMAN_SWORD_LORE.add("§eAbility: §4Caveman's Strength");
        CAVEMAN_SWORD_LORE.add("§3As you use the sword more, it will gain more levels");
        CAVEMAN_SWORD_LORE.add("§3of §bSharpness§3, up to §b16 §3levels.");
        CAVEMAN_SWORD_LORE.add("");
        CAVEMAN_SWORD_LORE.add("§cDo not enchant this item with Sharpness.");
        CAVEMAN_SWORD_LORE.add("");
        CAVEMAN_SWORD_LORE.add("§6LEGENDARY");
        CAVEMAN_SWORD_LORE.add("§6---------------");
        // Warlock Pants
        WARLOCK_PANTS_LORE.add("id: WARLOCK_PANTS");
        WARLOCK_PANTS_LORE.add("§6---------------");
        WARLOCK_PANTS_LORE.add("");
        WARLOCK_PANTS_LORE.add("§eAbility: §4Hasty Power");
        WARLOCK_PANTS_LORE.add("§3Gain permanent §cStrength I");
        WARLOCK_PANTS_LORE.add("§3while wearing.");
        WARLOCK_PANTS_LORE.add("");
        WARLOCK_PANTS_LORE.add("§6LEGENDARY");
        WARLOCK_PANTS_LORE.add("§6---------------");
        // Fireball
        FIREBALL_LORE.add("id: FIREBALL");
        FIREBALL_LORE.add("§6---------------");
        FIREBALL_LORE.add("");
        FIREBALL_LORE.add("§e§lRIGHT CLICK §3to summon fireball.");
        FIREBALL_LORE.add("§3Small chance of summoning a dragon fireball.");
        FIREBALL_LORE.add("");
        FIREBALL_LORE.add("§dEPIC");
        FIREBALL_LORE.add("§6---------------");
        // Atom Bomb
        ATOM_BOMB_LORE.add("id: ATOM_BOMB");
        ATOM_BOMB_LORE.add("§6---------------");
        ATOM_BOMB_LORE.add("");
        ATOM_BOMB_LORE.add("§cDetonates immediately upon placing.");
        ATOM_BOMB_LORE.add("§cCauses extreme damage.");
        ATOM_BOMB_LORE.add("");
        ATOM_BOMB_LORE.add("§cSUPREME");
        ATOM_BOMB_LORE.add("§6---------------");
        // Netherite Staff
        NETHERITE_STAFF_LORE.add("id: NETHERITE_STAFF");
        NETHERITE_STAFF_LORE.add("§6---------------");
        NETHERITE_STAFF_LORE.add("");
        NETHERITE_STAFF_LORE.add("§3Gives random effects to opponent when hit.");
        NETHERITE_STAFF_LORE.add("§3Effects can be good or bad and have high levels.");
        NETHERITE_STAFF_LORE.add("");
        NETHERITE_STAFF_LORE.add("§3Shoots an arrow with random effect. §e§lRIGHT CLICK");
        NETHERITE_STAFF_LORE.add("");
        NETHERITE_STAFF_LORE.add("§6LEGENDARY");
        NETHERITE_STAFF_LORE.add("§6---------------");
        // Sniper Rifle
        SNIPER_RIFLE_LORE.add("id: SNIPER_RIFLE");
        SNIPER_RIFLE_LORE.add("§6---------------");
        SNIPER_RIFLE_LORE.add("");
        SNIPER_RIFLE_LORE.add("§eAbility: §4Shoot §e§lLEFT CLICK");
        SNIPER_RIFLE_LORE.add("§6Shoots a precise bullet.");
        SNIPER_RIFLE_LORE.add("§71.5 second cooldown");
        SNIPER_RIFLE_LORE.add("");
        SNIPER_RIFLE_LORE.add("§eMuzzle Velocity: §33343 f/s §eor §31019 m/s");
        SNIPER_RIFLE_LORE.add("§7Enchantments do not affect this weapon.");
        SNIPER_RIFLE_LORE.add("§7Minor recoil");
        SNIPER_RIFLE_LORE.add("");
        SNIPER_RIFLE_LORE.add("§6LEGENDARY");
        SNIPER_RIFLE_LORE.add("§6---------------");
        // Miner's Blessing
        MINERS_BLESSING_LORE.add("id: MINERS_BLESSING");
        MINERS_BLESSING_LORE.add("§6---------------");
        MINERS_BLESSING_LORE.add("");
        MINERS_BLESSING_LORE.add("§6Experience: §70");
        MINERS_BLESSING_LORE.add("§6Required: §7Break Something!");
        MINERS_BLESSING_LORE.add("§6Tier: §70");
        MINERS_BLESSING_LORE.add("");
        MINERS_BLESSING_LORE.add("§7Unlock more abilities by levelling up!");
        MINERS_BLESSING_LORE.add("");
        MINERS_BLESSING_LORE.add("§6LEGENDARY");
        MINERS_BLESSING_LORE.add("§6---------------");
        // Pilot's Sword
        PILOT_SWORD_LORE.add("id: PILOT_SWORD");
        PILOT_SWORD_LORE.add("§6---------------");
        PILOT_SWORD_LORE.add("");
        PILOT_SWORD_LORE.add("§eAbility: §4Airliner");
        PILOT_SWORD_LORE.add("§3Attack people super fast!");
        PILOT_SWORD_LORE.add("§3Give everyone a free flight back to spawn!");
        PILOT_SWORD_LORE.add("");
        PILOT_SWORD_LORE.add("§7All damage will be reduced by §c80%§7.");
        PILOT_SWORD_LORE.add("");
        PILOT_SWORD_LORE.add("§6LEGENDARY");
        PILOT_SWORD_LORE.add("§6---------------");
        // Shredded Axe
        SHREDDED_AXE_LORE.add("id: SHREDDED_AXE");
        SHREDDED_AXE_LORE.add("§6---------------");
        SHREDDED_AXE_LORE.add("");
        SHREDDED_AXE_LORE.add("§eAbility: §4Reanimation");
        SHREDDED_AXE_LORE.add("§3Spawn a powerful §2Zombie §3whenever you");
        SHREDDED_AXE_LORE.add("§3hit anything. You can get up to §b10 §3of them.");
        SHREDDED_AXE_LORE.add("");
        SHREDDED_AXE_LORE.add("§6LEGENDARY");
        SHREDDED_AXE_LORE.add("§6---------------");
        // Napalm Missile
        NAPALM_MISSILE_LORE.add("id: NAPALM_MISSILE");
        NAPALM_MISSILE_LORE.add("§6---------------");
        NAPALM_MISSILE_LORE.add("");
        NAPALM_MISSILE_LORE.add("§3Creates massive fire when thrown!");
        NAPALM_MISSILE_LORE.add("");
        NAPALM_MISSILE_LORE.add("§cSUPREME");
        NAPALM_MISSILE_LORE.add("§6---------------");
        // Ninja Bow
        NINJA_BOW_LORE.add("id: NINJA_BOW");
        NINJA_BOW_LORE.add("§6---------------");
        NINJA_BOW_LORE.add("");
        NINJA_BOW_LORE.add("§eAbility: §4Shuriken Shot §e§lLEFT CLICK");
        NINJA_BOW_LORE.add("§3Unleash a triple shot to rain down upon your enemies.");
        NINJA_BOW_LORE.add("§3Other arrows are slightly inaccurate and deal 60% damage.");
        NINJA_BOW_LORE.add("§70.4 second cooldown");
        NINJA_BOW_LORE.add("");
        NINJA_BOW_LORE.add("§6LEGENDARY");
        NINJA_BOW_LORE.add("§6---------------");
        // Dragon Armor
        DRAGON_ARMOR_LORE.add("id: DRAGON_ARMOR");
        DRAGON_ARMOR_LORE.add("§6---------------");
        DRAGON_ARMOR_LORE.add("");
        DRAGON_ARMOR_LORE.add("§eAbility: §4Dragon Blood");
        DRAGON_ARMOR_LORE.add("§3Reduces damage taken by §b5%§3. (Up to 20%)");
        DRAGON_ARMOR_LORE.add("§3Increases damage dealt by §b2%§3. (Up to 8%)");
        DRAGON_ARMOR_LORE.add("");
        DRAGON_ARMOR_LORE.add("§eFull Set Bonus: §4Superior Power");
        DRAGON_ARMOR_LORE.add("§3Increases damage dealt by §aDragon Sword §3by §b15%§3.");
        DRAGON_ARMOR_LORE.add("§3Hunger decreases §b33% §3slower.");
        DRAGON_ARMOR_LORE.add("§3Reduce damage further by §b0.75% §3per §aProtection §3level.");
        // Lines 1(blank)/2/3/4 at 11/12/13/14
        DRAGON_ARMOR_LORE.add("");
        DRAGON_ARMOR_LORE.add("§6LEGENDARY");
        DRAGON_ARMOR_LORE.add("§6---------------");
        // Copper Sword
        COPPER_SWORD_LORE.add("id: COPPER_SWORD");
        COPPER_SWORD_LORE.add("§6---------------");
        COPPER_SWORD_LORE.add("");
        COPPER_SWORD_LORE.add("§eAbility: §4Heavy Blow");
        COPPER_SWORD_LORE.add("§3Small chance of stunning the enemy with a charged attack.");
        COPPER_SWORD_LORE.add("");
        COPPER_SWORD_LORE.add("§7This sword will oxidize over time, making it weaker and weaker.");
        COPPER_SWORD_LORE.add("");
        COPPER_SWORD_LORE.add("§bNORMAL");
        COPPER_SWORD_LORE.add("§6NOT WAXED");
        COPPER_SWORD_LORE.add("");
        COPPER_SWORD_LORE.add("§dEPIC");
        COPPER_SWORD_LORE.add("§6---------------");
        // Poseidon's Trident
        POSEIDON_TRIDENT_LORE.add("id: POSEIDON_TRIDENT");
        POSEIDON_TRIDENT_LORE.add("§6---------------");
        POSEIDON_TRIDENT_LORE.add("");
        POSEIDON_TRIDENT_LORE.add("§eAbility: §4Sea God");
        POSEIDON_TRIDENT_LORE.add("§3Gives Conduit Power while holding.");
        POSEIDON_TRIDENT_LORE.add("§3Small chance of giving Dolphin's Grace.");
        POSEIDON_TRIDENT_LORE.add("§3Small chance of summoning lightning on hit.");
        POSEIDON_TRIDENT_LORE.add("");
        POSEIDON_TRIDENT_LORE.add("§6LEGENDARY");
        POSEIDON_TRIDENT_LORE.add("§6---------------");
        // Valkyrie Axe
        VALKYRIE_AXE_LORE.add("id: VALKYRIE_AXE");
        VALKYRIE_AXE_LORE.add("§6---------------");
        VALKYRIE_AXE_LORE.add("");
        VALKYRIE_AXE_LORE.add("§2A treasure against many enemies.");
        VALKYRIE_AXE_LORE.add("");
        VALKYRIE_AXE_LORE.add("§eAbility: §4Area Damage");
        VALKYRIE_AXE_LORE.add("§3Nearby entities take 75% of the damage of your attack.");
        VALKYRIE_AXE_LORE.add("");
        VALKYRIE_AXE_LORE.add("§eAbility: §4Fire Storm");
        VALKYRIE_AXE_LORE.add("§3A vortex of fire engulfs entities in your path.");
        VALKYRIE_AXE_LORE.add("§3Attacked entities take massive knockback.");
        VALKYRIE_AXE_LORE.add("§7Costs 15 durability and some hunger to use");
        VALKYRIE_AXE_LORE.add("");
        VALKYRIE_AXE_LORE.add("§214 Attack Damage");
        VALKYRIE_AXE_LORE.add("§20.6 Attack Speed");
        VALKYRIE_AXE_LORE.add("");
        VALKYRIE_AXE_LORE.add("§6LEGENDARY");
        VALKYRIE_AXE_LORE.add("§6---------------");
        // Assault Rifle
        ASSAULT_RIFLE_LORE.add("id: ASSAULT_RIFLE");
        ASSAULT_RIFLE_LORE.add("§6---------------");
        ASSAULT_RIFLE_LORE.add("");
        ASSAULT_RIFLE_LORE.add("§eLEFT CLICK §3to fire.");
        ASSAULT_RIFLE_LORE.add("§7Use §eRIGHT CLICK §7to cycle between modes: §3single shot§7, §3burst§7.");
        ASSAULT_RIFLE_LORE.add("§eSHIFT RIGHT CLICK §7to reload.");
        ASSAULT_RIFLE_LORE.add("");
        ASSAULT_RIFLE_LORE.add("§eMuzzle Velocity: §33038 f/s §eor §3926 m/s");
        ASSAULT_RIFLE_LORE.add("§7Enchantments do not affect this weapon.");
        ASSAULT_RIFLE_LORE.add("§7Minor recoil");
        ASSAULT_RIFLE_LORE.add("");
        ASSAULT_RIFLE_LORE.add("§3Up to §b64 §3rounds can be loaded at once.");
        ASSAULT_RIFLE_LORE.add("");
        ASSAULT_RIFLE_LORE.add("§cSUPREME");
        ASSAULT_RIFLE_LORE.add("§6---------------");
        // Dragon Elytra
        DRAGON_ELYTRA_LORE.add("id: DRAGON_ELYTRA");
        DRAGON_ELYTRA_LORE.add("§6---------------");
        DRAGON_ELYTRA_LORE.add("");
        DRAGON_ELYTRA_LORE.add("§eAbility: §4Dragon Flight");
        DRAGON_ELYTRA_LORE.add("§3A mysterious force propels you forward.");
        DRAGON_ELYTRA_LORE.add("§3Gain a small permanent boost while flying!");
        DRAGON_ELYTRA_LORE.add("");
        DRAGON_ELYTRA_LORE.add("§eAbility: §4Ender Boost");
        DRAGON_ELYTRA_LORE.add("§eRIGHT CLICK §3with §2Eye of Ender");
        DRAGON_ELYTRA_LORE.add("§3to get a massive speed boost!");
        DRAGON_ELYTRA_LORE.add("");
        DRAGON_ELYTRA_LORE.add("§6LEGENDARY");
        DRAGON_ELYTRA_LORE.add("§6---------------");
        // Guided Missile
        GUIDED_MISSILE_LORE.add("id: GUIDED_MISSILE");
        GUIDED_MISSILE_LORE.add("§6---------------");
        GUIDED_MISSILE_LORE.add("");
        GUIDED_MISSILE_LORE.add("§eAbility: §4Missile Lock");
        GUIDED_MISSILE_LORE.add("§3Point at any target to lock on.");
        GUIDED_MISSILE_LORE.add("§3The missile will seek to the target and");
        GUIDED_MISSILE_LORE.add("§3explode when it gets close.");
        GUIDED_MISSILE_LORE.add("");
        GUIDED_MISSILE_LORE.add("§6LEGENDARY");
        GUIDED_MISSILE_LORE.add("§6---------------");
        // Assassin's Blade
        ASSASSINS_BLADE_LORE.add("id: ASSASSINS_BLADE");
        ASSASSINS_BLADE_LORE.add("§6---------------");
        ASSASSINS_BLADE_LORE.add("");
        ASSASSINS_BLADE_LORE.add("§eAbility: §4Backstab");
        ASSASSINS_BLADE_LORE.add("§3Hits from the back are extremely");
        ASSASSINS_BLADE_LORE.add("§3deadly and deal §b+40% §3damage.");
        ASSASSINS_BLADE_LORE.add("");
        ASSASSINS_BLADE_LORE.add("§dEPIC");
        ASSASSINS_BLADE_LORE.add("§6---------------");
        // Wither Staff
        WITHER_STAFF_LORE.add("id: WITHER_STAFF");
        WITHER_STAFF_LORE.add("§6---------------");
        WITHER_STAFF_LORE.add("");
        WITHER_STAFF_LORE.add("§eAbility: §4Skeleton's Call §e§lRIGHT CLICK");
        WITHER_STAFF_LORE.add("§3Shoots wither skeleton skulls.");
        WITHER_STAFF_LORE.add("§3The skulls explode on impact.");
        WITHER_STAFF_LORE.add("§71 second cooldown");
        WITHER_STAFF_LORE.add("");
        WITHER_STAFF_LORE.add("§dEPIC");
        WITHER_STAFF_LORE.add("§6---------------");
        // Bomb Cannon
        BOMB_CANNON_LORE.add("id: BOMB_CANNON");
        BOMB_CANNON_LORE.add("§6---------------");
        BOMB_CANNON_LORE.add("");
        BOMB_CANNON_LORE.add("§eAbility: §4Explosive Arrows");
        BOMB_CANNON_LORE.add("§3Fully charged arrows fired from this bow");
        BOMB_CANNON_LORE.add("§3explode upon impact.");
        BOMB_CANNON_LORE.add("");
        BOMB_CANNON_LORE.add("§dEPIC");
        BOMB_CANNON_LORE.add("§6---------------");
        // Throwing Knife
        THROWING_KNIFE_LORE.add("id: THROWING_KNIFE");
        THROWING_KNIFE_LORE.add("§6---------------");
        THROWING_KNIFE_LORE.add("");
        THROWING_KNIFE_LORE.add("§eAbility: §4Throw §e§lRIGHT CLICK");
        THROWING_KNIFE_LORE.add("§3Throw a knife at your target. The knife");
        THROWING_KNIFE_LORE.add("§3will damage anyone caught in its path.");
        THROWING_KNIFE_LORE.add("");
        THROWING_KNIFE_LORE.add("§dEPIC");
        THROWING_KNIFE_LORE.add("§6---------------");
        // Witch Sword
        WITCH_SWORD_LORE.add("id: WITCH_SWORD");
        WITCH_SWORD_LORE.add("§6---------------");
        WITCH_SWORD_LORE.add("");
        WITCH_SWORD_LORE.add("§eAbility: §4Black Magic");
        WITCH_SWORD_LORE.add("§3Consumes §c30% §3of the damage dealt.");
        WITCH_SWORD_LORE.add("§3Attacked targets are cursed for §b10 §3seconds.");
        WITCH_SWORD_LORE.add("§3If they are attacked, their attackers are");
        WITCH_SWORD_LORE.add("§3healed for §a15% §3of their maximum health.");
        WITCH_SWORD_LORE.add("");
        WITCH_SWORD_LORE.add("§6LEGENDARY");
        WITCH_SWORD_LORE.add("§6---------------");
        // End Sword
        END_SWORD_LORE.add("id: END_SWORD");
        END_SWORD_LORE.add("§6---------------");
        END_SWORD_LORE.add("");
        END_SWORD_LORE.add("§3Stores §b150 §dEnd Power");
        END_SWORD_LORE.add("");
        END_SWORD_LORE.add("§eAbility: §4Ender Transmission §e§lRIGHT CLICK");
        END_SWORD_LORE.add("§3Teleport 7 blocks in front of you.");
        END_SWORD_LORE.add("§7Costs §b10 §dEnd Power §7per use");
        END_SWORD_LORE.add("");
        END_SWORD_LORE.add("§3Every 100 ender pearls thrown increases your maximum");
        END_SWORD_LORE.add("§dEnd Power §3by §b1§3, up to a maximum of §b+30§3.");
        END_SWORD_LORE.add("");
        END_SWORD_LORE.add("§6LEGENDARY");
        END_SWORD_LORE.add("§6---------------");
        // End Armor
        END_ARMOR_LORE.add("id: END_ARMOR");
        END_ARMOR_LORE.add("§6---------------");
        END_ARMOR_LORE.add("");
        END_ARMOR_LORE.add("§eAbility: §4Soul of the End");
        END_ARMOR_LORE.add("§3Increases your maximum §dEnd Power §3by §b+35§3. (Up to 140)");
        END_ARMOR_LORE.add("");
        END_ARMOR_LORE.add("§eFull Set Bonus: §4Soul of the End");
        END_ARMOR_LORE.add("§3Gain a §b30% §3chance to not consume an ender pearl when thrown.");
        END_ARMOR_LORE.add("");
        END_ARMOR_LORE.add("§6LEGENDARY");
        END_ARMOR_LORE.add("§6---------------");
        // Scorpion Bow
        SCORPION_BOW_LORE.add("id: SCORPION_BOW");
        SCORPION_BOW_LORE.add("§6---------------");
        SCORPION_BOW_LORE.add("");
        SCORPION_BOW_LORE.add("§eAbility: §4Venom");
        SCORPION_BOW_LORE.add("§3Targets shot are poisoned for");
        SCORPION_BOW_LORE.add("§b6 §3seconds.");
        SCORPION_BOW_LORE.add("");
        SCORPION_BOW_LORE.add("§dEPIC");
        SCORPION_BOW_LORE.add("§6---------------");
        // Ares
        ARES_LORE.add("id: ARES");
        ARES_LORE.add("§6---------------");
        ARES_LORE.add("");
        ARES_LORE.add("§eAbility: §4Sky Strike");
        ARES_LORE.add("§3A powerful lightning strike deals 1 heart of §6True Damage§3.");
        ARES_LORE.add("");
        ARES_LORE.add("§6LEGENDARY");
        ARES_LORE.add("§6---------------");
        // Ninja Master's Bow
        NINJA_MASTERS_BOW_LORE.add("id: NINJA_MASTERS_BOW");
        NINJA_MASTERS_BOW_LORE.add("§6---------------");
        NINJA_MASTERS_BOW_LORE.add("");
        NINJA_MASTERS_BOW_LORE.add("§eAbility: §6Master §4Shuriken Shot §e§lLEFT CLICK");
        NINJA_MASTERS_BOW_LORE.add("§3Shoots §b25 §3arrows at a time!");
        NINJA_MASTERS_BOW_LORE.add("§7No cooldown!");
        NINJA_MASTERS_BOW_LORE.add("");
        NINJA_MASTERS_BOW_LORE.add("§cSUPREME");
        NINJA_MASTERS_BOW_LORE.add("§6---------------");
        // Death Rod
        DEATH_ROD_LORE.add("id: DEATH_ROD");
        DEATH_ROD_LORE.add("§6---------------");
        DEATH_ROD_LORE.add("");
        DEATH_ROD_LORE.add("§4Legend says the rod drops when Death itself is killed. It is believed");
        DEATH_ROD_LORE.add("§4that those who touch the rod instantly die.");
        DEATH_ROD_LORE.add("");
        DEATH_ROD_LORE.add("§cSUPREME");
        DEATH_ROD_LORE.add("§6---------------");
        // Plutonium Blade
        PLUTONIUM_BLADE_LORE.add("id: PLUTONIUM_BLADE");
        PLUTONIUM_BLADE_LORE.add("§6---------------");
        PLUTONIUM_BLADE_LORE.add("");
        PLUTONIUM_BLADE_LORE.add("§3The dense material of the blade crits for §6+20% §3damage.");
        PLUTONIUM_BLADE_LORE.add("");
        PLUTONIUM_BLADE_LORE.add("§eAbility: §4Implosion §e§oLOOK STRAIGHT DOWN & SHIFT RIGHT CLICK");
        PLUTONIUM_BLADE_LORE.add("§3Creates a nuclear shockwave traveling outwards up to §b20 §3blocks.");
        PLUTONIUM_BLADE_LORE.add("§3Those caught in the blast take §6massive §3knockback and damage.");
        PLUTONIUM_BLADE_LORE.add("§7The shockwave travels through blocks!");
        PLUTONIUM_BLADE_LORE.add("§720 second cooldown");
        PLUTONIUM_BLADE_LORE.add("§7100 durability per use");
    }

    public ItemStack generateMeta(List<String> lore, String name, Material mat) {
        ItemStack items = new ItemStack(mat, 1);
        ItemMeta iMeta = items.getItemMeta();
        iMeta.setLore(lore);
        iMeta.setDisplayName(name);
        items.setItemMeta(iMeta);
        return items;
    }

    public boolean checkFor(ItemStack item, int index, String requiredLoreSection) {
        if (item == null) { return false; }
        ItemMeta iMeta = item.getItemMeta();
        if (iMeta == null || iMeta.getLore() == null) { return false; }
        if (iMeta.getLore().size() <= index) { return false; }
        return iMeta.getLore().get(index).equalsIgnoreCase(requiredLoreSection);
    }

    public static boolean checkForInteractableBlock(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)  { return false; }
        Block block = event.getClickedBlock();
        if (block == null) { return false; }
        Material type = block.getType();
        return (type.isInteractable());
    }

    public boolean checkForRPGLauncher(ItemStack item) {
        return this.checkFor(item, 0, "id: RPG_LAUNCHER");
    }
    public boolean checkForVampAxe(ItemStack item) {
        return this.checkFor(item, 0, "id: VAMP_AXE");
    }
    public boolean checkForDeathScythe(ItemStack item) {
        return this.checkFor(item, 0, "id: DEATH_SCYTHE");
    }
    public boolean checkForDragonSword(ItemStack item) {
        return this.checkFor(item, 0, "id: DRAGON_SWORD");
    }
    public boolean checkForArtemisBow(ItemStack item) {
        return this.checkFor(item, 0, "id: ARTEMIS_BOW");
    }
    public boolean checkForAnduril(ItemStack item) {
        return this.checkFor(item, 0, "id: ANDURIL");
    }
    public boolean checkForExcalibur(ItemStack item) {
        return this.checkFor(item, 0, "id: EXCALIBUR");
    }
    public boolean checkForExodus(ItemStack item) {
        return this.checkFor(item, 0, "id: EXODUS");
    }
    public boolean checkForRocketStick(ItemStack item) {
        return this.checkFor(item, 0, "id: ROCKET_STICK");
    }
    public boolean checkForHelios(ItemStack item) {
        return this.checkFor(item, 0, "id: HELIOS");
    }
    public boolean checkForScylla(ItemStack item) {
        return this.checkFor(item, 0, "id: SCYLLA_CHESTPLATE");
    }
    public boolean checkForHermesBoots(ItemStack item) {
        return this.checkFor(item, 0, "id: HERMES_BOOTS");
    }
    public boolean checkForLifeHelmet(ItemStack item) {
        return this.checkFor(item, 0, "id: LIFE_HELMET");
    }
    public boolean checkForCavemanSword(ItemStack item) {
        return this.checkFor(item, 0, "id: CAVEMAN_SWORD");
    }
    public boolean checkForWarlockPants(ItemStack item) {
        return this.checkFor(item, 0, "id: WARLOCK_PANTS");
    }
    public boolean checkForFireball(ItemStack item) {
        return this.checkFor(item, 0, "id: FIREBALL");
    }
    public boolean checkForAtomBomb(ItemStack item) {
        return this.checkFor(item, 0, "id: ATOM_BOMB");
    }
    public boolean checkForNetheriteStaff(ItemStack item) {
        return this.checkFor(item, 0, "id: NETHERITE_STAFF");
    }
    public boolean checkForSniperRifle(ItemStack item) {
        return this.checkFor(item, 0, "id: SNIPER_RIFLE");
    }
    public boolean checkForMinersBlessing(ItemStack item) {
        return this.checkFor(item, 0, "id: MINERS_BLESSING");
    }
    public boolean checkForPilotSword(ItemStack item) {
        return this.checkFor(item, 0, "id: PILOT_SWORD");
    }
    public boolean checkForShreddedAxe(ItemStack item) {
        return this.checkFor(item, 0, "id: SHREDDED_AXE");
    }
    public boolean checkForNapalmMissile(ItemStack item) {
        return this.checkFor(item, 0, "id: NAPALM_MISSILE");
    }
    public boolean checkForNinjaBow(ItemStack item) {
        return this.checkFor(item, 0, "id: NINJA_BOW");
    }
    public boolean checkForDragonArmor(ItemStack item) {
        return this.checkFor(item, 0, "id: DRAGON_ARMOR");
    }
    public boolean checkForCopperSword(ItemStack item) {
        return this.checkFor(item, 0, "id: COPPER_SWORD");
    }
    public boolean checkForPoseidonTrident(ItemStack item) {
        return this.checkFor(item, 0, "id: POSEIDON_TRIDENT");
    }
    public boolean checkForValkyrieAxe(ItemStack item) {
        return this.checkFor(item, 0, "id: VALKYRIE_AXE");
    }
    public boolean checkForAssaultRifle(ItemStack item) {
        return this.checkFor(item, 0, "id: ASSAULT_RIFLE");
    }
    public boolean checkForDragonElytra(ItemStack item) {
        return this.checkFor(item, 0, "id: DRAGON_ELYTRA");
    }
    public boolean checkForGuidedMissile(ItemStack item) {
        return this.checkFor(item, 0, "id: GUIDED_MISSILE");
    }
    public boolean checkForAssassinsBlade(ItemStack item) {
        return this.checkFor(item, 0, "id: ASSASSINS_BLADE");
    }
    public boolean checkForWitherStaff(ItemStack item) {
        return this.checkFor(item, 0, "id: WITHER_STAFF");
    }
    public boolean checkForBombCannon(ItemStack item) {
        return this.checkFor(item, 0, "id: BOMB_CANNON");
    }
    public boolean checkForThrowingKnife(ItemStack item) {
        return this.checkFor(item, 0, "id: THROWING_KNIFE");
    }
    public boolean checkForWitchSword(ItemStack item) {
        return this.checkFor(item, 0, "id: WITCH_SWORD");
    }
    public boolean checkForEndSword(ItemStack item) {
        return this.checkFor(item, 0, "id: END_SWORD");
    }
    public boolean checkForEndArmor(ItemStack item) {
        return this.checkFor(item, 0, "id: END_ARMOR");
    }
    public boolean checkForScorpionBow(ItemStack item) {
        return this.checkFor(item, 0, "id: SCORPION_BOW");
    }
    public boolean checkForAres(ItemStack item) {
        return this.checkFor(item, 0, "id: ARES");
    }
    public boolean checkForNinjaMastersBow(ItemStack item) {
        return this.checkFor(item, 0, "id: NINJA_MASTERS_BOW");
    }
    public boolean checkForDeathRod(ItemStack item) {
        return this.checkFor(item, 0, "id: DEATH_ROD");
    }
    public boolean checkForPlutoniumBlade(ItemStack item) {
        return this.checkFor(item, 0, "id: PLUTONIUM_BLADE");
    }
}
