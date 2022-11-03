package io.github.greatericontop.weaponmaster.dragondescent;

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

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class DescentCommand implements CommandExecutor {

    private final int INVENTORY_SIZE = 54;
    private final WeaponMasterMain plugin;
    public DescentCommand(WeaponMasterMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cPlayers only, sorry!");
            return true;
        }
        Player player = (Player) sender;
        openInventory(player);
        return true;
    }

    public void openInventory(Player player) {
        plugin.descent.updateShards(player.getUniqueId());
        Inventory gui = Bukkit.createInventory(player, INVENTORY_SIZE, plugin.descent.DESCENT_GUI_NAME);
        addItems(player, gui,
                plugin.descent.getShards(player.getUniqueId()),
                plugin.descent.getDragonPower(player.getUniqueId()),
                plugin.descent.getPrice(plugin.descent.getPurchases(player.getUniqueId()) + 1)
        );
        player.openInventory(gui);
    }

    private String renderLoreLevel(Player player, String upgradeType) {
        int level = plugin.descent.getUpgrade(player, upgradeType);
        return String.format("§fLevel: §b%d §f/ §b%d", level, plugin.descent.MAX_LEVEL);
    }

    private void addItems(Player player, Inventory gui, int shardCount, int dragonPowerCount, int price) {
        // fill everything with dummy items first
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            gui.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));
        }

        ItemStack shards = newItemStack(Material.DIAMOND, 1,
                false,
                "§bShards",
                "§7You have §c"+shardCount+"§7 shards.");
        gui.setItem(18, shards);
        int displayPowerAmount = Math.min(Math.max(dragonPowerCount, 1), 10);
        ItemStack power = newItemStack(Material.DRAGON_HEAD, displayPowerAmount,
                false,
                "§5Dragon Power",
                "§7You have §c"+dragonPowerCount+" §5Dragon Power§7.",
                "§7Your next purchase will cost §b"+price+"§7.");
        gui.setItem(27, power);

        // TODO: fix all %s after we make sure that the descent is toggleable
        // MAIN TREE
        ItemStack allDamageResistance = newItemStack("allDamageResistance", Material.DIAMOND_CHESTPLATE,
                player, null,
                "§3All Damage Resistance",
                "§7Reduce all damage taken by 0.5% every level.");
        ItemStack dragonExtraRNG = newItemStack("dragonExtraRNG", Material.BLUE_ORCHID,
                player, "allDamageResistance",
                "§3Dragon Extra RNG Drops", "Not Tested // fix %",
                "§7Receive 1% more chance to get dragon drops every level.");
        ItemStack extraAttackSpeed = newItemStack("extraAttackSpeed", Material.GOLDEN_AXE,
                player, "dragonExtraRNG",
                "§3Extra Attack Speed",
                "§7Increase attack speed by 2% every level.");
        ItemStack mightyStrength = newItemStack("mightyStrength", Material.STONE_HOE,
                player, "extraAttackSpeed",
                "§3Mighty Strength",
                "§7Gain a chance to get 5s of Strength I whenever hit.",
                "§7+0.1% every level");
        ItemStack vitality = newItemStack("vitality", Material.BLAZE_POWDER,
                player, "mightyStrength",
                "§3Vitality", "Not Tested",
                "§7Gain Resistance and Fire Resistance for every kill.",
                "§7+1s every level");
        ItemStack strongAttacks = newItemStack("strongAttacks", Material.IRON_SWORD,
                player, "vitality",
                "§3Strong Attacks", "§7§k~ §f§nNot Implemented",
                "§7Increase attack damage by 0.5% every level.");

        // LEFT TREE
        ItemStack heartbleed = newItemStack("heartbleed", Material.REDSTONE,
                player, "extraAttackSpeed",
                "§3Heartbleed", "Not Tested",
                "§7Gain a 20% chance every level to regenerate 1 HP when",
                "§7killing any entity. (2 second cooldown)");
        ItemStack shardSeeker = newItemStack("shardSeeker", Material.DIAMOND_BLOCK,
                player, "heartbleed",
                "§3Shard Seeker", "§7§k~ §f§nNot Implemented",
                "§7Gain 2% more shards every level.");
        ItemStack shieldedArmor = newItemStack("shieldedArmor", Material.SHIELD,
                player, "shardSeeker",
                "§3Shielded Armor", "§7§k~ §f§nNot Implemented",
                "§7Your armor will take less durability and last longer.",
                "§7Gain a 1.5% chance to not consume armor durability every level.");
        ItemStack silkyTouch = newItemStack("silkyTouch", Material.COBWEB,
                player, "shieldedArmor",
                "§3Silky Touch", "§7§k~ §f§nNot Implemented",
                "§7Gain a 0.4% chance every level to inflict 5s Slowness I",
                "§7on any damaged entities.");

        // RIGHT TREE
        ItemStack tougherArmor = newItemStack("tougherArmor", Material.NETHERITE_HELMET,
                player, "dragonExtraRNG",
                "§3Tougher Armor",
                "§7Increase armor toughness by 3% every level.");
        ItemStack enhancedEnergy = newItemStack("enhancedEnergy", Material.CARROT,
                player, "tougherArmor",
                "§3Enhanced Energy", "§7§k~ §f§nNot Implemented",
                "§7Decrease hunger exhaustion by 2% every level.");
        ItemStack wisdom = newItemStack("wisdom", Material.ENCHANTED_BOOK,
                player, "enhancedEnergy",
                "§3Wisdom", "§7§k~ §f§nNot Implemented",
                "§7Gain 4% more XP from orbs every level.");
        ItemStack strongLegs = newItemStack("strongLegs", Material.WATER_BUCKET,
                player, "wisdom",
                "§3Strong Legs", "§7§k~ §f§nNot Implemented",
                "§7Take 15% less fall damage every level.");
        ItemStack sicklyTouch = newItemStack("sicklyTouch", Material.SPIDER_EYE,
                player, "strongLegs",
                "§3Sickly Touch", "§7§k~ §f§nNot Implemented",
                "§7Gain a 0.4% chance every level to inflict 5s Poison I",
                "§7on any damaged entities.");
        ItemStack witch = newItemStack("witch", Material.DRAGON_BREATH,
                player, "sicklyTouch",
                "§3Witch", "§7§k~ §f§nNot Implemented",
                "§7Drinkable potions last 2.5% longer every level.");

        // RANDOM OFF-TO-THE-SIDE UPGRADES
        ItemStack strongArrows = newItemStack("strongArrows", Material.CROSSBOW,
                player, "enhancedEnergy",
                "§3Strong Arrows", "§7§k~ §f§nNot Implemented",
                "§7Increase arrow damage by 1% every level.");
        ItemStack grimTouch = newItemStack("grimTouch", Material.BONE,
                player, "strongArrows",
                "§3Grim Touch", "§7§k~ §f§nNot Implemented",
                "§7Gain a 0.4% chance every level to inflict 5s Weakness I",
                "§7on any damaged entities.");
        ItemStack reviver = newItemStack("reviver", Material.TOTEM_OF_UNDYING,
                player, "strongLegs",
                "§3Strong Revive", "§7§k~ §f§nNot Implemented",
                "§7When reviving, regenerate for 3s longer every level.");

        gui.setItem(4, allDamageResistance);
        gui.setItem(13, dragonExtraRNG);
        gui.setItem(14, tougherArmor);
        gui.setItem(15, enhancedEnergy);
        gui.setItem(16, strongArrows);
        gui.setItem(17, grimTouch);
        gui.setItem(20, shardSeeker);
        gui.setItem(21, heartbleed);
        gui.setItem(22, extraAttackSpeed);
        gui.setItem(24, wisdom);
        gui.setItem(29, shieldedArmor);
        gui.setItem(31, mightyStrength);
        gui.setItem(33, strongLegs);
        gui.setItem(34, reviver);
        gui.setItem(38, silkyTouch);
        gui.setItem(40, vitality);
        gui.setItem(42, sicklyTouch);
        gui.setItem(49, strongAttacks);
        gui.setItem(51, witch);
    }

    private ItemStack newItemStack(String upgradeKey, Material material, Player player, String prerequisite, String name, String... lore) {
        if (prerequisite != null && plugin.descent.getUpgrade(player, prerequisite) == 0) {
            ItemStack stack = new ItemStack(Material.BARRIER, 1);
            ItemMeta im = stack.getItemMeta();
            im.setDisplayName("§cLocked!");
            stack.setItemMeta(im);
            return stack;
        }
        lore = Arrays.copyOf(lore, lore.length + 1);
        lore[lore.length - 1] = renderLoreLevel(player, upgradeKey);
        int level = plugin.descent.getUpgrade(player, upgradeKey);
        boolean isEnchanted = (level > 0);
        return newItemStack(material, Math.max(level, 1), isEnchanted, name, lore);
    }

    private ItemStack newItemStack(Material material, int amount, boolean enchanted, String name, String... lore) {
        ItemStack stack = new ItemStack(material, amount);
        if (enchanted) {
            stack.addUnsafeEnchantment(Enchantment.LUCK, 1);
        }
        ItemMeta im = stack.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lore));
        stack.setItemMeta(im);
        return stack;
    }

}
