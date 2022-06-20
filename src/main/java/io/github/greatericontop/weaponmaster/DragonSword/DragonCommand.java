package io.github.greatericontop.weaponmaster.DragonSword;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.greatericontop.weaponmaster.Util.Util;

public class DragonCommand implements CommandExecutor {

    private final Util util;
    public DragonCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§2§lDragon Sword");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/dragonsword give§3 to give yourself the item.");
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
            ItemStack drag = util.generateMeta(util.DRAGON_SWORD_LORE, util.DRAGON_SWORD_NAME, Material.NETHERITE_SWORD);
            ((Player) sender).getInventory().addItem(drag);
            sender.sendMessage("§7Gave you §f[" + util.DRAGON_SWORD_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
