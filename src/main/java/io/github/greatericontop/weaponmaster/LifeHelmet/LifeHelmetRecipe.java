package io.github.greatericontop.weaponmaster.LifeHelmet;

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

public class LifeHelmetRecipe
{
    public final Util util;
    
    public LifeHelmetRecipe() {
        this.util = new Util(null);
    }
    
    public void regRecipe() {
        ItemStack lifehelmet = util.generateMeta(util.LIFE_HELMET_LORE, util.LIFE_HELMET_NAME, Material.DIAMOND_HELMET);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("life_helmet"), lifehelmet);
        recipe.shape("XYX",
                     "XTX",
                     "ZTR");
        recipe.setIngredient('X', Material.DIAMOND_BLOCK);
        recipe.setIngredient('Y', Material.NETHERITE_BLOCK);
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);
        ItemStack healpotion = new ItemStack(Material.POTION, 1);
        PotionMeta healmeta = (PotionMeta) healpotion.getItemMeta();
        healmeta.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL, false, false));
        healpotion.setItemMeta(healmeta);
        recipe.setIngredient('Z', new RecipeChoice.ExactChoice(healpotion));
        ItemStack regenpotion = new ItemStack(Material.POTION, 1);
        PotionMeta regenmeta = (PotionMeta) regenpotion.getItemMeta();
        regenmeta.setBasePotionData(new PotionData(PotionType.REGEN, false, false));
        regenpotion.setItemMeta(regenmeta);
        recipe.setIngredient('R', new RecipeChoice.ExactChoice(regenpotion));
        Bukkit.getServer().addRecipe(recipe);
    }
}
