package io.github.greatericontop.weaponmaster.DeathScythe;

/*
    Copyright (C) 2021 greateric.
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import org.bukkit.inventory.ItemStack;
import java.util.List;
import org.bukkit.inventory.Recipe;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.NamespacedKey;
import org.bukkit.Material;
import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;

public class ScytheRecipe
{
    public final Util util;
    
    public ScytheRecipe() {
        util = new Util(null);
    }
    
    public void regRecipe() {
        ItemStack scythe = util.generateMeta(util.DEATH_SCYTHE_LORE, util.DEATH_SCYTHE_NAME, Material.IRON_HOE);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("death_scythe"), scythe);
        recipe.shape("YX ",
                     "INl",
                     "mNm");
        recipe.setIngredient('X', Material.NETHERITE_BLOCK);
        recipe.setIngredient('Y', Material.NETHERITE_INGOT);
        recipe.setIngredient('N', Material.BLAZE_ROD);
        recipe.setIngredient('I', Material.NETHERITE_SWORD);
        recipe.setIngredient('l', Material.NETHERITE_AXE);
        recipe.setIngredient('m', Material.REDSTONE_BLOCK);
        Bukkit.getServer().addRecipe(recipe);
    }
}
