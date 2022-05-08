package io.github.greatericontop.weaponmaster.NetheriteStaff;

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NetheriteStaffCommand implements CommandExecutor {

    private final Util util;
    public NetheriteStaffCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lNetherite Staff");
        to.sendMessage("§e§oby greateric, gerseneck");
        to.sendMessage("§3Use §2/netheritest give§3 to give yourself the item.");
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
            ItemStack netheriteStaff = util.generateMeta(util.NETHERITE_STAFF_LORE, util.NETHERITE_STAFF_NAME, Material.NETHERITE_SHOVEL);
            ((Player) sender).getInventory().addItem(netheriteStaff);
            sender.sendMessage("§7Gave you §f[" + util.NETHERITE_STAFF_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
