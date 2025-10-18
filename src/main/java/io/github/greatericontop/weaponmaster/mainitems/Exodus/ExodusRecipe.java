package io.github.greatericontop.weaponmaster.mainitems.Exodus;

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

public class ExodusRecipe {

    private final Util util;
    public ExodusRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack exodus = util.generateMeta(util.EXODUS_LORE, util.EXODUS_NAME, Material.DIAMOND_HELMET);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey("weaponmaster", "exodus"), exodus);
        recipe.shape("ddd",
                     "dGd",
                     "cEc");
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        recipe.setIngredient('G', Material.ENCHANTED_GOLDEN_APPLE);
        recipe.setIngredient('E', Material.END_CRYSTAL);
        recipe.setIngredient('c', Material.GOLDEN_CARROT);
        Bukkit.getServer().addRecipe(recipe);
    }

}
