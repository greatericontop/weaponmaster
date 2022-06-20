package io.github.greatericontop.weaponmaster.ValkyrieAxe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.Util.Util;

public class ValkyrieRecipe {
    private final Util util;
    public ValkyrieRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack valkyrieaxe = util.generateMeta(util.VALKYRIE_AXE_LORE, util.VALKYRIE_AXE_NAME, Material.IRON_AXE);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("valkyrie_axe"), valkyrieaxe);
        recipe.shape("WnW",
                     "WxW",
                     " x ");
        recipe.setIngredient('W', Material.IRON_BLOCK);
        recipe.setIngredient('n', Material.NETHERITE_INGOT);
        recipe.setIngredient('x', Material.STICK);
        Bukkit.getServer().addRecipe(recipe);
    }
}
