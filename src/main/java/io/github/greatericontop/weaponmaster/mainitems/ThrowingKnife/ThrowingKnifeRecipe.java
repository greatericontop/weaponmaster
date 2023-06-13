package io.github.greatericontop.weaponmaster.mainitems.ThrowingKnife;

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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class ThrowingKnifeRecipe {

    private final Util util;
    public ThrowingKnifeRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack ThrowingKnife = util.generateMeta(util.THROWING_KNIFE_LORE, util.THROWING_KNIFE_NAME, Material.IRON_SWORD);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("throwing_knife"), ThrowingKnife);
        recipe.shape("  I",
                     " S ",
                     "P  ");
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('S', Material.IRON_SWORD);
        recipe.setIngredient('P', Material.PISTON);
        Bukkit.getServer().addRecipe(recipe);
    }

}
