package io.github.greatericontop.weaponmaster;

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
import org.bukkit.NamespacedKey;
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
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import io.github.greatericontop.weaponmaster.dragon_manager.LootDropper;

import java.util.UUID;

public class WeaponMasterCommand implements CommandExecutor {

    private WeaponMasterMain plugin;
    public WeaponMasterCommand(WeaponMasterMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 1 && args[0].equals("debug")) {
            Player player = (Player) sender;
            ItemStack itemStack = new ItemStack(Material.POTION, 1);
            PotionMeta im = (PotionMeta) itemStack.getItemMeta();
            im.setBasePotionData(new PotionData(PotionType.SPEED, true, false));
            itemStack.setItemMeta(im);
            player.getInventory().addItem(itemStack);
            player.sendMessage("§7Given!");
        }
        if (args.length >= 1 && args[0].equals("debug1")) {
            sender.sendMessage("§7dragon: " + plugin.dragonManager.currentlyActiveDragon);
            sender.sendMessage("§7explosive damage dealt: " + plugin.dragonManager.damageDealtToDragonThroughExplosions);
            int wasted = new LootDropper(plugin)
                    .doAllDrops(
                            plugin.dragonManager.currentlyActiveDragon.getWorld(),
                            1000, ((Player) sender)
            );
            sender.sendMessage("§7"+wasted+" was wasted");
            sender.sendMessage("§7current hp: §c" + plugin.dragonManager.currentlyActiveDragon.getHealth());
            return true;
        }
        if (args.length >= 1 && args[0].equals("attributemodifier")) {
            if (args.length < 5) {
                sender.sendMessage("§cError: §4Missing arguments: /weaponmaster attributemodifier <attribute> <operation> <amount> <slot> [<optional uuid>]");
                return true;
            }
            Attribute attribute;
            AttributeModifier.Operation operation;
            double amount;
            EquipmentSlot slot;
            UUID uuid;
            try {
                attribute = Attribute.valueOf(args[1]);
            } catch (IllegalArgumentException e) {
                sender.sendMessage("§cError: §4You gave an invalid attribute. You need bukkit names, such as 'GENERIC_MAX_HEALTH'.");
                return true;
            }
            try {
                operation = AttributeModifier.Operation.valueOf(args[2]);
            } catch (IllegalArgumentException e) {
                sender.sendMessage("§cError: §4You gave an invalid operation. Choose from 'ADD_NUMBER', 'ADD_SCALAR', 'MULTIPLY_SCALAR_1'");
                return true;
            }
            try {
                amount = Double.parseDouble(args[3]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cError: §4You gave an invalid number. Please give a decimal value.");
                return true;
            }
            try {
                slot = EquipmentSlot.valueOf(args[4]);
            } catch (IllegalArgumentException e) {
                sender.sendMessage("§cError: §4You gave an invalid equipment slot. You need bukkit names, such as 'HEAD'.");
                return true;
            }
            if (args.length >= 6) {
                try {
                    uuid = UUID.fromString(args[5]);
                } catch (IllegalArgumentException e) {
                    sender.sendMessage("§cError: §4Invalid UUID provided, try leaving it blank.");
                    return true;
                }
            } else {
                uuid = UUID.randomUUID();
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cError: §4Must be a player.");
                return true;
            }
            Player player = (Player) sender;
            ItemStack itemStack = player.getInventory().getItemInMainHand();
            ItemMeta im = itemStack.getItemMeta();
            im.addAttributeModifier(attribute, new AttributeModifier(uuid, "weaponmaster", amount, operation, slot));
            itemStack.setItemMeta(im);
            player.sendMessage(String.format("§3Successfully added attribute modifier §4%s§3.", uuid));
            return true;
        }

        if (args.length >= 1 && args[0].equals("illegalstack")) {
            if (args.length < 2) {
                sender.sendMessage("§cError: §4Missing arguments: /weaponmaster illegalstack <number>");
                return true;
            }
            byte amount;
            try {
                if (args[1].equalsIgnoreCase("max")) {
                    amount = 127;
                } else {
                    amount = Byte.parseByte(args[1]);
                }
            } catch (NumberFormatException e) {
                sender.sendMessage("§cError: §4You gave an invalid number. Please give a byte from -128 to 127, or use 'max'.");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cError: §4Must be a player.");
                return true;
            }
            ((Player) sender).getInventory().getItemInMainHand().setAmount(amount);
            sender.sendMessage(String.format("§3Set stack size to §4%d§3.", amount));
            return true;
        }

        if (args.length >= 1 && args[0].equals("forceenchant")) {
            if (args.length < 3) {
                sender.sendMessage("§cError: §4Missing arguments: /weaponmaster forceenchant <enchant> <level>");
                return true;
            }
            Enchantment enchant;
            int level;
            try {
                enchant = Enchantment.getByKey(NamespacedKey.minecraft(args[1]));
                if (args[2].equalsIgnoreCase("max")) {
                    level = 255;
                } else {
                    level = Integer.parseInt(args[2]);
                }
            } catch (NumberFormatException e) {
                sender.sendMessage("§cError: §4You gave an invalid number. Please give an int, or use 'max'.");
                return true;
            }
            if (enchant == null) {
                sender.sendMessage("§cError: §4That enchant does not exist. Try using Minecraft namespaced IDs.");
                return true;
            }
            if (level > 255) {
                level = 255;
                sender.sendMessage("§cWarning: §4Enchantment level in 1.18 is limited to 255.");
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cError: §4Must be a player.");
                return true;
            }
            ((Player) sender).getInventory().getItemInMainHand().addUnsafeEnchantment(enchant, level);
            sender.sendMessage(String.format("§3Added §4%s §3level §4%d§3!", enchant.getKey(), level));
            return true;
        }

        sender.sendMessage("----------------------------------------");
        sender.sendMessage("");
        sender.sendMessage("§4§lWeaponMaster");
        sender.sendMessage("§7§oby greateric");
        sender.sendMessage("");
        sender.sendMessage("§e/weaponmaster forceenchant");
        sender.sendMessage("§e/weaponmaster illegalstack");
        sender.sendMessage("§e/weaponmaster attributemodifier");
        sender.sendMessage("");
        sender.sendMessage("----------------------------------------");
        return true;
    }
}
