package io.github.greatericontop.weaponmaster.items.hermes_boots;

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

public class HermesCommand implements CommandExecutor {

    private static final UUID modifierToughness = UUID.fromString("00000000-1111-0000-0000-bd1d2340551d");
    private static final UUID modifierSpeed = UUID.fromString("00000000-1111-0000-0000-510c1e6d1ca7");
    private static final UUID modifierArmor = UUID.fromString("00000000-1111-0000-0000-000000000001");
    private static final UUID modifierKnockback = UUID.fromString("00000000-1111-0000-0000-000000000002");
    private final Util util;
    public HermesCommand() {
        util = new Util(null);
    }

    public static ItemStack giveHermes(Util util) {
        ItemStack hermes = util.generateMeta(util.HERMES_BOOTS_LORE, util.HERMES_BOOTS_NAME, Material.NETHERITE_BOOTS);
        ItemMeta itemMeta = hermes.getItemMeta();
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS,
                new AttributeModifier(modifierToughness, "hermesToughness", 5.0,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED,
                new AttributeModifier(modifierSpeed, "hermesSpeed", 0.1,
                        AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET));
        itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR,
                new AttributeModifier(modifierArmor, "hermesDefaultArmor", 3.0,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE,
                new AttributeModifier(modifierKnockback, "hermesDefaultKnockback", 0.1,
                        AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        hermes.setItemMeta(itemMeta);
        return hermes;
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lHermes' Boots");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/hermesboots give§3 to give yourself the item.");
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
            ItemStack hermes = giveHermes(util);
            ((Player) sender).getInventory().addItem(hermes);
            sender.sendMessage("§7Gave you §f[" + util.HERMES_BOOTS_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}
