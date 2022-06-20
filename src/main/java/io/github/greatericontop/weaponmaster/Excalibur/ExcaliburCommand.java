package io.github.greatericontop.weaponmaster.Excalibur;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.greatericontop.weaponmaster.Util.Util;

public class ExcaliburCommand implements CommandExecutor {

    private final Util util;
    public ExcaliburCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lExcalibur");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/excalibur give§3 to give yourself the item.");
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
            ItemStack excalibur = util.generateMeta(util.EXCALIBUR_LORE, util.EXCALIBUR_NAME, Material.DIAMOND_SWORD);
            excalibur.addEnchantment(Enchantment.DURABILITY, 2);
            ((Player) sender).getInventory().addItem(excalibur);
            sender.sendMessage("§7Gave you §f[" + util.EXCALIBUR_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
