package io.github.greatericontop.weaponmaster.items.artemisBow;

import io.github.greatericontop.weaponmaster.other_crafts.CustomItems;
import io.github.greatericontop.weaponmaster.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class ArtemisRecipe {

    private final Util util;
    private final CustomItems customitems;
    public ArtemisRecipe() {
        util = new Util(null);
        customitems = new CustomItems();
    }

    public void regRecipe() {
        ItemStack artemis = util.generateMeta(util.ARTEMIS_BOW_LORE, util.ARTEMIS_BOW_NAME, Material.BOW);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("artemis"), artemis);
        recipe.shape("snB",
                     "NeB",
                     "dnB");
        recipe.setIngredient('B', new RecipeChoice.ExactChoice(customitems.generateSilkyStringItemStack()));
        recipe.setIngredient('n', Material.NETHERITE_INGOT);
        recipe.setIngredient('N', Material.NETHERITE_BLOCK);
        recipe.setIngredient('e', Material.ENDER_EYE);
        recipe.setIngredient('s', Material.WITHER_SKELETON_SKULL);
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        Bukkit.getServer().addRecipe(recipe);
    }

}
