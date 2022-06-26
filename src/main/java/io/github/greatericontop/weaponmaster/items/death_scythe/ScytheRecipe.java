package io.github.greatericontop.weaponmaster.items.death_scythe;

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

import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

import org.bukkit.NamespacedKey;
import org.bukkit.Material;

public class ScytheRecipe {

    public final Util util;
    public ScytheRecipe() {
        util = new Util(null);
    }
    
    public void regRecipe() {
        ItemStack scythe = util.generateMeta(util.DEATH_SCYTHE_LORE, util.DEATH_SCYTHE_NAME, Material.IRON_HOE);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("death_scythe"), scythe);
        recipe.shape("NNC",
                     "WbS",
                     "rbr");
        recipe.setIngredient('C', Material.CLOCK);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        recipe.setIngredient('b', Material.BLAZE_ROD);
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('S', Material.NETHER_STAR);
        recipe.setIngredient('r', Material.REDSTONE_BLOCK);
        Bukkit.getServer().addRecipe(recipe);
    }
}
