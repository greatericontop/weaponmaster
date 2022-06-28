package io.github.greatericontop.weaponmaster.items.valkyrie_axe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class ValkyrieRecipe {
    private final Util util;
    public ValkyrieRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack valkyrie = ValkyrieCommand.giveValkyrie(util);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("valkyrie_axe"), valkyrie);
        recipe.shape("WnW",
                     "WxW",
                     " x ");
        recipe.setIngredient('W', Material.IRON_BLOCK);
        recipe.setIngredient('n', Material.NETHERITE_INGOT);
        recipe.setIngredient('x', Material.STICK);
        Bukkit.getServer().addRecipe(recipe);
    }
}
