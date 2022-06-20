package io.github.greatericontop.weaponmaster.CopperSword;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.Util.Util;

public class CopperSwordRecipe {

    private final Util util;
    public CopperSwordRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack copperSword = util.generateMeta(util.COPPER_SWORD_LORE, util.COPPER_SWORD_NAME, Material.GOLDEN_SWORD);
        copperSword.addUnsafeEnchantment(Enchantment.DURABILITY, 9);
        copperSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
        ShapedRecipe copperRec = new ShapedRecipe(NamespacedKey.minecraft("copper_sword"), copperSword);
        copperRec.shape("ccc",
                        "cic",
                        "ccc");
        copperRec.setIngredient('c', Material.COPPER_BLOCK);
        copperRec.setIngredient('i', Material.IRON_SWORD);
        Bukkit.getServer().addRecipe(copperRec);
    }
}
