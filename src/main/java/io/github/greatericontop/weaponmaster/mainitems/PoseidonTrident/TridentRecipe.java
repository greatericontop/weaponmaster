package io.github.greatericontop.weaponmaster.mainitems.PoseidonTrident;

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

public class TridentRecipe {
    private final Util util;
    public TridentRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack trident = util.generateMeta(util.POSEIDON_TRIDENT_LORE, util.POSEIDON_TRIDENT_NAME, Material.TRIDENT);
        trident.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("poseidon_trident"), trident);
        recipe.shape(" HT",
                     "bTH",
                     "Tb ");
        recipe.setIngredient('T', Material.TRIDENT);
        recipe.setIngredient('H', Material.HEART_OF_THE_SEA);
        recipe.setIngredient('b', Material.BLAZE_ROD);
        Bukkit.getServer().addRecipe(recipe);
    }
}
