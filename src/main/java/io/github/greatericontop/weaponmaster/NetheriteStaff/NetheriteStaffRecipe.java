package io.github.greatericontop.weaponmaster.NetheriteStaff;

import com.sun.org.apache.xml.internal.utils.NameSpace;
import io.github.greatericontop.weaponmaster.CustomItems;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class NetheriteStaffRecipe {

    private final Util util;
    private CustomItems customItems;
    public NetheriteStaffRecipe() {
        util = new Util(null);
        customItems = new CustomItems();
    }

    public void regRecipe() {
        ItemStack staff = util.generateMeta(util.NETHERITE_STAFF_LORE, util.NETHERITE_STAFF_NAME, Material.NETHERITE_SHOVEL);
        staff.addUnsafeEnchantment(Enchantment.LUCK, 1);
        ItemMeta im = staff.getItemMeta();
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "weaponmaster", 0.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "weaponmaster", 20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        staff.setItemMeta(im);
        ShapedRecipe staffRec = new ShapedRecipe(NamespacedKey.minecraft("netherite_staff"), staff);
        staffRec.shape(" sB",
                       " Ns",
                       "i  ");
        staffRec.setIngredient('s', Material.NETHER_STAR);
        staffRec.setIngredient('B', new RecipeChoice.ExactChoice(customItems.generateCoreStaffItemStack()));
        staffRec.setIngredient('N', Material.NETHERITE_BLOCK);
        staffRec.setIngredient('i', Material.NETHERITE_INGOT);
        Bukkit.getServer().addRecipe(staffRec);
    }
}
