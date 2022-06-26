package io.github.greatericontop.weaponmaster.Fireball;

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
import org.bukkit.inventory.ShapelessRecipe;

import io.github.greatericontop.weaponmaster.Util.Util;

public class FireballRecipe {

    private final Util util;
    public FireballRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack fireball = util.generateMeta(util.FIREBALL_LORE, util.FIREBALL_NAME, Material.FIRE_CHARGE);
        fireball.setAmount(4);
        ShapelessRecipe fireballRec = new ShapelessRecipe(NamespacedKey.minecraft("fireball"), fireball);
        fireballRec.addIngredient(Material.FIRE_CHARGE);
        fireballRec.addIngredient(Material.TNT);
        fireballRec.addIngredient(Material.ARROW);
        Bukkit.getServer().addRecipe(fireballRec);
    }
}
