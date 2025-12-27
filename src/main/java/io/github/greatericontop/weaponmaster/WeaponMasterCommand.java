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

import io.github.greatericontop.weaponmaster.utils.TrueDamageHelper;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
            TrueDamageHelper.dealTrueDamage(player, 30.0);
            player.sendMessage("§7I just damaged you for 30 true damage!");
        }
        if (args.length >= 1 && args[0].equals("debug1")) {
            sender.sendMessage("§7dragon: " + plugin.dragonManager.currentlyActiveDragon);
            sender.sendMessage("§7explosive damage dealt: " + plugin.dragonManager.damageDealtToDragonThroughExplosions);
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
            if (im == null) {
                sender.sendMessage("§cError: §4This item does not have any ItemMeta.");
                return true;
            }
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
            int amount;
            try {
                if (args[1].equalsIgnoreCase("max")) {
                    amount = 99;
                } else {
                    amount = Integer.parseInt(args[1]);
                }
            } catch (NumberFormatException e) {
                sender.sendMessage("§cError: §4You gave an invalid number.");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cError: §4Must be a player.");
                return true;
            }
            if (amount > 99) {
                sender.sendMessage("§6Warning: Stack sizes above 99 will not save and can cause data loss or duplication exploits!");
            }
            ((Player) sender).getInventory().getItemInMainHand().setAmount(amount);
            sender.sendMessage(String.format("§3Set stack size to §4%d§3.", amount));
            return true;
        }

        if (args.length >= 1 && args[0].equals("vanillastack")) {
            if (args.length < 2) {
                sender.sendMessage("§cError: §4Missing arguments: /weaponmaster vanillastack <number>");
                return true;
            }
            int amount;
            try {
                if (args[1].equalsIgnoreCase("max")) {
                    amount = 99;
                } else {
                    amount = Integer.parseInt(args[1]);
                }
            } catch (NumberFormatException e) {
                sender.sendMessage("§cError: §4You gave an invalid number.");
                return true;
            }
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cError: §4Must be a player.");
                return true;
            }
            amount = Math.min(Math.max(amount, 1), 99);
            ItemStack stack = ((Player) sender).getInventory().getItemInMainHand();
            ItemMeta im = stack.getItemMeta();
            if (im == null) {
                sender.sendMessage("§3The stack size of this item can't be changed!");
                return true;
            }
            im.setMaxStackSize(amount);
            stack.setItemMeta(im);
            stack.setAmount(amount);
            sender.sendMessage(String.format("§3Set stack size to §4%d§3.", amount));
            return true;
        }

        if (args.length >= 1 && args[0].equals("forceenchant")) {
            if (args.length < 3) {
                sender.sendMessage("§cError: §4Missing arguments: /weaponmaster forceenchant <enchant> <level>");
                return true;
            }
            Enchantment enchant;
            try {
                enchant = Enchantment.getByKey(NamespacedKey.minecraft(args[1]));
            } catch (IllegalArgumentException e) {
                sender.sendMessage("§cError: §4You gave an invalid enchantment. Try using Minecraft namespaced IDs.");
                return true;
            }
            int level;
            try {
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
            ItemStack stack = ((Player) sender).getInventory().getItemInMainHand();
            ItemMeta im = stack.getItemMeta();
            if (im == null) {
                sender.sendMessage("§cError: §4This item does not have any ItemMeta.");
                return true;
            }
            if (level <= 0) {
                im.removeEnchant(enchant);
                stack.setItemMeta(im);
                sender.sendMessage(String.format("§3Removed enchant §4%s§3!", enchant.getKey()));
            } else {
                im.addEnchant(enchant, level, true);
                stack.setItemMeta(im);
                sender.sendMessage(String.format("§3Added §4%s §3level §4%d§3!", enchant.getKey(), level));
            }
            return true;
        }

        if (args.length >= 1 && args[0].equals("addpotioneffect")) {
            if (args.length < 4) {
                sender.sendMessage("§cError: §4Missing arguments: /weaponmaster forceenchant <effect type> <duration (seconds)> <amplifier>");
                return true;
            }
            PotionEffectType potionEffectType;
            try {
                potionEffectType = PotionEffectType.getByKey(NamespacedKey.minecraft(args[1]));
            } catch (IllegalArgumentException e) {
                sender.sendMessage("§cError: §4You gave an invalid potion effect type. Try using Minecraft namespaced IDs.");
                return true;
            }
            if (potionEffectType == null) {
                sender.sendMessage("§cError: §4That potion effect type does not exist. Try using Minecraft namespaced IDs.");
                return true;
            }
            int lengthTicks;
            if (args[2].equalsIgnoreCase("max")) {
                lengthTicks = 2147483647;
            } else {
                double lengthSeconds;
                try {
                    lengthSeconds = Double.parseDouble(args[2]);
                } catch (NumberFormatException e) {
                    sender.sendMessage("§cError: §4You gave an invalid number. Please give a decimal value, or use 'max'.");
                    return true;
                }
                lengthTicks = Math.min(Math.max((int) (lengthSeconds * 20), 1), 2147483647);
            }
            int amplifier;
            try {
                amplifier = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                sender.sendMessage("§cError: §4You gave an invalid number. Please give an int.");
                return true;
            }
            if (amplifier < 0 || amplifier > 127) {
                sender.sendMessage("§cError: §4Amplifier must be between 0 and 127.");
                return true;
            }
            ItemStack stack = ((Player) sender).getInventory().getItemInMainHand();
            ItemMeta im = stack.getItemMeta();
            if (im == null) {
                sender.sendMessage("§cError: §4This item does not have any ItemMeta.");
                return true;
            }
            if (!(im instanceof PotionMeta)) {
                sender.sendMessage("§cError: §4This item does not support potion effects.");
                return true;
            }
            PotionMeta pm = (PotionMeta) im;
            pm.addCustomEffect(new PotionEffect(potionEffectType, lengthTicks, amplifier), true);
            stack.setItemMeta(pm);
            sender.sendMessage("§3Added potion effect!");
            return true;
        }

        sender.sendMessage("----------------------------------------");
        sender.sendMessage("");
        sender.sendMessage("§4§lWeaponMaster");
        sender.sendMessage("§7§oby greateric");
        sender.sendMessage("");
        sender.sendMessage("§e/weaponmaster attributemodifier");
        sender.sendMessage("§e/weaponmaster illegalstack");
        sender.sendMessage("§e/weaponmaster forceenchant");
        sender.sendMessage("§e/weaponmaster addpotioneffect");
        sender.sendMessage("");
        sender.sendMessage("----------------------------------------");
        return true;
    }
}
