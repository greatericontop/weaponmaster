package io.github.greatericontop.weaponmaster.utils;

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

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.ItemMeta;

public class MathHelper {

    public static int roundProbability(double number) {
        int intPart = (int) number;
        double fracPart = number - intPart;
        if (Math.random() < fracPart) {
            intPart++;
        }
        return intPart;
    }

    /*
     * Minecraft's implementation of unbreaking. Not exactly the fastest with high durability values.
     */
    public static int getDamageWithUnbreaking(int rawDamage, int unbreakingLevel) {
        int newAmount = 0;
        for (int i = 0; i < rawDamage; i++) {
            if (Math.random() < 1.0/unbreakingLevel) {
                newAmount++;
            }
        }
        return newAmount;
    }

    public static int getDamageWithUnbreaking(int rawDamage, ItemMeta im) {
        int unbreakingLevel = im.getEnchantLevel(Enchantment.DURABILITY);
        return getDamageWithUnbreaking(rawDamage, unbreakingLevel);
    }

    public static String getColor(double percent) {
        if (percent >= 90)  return "a";
        if (percent >= 50)  return "e";
        if (percent >= 25)  return "6";
        return "c";
    }

}
