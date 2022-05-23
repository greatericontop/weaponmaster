package io.github.greatericontop.weaponmaster.NetheriteStaff;

/*
    Copyright (C) 2021 greateric.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import io.github.greatericontop.weaponmaster.other_crafts.MinorItemUtil;
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
    private MinorItemUtil customItems;
    public NetheriteStaffRecipe() {
        util = new Util(null);
        customItems = new MinorItemUtil();
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
