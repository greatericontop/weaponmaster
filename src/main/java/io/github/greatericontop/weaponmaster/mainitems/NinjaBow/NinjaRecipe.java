package io.github.greatericontop.weaponmaster.mainitems.NinjaBow;

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

import io.github.greatericontop.weaponmaster.minorcrafts.MinorItems;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class NinjaRecipe {

    private final Util util;
    private final MinorItems customitems;

    public NinjaRecipe() {
        util = new Util(null);
        customitems = new MinorItems();
    }

    public void regRecipe() {
        ItemStack ninjabow = util.generateMeta(util.NINJA_BOW_LORE, util.NINJA_BOW_NAME, Material.BOW);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("ninja_bow"), ninjabow);
        recipe.shape("AbS",
                     "brS",
                     "AbS");
        recipe.setIngredient('b', Material.BOW);
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(customitems.generateSilkyStringItemStack()));
        recipe.setIngredient('r', Material.BLAZE_ROD);
        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        Bukkit.getServer().addRecipe(recipe);
    }
}
