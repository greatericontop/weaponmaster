package io.github.greatericontop.weaponmaster.mainitems.MinerBlessing;

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
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

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
        if (getMode(lore).equals("§a>§b>§c> §6Currently set to §9Silk Touch"))  return silkLoot(mat);
        if (getMode(lore).equals("§a>§b>§c> §6Currently set to §9Smelting Touch"))  return smeltingLoot(mat);
        return defaultLoot(mat);
    }
    protected int silkLoot(Material mat) {
        if (mat == Material.OBSIDIAN || mat == Material.ANCIENT_DEBRIS)  return 55;
        if (mat == Material.DEEPSLATE)  return 15;
        if (mat == Material.STONE || mat == Material.TUFF)  return 4;
        return 1;
    }
    protected int smeltingLoot(Material mat) {
        if (mat == Material.JIGSAW)  return 5_000_000;
        if (mat == Material.STRUCTURE_BLOCK)  return 500_000;
        if (mat == Material.BEDROCK)  return 100_000;
        if (mat == Material.ANCIENT_DEBRIS)  return 2600;
        return defaultLoot(mat);
    }
    protected int defaultLoot(Material mat) {
        if (mat == Material.DEEPSLATE_COAL_ORE)  return 3300;
        if (mat == Material.DEEPSLATE_EMERALD_ORE)  return 1800;
        if (mat == Material.DIAMOND_ORE || mat == Material.DEEPSLATE_DIAMOND_ORE)  return 1500;
        if (mat == Material.EMERALD_ORE)  return 1000;
        if (mat == Material.DEEPSLATE_LAPIS_ORE || mat == Material.DEEPSLATE_COPPER_ORE || mat == Material.DEEPSLATE_IRON_ORE || mat == Material.DEEPSLATE_GOLD_ORE)  return 810;
        if (mat == Material.IRON_ORE || mat == Material.GOLD_ORE || mat == Material.DEEPSLATE_REDSTONE_ORE || mat == Material.REDSTONE_ORE || mat == Material.LAPIS_ORE)  return 600;
        if (mat == Material.COAL_ORE || mat == Material.COPPER_ORE || mat == Material.NETHER_QUARTZ_ORE || mat == Material.NETHER_GOLD_ORE)  return 220;
        if (mat == Material.OBSIDIAN || mat == Material.ANCIENT_DEBRIS)  return 55;
        if (mat == Material.DEEPSLATE)  return 15;
        if (mat == Material.STONE || mat == Material.TUFF)  return 4;
        return 1;
    }

    /*
     * Applies to coal, diamond, emerald, iron, copper, gold
     * NOT redstone
     */
    protected int doFortuneOre(int amount, int hasFortune) {
        if (hasFortune == 0) {
            return amount;
        }
        float f = rnd.nextFloat();
        int multiplier;
        if (hasFortune == 4) {
            multiplier = Math.max((int) (f * 6), 1);
        } else if (hasFortune == 3) {
            multiplier = Math.max((int) (f * 5), 1);
        } else {
            throw new IllegalStateException("fortune has to be 3 or 4");
        }
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
        if (level >= 16) {
            return 2_000_000 + (1 << Math.min(level-16, 30));
        }
        return new int[]{
                // (n choose 4)/2 + (n+1 choose 3) + (n+1 choose 2) + 1
                2_000,
                5_000, // 1
                11_000,
                21_000,
                38_000,
                64_000, // 5
                102_000,
                156_000,
                229_000,
                326_000,
                452_000, // 10
                612_000,
                813_000,
                1_061_000,
                1_363_000,
                1_727_000, // 15
        }[level];
    }

    /*
     * Set EFFICIENCY and UNBREAKING to the proper levels depending on the tier of the pickaxe
     * Remove FORTUNE and SILK TOUCH completely
     * Don't touch SHARPNESS because it can't be messed with
     */
    public void fixEnchants(int tier, ItemMeta im, Player player) {
        if (tier >= 7)  return;
        im.removeEnchant(Enchantment.EFFICIENCY);
        im.removeEnchant(Enchantment.UNBREAKING);
        im.removeEnchant(Enchantment.FORTUNE);
        im.removeEnchant(Enchantment.SILK_TOUCH);
        switch (tier) {
            case 0:
                break;
            case 1:
                im.addEnchant(Enchantment.EFFICIENCY, 1, false);
                break;
            case 2:
                im.addEnchant(Enchantment.EFFICIENCY, 2, false);
                break;
            case 3:
                im.addEnchant(Enchantment.EFFICIENCY, 3, false);
                im.addEnchant(Enchantment.UNBREAKING, 1, false);
                break;
            case 4:
                im.addEnchant(Enchantment.EFFICIENCY, 4, false);
                im.addEnchant(Enchantment.UNBREAKING, 1, false);
                im.addEnchant(Enchantment.SHARPNESS, 1, false);
                break;
            case 5:
                im.addEnchant(Enchantment.EFFICIENCY, 5, false);
                im.addEnchant(Enchantment.UNBREAKING, 2, false);
                im.addEnchant(Enchantment.SHARPNESS, 2, false);
                break;
            case 6:
                im.addEnchant(Enchantment.EFFICIENCY, 5, false);
                im.addEnchant(Enchantment.UNBREAKING, 3, false);
                im.addEnchant(Enchantment.SHARPNESS, 3, false);
                break;
        }
    }

}
