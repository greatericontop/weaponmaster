package io.github.greatericontop.weaponmaster.items.dragon_armor;

import io.github.greatericontop.weaponmaster.other_crafts.CustomItems;
import io.github.greatericontop.weaponmaster.util.Util;

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
