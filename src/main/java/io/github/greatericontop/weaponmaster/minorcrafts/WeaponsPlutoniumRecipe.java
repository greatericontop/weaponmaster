package io.github.greatericontop.weaponmaster.minorcrafts;

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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class WeaponsPlutoniumRecipe {
    private MinorItems minorItems;

    public WeaponsPlutoniumRecipe() {
        minorItems = new MinorItems();
    }

    public void regRecipe() {
        ItemStack plut = minorItems.generateWeaponsGradePlutoniumItemStack();
        ShapedRecipe rec = new ShapedRecipe(NamespacedKey.minecraft("weapons_plutonium"), plut);
        rec.shape("xxx",
                      "xPx",
                      "xxx");
        rec.setIngredient('P', new RecipeChoice.ExactChoice(minorItems.generateCrudePlutoniumItemStack()));
        rec.setIngredient('x', Material.PISTON);
        Bukkit.getServer().addRecipe(rec);
    }
}
