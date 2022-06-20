package io.github.greatericontop.weaponmaster.NinjaBow;

import io.github.greatericontop.weaponmaster.OtherCrafts.CustomItems;
import io.github.greatericontop.weaponmaster.Util.Util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

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
        recipe.shape("AbS",
                     "brS",
                     "AbS");
        recipe.setIngredient('b', Material.BOW);
        recipe.setIngredient('S', new RecipeChoice.ExactChoice(customitems.generateSilkyStringItemStack()));
        recipe.setIngredient('r', Material.BLAZE_ROD);
        recipe.setIngredient('A', Material.AMETHYST_SHARD);
        Bukkit.getServer().addRecipe(recipe);
    }
}
