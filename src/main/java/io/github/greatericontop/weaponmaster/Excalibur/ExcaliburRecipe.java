package io.github.greatericontop.weaponmaster.Excalibur;

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

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import io.github.greatericontop.weaponmaster.Util.Util;

public class ExcaliburRecipe {

    private final Util util;
    public ExcaliburRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack excalibur = util.generateMeta(util.EXCALIBUR_LORE, util.EXCALIBUR_NAME, Material.DIAMOND_SWORD);
        excalibur.addEnchantment(Enchantment.DURABILITY, 2);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("excalibur"), excalibur);
        recipe.shape("dNt",
                     "zNs",
                     "dCt");
        recipe.setIngredient('N', Material.NETHER_STAR);
        recipe.setIngredient('C', Material.END_CRYSTAL);
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        recipe.setIngredient('t', Material.TNT);
        recipe.setIngredient('z', Material.ZOMBIE_HEAD);
        recipe.setIngredient('s', Material.SKELETON_SKULL);

        Bukkit.getServer().addRecipe(recipe);
    }

}
