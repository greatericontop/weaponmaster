package io.github.greatericontop.weaponmaster.mainitems.EndSword;

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

import io.github.greatericontop.weaponmaster.minorcrafts.CustomItems;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class EndSwordRecipe {

    private final Util util;
    private final CustomItems customitems;
    public EndSwordRecipe() {
        util = new Util(null);
        customitems = new CustomItems();
    }

    public void regRecipe() {
        ItemStack endSword = util.generateMeta(util.END_SWORD_LORE, util.END_SWORD_NAME, Material.IRON_SWORD);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("end_sword"), endSword);
        recipe.shape("PxP",
                     "xSx",
                     "PxP");
        recipe.setIngredient('x', new RecipeChoice.ExactChoice(customitems.generateEndArtifactItemStack()));
        recipe.setIngredient('S', Material.IRON_SWORD);
        recipe.setIngredient('P', Material.ENDER_PEARL);
        Bukkit.getServer().addRecipe(recipe);
    }

}
