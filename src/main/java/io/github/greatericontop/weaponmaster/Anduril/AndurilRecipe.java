package io.github.greatericontop.weaponmaster.Anduril;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.Util.Util;

public class AndurilRecipe {

    private final Util util;
    public AndurilRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack anduril = util.generateMeta(util.ANDURIL_LORE, util.ANDURIL_NAME, Material.IRON_SWORD);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("anduril"), anduril);
        recipe.shape("fAf",
                     "fIf",
                     "fSf");
        recipe.setIngredient('S', Material.IRON_SWORD);
        recipe.setIngredient('I', Material.IRON_BLOCK);
        recipe.setIngredient('A', Material.ANVIL);
        recipe.setIngredient('f', Material.FEATHER);
        Bukkit.getServer().addRecipe(recipe);
    }

}
