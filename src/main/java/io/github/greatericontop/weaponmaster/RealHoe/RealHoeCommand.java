package io.github.greatericontop.weaponmaster.RealHoe;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.greatericontop.weaponmaster.utils.Util;

public class RealHoeCommand implements CommandExecutor {
    
    private final Util util;
    public RealHoeCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lReal Hoe");
        to.sendMessage("§e§oby bigfatmiget");
        to.sendMessage("§3Use §2/realhoe give§3 to give yourself the item.");
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
            ItemStack realHoe = util.generateMeta(util.REAL_HOE_LORE, util.REAL_HOE_NAME, Material.DIAMOND_HOE);
            ((Player) sender).getInventory().addItem(realHoe);
            sender.sendMessage("§7Gave you §f[" + util.REAL_HOE_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
