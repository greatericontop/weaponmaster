package io.github.greatericontop.weaponmaster.Fireball;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.greatericontop.weaponmaster.Util.Util;

public class FireballCommand implements CommandExecutor {
    private final Util util;
    public FireballCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lFireball");
        to.sendMessage("§e§oby greateric, gerseneck");
        to.sendMessage("§3Use §2/fireball give§3 to give yourself the item.");
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
            ItemStack fireball = util.generateMeta(util.FIREBALL_LORE, util.FIREBALL_NAME, Material.FIRE_CHARGE);
            ((Player) sender).getInventory().addItem(fireball);
            sender.sendMessage("§7Gave you §f[" + util.FIREBALL_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
