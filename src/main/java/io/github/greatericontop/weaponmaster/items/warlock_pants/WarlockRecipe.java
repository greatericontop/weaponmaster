package io.github.greatericontop.weaponmaster.items.warlock_pants;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class WarlockRecipe {

    private final Util util;
    public WarlockRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack warlock = util.generateMeta(util.WARLOCK_PANTS_LORE, util.WARLOCK_PANTS_NAME, Material.NETHERITE_LEGGINGS);
        warlock.addUnsafeEnchantment(Enchantment.DURABILITY, 4);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("warlock"), warlock);
        recipe.shape("nWn",
                     "bLb",
                     "mSE");
        recipe.setIngredient('L', Material.NETHERITE_LEGGINGS);
        recipe.setIngredient('b', Material.BLAZE_ROD);
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('n', Material.NETHERITE_INGOT);
        recipe.setIngredient('m', Material.MAGMA_CREAM);
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND_SWORD, 1)));
        recipe.setIngredient('E', Material.END_CRYSTAL);
        Bukkit.getServer().addRecipe(recipe);
    }

}
