package io.github.greatericontop.weaponmaster.items.dragon_sword;

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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class DragonRecipe {

    private final Util util;
    public DragonRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack drag = util.generateMeta(util.DRAGON_SWORD_LORE, util.DRAGON_SWORD_NAME, Material.NETHERITE_SWORD);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("dragon_sword"), drag);
        recipe.shape("bDb",
                     "bDb",
                     "bSb");
        recipe.setIngredient('S', Material.NETHERITE_SWORD);
        recipe.setIngredient('D', Material.DRAGON_HEAD);
        recipe.setIngredient('b', Material.BLAZE_ROD);
        Bukkit.getServer().addRecipe(recipe);
    }

}
