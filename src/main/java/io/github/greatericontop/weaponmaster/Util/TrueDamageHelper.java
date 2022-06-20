package io.github.greatericontop.weaponmaster.Util;

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
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.LivingEntity;

public class TrueDamageHelper {

    public static void dealTrueDamage(LivingEntity target, double amount) {
        double absorptionAmount = target.getAbsorptionAmount();
        double leftAmount = amount - absorptionAmount;
        if (leftAmount <= 0) {
            // all the damage was used on absorption hearts
            target.setAbsorptionAmount(absorptionAmount - amount);
        } else {
            target.setAbsorptionAmount(0.0);
            // damage left over for health
            double newHealth = target.getHealth() - leftAmount;
            if (newHealth <= 0.000_001) {
                target.setHealth(0.0);
                return;
            }
            Bukkit.getServer().getLogger().warning("unhandled damage may occur; setting health right now");
            target.setHealth(newHealth);
        }
        target.playEffect(EntityEffect.HURT);
        target.getWorld().playSound(target, Sound.ENTITY_PLAYER_HURT, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }

}
