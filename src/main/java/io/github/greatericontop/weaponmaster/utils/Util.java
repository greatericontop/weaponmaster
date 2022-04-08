package io.github.greatericontop.weaponmaster.utils;

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
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
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
    public final String HERMES_BOOTS_NAME = "§eHerme's Boots §6⚝⚝⚝⚝⚝";
    public final String LIFE_HELMET_NAME = "§9§lHelmet of Life §6⚝⚝⚝⚝⚝";
    public final String CAVEMAN_SWORD_NAME = "§8§lCaveman Sword §6⚝⚝⚝⚝⚝";
    public final String ASSAULT_RIFLE_NAME = "§9§lAssault Rifle §6⚝⚝⚝⚝⚝";

    public List<String> RPG_LAUNCHER_LORE = new ArrayList<String>();
    public List<String> VAMP_AXE_LORE = new ArrayList<String>();
    public List<String> DEATH_SCYTHE_LORE = new ArrayList<String>();
    public List<String> DRAGON_SWORD_LORE = new ArrayList<String>();
    public List<String> ARTEMIS_BOW_LORE = new ArrayList<String>();
    public List<String> ANDURIL_LORE = new ArrayList<String>();
    public List<String> EXCALIBUR_LORE = new ArrayList<String>();
    public List<String> EXODUS_LORE = new ArrayList<String>();
    public List<String> ROCKET_STICK_LORE = new ArrayList<String>();
    public List<String> HELIOS_LORE = new ArrayList<String>();
    public List<String> SCYLLA_CHESTPLATE_LORE = new ArrayList<String>();
    public List<String> HERMES_BOOTS_LORE = new ArrayList<String>();
    public List<String> LIFE_HELMET_LORE = new ArrayList<String>();
    public List<String> CAVEMAN_SWORD_LORE = new ArrayList<String>();
    public List<String> ASSAULT_RIFLE_LORE = new ArrayList<String>();

    public final int CAVEMAN_EXP_I = 3;
    public final int CAVEMAN_REQ_I = 4;
    public final int CAVEMAN_LVL_I = 5;

    private final WeaponMasterMain plugin;
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
        ANDURIL_LORE.add("§9Grants permanent effects while holding.");
        ANDURIL_LORE.add("§9Speed I");
        ANDURIL_LORE.add("§9Resistance I");
        ANDURIL_LORE.add("");
        ANDURIL_LORE.add("§6LEGENDARY");
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
        EXODUS_LORE.add("§3entity! (6 second cooldown)");
        EXODUS_LORE.add("");
        EXODUS_LORE.add("§6LEGENDARY");
        EXODUS_LORE.add("§6---------------");
        // Rocket Stick
        ROCKET_STICK_LORE.add("id: ROCKET_STICK");
        ROCKET_STICK_LORE.add("§6---------------");
        ROCKET_STICK_LORE.add("");
        ROCKET_STICK_LORE.add("§6Launch your enemies into the air!");
        ROCKET_STICK_LORE.add("§7Hit with stick to use. (players only)");
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
        HELIOS_LORE.add("§aUp to 50 levels, this sword increases");
        HELIOS_LORE.add("§ain power with more experience levels.");
        HELIOS_LORE.add("§aSword damage is increased by 1% per level.");
        HELIOS_LORE.add("");
        HELIOS_LORE.add("§eAbility: §4Godly Swarm §e§lRIGHT CLICK");
        HELIOS_LORE.add("§3Summon fire and deal damage to any");
        HELIOS_LORE.add("§3targets within 5 blocks!");
        HELIOS_LORE.add("§3Costs hunger to use.");
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
        // Herme's Boots
        HERMES_BOOTS_LORE.add("id: HERMES_BOOTS");
        HERMES_BOOTS_LORE.add("§6---------------");
        HERMES_BOOTS_LORE.add("");
        HERMES_BOOTS_LORE.add("§aProvides §b+5 §aarmor toughness.");
        HERMES_BOOTS_LORE.add("");
        HERMES_BOOTS_LORE.add("§aGain §b+10% §aspeed while wearing.");
        HERMES_BOOTS_LORE.add("");
        HERMES_BOOTS_LORE.add("§eAbility: §4Mythic Speed");
        HERMES_BOOTS_LORE.add("§3Gain a §c12% §3chance to dodge an attack.");
        HERMES_BOOTS_LORE.add("§3(16 second cooldown)");
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
        CAVEMAN_SWORD_LORE.add("§3of §bSharpness§3, up to §b20 §3levels.");
        CAVEMAN_SWORD_LORE.add("");
        CAVEMAN_SWORD_LORE.add("§cDo not enchant this item with Sharpness.");
        CAVEMAN_SWORD_LORE.add("");
        CAVEMAN_SWORD_LORE.add("§6LEGENDARY");
        CAVEMAN_SWORD_LORE.add("§6---------------");
        // Assault Rifle
        ASSAULT_RIFLE_LORE.add("id: ASSAULT_RIFLE");
        ASSAULT_RIFLE_LORE.add("§6---------------");
        ASSAULT_RIFLE_LORE.add("");
        ASSAULT_RIFLE_LORE.add("§bBurst Fire §l§eLEFT CLICK");
        ASSAULT_RIFLE_LORE.add("§6Slow burst fire 3x 5.56 bullets.");
        ASSAULT_RIFLE_LORE.add("");
        ASSAULT_RIFLE_LORE.add("§bBigger Burst §l§eRIGHT CLICK");
        ASSAULT_RIFLE_LORE.add("§6Fire 25 rounds of 5.56 in a row.");
        ASSAULT_RIFLE_LORE.add("§6Rounds are fired at an extreme speed of 1200 per minute.");
        ASSAULT_RIFLE_LORE.add("");
        ASSAULT_RIFLE_LORE.add("§eMuzzle Velocity: §33038 f/s §eor §3926 m/s");
        ASSAULT_RIFLE_LORE.add("§eMax Spread: §30.6m §eat §3100 §eblocks + drop");
        ASSAULT_RIFLE_LORE.add("§7Enchantments do not affect this weapon.");
        ASSAULT_RIFLE_LORE.add("§7Minor recoil");
        ASSAULT_RIFLE_LORE.add("");
        ASSAULT_RIFLE_LORE.add("§6LEGENDARY");
        ASSAULT_RIFLE_LORE.add("§6---------------");
    }

    public ItemStack generateMeta(List<String> lore, String name, Material mat) {
        ItemStack items = new ItemStack(mat, 1);
        ItemMeta iMeta = items.getItemMeta();
        iMeta.setLore(lore);
        iMeta.setDisplayName(name);
        items.setItemMeta(iMeta);
        return items;
    }

    private boolean checkFor(ItemStack item, String requiredName, int index, String requiredLoreSection) {
        if (item == null) { return false; }
        ItemMeta iMeta = item.getItemMeta();
        if (iMeta == null || iMeta.getLore() == null) { return false; }
        boolean nameCorrect = iMeta.getDisplayName().equals(requiredName);
        boolean loreCorrect = iMeta.getLore().get(index).equalsIgnoreCase(requiredLoreSection);
        return nameCorrect && loreCorrect;
    }

    public boolean checkForRPGLauncher(ItemStack item) {
        return this.checkFor(item, RPG_LAUNCHER_NAME, 0, "id: RPG_LAUNCHER");
    }
    public boolean checkForVampAxe(ItemStack item) {
        return this.checkFor(item, VAMP_AXE_NAME, 0, "id: VAMP_AXE");
    }
    public boolean checkForDeathScythe(ItemStack item) {
        return this.checkFor(item, DEATH_SCYTHE_NAME, 0, "id: DEATH_SCYTHE");
    }
    public boolean checkForDragonSword(ItemStack item) {
        return this.checkFor(item, DRAGON_SWORD_NAME, 0, "id: DRAGON_SWORD");
    }
    public boolean checkForArtemisBow(ItemStack item) {
        return this.checkFor(item, ARTEMIS_BOW_NAME, 0, "id: ARTEMIS_BOW");
    }
    public boolean checkForAnduril(ItemStack item) {
        return this.checkFor(item, ANDURIL_NAME, 0, "id: ANDURIL");
    }
    public boolean checkForExcalibur(ItemStack item) {
        return this.checkFor(item, EXCALIBUR_NAME, 0, "id: EXCALIBUR");
    }
    public boolean checkForExodus(ItemStack item) {
        return this.checkFor(item, EXODUS_NAME, 0, "id: EXODUS");
    }
    public boolean checkForRocketStick(ItemStack item) {
        return this.checkFor(item, ROCKET_STICK_NAME, 0, "id: ROCKET_STICK");
    }
    public boolean checkForHelios(ItemStack item) {
        return this.checkFor(item, HELIOS_NAME, 0, "id: HELIOS");
    }
    public boolean checkForScylla(ItemStack item) {
        return this.checkFor(item, SCYLLA_CHESTPLATE_NAME, 0, "id: SCYLLA_CHESTPLATE");
    }
    public boolean checkForHermesBoots(ItemStack item) {
        return this.checkFor(item, HERMES_BOOTS_NAME, 0, "id: HERMES_BOOTS");
    }
    public boolean checkForLifeHelmet(ItemStack item) {
        return this.checkFor(item, LIFE_HELMET_NAME, 0, "id: LIFE_HELMET");
    }
    public boolean checkForCavemanSword(ItemStack item) {
        return this.checkFor(item, CAVEMAN_SWORD_NAME, 0, "id: CAVEMAN_SWORD");
    }
    //public boolean checkForAssaultRifle(ItemStack item) {
    //    return this.checkFor(item, ASSAULT_RIFLE_NAME, 0, "id: ASSAULT_RIFLE");
    //}

}
