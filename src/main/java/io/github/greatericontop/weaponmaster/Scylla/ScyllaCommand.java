package io.github.greatericontop.weaponmaster.Scylla;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.greatericontop.weaponmaster.Util.Util;

public class ScyllaCommand implements CommandExecutor {

    private final Util util;
    public ScyllaCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lScylla's Chestplate");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/scylla give§3 to give yourself the item.");
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
            ItemStack scylla = util.generateMeta(util.SCYLLA_CHESTPLATE_LORE, util.SCYLLA_CHESTPLATE_NAME, Material.DIAMOND_CHESTPLATE);
            ItemMeta itemMeta = scylla.getItemMeta();
            scylla.setItemMeta(itemMeta);
            ((Player) sender).getInventory().addItem(scylla);
            sender.sendMessage("§7Gave you §f[" + util.SCYLLA_CHESTPLATE_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
