package io.github.greatericontop.weaponmaster.NinjaBow;

import io.github.greatericontop.weaponmaster.other_crafts.CustomItems;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class NinjaRecipe {
    private final Util util;
    private final CustomItems customitems;

    public NinjaRecipe() {
        util = new Util(null);
        customitems = new CustomItems();
    }

    public void regRecipe() {
        ItemStack ninjabow = util.generateMeta(util.NINJA_BOW_LORE, util.NINJA_BOW_NAME, Material.BOW);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("ninja_bow"), ninjabow);
        recipe.shape(" bS",
                     "brS",
                     " bS");
        recipe.setIngredient('b', new RecipeChoice.ExactChoice(new ItemStack(Material.BOW, 1)));
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(customitems.generateSilkyStringItemStack()));
        recipe.setIngredient('r', Material.BLAZE_ROD);
        Bukkit.getServer().addRecipe(recipe);
    }
}
