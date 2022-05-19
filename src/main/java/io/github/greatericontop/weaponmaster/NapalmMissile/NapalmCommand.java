package io.github.greatericontop.weaponmaster.NapalmMissile;

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NapalmCommand implements CommandExecutor {
    
    private final Util util;
    public NapalmCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lNapalm Missile");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/napalm give§3 to give yourself the item.");
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
            ItemStack napalm = util.generateMeta(util.NAPALM_MISSILE_LORE, util.NAPALM_MISSILE_NAME, Material.SNOWBALL);
            ((Player) sender).getInventory().addItem(napalm);
            sender.sendMessage("§7Gave you §f[" + util.NAPALM_MISSILE_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
