package io.github.greatericontop.weaponmaster.mainitems.PlutoniumBlade;

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

public class PlutoniumBladeRecipe {

    private final Util util;
    private final MinorItems minorItems;
    public PlutoniumBladeRecipe() {
        util = new Util(null);
        minorItems = new MinorItems();
    }

    public void regRecipe() {
        ItemStack plutoniumBlade = util.generateMeta(util.PLUTONIUM_BLADE_LORE, util.PLUTONIUM_BLADE_NAME, Material.NETHERITE_SWORD);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey("weaponmaster", "plutonium_blade"), plutoniumBlade);
        recipe.shape("xxN",
                     "xSx",
                     "Nxx");
        recipe.setIngredient('x', new RecipeChoice.ExactChoice(minorItems.generateWeaponsGradePlutoniumItemStack()));
        recipe.setIngredient('S', Material.NETHERITE_SWORD);
        recipe.setIngredient('N', Material.NETHER_STAR);
        Bukkit.getServer().addRecipe(recipe);
    }

}
