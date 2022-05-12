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

import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class WeaponMasterCommand implements CommandExecutor {

    private WeaponMasterMain plugin;
    public WeaponMasterCommand(WeaponMasterMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 1 && args[0].equals("forceenchant")) {
            if (args.length < 3) {
                sender.sendMessage("§cError: §4usage: /weaponmaster forceenchant <enchant> <level>");
                return true;
            }
            // TODO: fix null enchant. change except e to NumberFormatException, add alias "max"->lvl255
            // TODO: make nicer error message, or use ACF or something once you figure it out
            Enchantment enchant;
            int level;
            try {
                enchant = Enchantment.getByKey(NamespacedKey.minecraft(args[1]));
                level = Integer.parseInt(args[2]);
            } catch (Exception e) {
                e.printStackTrace();
                sender.sendMessage("§cError: §4usage: /weaponmaster forceenchant <enchant> <level>");
                return true;
            }
            if (level > 255) {
                level = 255;
                sender.sendMessage("§cWarning: §4Enchantment level in 1.18 is limited to 255.");
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cError: §4Must be a player.");
                return true;
            }
            ((Player) sender).getInventory().getItemInMainHand().addUnsafeEnchantment(enchant, level);
            sender.sendMessage(String.format("§3Added %s level %d!", enchant, level));
            return true;
        }
        sender.sendMessage("----------------------------------------");
        sender.sendMessage("");
        sender.sendMessage("§4§lWeaponMaster");
        sender.sendMessage("§7§oby greateric");
        sender.sendMessage("");
        sender.sendMessage("§e/weaponmaster forceenchant");
        sender.sendMessage("");
        sender.sendMessage("----------------------------------------");
        return true;
    }
}
