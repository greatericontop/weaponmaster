package io.github.greatericontop.weaponmaster.PoseidonTrident;

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TridentCommand implements CommandExecutor {
    private final Util util;
    public TridentCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§bPoseidon's Trident");
        to.sendMessage("§e§oby gerseneck");
        to.sendMessage("§3Use §2/poseidontrident give§3 to give yourself the item.");
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
            ItemStack trident = util.generateMeta(util.POSEIDON_TRIDENT_LORE, util.POSEIDON_TRIDENT_NAME, Material.TRIDENT);
            ((Player) sender).getInventory().addItem(trident);
            sender.sendMessage("§7Gave you §f[" + util.POSEIDON_TRIDENT_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
