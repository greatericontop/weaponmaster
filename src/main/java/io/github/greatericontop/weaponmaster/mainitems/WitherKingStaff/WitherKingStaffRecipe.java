package io.github.greatericontop.weaponmaster.mainitems.WitherKingStaff;

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

import io.github.greatericontop.weaponmaster.minorcrafts.MinorItems;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class WitherKingStaffRecipe {

    private final Util util;
    private final MinorItems minorItems;
    public WitherKingStaffRecipe() {
        util = new Util(null);
        minorItems = new MinorItems();
    }

    public void regRecipe() {
        ItemStack staff = util.generateMeta(util.WITHER_KING_STAFF_LORE, util.WITHER_KING_STAFF_NAME, Material.BLAZE_ROD);
        staff.addUnsafeEnchantment(Enchantment.LUCK_OF_THE_SEA, 1);
        ShapedRecipe staffRec = new ShapedRecipe(new NamespacedKey("weaponmaster", "wither_king_staff"), staff);
        staffRec.shape(" hh",
                       " Rh",
                       "r  ");
        staffRec.setIngredient('r', Material.BLAZE_ROD);
        staffRec.setIngredient('R', new RecipeChoice.ExactChoice(util.generateMeta(util.WITHER_STAFF_LORE, util.WITHER_STAFF_NAME, Material.BLAZE_ROD)));
        staffRec.setIngredient('h', new RecipeChoice.ExactChoice(minorItems.generateWitherHeadItemStack()));
        Bukkit.getServer().addRecipe(staffRec);
    }
}
