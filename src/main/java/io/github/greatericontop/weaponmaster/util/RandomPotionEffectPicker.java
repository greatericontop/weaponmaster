package io.github.greatericontop.weaponmaster.util;

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

import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class RandomPotionEffectPicker {

    private static final Random rnd = new Random();

    public static final Object[][] effects = {
            // https://minecraft.fandom.com/wiki/Effect
            // weight, identifier, minimumTime, maximumTime, minimumAmplifier, maximumAmplifier, modifiedAmplifierForArrow
            // {10, PotionEffectType., 80, , 0, },
            {10, PotionEffectType.SPEED, 80, 600, 0, 7, 2},
            {10, PotionEffectType.SLOW, 80, 600, 0, 4, 2},
            {10, PotionEffectType.FAST_DIGGING, 80, 600, 0, 2, 1},
            {10, PotionEffectType.SLOW_DIGGING, 80, 600, 0, 2, 1},
            {10, PotionEffectType.INCREASE_DAMAGE, 80, 600, 0, 4, 2},
            {10, PotionEffectType.HEAL, 1, 1, 0, 3, 1},
            {10, PotionEffectType.HARM, 1, 1, 0, 3, 1},
            {10, PotionEffectType.JUMP, 80, 600, 0, 7, 3},
            {10, PotionEffectType.CONFUSION, 80, 300, 0, 0, 0},
            {10, PotionEffectType.REGENERATION, 80, 400, 0, 3, 1},
            {10, PotionEffectType.DAMAGE_RESISTANCE, 80, 400, 0, 4, 2},
            {10, PotionEffectType.FIRE_RESISTANCE, 80, 600, 0, 0, 0},
            {10, PotionEffectType.WATER_BREATHING, 80, 600, 0, 0, 0},
            {10, PotionEffectType.INVISIBILITY, 80, 600, 0, 0, 0},
            {10, PotionEffectType.BLINDNESS, 80, 300, 0, 0, 0},
            {10, PotionEffectType.NIGHT_VISION, 80, 600, 0, 0, 0},
            {10, PotionEffectType.HUNGER, 80, 400, 0, 9, 3},
            {10, PotionEffectType.WEAKNESS, 80, 600, 0, 3, 1},
            {10, PotionEffectType.POISON, 80, 400, 0, 2, 0},
            {10, PotionEffectType.WITHER, 80, 400, 0, 2, 0},
            {10, PotionEffectType.HEALTH_BOOST, 200, 1800, 0, 7, 3},
            {10, PotionEffectType.ABSORPTION, 200, 1800, 0, 7, 3},
            {10, PotionEffectType.SATURATION, 1, 1, 0, 5, 3},
            {10, PotionEffectType.GLOWING, 80, 600, 0, 0, 0},
            {10, PotionEffectType.LEVITATION, 20, 200, 0, 9, 3},
            {10, PotionEffectType.LUCK, 80, 600, 0, 4, 4},
            {10, PotionEffectType.UNLUCK, 80, 600, 0, 4, 4},
            {10, PotionEffectType.SLOW_FALLING, 80, 600, 0, 0, 0},
            {10, PotionEffectType.CONDUIT_POWER, 80, 600, 0, 0, 0},
            {10, PotionEffectType.DOLPHINS_GRACE, 20, 200, 0, 0, 0},
            {10, PotionEffectType.BAD_OMEN, 80, 600, 0, 4, 4},
            {10, PotionEffectType.HERO_OF_THE_VILLAGE, 80, 600, 0, 4, 4},
    };

    public static Object[] getRandomEffect() {
        Object[] selectedEffect = null;
        int totalWeight = 0;
        for (Object[] effect : effects) {
            for (int i = 0; i < (int) effect[0]; i++) {
                totalWeight++;
                if (rnd.nextDouble() < 1.0 / totalWeight) {
                    selectedEffect = effect;
                }
            }
        }
        return selectedEffect;
    }

    public static Object[] getRandomEffect(boolean isShortened) {
        Object[] data = getRandomEffect();
        int minDuration = (int) data[2];
        int maxDuration = isShortened ? ((int) data[3]) / 2 : (int) data[3];
        int minAmplifier = (int) data[4];
        int maxAmplifier = isShortened ? (int) data[6] : (int) data[5];
        int duration = rnd.nextInt(maxDuration - minDuration + 1) + minDuration;
        int amplifier = rnd.nextInt(maxAmplifier - minAmplifier + 1) + minAmplifier;
        return new Object[]{data[1], duration, amplifier};
    }

}
