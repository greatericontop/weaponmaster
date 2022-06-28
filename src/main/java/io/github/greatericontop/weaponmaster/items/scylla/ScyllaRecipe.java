package io.github.greatericontop.weaponmaster.items.scylla;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import io.github.greatericontop.weaponmaster.util.Util;

public class ScyllaRecipe {

    private final Util util;
    public ScyllaRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack scylla = util.generateMeta(util.SCYLLA_CHESTPLATE_LORE, util.SCYLLA_CHESTPLATE_NAME, Material.DIAMOND_CHESTPLATE);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("scylla_chestplate"), scylla);
        recipe.shape("sNs",
                     "PCP",
                     "sNs");
        recipe.setIngredient('C', Material.DIAMOND_CHESTPLATE);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        ItemStack resistance = new ItemStack(Material.POTION, 1);
        PotionMeta im = (PotionMeta) resistance.getItemMeta();
        im.setBasePotionData(new PotionData(PotionType.TURTLE_MASTER, false, true));
        resistance.setItemMeta(im);
        recipe.setIngredient('P', new RecipeChoice.ExactChoice(resistance));
        recipe.setIngredient('s', Material.SPONGE);
        Bukkit.getServer().addRecipe(recipe);
    }
}
