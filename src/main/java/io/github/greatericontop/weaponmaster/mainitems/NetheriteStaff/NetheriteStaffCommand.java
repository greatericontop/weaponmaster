package io.github.greatericontop.weaponmaster.mainitems.NetheriteStaff;

/*
 * WeaponMaster Copyright (C) 2021-present greateric.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty  of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

public class NetheriteStaffCommand implements CommandExecutor {

    private final Util util;
    public NetheriteStaffCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lNetherite Staff");
        to.sendMessage("§e§oby greateric, gerseneck");
        to.sendMessage("§3Use §2/netheritestaff give§3 to give yourself the item.");
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
            ItemStack netheriteStaff = util.generateMeta(util.NETHERITE_STAFF_LORE, util.NETHERITE_STAFF_NAME, Material.NETHERITE_SHOVEL);
            netheriteStaff.addUnsafeEnchantment(Enchantment.LUCK, 1);
            netheriteStaff.addUnsafeEnchantment(Enchantment.MENDING, 1);
            ItemMeta im = netheriteStaff.getItemMeta();
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "weaponmaster", 0.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "weaponmaster", 20, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            netheriteStaff.setItemMeta(im);
            ((Player) sender).getInventory().addItem(netheriteStaff);
            sender.sendMessage("§7Gave you §f[" + util.NETHERITE_STAFF_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
