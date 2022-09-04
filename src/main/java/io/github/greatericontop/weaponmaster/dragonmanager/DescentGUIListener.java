package io.github.greatericontop.weaponmaster.dragonmanager;

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
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class DescentGUIListener implements Listener {

    private final WeaponMasterMain plugin;
    public DescentGUIListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(plugin.descent.DESCENT_GUI_NAME)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();

        switch (event.getSlot()) {
            case 4:
                increment(player, "all_damage_resistance");
                break;
            case 13:
                increment(player, "dragon_extra_rng");
                break;
            default:
                player.sendMessage("§cThat's not an option.");
                break;
        }

        event.setCancelled(true);
    }

    public void increment(Player player, String upgradeName) {
        if (!plugin.descent.incrementDescent(player.getUniqueId(), upgradeName)) {
            player.sendMessage("§cYou can't afford this!");
        }
        player.sendMessage(String.format("§3You successfully bought this upgrade. §7[Level §4%d§7]",
                plugin.descent.getDescentUpgradeLevel(player.getUniqueId(), upgradeName)));
    }

}
