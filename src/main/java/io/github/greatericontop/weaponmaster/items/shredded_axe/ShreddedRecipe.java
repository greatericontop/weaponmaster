package io.github.greatericontop.weaponmaster.items.shredded_axe;

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

import io.github.greatericontop.weaponmaster.util.Util;

public class ShreddedRecipe {

    private final Util util;
    public ShreddedRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack shredded = util.generateMeta(util.SHREDDED_AXE_LORE, util.SHREDDED_AXE_NAME, Material.DIAMOND_AXE);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("shredded_axe"), shredded);
        recipe.shape(" ZZ",
                     "dbZ",
                     " b ");
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        recipe.setIngredient('b', Material.BONE);
        recipe.setIngredient('Z', Material.ZOMBIE_HEAD);
        Bukkit.getServer().addRecipe(recipe);
    }

}
