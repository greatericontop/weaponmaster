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
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class EndPowerManager {
    private final Map<Player, Integer> powerMap;

    private final Util util;
    public EndPowerManager() {
        this.powerMap = new HashMap<>();
        util = new Util(null);
    }

    public int getPower(Player player) {
        return powerMap.getOrDefault(player, getMaxPower(player));
    }

    public void incrementPower(Player player, int by) {
        powerMap.put(player, Math.min(Math.max(getPower(player) + by, 0), getMaxPower(player)));
    }

    public int getMaxPower(Player player) {
        int pearlsThrown = player.getStatistic(Statistic.USE_ITEM, org.bukkit.Material.ENDER_PEARL);
        int armorBonus = 0;
        for (ItemStack armorPiece : player.getInventory().getArmorContents()) {
            if (util.checkForEndArmor(armorPiece)) {
                armorBonus += 35;
            }
        }
        return 150 + Math.min(pearlsThrown / 100, 30) + armorBonus;
    }

    public void registerEndPowerManagerTask(WeaponMasterMain plugin) {
        int ticksPer = 5;
        new BukkitRunnable() {
            int tickCounter = 0;
            public void run() {
                tickCounter += ticksPer;

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (util.checkForEndSword(player.getInventory().getItemInMainHand())) {
                        int power = getPower(player);
                        int maxPower = getMaxPower(player);
                        boolean sendAnyway = tickCounter % 100 == 0; // text update every 5 seconds
                        plugin.paperUtils.sendActionBar(player, String.format("§f%d §7/ §f%d §dEnd Power", power, maxPower), sendAnyway);
                    }
                }

                // Regen (10% every 4 seconds, or 2.5% per second)
                if (tickCounter % 80 == 0) {
                    for (Player player : powerMap.keySet()) {
                        incrementPower(player, getMaxPower(player)/10);
                    }
                }

            }
        }.runTaskTimer(plugin, ticksPer, ticksPer);
    }
}
