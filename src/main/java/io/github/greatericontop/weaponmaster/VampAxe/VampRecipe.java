package io.github.greatericontop.weaponmaster.VampAxe;

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

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.Util.Util;

public class VampRecipe {

    private final Util util;
    public VampRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack vampaxe = util.generateMeta(util.VAMP_AXE_LORE, util.VAMP_AXE_NAME, Material.NETHERITE_AXE);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("vamp_axe"), vampaxe);
        recipe.shape(" Wx",
                     " Rx",
                     "R x");
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);
        recipe.setIngredient('x', Material.NETHERITE_AXE);
        Bukkit.getServer().addRecipe(recipe);
    }

}
