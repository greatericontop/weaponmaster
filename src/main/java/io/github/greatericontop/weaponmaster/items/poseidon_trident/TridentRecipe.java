package io.github.greatericontop.weaponmaster.items.poseidon_trident;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class TridentRecipe {
    private final Util util;
    public TridentRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack trident = util.generateMeta(util.POSEIDON_TRIDENT_LORE, util.POSEIDON_TRIDENT_NAME, Material.TRIDENT);
        trident.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("poseidon_trident"), trident);
        recipe.shape(" HT",
                     "bTH",
                     "Tb ");
        recipe.setIngredient('T', Material.TRIDENT);
        recipe.setIngredient('H', Material.HEART_OF_THE_SEA);
        recipe.setIngredient('b', Material.BLAZE_ROD);
        Bukkit.getServer().addRecipe(recipe);
    }
}
