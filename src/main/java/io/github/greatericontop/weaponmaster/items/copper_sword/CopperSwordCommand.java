package io.github.greatericontop.weaponmaster.items.copper_sword;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.greatericontop.weaponmaster.util.Util;

public class CopperSwordCommand implements CommandExecutor {
    private final Util util;
    public CopperSwordCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lCopper Sword");
        to.sendMessage("§e§oby gerseneck");
        to.sendMessage("§3Use §2/coppersword give§3 to give yourself the item.");
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
            ItemStack copperSword = util.generateMeta(util.COPPER_SWORD_LORE, util.COPPER_SWORD_NAME, Material.GOLDEN_SWORD);
            copperSword.addUnsafeEnchantment(Enchantment.DURABILITY, 9);
            copperSword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 3);
            ((Player) sender).getInventory().addItem(copperSword);
            sender.sendMessage("§7Gave you §f[" + util.COPPER_SWORD_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
