package io.github.greatericontop.weaponmaster.mainitems.PilotSword;

/*
 * WeaponMaster Copyright (C) 2021-present greateric.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty  of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class PilotRecipe {
    private final Util util;
    public PilotRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack pilotsword = util.generateMeta(util.PILOT_SWORD_LORE, util.PILOT_SWORD_NAME, Material.NETHERITE_SWORD);
        ItemMeta pilotIM = pilotsword.getItemMeta();
        pilotIM.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "weaponmaster", 7.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        pilotIM.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "weaponmaster", 20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        pilotIM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        pilotsword.setItemMeta(pilotIM);
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey("weaponmaster", "pilot_sword"), pilotsword);
        recipe.shape("YeY",
                     "XTX",
                     "YYY");
        recipe.setIngredient('T', new RecipeChoice.ExactChoice(new ItemStack(Material.NETHERITE_SWORD, 1)));
        ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);
        EnchantmentStorageMeta im = (EnchantmentStorageMeta) book.getItemMeta();
        im.addStoredEnchant(Enchantment.EFFICIENCY, 5, true);
        book.setItemMeta(im);
        recipe.setIngredient('X', new RecipeChoice.ExactChoice(book));
        recipe.setIngredient('Y', Material.NETHER_STAR);
        recipe.setIngredient('e', Material.ELYTRA);
        Bukkit.getServer().addRecipe(recipe);
    }
}
