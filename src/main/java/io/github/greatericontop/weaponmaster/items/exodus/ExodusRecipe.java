package io.github.greatericontop.weaponmaster.items.exodus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class ExodusRecipe {

    private final Util util;
    public ExodusRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack exodus = util.generateMeta(util.EXODUS_LORE, util.EXODUS_NAME, Material.DIAMOND_HELMET);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("exodus"), exodus);
        recipe.shape("ddd",
                     "dGd",
                     "cEc");
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        recipe.setIngredient('G', Material.ENCHANTED_GOLDEN_APPLE);
        recipe.setIngredient('E', Material.END_CRYSTAL);
        recipe.setIngredient('c', Material.GOLDEN_CARROT);
        Bukkit.getServer().addRecipe(recipe);
    }

}
