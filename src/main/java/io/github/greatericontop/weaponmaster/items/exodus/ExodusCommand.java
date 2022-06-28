package io.github.greatericontop.weaponmaster.items.exodus;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.greatericontop.weaponmaster.util.Util;

public class ExodusCommand implements CommandExecutor {

    private final Util util;
    public ExodusCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lExodus Sword");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/exodus give§3 to give yourself the item.");
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
            ItemStack exodus = util.generateMeta(util.EXODUS_LORE, util.EXODUS_NAME, Material.DIAMOND_HELMET);
            ((Player) sender).getInventory().addItem(exodus);
            sender.sendMessage("§7Gave you §f[" + util.EXODUS_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
