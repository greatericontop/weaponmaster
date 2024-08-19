package io.github.greatericontop.weaponmaster.utils;/*
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
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlotGroup;

import java.util.UUID;

public class VersionSpecificUtil {

    public static void modifyAttributeModifier(AttributeInstance instance, UUID withUUID, double amountDelta, double min, double max) {
        String[] ver = Bukkit.getVersion().split("\\."); // 1.X[.Y]
        int major = Integer.parseInt(ver[1]);
        if (major >= 21) {
            modifyAttributeModifierNew(instance, withUUID, amountDelta, min, max);
        } else {
            modifyAttributeModifierOld(instance, withUUID, amountDelta, min, max);
        }
    }

    private static void modifyAttributeModifierNew(AttributeInstance instance, UUID withUUID, double amountDelta, double min, double max) {
        String stringKey = withUUID.toString();
        AttributeModifier savedAM = null;
        double amount = 0;
        for (AttributeModifier am : instance.getModifiers()) {
            if (am.getKey().getKey().equals(stringKey)) {
                double oldAmount = am.getAmount();
                amount = Math.min(Math.max(oldAmount + amountDelta, min), max);
                savedAM = am;
                break;
            }
        }
        AttributeModifier newAM;
        if (savedAM == null) {
            newAM = new AttributeModifier(new NamespacedKey("weaponmaster", stringKey), Math.min(Math.max(amountDelta, min), max), AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY);
        } else {
            instance.removeModifier(savedAM);
            newAM = new AttributeModifier(new NamespacedKey("weaponmaster", stringKey), amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlotGroup.ANY);
        }
        instance.addModifier(newAM);
    }

    private static void modifyAttributeModifierOld(AttributeInstance instance, UUID withUUID, double amountDelta, double min, double max) {
        AttributeModifier savedAM = null;
        double amount = 0;
        for (AttributeModifier am : instance.getModifiers()) {
            if (am.getUniqueId().equals(withUUID)) {
                double oldAmount = am.getAmount();
                amount = Math.min(Math.max(oldAmount + amountDelta, min), max);
                savedAM = am;
                break;
            }
        }
        AttributeModifier newAM;
        if (savedAM == null) {
            newAM = new AttributeModifier(withUUID, "weaponmaster", Math.min(Math.max(amountDelta, min), max), AttributeModifier.Operation.ADD_NUMBER);
        } else {
            instance.removeModifier(savedAM);
            newAM = new AttributeModifier(withUUID, "weaponmaster", amount, AttributeModifier.Operation.ADD_NUMBER);
        }
        instance.addModifier(newAM);
    }

}
