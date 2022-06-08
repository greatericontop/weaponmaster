package io.github.greatericontop.weaponmaster.WarlockPants;

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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class WarlockRecipe {

    private final Util util;
    public WarlockRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack warlock = util.generateMeta(util.WARLOCK_PANTS_LORE, util.WARLOCK_PANTS_NAME, Material.NETHERITE_LEGGINGS);
        warlock.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("warlock"), warlock);
        recipe.shape("nWn",
                     "bLb",
                     "mSE");
        recipe.setIngredient('L', Material.NETHERITE_LEGGINGS);
        recipe.setIngredient('b', Material.BLAZE_ROD);
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('n', Material.NETHERITE_INGOT);
        recipe.setIngredient('m', Material.MAGMA_CREAM);
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND_SWORD, 1)));
        recipe.setIngredient('E', Material.END_CRYSTAL);
        Bukkit.getServer().addRecipe(recipe);
    }

}
