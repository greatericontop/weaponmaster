package io.github.greatericontop.weaponmaster;

/*
    Copyright (C) 2021 greateric.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WeaponMasterCommand implements CommandExecutor {

    private WeaponMasterMain plugin;
    public WeaponMasterCommand(WeaponMasterMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sender.sendMessage("----------------------------------------");
        sender.sendMessage("");
        sender.sendMessage("§4§lWeaponMaster");
        sender.sendMessage("§7§oby greateric");
        sender.sendMessage("");
        sender.sendMessage("§eThis server is running a valid license!");
        sender.sendMessage("§eLicensed to: §3" + plugin.getConfig().getString("license.issued-to"));
        sender.sendMessage("§eLicense: §3" + plugin.getConfig().getString("license.key").substring(0, 10) + "...");
        sender.sendMessage("");
        sender.sendMessage("----------------------------------------");
        return true;
    }
}
