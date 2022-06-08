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

import org.bukkit.util.Vector;

public class InaccuracyAdder {

    public static double randRange(double l, double h) {
        return l + (h-l)*Math.random();
    }

    public static Vector generateInaccuracy(double radius) {
        double x, y, z;
        // Generate a uniformly random point in the sphere of radius :radius: through simple rejection method
        // Odds of requiring 8 attempts (or more):  0.55694%
        // Odds of requiring 12 attempts (or more): 0.02869%
        // Odds of requiring 16 attempts (or more): 0.00148%
        do {
            x = randRange(-radius, radius);
            y = randRange(-radius, radius);
            z = randRange(-radius, radius);
        } while ( x * x + y * y + z * z > 1 );
        return new Vector(x, y, z);
    }

}
