package io.github.greatericontop.weaponmaster.minorcrafts;

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

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MinorItemCommand implements CommandExecutor {

    private final MinorItems minorItems;
    public MinorItemCommand() {
        this.minorItems = new MinorItems();
    }

    private void sendInfo(CommandSender to) {
        to.sendMessage("§6----------------------------------------");
        to.sendMessage("§4§lMinor Items");
        to.sendMessage("§3Use §2/minoritem list§3 to view the available items.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sendInfo(sender);
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage("§3Sorry, players only.");
            return true;
        }
        if (args[0].equalsIgnoreCase("list")) {
            sender.sendMessage("§7Heart of Leviathan - /minoritem leviathanHeart");
            sender.sendMessage("§7Netherite Staff Core - /minoritem coreStaff");
            sender.sendMessage("§7Flask of Ichor - /minoritem flaskIchor");
            sender.sendMessage("§7Dragon Scale - /minoritem dragonScale");
            sender.sendMessage("§7Magic Energy Bar - /minoritem magicEnergyBar");
            sender.sendMessage("§7Expert Seal - /minoritem expertSeal");
            sender.sendMessage("§7Dragon Wing - /minoritem dragonWing");
            sender.sendMessage("§7Dragon Horn - /minoritem dragonHorn");
            sender.sendMessage("§7Silky String - /minoritem silkyString");
            sender.sendMessage("§7Life Core - /minoritem lifeCore");
            sender.sendMessage("§7End Artifact - /minoritem endArtifact");
            sender.sendMessage("§7Super XP Bottle - /minoritem superXPBottle");
            sender.sendMessage("§7Crude Plutonium - /minoritem crudePlutonium");
            sender.sendMessage("§7Weapons Grade Plutonium - /minoritem weaponsGradePlutonium");
            sender.sendMessage("§7Wither Dye - /minoritem witherDye");
            sender.sendMessage("§7Expert Dye - /minoritem expertDye");
            return true;
        }
        if (args[0].equalsIgnoreCase("leviathanHeart")) {
            ItemStack item = minorItems.generateLeviathanHeartItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.LEVIATHAN_HEART_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("coreStaff")) {
            ItemStack item = minorItems.generateCoreStaffItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.CORE_STAFF_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("flaskIchor")) {
            ItemStack item = minorItems.generateFlaskIchorItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.FLASK_ICHOR_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("dragonScale")) {
            ItemStack item = minorItems.generateDragonScaleItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.DRAGON_SCALE_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("magicEnergyBar")) {
            ItemStack item = minorItems.generateMagicEnergyBarItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.MAGIC_ENERGY_BAR_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("expertSeal")) {
            ItemStack item = minorItems.generateExpertSealItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.EXPERT_SEAL_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("dragonWing")) {
            ItemStack item = minorItems.generateDragonWingItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.DRAGON_WING_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("dragonHorn")) {
            ItemStack item = minorItems.generateDragonHornItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.DRAGON_HORN_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("silkyString")) {
            ItemStack item = minorItems.generateSilkyStringItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.SILKY_STRING_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("lifeCore")) {
            ItemStack item = minorItems.generateLifeCoreItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.LIFE_CORE_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("endArtifact")) {
            ItemStack item = minorItems.generateEndArtifactItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.END_ARTIFACT_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("superXPBottle")) {
            ItemStack item = minorItems.generateSuperXPBottleItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.SUPER_XP_BOTTLE_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("crudePlutonium")) {
            ItemStack item = minorItems.generateCrudePlutoniumItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.CRUDE_PLUTONIUM_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("weaponsGradePlutonium")) {
            ItemStack item = minorItems.generateWeaponsGradePlutoniumItemStack();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.WEAPONS_GRADE_PLUTONIUM_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("witherDye")) {
            ItemStack item = minorItems.generateWitherDye();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.WITHER_DYE_NAME + "§f]§7.");
            return true;
        }
        if (args[0].equalsIgnoreCase("expertDye")) {
            ItemStack item = minorItems.generateExpertDye();
            ((Player) sender).getInventory().addItem(item);
            sender.sendMessage("§7Gave you §f[" + minorItems.EXPERT_DYE_NAME + "§f]§7.");
            return true;
        }
        return false;
    }
}