package io.github.greatericontop.weaponmaster.ArtemisBow;

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

import io.github.greatericontop.weaponmaster.other_crafts.CustomItems;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class ArtemisRecipe {

    private final Util util;
    private final CustomItems customitems;
    public ArtemisRecipe() {
        util = new Util(null);
        customitems = new CustomItems();
    }

    public void regRecipe() {
        ItemStack artemis = util.generateMeta(util.ARTEMIS_BOW_LORE, util.ARTEMIS_BOW_NAME, Material.BOW);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("artemis"), artemis);
        recipe.shape("snB",
                     "NeB",
                     "dnB");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(customitems.generateSilkyStringItemStack()));
        recipe.setIngredient('n', Material.NETHERITE_INGOT);
        recipe.setIngredient('N', Material.NETHERITE_BLOCK);
        recipe.setIngredient('e', Material.ENDER_EYE);
        recipe.setIngredient('s', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(recipe);
    }

}
