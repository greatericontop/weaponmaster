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
    public final String ASSAULT_RIFLE_NAME = "§9§lAssault Rifle §6⚝⚝⚝⚝⚝";

    public List<String> RPG_LAUNCHER_LORE = new ArrayList<String>();
    public List<String> VAMP_AXE_LORE = new ArrayList<String>();
    public List<String> DEATH_SCYTHE_LORE = new ArrayList<String>();
    public List<String> DRAGON_SWORD_LORE = new ArrayList<String>();
    public List<String> ARTEMIS_BOW_LORE = new ArrayList<String>();
    public List<String> ANDURIL_LORE = new ArrayList<String>();
    public List<String> EXCALIBUR_LORE = new ArrayList<String>();
    public List<String> EXODUS_LORE = new ArrayList<String>();
    public List<String> ASSAULT_RIFLE_LORE = new ArrayList<String>();

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
        RPG_LAUNCHER_LORE.add("§cSUPREME WEAPON");
        RPG_LAUNCHER_LORE.add("§6---------------");
        // Vampire's Axe
        VAMP_AXE_LORE.add("id: VAMP_AXE");
        VAMP_AXE_LORE.add("§6---------------");
        VAMP_AXE_LORE.add("");
        VAMP_AXE_LORE.add("§eAbility: §4Vampiric");
        VAMP_AXE_LORE.add("§3Life steal §b§l16% §3of dealt damage.");
        VAMP_AXE_LORE.add("");
        VAMP_AXE_LORE.add("§6LEGENDARY WEAPON");
        VAMP_AXE_LORE.add("§6---------------");
        // Death's Scythe
        DEATH_SCYTHE_LORE.add("id: DEATH_SCYTHE");
        DEATH_SCYTHE_LORE.add("§6---------------");
        DEATH_SCYTHE_LORE.add("");
        DEATH_SCYTHE_LORE.add("§eAbility: §4Deathly");
        DEATH_SCYTHE_LORE.add("§3Each hit deals §b§l50% §3of your target's");
        DEATH_SCYTHE_LORE.add("§3health as true damage.");
        DEATH_SCYTHE_LORE.add("");
        DEATH_SCYTHE_LORE.add("§6LEGENDARY WEAPON");
        DEATH_SCYTHE_LORE.add("§6---------------");
        // Dragon Sword
        DRAGON_SWORD_LORE.add("id: DRAGON_SWORD");
        DRAGON_SWORD_LORE.add("§6---------------");
        DRAGON_SWORD_LORE.add("");
        DRAGON_SWORD_LORE.add("§6Crafted from a dragon's head, it carries power.");
        DRAGON_SWORD_LORE.add("");
        DRAGON_SWORD_LORE.add("§eAbility: §4One With The Dragon");
        DRAGON_SWORD_LORE.add("§3Gain §bStrength §cI §3for §b10 §3seconds after a hit.");
        DRAGON_SWORD_LORE.add("");
        DRAGON_SWORD_LORE.add("§6LEGENDARY WEAPON");
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
        ARTEMIS_BOW_LORE.add("§6LEGENDARY WEAPON");
        ARTEMIS_BOW_LORE.add("§6---------------");
        // Anduril
        ANDURIL_LORE.add("id: ANDURIL");
        ANDURIL_LORE.add("§6---------------");
        ANDURIL_LORE.add("");
        ANDURIL_LORE.add("§9Grants permanent effects while holding.");
        ANDURIL_LORE.add("§9Speed I");
        ANDURIL_LORE.add("§9Resistance I");
        ANDURIL_LORE.add("");
        ANDURIL_LORE.add("§6LEGENDARY WEAPON");
        ANDURIL_LORE.add("§6---------------");
        // Excalibur
        EXCALIBUR_LORE.add("id: EXCALIBUR");
        EXCALIBUR_LORE.add("§6---------------");
        EXCALIBUR_LORE.add("");
        EXCALIBUR_LORE.add("§eAbility: §4Fury");
        EXCALIBUR_LORE.add("§3Violently explode dealing §b3");
        EXCALIBUR_LORE.add("§3damage. (6 second cooldown)");
        EXCALIBUR_LORE.add("");
        EXCALIBUR_LORE.add("§6---------------");
        // Exodus
        EXODUS_LORE.add("id: EXODUS");
        EXODUS_LORE.add("§6---------------");
        EXODUS_LORE.add("");
        EXODUS_LORE.add("§3Regenerate a small portion");
        EXODUS_LORE.add("§3of health when you hit any");
        EXODUS_LORE.add("§3entity! (18 second cooldown)");
        EXODUS_LORE.add("");
        EXODUS_LORE.add("§6---------------");
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
        ASSAULT_RIFLE_LORE.add("§6LEGENDARY WEAPON");
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
    //public boolean checkForAssaultRifle(ItemStack item) {
    //    return this.checkFor(item, ASSAULT_RIFLE_NAME, 0, "id: ASSAULT_RIFLE");
    //}

}
