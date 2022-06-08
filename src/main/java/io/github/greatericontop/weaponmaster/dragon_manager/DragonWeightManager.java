package io.github.greatericontop.weaponmaster.dragon_manager;

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
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DragonWeightManager {

    private final Map<UUID, Double> dragonDamage = new HashMap<>();

    private final WeaponMasterMain plugin;
    private final EnderDragon currentlyActiveDragon;
    private boolean enabled;
    public DragonWeightManager(WeaponMasterMain plugin, EnderDragon currentlyActiveDragon) {
        this.plugin = plugin;
        this.currentlyActiveDragon = currentlyActiveDragon;
        this.enabled = true;
    }

    public double getDamage(UUID player) {
        return dragonDamage.getOrDefault(player, 0.0);
    }

    public void setDamage(UUID player, double amount) {
        dragonDamage.put(player, amount);
    }

    public void incrementDamage(UUID player, double amount) {
        setDamage(player, getDamage(player) + amount);
    }

    public DragonWeightManager setEnabled(boolean v) {
        enabled = v;
        return this;
    }

    public void onDamage(EntityDamageByEntityEvent event) {
        // TODO: should non-player damage be attributed to the nearest player?
        if (!enabled) { return; }
        if (!(event.getDamager() instanceof Player)) { return; }
        Player player = (Player) event.getDamager();
        incrementDamage(player.getUniqueId(), event.getFinalDamage());
    }

}
