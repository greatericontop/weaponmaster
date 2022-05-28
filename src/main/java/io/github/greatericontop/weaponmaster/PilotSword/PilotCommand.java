package io.github.greatericontop.weaponmaster.PilotSword;

/*
    Copyright (C) 2021 greateric.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import io.github.greatericontop.weaponmaster.utils.Util;
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

import java.util.UUID;

public class PilotCommand implements CommandExecutor {

    private final Util util;
    public PilotCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lPilot Sword");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/pilotsword give§3 to give yourself the item.");
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
            ItemStack pilotSword = util.generateMeta(util.PILOT_SWORD_LORE, util.PILOT_SWORD_NAME, Material.NETHERITE_SWORD);
            ItemMeta im = pilotSword.getItemMeta();
            // +5.5 == 6.5, between iron sword and diamond sword
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "weaponmaster", 4.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            im.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "weaponmaster", 20.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
            pilotSword.setItemMeta(im);
            ((Player) sender).getInventory().addItem(pilotSword);
            sender.sendMessage("§7Gave you §f[" + util.PILOT_SWORD_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
