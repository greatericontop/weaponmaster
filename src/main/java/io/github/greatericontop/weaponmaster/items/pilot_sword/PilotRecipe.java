package io.github.greatericontop.weaponmaster.items.pilot_sword;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.greatericontop.weaponmaster.util.Util;

public class PilotRecipe {
    private final Util util;
    public PilotRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack pilotsword = util.generateMeta(util.PILOT_SWORD_LORE, util.PILOT_SWORD_NAME, Material.NETHERITE_SWORD);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("pilot_sword"), pilotsword);
        recipe.shape("WXW",
                     "YTY",
                     "WXW");
        recipe.setIngredient('T', new RecipeChoice.ExactChoice(new ItemStack(Material.NETHERITE_SWORD, 1)));
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta im = book.getItemMeta();
        im.addEnchant(Enchantment.DIG_SPEED, 5, true);
        book.setItemMeta(im);
        recipe.setIngredient('X', new RecipeChoice.ExactChoice(book));
        recipe.setIngredient('Y', Material.NETHER_STAR);
        recipe.setIngredient('W', Material.WITHER_SKELETON_SKULL);
        Bukkit.getServer().addRecipe(recipe);
    }
}
