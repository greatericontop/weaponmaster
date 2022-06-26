package io.github.greatericontop.weaponmaster.items.caveman_sword;

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
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class CavemanRecipe {

    private final Util util;
    public CavemanRecipe() {
        util = new Util(null);
    }
    
    public void regRecipe() {
        ItemStack caveman = util.generateMeta(util.CAVEMAN_SWORD_LORE, util.CAVEMAN_SWORD_NAME, Material.STONE_SWORD);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("caveman_sword"), caveman);
        recipe.shape(" n ",
                     "SSS",
                     "drd");
        recipe.setIngredient('n', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.NETHERITE_SWORD, 1)));
        recipe.setIngredient('r', Material.BLAZE_ROD);
        recipe.setIngredient('d', Material.DEEPSLATE);
        Bukkit.getServer().addRecipe(recipe);
    }
}
