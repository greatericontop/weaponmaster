package io.github.greatericontop.weaponmaster.MinerBlessing;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.Util.Util;

public class MinerRecipe {

    private final Util util;
    public MinerRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack miner = util.generateMeta(util.MINERS_BLESSING_LORE, util.MINERS_BLESSING_NAME, Material.NETHERITE_PICKAXE);
        ShapedRecipe minerRec = new ShapedRecipe(NamespacedKey.minecraft("miner"), miner);
        minerRec.shape("BNB",
                       "iDi",
                       "HrH");
        minerRec.setIngredient('N', Material.NETHERITE_PICKAXE);
        minerRec.setIngredient('B', Material.BLAST_FURNACE);
        minerRec.setIngredient('i', Material.NETHERITE_INGOT);
        minerRec.setIngredient('r', Material.BLAZE_ROD);
        minerRec.setIngredient('D', Material.DIAMOND_BLOCK);
        minerRec.setIngredient('H', Material.HEART_OF_THE_SEA);
        Bukkit.getServer().addRecipe(minerRec);
    }
}
