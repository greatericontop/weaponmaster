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
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class DescentGUIListener implements Listener {

    private final WeaponMasterMain plugin;
    private final DescentCommand descentCommandManager;
    public DescentGUIListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.descentCommandManager = new DescentCommand(plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(plugin.descent.DESCENT_GUI_NAME)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!event.getView().getTopInventory().equals(event.getClickedInventory())) {
            // skip if player is not clicking the top inventory with the items in it
            return;
        }

        if (event.getCurrentItem() == null
                || event.getCurrentItem().getType() == Material.GRAY_STAINED_GLASS_PANE
                || event.getCurrentItem().getType() == Material.BARRIER) {
            event.setCancelled(true);
            return;
        }

        switch (event.getSlot()) {
            // TODO: use some kind of array for this instead of a fat switch statement
            case 4:
                increment(player, "allDamageResistance");
                break;
            case 13:
                increment(player, "dragonExtraRNG");
                break;
            case 14:
                increment(player, "tougherArmor");
                break;
            case 15:
                increment(player, "enhancedEnergy");
                break;
            case 16:
                increment(player, "strongArrows");
                break;
            case 17:
                increment(player, "grimTouch");
                break;
            case 20:
                increment(player, "shardSeeker");
                break;
            case 21:
                increment(player, "heartbleed");
                break;
            case 22:
                increment(player, "extraAttackSpeed");
                break;
            case 24:
                increment(player, "wisdom");
                break;
            case 29:
                increment(player, "shieldedArmor");
                break;
            case 31:
                increment(player, "mightyStrength");
                break;
            case 33:
                increment(player, "strongLegs");
                break;
            case 34:
                increment(player, "reviver");
                break;
            case 38:
                increment(player, "silkyTouch");
                break;
            case 40:
                increment(player, "vitality");
                break;
            case 42:
                increment(player, "sicklyTouch");
                break;
            case 49:
                increment(player, "strongAttacks");
                break;
            case 51:
                increment(player, "witch");
                break;
            case 52:
                increment(player, "runner");
                break;
            default:
                player.sendMessage("§cThat's not an option.");
                break;
        }
        event.getView().close();
        new BukkitRunnable() {
            public void run() {
                descentCommandManager.openInventory(player);
            }
        }.runTaskLater(plugin, 5L);
    }

    public void increment(Player player, String upgradeName) {
        if (plugin.descent.getUpgrade(player, upgradeName) >= plugin.descent.MAX_LEVEL) {
            player.sendMessage("§cThis upgrade is already at its maximum level!");
            return;
        }
        if (!plugin.descent.incrementDescent(player.getUniqueId(), upgradeName)) {
            player.playSound(player, Sound.ENTITY_ENDERMAN_TELEPORT, 1.0F, 1.0F);
            player.sendMessage("§cYou can't afford this!");
        } else {
            player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            player.sendMessage(String.format("§3You successfully bought this upgrade. §7[Level §4%d§7]",
                    plugin.descent.getUpgrade(player, upgradeName)));
        }
    }

}
