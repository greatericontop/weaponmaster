package io.github.greatericontop.weaponmaster.items.valkyrie_axe;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.greatericontop.weaponmaster.util.Util;

import java.util.UUID;

public class ValkyrieCommand implements CommandExecutor {

    private final Util util;
    public ValkyrieCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lValkyrie Axe");
        to.sendMessage("§e§oby bigfatmidget");
        to.sendMessage("§3Use §2/valkyrieaxe give§3 to give yourself the item.");
    }

    public static ItemStack giveValkyrie(Util util) {
        ItemStack valkyrie = util.generateMeta(util.VALKYRIE_AXE_LORE, util.VALKYRIE_AXE_NAME, Material.IRON_AXE);
        ItemMeta im = valkyrie.getItemMeta();
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "weaponmaster", 13.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "weaponmaster", -3.4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        valkyrie.setItemMeta(im);
        return valkyrie;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendInfo(sender);
            return true;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("give")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("§3Sorry, players only.");
                return true;
            }
            ItemStack valkyrie = giveValkyrie(util);
            ((Player) sender).getInventory().addItem(valkyrie);
            sender.sendMessage("§7Gave you §f[" + util.VALKYRIE_AXE_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
