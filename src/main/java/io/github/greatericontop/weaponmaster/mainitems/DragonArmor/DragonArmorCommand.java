package io.github.greatericontop.weaponmaster.mainitems.DragonArmor;

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
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class DragonArmorCommand implements CommandExecutor {

    public Material[] armors = {Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS};
    private final Random rnd = new Random();
    private final Util util;
    public DragonArmorCommand() {
        util = new Util(null);
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lDragon Armor");
        to.sendMessage("§e§oby greateric");
        to.sendMessage("§3Use §2/dragonarmor give§3 or §2/dragonarmor giveall§3 to give yourself the item.");
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
            ItemStack drag = util.generateMeta(util.DRAGON_ARMOR_LORE, util.DRAGON_ARMOR_NAME, armors[rnd.nextInt(armors.length)]);
            ((Player) sender).getInventory().addItem(drag);
            sender.sendMessage("§7Gave you §f[" + util.DRAGON_ARMOR_NAME + "§f]§7.");
            return true;
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("giveall")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§3Sorry, players only.");
                return true;
            }
            Player player = (Player) sender;
            ItemStack drag = util.generateMeta(util.DRAGON_ARMOR_LORE, util.DRAGON_ARMOR_NAME, Material.NETHERITE_HELMET);
            player.getInventory().addItem(drag);
            drag = util.generateMeta(util.DRAGON_ARMOR_LORE, util.DRAGON_ARMOR_NAME, Material.NETHERITE_CHESTPLATE);
            player.getInventory().addItem(drag);
            drag = util.generateMeta(util.DRAGON_ARMOR_LORE, util.DRAGON_ARMOR_NAME, Material.NETHERITE_LEGGINGS);
            player.getInventory().addItem(drag);
            drag = util.generateMeta(util.DRAGON_ARMOR_LORE, util.DRAGON_ARMOR_NAME, Material.NETHERITE_BOOTS);
            player.getInventory().addItem(drag);
            sender.sendMessage("§7Gave you §f[" + util.DRAGON_ARMOR_NAME + "§f]§7 x4.");
            return true;
        }
        return false;
    }
}
