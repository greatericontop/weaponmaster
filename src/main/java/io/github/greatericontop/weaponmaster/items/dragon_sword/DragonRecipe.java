package io.github.greatericontop.weaponmaster.items.dragon_sword;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class DragonRecipe {

    private final Util util;
    public DragonRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack drag = util.generateMeta(util.DRAGON_SWORD_LORE, util.DRAGON_SWORD_NAME, Material.NETHERITE_SWORD);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("dragon_sword"), drag);
        recipe.shape("bDb",
                     "bDb",
                     "bSb");
        recipe.setIngredient('S', Material.NETHERITE_SWORD);
        recipe.setIngredient('D', Material.DRAGON_HEAD);
        recipe.setIngredient('b', Material.BLAZE_ROD);
        Bukkit.getServer().addRecipe(recipe);
    }

}
