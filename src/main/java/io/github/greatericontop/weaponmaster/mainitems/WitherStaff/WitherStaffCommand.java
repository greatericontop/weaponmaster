package io.github.greatericontop.weaponmaster.mainitems.WitherStaff;

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

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WitherStaffCommand implements CommandExecutor {

    private final Util util;
    public WitherStaffCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lWither Staff");
        to.sendMessage("§e§oby greateric, gerseneck");
        to.sendMessage("§3Use §2/witherstaff give§3 to give yourself the item.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendInfo(sender);
            return true;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("give")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§3Sorry, players only.");
                return true;
            }
            ItemStack witherStaff = util.generateMeta(util.WITHER_STAFF_LORE, util.WITHER_STAFF_NAME, Material.BLAZE_ROD);
            ItemMeta im = witherStaff.getItemMeta();
            witherStaff.setItemMeta(im);
            ((Player) sender).getInventory().addItem(witherStaff);
            sender.sendMessage("§7Gave you §f[" + util.WITHER_STAFF_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
