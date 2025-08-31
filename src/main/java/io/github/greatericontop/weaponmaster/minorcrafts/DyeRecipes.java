package io.github.greatericontop.weaponmaster.minorcrafts;

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

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class DyeRecipes {
    private MinorItems minorItems;

    public DyeRecipes() {
        minorItems = new MinorItems();
    }

    public void regRecipes() {
        ItemStack diamond = minorItems.generateDiamondDye();
        ShapedRecipe diamondRec = new ShapedRecipe(NamespacedKey.minecraft("dye_diamond"), diamond);
        diamondRec.shape("xxx",
                         "xWx",
                         "xxx");
        diamondRec.setIngredient('W', new RecipeChoice.ExactChoice(minorItems.generateWitherDye()));
        diamondRec.setIngredient('x', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(diamondRec);

        ItemStack emerald = minorItems.generateEmeraldDye();
        ShapedRecipe emeraldRec = new ShapedRecipe(NamespacedKey.minecraft("dye_emerald"), emerald);
        emeraldRec.shape("xxx",
                         "xWx",
                         "xxx");
        emeraldRec.setIngredient('W', new RecipeChoice.ExactChoice(minorItems.generateWitherDye()));
        emeraldRec.setIngredient('x', Material.EMERALD_BLOCK);
        Bukkit.getServer().addRecipe(emeraldRec);

        ItemStack crystal = minorItems.generateCrystalDye();
        ShapedRecipe crystalRec = new ShapedRecipe(NamespacedKey.minecraft("dye_crystal"), crystal);
        crystalRec.shape("xxx",
                         "xWx",
                         "xxx");
        crystalRec.setIngredient('W', new RecipeChoice.ExactChoice(minorItems.generateWitherDye()));
        crystalRec.setIngredient('x', Material.AMETHYST_CLUSTER);
        Bukkit.getServer().addRecipe(crystalRec);

        ItemStack lapis = minorItems.generateLapisDye();
        ShapedRecipe lapisRec = new ShapedRecipe(NamespacedKey.minecraft("dye_lapis"), lapis);
        lapisRec.shape("xxx",
                       "xWx",
                       "xxx");
        lapisRec.setIngredient('W', new RecipeChoice.ExactChoice(minorItems.generateWitherDye()));
        lapisRec.setIngredient('x', Material.LAPIS_BLOCK);
        Bukkit.getServer().addRecipe(lapisRec);

        ItemStack darkDiamond = minorItems.generateDarkDiamondDye();
        ShapedRecipe darkDiamondRec = new ShapedRecipe(NamespacedKey.minecraft("dye_dark_diamond"), darkDiamond);
        darkDiamondRec.shape(" x ",
                             "xWx",
                             " x ");
        darkDiamondRec.setIngredient('W', new RecipeChoice.ExactChoice(minorItems.generateWitherDye()));
        darkDiamondRec.setIngredient('x', new RecipeChoice.ExactChoice(minorItems.generateDiamondDye()));
        Bukkit.getServer().addRecipe(darkDiamondRec);

        ItemStack gold = minorItems.generateGoldDye();
        ShapedRecipe goldRec = new ShapedRecipe(NamespacedKey.minecraft("dye_gold"), gold);
        goldRec.shape(" x ",
                      "xWx",
                      " x ");
        goldRec.setIngredient('W', new RecipeChoice.ExactChoice(minorItems.generateWitherDye()));
        goldRec.setIngredient('x', Material.ENCHANTED_GOLDEN_APPLE);
        Bukkit.getServer().addRecipe(goldRec);

        ItemStack blood = minorItems.generateBloodDye();
        ShapedRecipe bloodRec = new ShapedRecipe(NamespacedKey.minecraft("dye_blood"), blood);
        bloodRec.shape(" x ",
                       "xWx",
                       " x ");
        bloodRec.setIngredient('W', new RecipeChoice.ExactChoice(minorItems.generateWitherDye()));
        bloodRec.setIngredient('x', Material.ZOMBIE_HEAD);
        Bukkit.getServer().addRecipe(bloodRec);

        ItemStack leviathan = minorItems.generateLeviathanDye();
        ShapedRecipe leviathanRec = new ShapedRecipe(NamespacedKey.minecraft("dye_leviathan"), leviathan);
        leviathanRec.shape("xLx",
                           "xWx",
                           "xxx");
        leviathanRec.setIngredient('W', new RecipeChoice.ExactChoice(minorItems.generateWitherDye()));
        leviathanRec.setIngredient('L', new RecipeChoice.ExactChoice(minorItems.generateLeviathanHeartItemStack()));
        leviathanRec.setIngredient('x', Material.WET_SPONGE);

//        ItemStack expert = minorItems.generateExpertDye();
//        ShapedRecipe expertRec = new ShapedRecipe(NamespacedKey.minecraft("dye_expert"), expert);
//        expertRec.shape(" E ",
//                        "EWE",
//                        " E ");
//        expertRec.setIngredient('W', new RecipeChoice.ExactChoice(minorItems.generateWitherDye()));
//        expertRec.setIngredient('E', new RecipeChoice.ExactChoice(minorItems.generateExpertSeal()));
//        Bukkit.getServer().addRecipe(expertRec);

        ItemStack dragon = minorItems.generateDragonDye();
        ShapedRecipe dragonRec = new ShapedRecipe(NamespacedKey.minecraft("dye_dragon"), dragon);
        dragonRec.shape(" hh",
                        "xWh",
                        "xx ");
        dragonRec.setIngredient('W', new RecipeChoice.ExactChoice(minorItems.generateWitherDye()));
        dragonRec.setIngredient('h', Material.DRAGON_HEAD);
        dragonRec.setIngredient('x', new RecipeChoice.ExactChoice(minorItems.generateDragonWingItemStack()));
        Bukkit.getServer().addRecipe(dragonRec);
    }
}
