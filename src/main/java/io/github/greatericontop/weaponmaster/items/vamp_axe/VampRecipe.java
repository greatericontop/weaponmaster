package io.github.greatericontop.weaponmaster.items.vamp_axe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class VampRecipe {

    private final Util util;
    public VampRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack vampaxe = util.generateMeta(util.VAMP_AXE_LORE, util.VAMP_AXE_NAME, Material.NETHERITE_AXE);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("vamp_axe"), vampaxe);
        recipe.shape(" Wx",
                     " Rx",
                     "R x");
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('R', Material.REDSTONE_BLOCK);
        recipe.setIngredient('x', Material.NETHERITE_AXE);
        Bukkit.getServer().addRecipe(recipe);
    }

}
