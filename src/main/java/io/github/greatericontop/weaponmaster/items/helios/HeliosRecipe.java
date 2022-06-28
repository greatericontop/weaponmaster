package io.github.greatericontop.weaponmaster.items.helios;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class HeliosRecipe {

    private final Util util;
    public HeliosRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack helios = util.generateMeta(util.HELIOS_LORE, util.HELIOS_NAME, Material.IRON_SWORD);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("helios"), helios);
        recipe.shape("tNt",
                     "tnt",
                     "tIt");
        recipe.setIngredient('I', Material.IRON_SWORD);
        recipe.setIngredient('n', Material.NETHERITE_BLOCK);
        recipe.setIngredient('N', Material.NETHER_STAR);
        recipe.setIngredient('t', Material.RESPAWN_ANCHOR);
        Bukkit.getServer().addRecipe(recipe);
    }

}
