package io.github.greatericontop.weaponmaster.Anduril;

/*
 * WeaponMaster Copyright (C) greateric 2021-present.
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

public class AndurilRecipe {

    private final Util util;
    public AndurilRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack anduril = util.generateMeta(util.ANDURIL_LORE, util.ANDURIL_NAME, Material.IRON_SWORD);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("anduril"), anduril);
        recipe.shape("fAf",
                     "fIf",
                     "fSf");
        recipe.setIngredient('S', Material.IRON_SWORD);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('A', Material.ANVIL);
        recipe.setIngredient('f', Material.FEATHER);
        Bukkit.getServer().addRecipe(recipe);
    }

}
