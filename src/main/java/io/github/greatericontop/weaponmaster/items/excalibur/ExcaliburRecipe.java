package io.github.greatericontop.weaponmaster.items.excalibur;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class ExcaliburRecipe {

    private final Util util;
    public ExcaliburRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack excalibur = util.generateMeta(util.EXCALIBUR_LORE, util.EXCALIBUR_NAME, Material.DIAMOND_SWORD);
        excalibur.addEnchantment(Enchantment.DURABILITY, 2);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("excalibur"), excalibur);
        recipe.shape("dNt",
                     "zNs",
                     "dCt");
        recipe.setIngredient('N', Material.NETHER_STAR);
        recipe.setIngredient('C', Material.END_CRYSTAL);
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        recipe.setIngredient('t', Material.TNT);
        recipe.setIngredient('z', Material.ZOMBIE_HEAD);
        recipe.setIngredient('s', Material.SKELETON_SKULL);

        Bukkit.getServer().addRecipe(recipe);
    }

}
