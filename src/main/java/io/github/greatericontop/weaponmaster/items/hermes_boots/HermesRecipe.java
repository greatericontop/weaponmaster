package io.github.greatericontop.weaponmaster.items.hermes_boots;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class HermesRecipe {

    public final Util util;
    public HermesRecipe() {
        util = new Util(null);
    }
    
    public void regRecipe() {
        ItemStack hermes = HermesCommand.giveHermes(util);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("hermes_boots"), hermes);
        recipe.shape("fBf",
                     "nXn",
                     "btb");
        recipe.setIngredient('B', Material.BEACON);
        recipe.setIngredient('f', Material.FEATHER);
        recipe.setIngredient('X', Material.NETHERITE_BOOTS);
        recipe.setIngredient('b', Material.BLAZE_ROD);
        recipe.setIngredient('t', Material.TNT);
        recipe.setIngredient('n', Material.NETHERITE_INGOT);
        Bukkit.getServer().addRecipe(recipe);
    }
}
