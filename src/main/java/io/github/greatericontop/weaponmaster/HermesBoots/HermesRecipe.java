package io.github.greatericontop.weaponmaster.HermesBoots;

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

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class HermesRecipe
{
    public final Util util;
    public HermesRecipe() {
        util = new Util(null);
    }
    
    public void regRecipe() {
        ItemStack hermesboots = util.generateMeta(util.HERMES_BOOTS_LORE, util.HERMES_BOOTS_NAME, Material.NETHERITE_BOOTS);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("hermes_boots"), hermesboots);
        recipe.shape("TTT",
                     "MXM",
                     "NNN");
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
        ItemStack speedpotion = new ItemStack(Material.POTION, 1);
        PotionMeta speedmeta = (PotionMeta) speedpotion.getItemMeta();
        speedmeta.setBasePotionData(new PotionData(PotionType.SPEED, false, false));
        speedpotion.setItemMeta(speedmeta);
        recipe.setIngredient('M', (RecipeChoice)new RecipeChoice.ExactChoice(speedpotion));
        recipe.setIngredient('X', Material.NETHERITE_BOOTS);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        Bukkit.getServer().addRecipe(recipe);
    }
}
