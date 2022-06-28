package io.github.greatericontop.weaponmaster.items.life_helmet;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.greatericontop.weaponmaster.util.Util;

public class LifeHelmetCommand implements CommandExecutor {

    private final Util util;
    private final LifeHelmetListener listener;
    public LifeHelmetCommand(LifeHelmetListener listener) {
        util = new Util(null);
        this.listener = listener;
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lHelmet of Life");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/lifehelmet give§3 to give yourself the item.");
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
            ItemStack lifeHelmet = util.generateMeta(util.LIFE_HELMET_LORE, util.LIFE_HELMET_NAME, Material.DIAMOND_HELMET);
            ((Player) sender).getInventory().addItem(lifeHelmet);
            sender.sendMessage("§7Gave you §f[" + util.LIFE_HELMET_NAME + "§f]§7.");
            return true;
        }
        if (args.length == 2 && (args[0].equalsIgnoreCase("cooldown") || args[0].equalsIgnoreCase("cd")
                || args[0].equalsIgnoreCase("reset") || args[0].equalsIgnoreCase("cdreset"))) {
            Player player = Bukkit.getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage("§cError: §4player is null");
                return true;
            }
            listener.cooldown.put(player.getUniqueId(), 0);
            sender.sendMessage(String.format("§3Reset the cooldown for §4%s§3.", player.getName()));
            return true;
        }
        return false;
    }
}
