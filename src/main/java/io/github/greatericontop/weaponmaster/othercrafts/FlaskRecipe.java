package io.github.greatericontop.weaponmaster.othercrafts;

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

public class FlaskRecipe {

    private CustomItems customItems;

    public FlaskRecipe() {
        customItems = new CustomItems();
    }

    public void regRecipe() {
        ItemStack flask = customItems.generateFlaskIchorItemStack();
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("flask_ichor"), flask);
        recipe.shape(" S ",
                     " g ",
                     "IbW");
        recipe.setIngredient('S', Material.FERMENTED_SPIDER_EYE);
        recipe.setIngredient('g', Material.GLASS_BOTTLE);
        recipe.setIngredient('b', Material.BLAZE_ROD);
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        Bukkit.getServer().addRecipe(recipe);
    }

}
