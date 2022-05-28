package io.github.greatericontop.weaponmaster.ValkyrieAxe;

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.w3c.dom.Attr;

import java.util.UUID;

public class ValkyrieAxeCommand implements CommandExecutor {

    private final Util util;
    public ValkyrieAxeCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lValkyrie Axe");
        to.sendMessage("§e§oby bigfatmidget");
        to.sendMessage("§3Use §2/valkyrieaxe give§3 to give yourself the item.");
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
            ItemStack valkyrieaxe = util.generateMeta(util.VALKYRIE_AXE_LORE, util.VALKYRIE_AXE_NAME, Material.IRON_AXE);
            ItemMeta im = valkyrieaxe.getItemMeta();
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "weaponmaster", 13.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "weaponmaster", 0.6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            valkyrieaxe.setItemMeta(im);
            ((Player) sender).getInventory().addItem(valkyrieaxe);
            sender.sendMessage("§7Gave you §f[" + util.VALKYRIE_AXE_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
