package io.github.greatericontop.weaponmaster.other_crafts;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CoreStaffRecipe {
    private MinorItemUtil customItems;

    public CoreStaffRecipe() {
        customItems = new MinorItemUtil();
    }

    public void regRecipe() {
        ItemStack core = customItems.generateCoreStaffItemStack();
        ShapedRecipe coreRec = new ShapedRecipe(NamespacedKey.minecraft("core_staff"), core);
        coreRec.shape("non",
                      "SdS",
                      "non");
        coreRec.setIngredient('n', Material.BLAZE_ROD);
        coreRec.setIngredient('S', Material.WITHER_SKELETON_SKULL);
        coreRec.setIngredient('d', Material.DIAMOND_BLOCK);
        coreRec.setIngredient('o', Material.OBSIDIAN);
        Bukkit.getServer().addRecipe(coreRec);
    }
}
