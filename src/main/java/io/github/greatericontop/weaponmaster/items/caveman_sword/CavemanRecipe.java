package io.github.greatericontop.weaponmaster.items.caveman_sword;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class CavemanRecipe {

    private final Util util;
    public CavemanRecipe() {
        util = new Util(null);
    }
    
    public void regRecipe() {
        ItemStack caveman = util.generateMeta(util.CAVEMAN_SWORD_LORE, util.CAVEMAN_SWORD_NAME, Material.STONE_SWORD);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("caveman_sword"), caveman);
        recipe.shape(" n ",
                     "SSS",
                     "drd");
        recipe.setIngredient('n', Material.NETHERITE_INGOT);
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.NETHERITE_SWORD, 1)));
        recipe.setIngredient('r', Material.BLAZE_ROD);
        recipe.setIngredient('d', Material.DEEPSLATE);
        Bukkit.getServer().addRecipe(recipe);
    }
}
