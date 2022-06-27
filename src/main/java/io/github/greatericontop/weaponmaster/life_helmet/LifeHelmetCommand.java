package io.github.greatericontop.weaponmaster.life_helmet;

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
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class LifeHelmetCommand implements CommandExecutor {

    private final Util util;
    private final LifeHelmetListener listener;
    public LifeHelmetCommand(LifeHelmetListener listener) {
        util = new Util(null);
        this.listener = listener;
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lHelmet of Life");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/lifehelmet give§3 to give yourself the item.");
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
            ItemStack lifeHelmet = util.generateMeta(util.LIFE_HELMET_LORE, util.LIFE_HELMET_NAME, Material.DIAMOND_HELMET);
            ((Player) sender).getInventory().addItem(lifeHelmet);
            sender.sendMessage("§7Gave you §f[" + util.LIFE_HELMET_NAME + "§f]§7.");
            return true;
        }
        if (args.length == 2 && (args[0].equalsIgnoreCase("cooldown") || args[0].equalsIgnoreCase("cd")
                || args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("cdreset"))) {
            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage("§cError: §4player is null");
                return true;
            }
            listener.cooldown.put(player.getUniqueId(), 0);
            sender.sendMessage(String.format("§3Reset the cooldown for §4%s§3.", player.getName()));
            return true;
        }
        return false;
    }
}
