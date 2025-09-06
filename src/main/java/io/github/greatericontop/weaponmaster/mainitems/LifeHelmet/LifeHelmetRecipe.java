package io.github.greatericontop.weaponmaster.mainitems.LifeHelmet;

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

public class LifeHelmetRecipe {

    private final Util util;
    private final MinorItems customItems;
    public LifeHelmetRecipe() {
        this.util = new Util(null);
        this.customItems = new MinorItems();
    }
    
    public void regRecipe() {
        ItemStack lifeHelmet = util.generateMeta(util.LIFE_HELMET_LORE, util.LIFE_HELMET_NAME, Material.DIAMOND_HELMET);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("life_helmet"), lifeHelmet);
        recipe.shape("ddd",
                     "dCd",
                     "TTT");
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
        recipe.setIngredient('C', new RecipeChoice.ExactChoice(customItems.generateLifeCoreItemStack()));
        Bukkit.getServer().addRecipe(recipe);
    }
}
