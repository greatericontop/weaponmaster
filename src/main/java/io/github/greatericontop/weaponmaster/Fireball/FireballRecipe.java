package io.github.greatericontop.weaponmaster.Fireball;

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

public class FireballRecipe {
    private final Util util;
    public FireballRecipe() {
        util = new Util(null);
    }

    public void fireballRecipe() {
        ItemStack fireball = util.generateMeta(util.FIREBALL_LORE, util.FIREBALL_NAME, Material.SLIME_BALL);
        fireball.setAmount(4);
        fireball.addEnchantment(Enchantment.DURABILITY, 0);
        ShapelessRecipe fireballRec = new ShapelessRecipe(NamespacedKey.minecraft("fireball"), fireball);
        fireballRec.addIngredient(Material.FIRE_CHARGE);
        fireballRec.addIngredient(Material.TNT);
        fireballRec.addIngredient(Material.ARROW);
        Bukkit.getServer().addRecipe(fireballRec);
    }
}
