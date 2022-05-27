package io.github.greatericontop.weaponmaster.PoseidonTrident;

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class TridentRecipe {
    private final Util util;
    public TridentRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack trident = util.generateMeta(util.POSEIDON_TRIDENT_LORE, util.POSEIDON_TRIDENT_NAME, Material.TRIDENT);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("poseidon_trident"), trident);
        recipe.shape(" HTH",
                " T ",
                "T  ");
        recipe.setIngredient('T', Material.TRIDENT);
        recipe.setIngredient('H', Material.HEART_OF_THE_SEA);
        recipe.setIngredient('b', Material.BLAZE_ROD);
        Bukkit.getServer().addRecipe(recipe);
    }
}
