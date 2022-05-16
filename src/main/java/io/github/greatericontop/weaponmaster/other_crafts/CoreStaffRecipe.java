package io.github.greatericontop.weaponmaster.other_crafts;

import io.github.greatericontop.weaponmaster.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class CoreStaffRecipe {
    private CustomItems customItems;

    public CoreStaffRecipe() {
        customItems = new CustomItems();
    }

    public void regRecipe() {
        ItemStack core = customItems.generateCoreStaffItemStack();
        ShapedRecipe coreRec = new ShapedRecipe(NamespacedKey.minecraft("core_staff"), core);
        coreRec.shape("non",
                      "nSn",
                      "non");
        coreRec.setIngredient('n', Material.NETHERITE_INGOT);
        coreRec.setIngredient('S', Material.WITHER_SKELETON_SKULL);
        coreRec.setIngredient('o', Material.OBSIDIAN);
        Bukkit.getServer().addRecipe(coreRec);
    }
}
