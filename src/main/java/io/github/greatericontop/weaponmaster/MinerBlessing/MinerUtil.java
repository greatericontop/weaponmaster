package io.github.greatericontop.weaponmaster.MinerBlessing;

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
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Material;

import java.util.List;
import java.util.Random;

public class MinerUtil   {

    protected final WeaponMasterMain plugin;
    protected final Util util;
    protected final Random rnd = new Random();
    public MinerUtil(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    protected String getMode(List<String> lore) {
        return lore.get(util.MINER_INSERTION + 3);
    }

    protected int xpToAdd(Material mat, List<String> lore) {
        if (getMode(lore).equals("§a>§b>§c> §6Currently set to §9Silk Touch"))  return 1;
        if (mat == Material.DEEPSLATE_COAL_ORE)  return 2700;
        if (mat == Material.DEEPSLATE_EMERALD_ORE || mat == Material.DEEPSLATE_DIAMOND_ORE)  return 1500;
        if (mat == Material.EMERALD_ORE || mat == Material.DIAMOND_ORE)  return 1000;
        if (mat == Material.DEEPSLATE_IRON_ORE || mat == Material.DEEPSLATE_REDSTONE_ORE || mat == Material.DEEPSLATE_GOLD_ORE || mat == Material.DEEPSLATE_LAPIS_ORE)  return 810;
        if (mat == Material.COAL_ORE || mat == Material.IRON_ORE || mat == Material.REDSTONE_ORE || mat == Material.GOLD_ORE || mat == Material.LAPIS_ORE)  return 600;
        if (mat == Material.OBSIDIAN || mat == Material.ANCIENT_DEBRIS)  return 70;
        if (mat == Material.DEEPSLATE)  return 15;
        if (mat == Material.STONE)  return 6;
        if (mat == Material.NETHERRACK) return 2;
        return 1;
    }

    /*
     * Applies to coal, diamond, emerald, iron, copper, gold
     * NOT redstone
     */
    protected int doFortuneOre(int amount) {
        float f = rnd.nextFloat();
        int multiplier = Math.min((int) (f * 5), 1);
        return amount * multiplier;
    }

    protected int parseExpInt(String s) {
        // §6Experience: §b<DATA>
        String data = s.substring(16, s.length());
        return Integer.parseInt(data);
    }
    protected int parseLevelInt(String s) {
        // §6Tier: §b<DATA>
        String data = s.substring(10, s.length());
        return Integer.parseInt(data);
    }

    protected int getRequirementToLevelUp(int level) {
        if (level >= 9) {
            return 2147483600;
        }
        return new int[]{
                10_000,
                15_000, // 1
                20_000,
                30_000,
                40_000,
                50_000, // 5
                60_000,
                70_000,
                80_000,
        }[level];
//        return new int[]{
//                10_000,
//                15_000, // 1
//                25_000,
//                40_000,
//                60_000,
//                90_000, // 5
//                135_000,
//                180_000,
//        }[level];
    }

}
