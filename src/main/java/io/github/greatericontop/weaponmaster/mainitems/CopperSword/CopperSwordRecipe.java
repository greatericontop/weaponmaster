package io.github.greatericontop.weaponmaster.mainitems.CopperSword;

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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;

public class CopperSwordRecipe {

    private final Util util;
    public CopperSwordRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack copperSword = util.generateMeta(util.COPPER_SWORD_LORE, util.COPPER_SWORD_NAME, Material.GOLDEN_SWORD);
        copperSword.addUnsafeEnchantment(Enchantment.UNBREAKING, 9);
        copperSword.addUnsafeEnchantment(Enchantment.SHARPNESS, 3);
        ShapedRecipe copperRec = new ShapedRecipe(NamespacedKey.minecraft("copper_sword"), copperSword);
        copperRec.shape("ccc",
                        "cic",
                        "ccc");
        copperRec.setIngredient('c', Material.COPPER_BLOCK);
        copperRec.setIngredient('i', Material.IRON_SWORD);
        Bukkit.getServer().addRecipe(copperRec);
    }
}
