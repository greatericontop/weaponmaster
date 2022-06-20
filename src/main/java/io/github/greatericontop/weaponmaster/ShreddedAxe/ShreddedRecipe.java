package io.github.greatericontop.weaponmaster.ShreddedAxe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.Util.Util;

public class ShreddedRecipe {

    private final Util util;
    public ShreddedRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack shredded = util.generateMeta(util.SHREDDED_AXE_LORE, util.SHREDDED_AXE_NAME, Material.DIAMOND_AXE);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("shredded_axe"), shredded);
        recipe.shape(" ZZ",
                     "dbZ",
                     " b ");
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        recipe.setIngredient('b', Material.BONE);
        recipe.setIngredient('Z', Material.ZOMBIE_HEAD);
        Bukkit.getServer().addRecipe(recipe);
    }

}
