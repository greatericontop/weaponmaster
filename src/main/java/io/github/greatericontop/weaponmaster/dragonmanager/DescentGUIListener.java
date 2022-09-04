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
            case 13:
                plugin.descent.incrementDescent(player.getUniqueId(), "all_damage_resistance");
                player.sendMessage("§7You clicked on ALL DAMAGE RESISTANCE.");
                break;
            case 22:
                plugin.descent.incrementDescent(player.getUniqueId(), "dragon_extra_rng");
                player.sendMessage("§7You clicked on DRAGON EXTRA RNG.");
                break;
            default:
                player.sendMessage("§cThat's not an option.");
                break;
        }

        event.setCancelled(true);
    }

}
