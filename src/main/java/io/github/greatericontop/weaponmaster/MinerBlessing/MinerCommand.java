package io.github.greatericontop.weaponmaster.MinerBlessing;

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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MinerCommand implements CommandExecutor {

    private final Util util;
    public MinerCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lMiner's Blessing");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/minersblessing give§3 to give yourself the item.");
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
            ItemStack miner = util.generateMeta(util.MINERS_BLESSING_LORE, util.MINERS_BLESSING_NAME, Material.NETHERITE_PICKAXE);
            ((Player) sender).getInventory().addItem(miner);
            sender.sendMessage("§7Gave you §f[" + util.MINERS_BLESSING_NAME + "§f]§7.");
            return true;
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("setStats")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§3Sorry, players only.");
                return true;
            }
            int tier, exp;
            try {
                tier = Integer.parseInt(args[1]);
                exp = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cError: §4You need integers <tier> and <exp>.");
                return true;
            }
            Player player = (Player) sender;
            ItemMeta iMeta = player.getInventory().getItemInMainHand().getItemMeta();
            List<String> lore = iMeta.getLore();
            lore.set(util.MINER_LVL, String.format("§6Tier: §b%d", tier));
            lore.set(util.MINER_EXP, String.format("§6Experience: §b%d", exp));
            iMeta.setLore(lore);
            player.getInventory().getItemInMainHand().setItemMeta(iMeta);
            player.sendMessage("§3Success. You may need to level up to fully update the changes.");
        }
        return false;
    }
}
