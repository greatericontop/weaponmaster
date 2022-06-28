package io.github.greatericontop.weaponmaster.items.fireball;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import io.github.greatericontop.weaponmaster.util.Util;

public class FireballRecipe {

    private final Util util;
    public FireballRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack fireball = util.generateMeta(util.FIREBALL_LORE, util.FIREBALL_NAME, Material.FIRE_CHARGE);
        fireball.setAmount(4);
        ShapelessRecipe fireballRec = new ShapelessRecipe(NamespacedKey.minecraft("fireball"), fireball);
        fireballRec.addIngredient(Material.FIRE_CHARGE);
        fireballRec.addIngredient(Material.TNT);
        fireballRec.addIngredient(Material.ARROW);
        Bukkit.getServer().addRecipe(fireballRec);
    }
}
