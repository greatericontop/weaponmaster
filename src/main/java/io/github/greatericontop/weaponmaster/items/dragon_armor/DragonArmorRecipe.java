package io.github.greatericontop.weaponmaster.items.dragon_armor;

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

import io.github.greatericontop.weaponmaster.other_crafts.CustomItems;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class DragonArmorRecipe {

    private final Util util;
    private final CustomItems customItems;
    public DragonArmorRecipe() {
        util = new Util(null);
        customItems = new CustomItems();
    }

    public void regHelmet() {
        ItemStack dragonArmor = util.generateMeta(util.DRAGON_ARMOR_LORE, util.DRAGON_ARMOR_NAME, Material.NETHERITE_HELMET);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("dragon_helmet"), dragonArmor);
        recipe.shape("www",
                     "wHw");
        recipe.setIngredient('w', new RecipeChoice.ExactChoice(customItems.generateDragonWingItemStack()));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(customItems.generateDragonHornItemStack()));
        Bukkit.getServer().addRecipe(recipe);
    }

    public void regChestplate() {
        ItemStack dragonArmor = util.generateMeta(util.DRAGON_ARMOR_LORE, util.DRAGON_ARMOR_NAME, Material.NETHERITE_CHESTPLATE);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("dragon_chestplate"), dragonArmor);
        recipe.shape("wHw",
                     "www",
                     "www");
        recipe.setIngredient('w', new RecipeChoice.ExactChoice(customItems.generateDragonWingItemStack()));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(customItems.generateDragonHornItemStack()));
        Bukkit.getServer().addRecipe(recipe);
    }

    public void regLeggings() {
        ItemStack dragonArmor = util.generateMeta(util.DRAGON_ARMOR_LORE, util.DRAGON_ARMOR_NAME, Material.NETHERITE_LEGGINGS);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("dragon_leggings"), dragonArmor);
        recipe.shape("www",
                     "wHw",
                     "w w");
        recipe.setIngredient('w', new RecipeChoice.ExactChoice(customItems.generateDragonWingItemStack()));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(customItems.generateDragonHornItemStack()));
        Bukkit.getServer().addRecipe(recipe);
    }

    public void regBoots() {
        ItemStack dragonArmor = util.generateMeta(util.DRAGON_ARMOR_LORE, util.DRAGON_ARMOR_NAME, Material.NETHERITE_BOOTS);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("dragon_boots"), dragonArmor);
        recipe.shape("w w",
                     "wHw");
        recipe.setIngredient('w', new RecipeChoice.ExactChoice(customItems.generateDragonWingItemStack()));
        recipe.setIngredient('H', new RecipeChoice.ExactChoice(customItems.generateDragonHornItemStack()));
        Bukkit.getServer().addRecipe(recipe);
    }

    public void registerAll() {
        regHelmet();
        regChestplate();
        regLeggings();
        regBoots();
    }

}
