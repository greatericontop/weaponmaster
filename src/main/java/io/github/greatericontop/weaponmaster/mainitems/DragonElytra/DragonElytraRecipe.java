package io.github.greatericontop.weaponmaster.mainitems.DragonElytra;

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

public class DragonElytraRecipe {

    private final Util util;
    private final MinorItems customItems;
    public DragonElytraRecipe() {
        util = new Util(null);
        customItems = new MinorItems();
    }

    public void regRecipe() {
        ItemStack elytra = util.generateMeta(util.DRAGON_ELYTRA_LORE, util.DRAGON_ELYTRA_NAME, Material.ELYTRA);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("dragon_elytra"), elytra);
        recipe.shape(" x ",
                     "xEx",
                     " x ");
        recipe.setIngredient('E', Material.ELYTRA);
        recipe.setIngredient('x', new RecipeChoice.ExactChoice(customItems.generateDragonWingItemStack()));
        Bukkit.getServer().addRecipe(recipe);
    }

}
