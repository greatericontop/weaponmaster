package io.github.greatericontop.weaponmaster.utils;

/*
 * WeaponMaster Copyright (C) greateric 2021-present.
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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownSystem {

    private final int ticksToReset;
    private final WeaponMasterMain plugin;
    private final Map<UUID, Boolean> internalCooldown = new HashMap<UUID, Boolean>();

    public CooldownSystem(WeaponMasterMain plugin, int ticksToReset) {
        this.plugin = plugin;
        this.ticksToReset = ticksToReset;
    }

    public boolean checkAvailable(UUID uuid) {
        return internalCooldown.getOrDefault(uuid, true);
    }

    public void triggerCooldown(UUID uuid) {
        internalCooldown.put(uuid, false);
    }

}
