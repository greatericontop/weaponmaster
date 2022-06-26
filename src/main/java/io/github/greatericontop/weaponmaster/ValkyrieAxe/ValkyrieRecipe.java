package io.github.greatericontop.weaponmaster.ValkyrieAxe;

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

public class ValkyrieRecipe {
    private final Util util;
    public ValkyrieRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack valkyrieaxe = util.generateMeta(util.VALKYRIE_AXE_LORE, util.VALKYRIE_AXE_NAME, Material.IRON_AXE);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("valkyrie_axe"), valkyrieaxe);
        recipe.shape("WnW",
                     "WxW",
                     " x ");
        recipe.setIngredient('W', Material.IRON_BLOCK);
        recipe.setIngredient('n', Material.NETHERITE_INGOT);
        recipe.setIngredient('x', Material.STICK);
        Bukkit.getServer().addRecipe(recipe);
    }
}
