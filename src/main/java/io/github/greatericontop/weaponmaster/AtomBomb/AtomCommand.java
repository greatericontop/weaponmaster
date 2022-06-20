package io.github.greatericontop.weaponmaster.AtomBomb;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.greatericontop.weaponmaster.Util.Util;

public class AtomCommand implements CommandExecutor {

    private final Util util;
    public AtomCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lAtom Bomb");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/atombomb give§3 to give yourself the item.");
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
            ItemStack atomBomb = util.generateMeta(util.ATOM_BOMB_LORE, util.ATOM_BOMB_NAME, Material.TNT);
            atomBomb.addUnsafeEnchantment(Enchantment.LUCK, 1);
            ((Player) sender).getInventory().addItem(atomBomb);
            sender.sendMessage("§7Gave you §f[" + util.ATOM_BOMB_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
