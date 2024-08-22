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
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EndPowerManager {
    private final Map<Player, Integer> powerMap;
    public EndPowerManager() {
        this.powerMap = new HashMap<>();
    }

    public int getPower(Player player) {
        return powerMap.getOrDefault(player, getMaxPower(player));
    }

    public void incrementPower(Player player, int by) {
        powerMap.put(player, Math.min(Math.max(getPower(player) + by, 0), getMaxPower(player)));
    }

    public int getMaxPower(Player player) {
        return 150; // TODO: will be dynamic later on
    }

    public void registerEndPowerManagerTask(WeaponMasterMain plugin) {
        int ticksPer = 10;
        new BukkitRunnable() {
            int tickCounter = 0;
            public void run() {
                tickCounter += ticksPer;
                for (Player player : powerMap.keySet()) {
                    incrementPower(player, 1);
                }

            }
        }.runTaskTimer(plugin, ticksPer, ticksPer);
    }
}
