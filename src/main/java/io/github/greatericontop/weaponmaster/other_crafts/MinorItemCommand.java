package io.github.greatericontop.weaponmaster.other_crafts;

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

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MinorItemCommand implements CommandExecutor {

    private final CustomItems customItems;
    public MinorItemCommand() {
        this.customItems = new CustomItems();
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lMinor Items");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/minoritem list§3 to list the minor items.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendInfo(sender);
            return true;
        }
        if (args[0].equalsIgnoreCase("list")) {
            sender.sendMessage("§7Heart of Leviathan - /minoritem leviathanHeart");
            sender.sendMessage("§7Netherite Staff Core - /minoritem coreStaff");
            sender.sendMessage("§7Flask of Ichor - /minoritem flaskIchor");
            sender.sendMessage("§7Dragon Scale - /minoritem dragonScale");
            return true;
        }
        if (args[0].equalsIgnoreCase("leviathanHeart")) {
            ItemStack item = customItems.generateLeviathanHeartItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + customItems.LEVIATHAN_HEART_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("coreStaff")) {
            ItemStack item = customItems.generateCoreStaffItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + customItems.CORE_STAFF_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("flaskIchor")) {
            ItemStack item = customItems.generateFlaskIchorItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + customItems.FLASK_ICHOR_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("dragonScale")) {
            ItemStack item = customItems.generateDragonScaleItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + customItems.DRAGON_SCALE_NAME + "§f]§7.");
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("§3Sorry, players only.");
            return true;
        }
        return false;
    }
}
