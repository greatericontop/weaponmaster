package io.github.greatericontop.weaponmaster.mainitems.EndSword;

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
import org.bukkit.event.Listener;

import java.util.UUID;

public class EndSwordListener implements Listener {

    private final WeaponMasterMain plugin;
    private final EndPowerManager powerManager;
    public EndSwordListener(WeaponMasterMain plugin, EndPowerManager powerManager) {
        this.plugin = plugin;
        this.powerManager = powerManager;
    }

}
