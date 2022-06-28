package io.github.greatericontop.weaponmaster.items.vamp_axe;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.greatericontop.weaponmaster.util.Util;

public class VampCommand implements CommandExecutor {

    private final Util util;
    public VampCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lVampire's Axe");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/vampaxe give§3 to give yourself the item.");
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
            ItemStack vampaxe = util.generateMeta(util.VAMP_AXE_LORE, util.VAMP_AXE_NAME, Material.NETHERITE_AXE);
            ((Player) sender).getInventory().addItem(vampaxe);
            sender.sendMessage("§7Gave you §f[" + util.VAMP_AXE_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
