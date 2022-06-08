package io.github.greatericontop.weaponmaster.MinerBlessing;

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

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class MinerRecipe {

    private final Util util;
    public MinerRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack miner = util.generateMeta(util.MINERS_BLESSING_LORE, util.MINERS_BLESSING_NAME, Material.NETHERITE_PICKAXE);
        ShapedRecipe minerRec = new ShapedRecipe(NamespacedKey.minecraft("miner"), miner);
        minerRec.shape("BNB",
                       "iDi",
                       "HrH");
        minerRec.setIngredient('N', Material.NETHERITE_PICKAXE);
        minerRec.setIngredient('B', Material.BLAST_FURNACE);
        minerRec.setIngredient('i', Material.NETHERITE_INGOT);
        minerRec.setIngredient('r', Material.BLAZE_ROD);
        minerRec.setIngredient('D', Material.DIAMOND_BLOCK);
        minerRec.setIngredient('H', Material.HEART_OF_THE_SEA);
        Bukkit.getServer().addRecipe(minerRec);
    }
}
