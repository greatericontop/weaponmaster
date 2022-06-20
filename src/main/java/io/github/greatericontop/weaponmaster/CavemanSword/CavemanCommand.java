package io.github.greatericontop.weaponmaster.CavemanSword;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.greatericontop.weaponmaster.Util.Util;

import java.util.List;

public class CavemanCommand implements CommandExecutor {

    private final Util util;
    public CavemanCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lCaveman Sword");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/cavemansword give§3 to give yourself the item.");
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
            ItemStack caveman = util.generateMeta(util.CAVEMAN_SWORD_LORE, util.CAVEMAN_SWORD_NAME, Material.STONE_SWORD);
            ((Player) sender).getInventory().addItem(caveman);
            sender.sendMessage("§7Gave you §f[" + util.CAVEMAN_SWORD_NAME + "§f]§7.");
            return true;
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("setStats")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§3Sorry, players only.");
                return true;
            }
            int level, exp;
            try {
                level = Integer.parseInt(args[1]);
                exp = Integer.parseInt(args[2]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cError: §4You need integers <level> and <exp>.");
                return true;
            }
            Player player = (Player) sender;
            ItemMeta iMeta = player.getInventory().getItemInMainHand().getItemMeta();
            List<String> lore = iMeta.getLore();
            lore.set(util.CAVEMAN_LVL, String.format("§6Sharpness Level: §b%d", level));
            lore.set(util.CAVEMAN_EXP, String.format("§6Experience: §b%d", exp));
            iMeta.setLore(lore);
            player.getInventory().getItemInMainHand().setItemMeta(iMeta);
            player.sendMessage("§3Success. You may need to level up to fully update the changes.");
        }
        return false;
    }
}
