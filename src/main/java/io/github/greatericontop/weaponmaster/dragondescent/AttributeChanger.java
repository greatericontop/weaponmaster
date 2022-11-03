package io.github.greatericontop.weaponmaster.dragondescent;

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

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class AttributeChanger implements Listener {

    // Random, but consistent, UUIDs for storing the permanent attribute modifiers in
    private final UUID extraAttackSpeedUUID = UUID.fromString("00000000-1111-0000-0000-68e91d3e87c2");
    private final UUID tougherArmorUUID = UUID.fromString("00000000-1111-0000-0000-eb63bfa7d783");

    private final WeaponMasterMain plugin;
    private final DescentDataManager descent;
    public AttributeChanger(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.descent = plugin.descent;
    }

    private AttributeModifier searchExistingAM(AttributeInstance instance, UUID withUUID) {
        for (AttributeModifier am : instance.getModifiers()) {
            if (am.getUniqueId().equals(withUUID)) {
                double oldAmount = am.getAmount();
                return am;
            }
        }
        return null;
    }

    public void updateAttributes(Player player) {
        // extraAttackSpeed
        int extraAttackSpeed = descent.getUpgrade(player, "extraAttackSpeed");
        AttributeModifier existingAttackSpeedAM = searchExistingAM(player.getAttribute(Attribute.GENERIC_ATTACK_SPEED), extraAttackSpeedUUID);
        if (existingAttackSpeedAM != null) {
            player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).removeModifier(existingAttackSpeedAM);
        }
        if (extraAttackSpeed > 0) {
            double multi = 0.02*extraAttackSpeed; // the base 1.0 is added by the game
            player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).addModifier(new AttributeModifier(extraAttackSpeedUUID, "weaponmaster", multi, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        }
        // tougherArmor
        int tougherArmor = descent.getUpgrade(player, "tougherArmor");
        AttributeModifier existingArmorToughnessAM = searchExistingAM(player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS), tougherArmorUUID);
        if (existingArmorToughnessAM != null) {
            player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).removeModifier(existingArmorToughnessAM);
        }
        if (tougherArmor > 0) {
            double multi = 0.03*tougherArmor;
            player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).addModifier(new AttributeModifier(tougherArmorUUID, "weaponmaster", multi, AttributeModifier.Operation.MULTIPLY_SCALAR_1));
        }
    }

    public void registerUpdateEveryone() {
        // update everyone every so often
        long period = 6000L;
        new BukkitRunnable() {
            public void run() {
                for (Player player : plugin.getServer().getOnlinePlayers()) {
                    updateAttributes(player);
                }
            }
        }.runTaskTimer(plugin, period, period);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerJoin(PlayerJoinEvent event) {
        // update when you join
        updateAttributes(event.getPlayer());
    }

}
