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
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DescentManagementCommand implements CommandExecutor {

    private final WeaponMasterMain plugin;
    public DescentManagementCommand(WeaponMasterMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cPlayers only, sorry!");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            return false;
        }

        if (args[0].equals("give-power")) {
            int power;
            if (args.length < 2) {
                player.sendMessage("§cError: §4Missing arguments: /descent-management give-power <power> [<shards>]");
                return true;
            }
            try {
                power = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                player.sendMessage("§cError: §4Invalid number!");
                return true;
            }
            int shards = 0;
            if (args.length >= 3) {
                try {
                    shards = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    player.sendMessage("§cError: §4Invalid number!");
                    return true;
                }
            }
            plugin.descent.setDragonPower(player.getUniqueId(), plugin.descent.getDragonPower(player.getUniqueId()) + power);
            plugin.descent.setShards(player.getUniqueId(), plugin.descent.getShards(player.getUniqueId()) + shards);
            player.sendMessage(String.format("§3Gave you %d power and %d shards!", power, shards));
            return true;
        }

        if (args[0].equals("clear")) {
            plugin.descent.clear(player.getUniqueId());
            player.sendMessage("§3Cleared your descent data!");
            return true;
        }
        return false;
    }

}
