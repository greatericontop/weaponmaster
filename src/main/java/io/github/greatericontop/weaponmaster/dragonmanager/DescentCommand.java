package io.github.greatericontop.weaponmaster.dragonmanager;

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
                plugin.descent.getPrice(plugin.descent.getPurchases(player.getUniqueId()))
        );
        player.openInventory(gui);
    }

    private String renderLoreLevel(Player player, String upgradeType) {
        int level = plugin.descent.getDescentUpgradeLevel(player.getUniqueId(), upgradeType);
        return String.format("§fLevel: §b%d §f/ §b%d", level, plugin.descent.MAX_LEVEL);
    }

    private void addItems(Player player, Inventory gui, int shardCount, int dragonPowerCount, int price) {
        // fill everything with dummy items first
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            gui.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));
        }

        ItemStack shards = newItemStack(Material.DIAMOND, 1,
                player, null,
                "§bShards",
                "§7You have §c"+shardCount+"§7 shards.");
        gui.setItem(18, shards);
        int displayPowerAmount = Math.min(Math.max(dragonPowerCount, 1), 10);
        ItemStack power = newItemStack(Material.DRAGON_HEAD, displayPowerAmount,
                player, null,
                "§5Dragon Power",
                "§7You have §c"+dragonPowerCount+" §5Dragon Power§7.",
                "§7Your next purchase will cost §b"+price+"§7.");
        gui.setItem(27, power);

        // MAIN TREE
        ItemStack allDamageResistance = newItemStack(Material.DIAMOND_CHESTPLATE, 1,
                player, null,
                "§3All Damage Resistance",
                "§7Reduce all damage taken by 1% every level.",
                renderLoreLevel(player, "allDamageResistance"));
        ItemStack dragonExtraRNG = newItemStack(Material.BLUE_ORCHID, 1,
                player, "allDamageResistance",
                "§3Dragon Extra RNG Drops",
                "§7Receive 1% more chance to get dragon drops every level.",
                renderLoreLevel(player, "dragonExtraRNG"));
        ItemStack extraAttackSpeed = newItemStack(Material.GOLDEN_AXE, 1,
                player, "dragonExtraRNG",
                "§3Extra Attack Speed",
                "§7Increase attack speed by 2% every level.",
                renderLoreLevel(player, "extraAttackSpeed"));
        ItemStack mightyStrength = newItemStack(Material.DIAMOND_SWORD, 1,
                player, "extraAttackSpeed",
                "§3Mighty Strength",
                "§7Gain a chance to get 5s of Strength I whenever hit.",
                "§7+0.1% every level",
                renderLoreLevel(player, "mightyStrength"));
        ItemStack vitality = newItemStack(Material.BLAZE_POWDER, 1,
                player, "mightyStrength",
                "§3Vitality",
                "§7Gain Resistance and Fire Resistance for every kill.",
                "§7+1s every level",
                renderLoreLevel(player, "vitality"));
        ItemStack strongAttacks = newItemStack(Material.IRON_SWORD, 1,
                player, "vitality",
                "§3Strong Attacks",
                "§7Increase attack damage by 0.5% every level.",
                renderLoreLevel(player, "strongAttacks"));

        // LEFT TREE
        ItemStack heartbleed = newItemStack(Material.REDSTONE, 1,
                player, "extraAttackSpeed",
                "§3Heartbleed",
                "§7Gain a 20% chance every level to regenerate 1 HP on kill.",
                renderLoreLevel(player, "heartbleed"));
        ItemStack shardSeeker = newItemStack(Material.DIAMOND_BLOCK, 1,
                player, "heartbleed",
                "§3Shard Seeker",
                "§7Gain 2% more shards every level.",
                renderLoreLevel(player, "shardSeeker"));
        ItemStack shieldedArmor = newItemStack(Material.SHIELD, 1,
                player, "shardSeeker",
                "§3Shielded Armor",
                "§7Your armor will take less durability and last longer.",
                "§7Gain a 1.5% chance to not consume armor durability every level.",
                renderLoreLevel(player, "shieldedArmor"));
        ItemStack silkyTouch = newItemStack(Material.COBWEB, 1,
                player, "shieldedArmor",
                "§3Silky Touch",
                "§7Gain a 0.4% chance every level to inflict 5s Slowness I",
                "§7on any damaged entities.",
                renderLoreLevel(player, "silkyTouch"));

        // RIGHT TREE
        ItemStack tougherArmor = newItemStack(Material.NETHERITE_HELMET, 1,
                player, "dragonExtraRNG",
                "§3Tougher Armor",
                "§7Increase armor toughness by 3% every level.",
                renderLoreLevel(player, "tougherArmor"));
        ItemStack enhancedEnergy = newItemStack(Material.CARROT, 1,
                player, "tougherArmor",
                "§3Enhanced Energy",
                "§7Decrease hunger exhaustion by 2% every level.",
                renderLoreLevel(player, "enhancedEnergy"));
        ItemStack wisdom = newItemStack(Material.ENCHANTED_BOOK, 1,
                player, "enhancedEnergy",
                "§3Wisdom",
                "§7Gain 4% more XP from orbs every level.",
                renderLoreLevel(player, "wisdom"));
        ItemStack strongLegs = newItemStack(Material.WATER_BUCKET, 1,
                player, "wisdom",
                "§3Strong Legs",
                "§7Take 15% less fall damage every level.",
                renderLoreLevel(player, "strongLegs"));
        ItemStack sicklyTouch = newItemStack(Material.SPIDER_EYE, 1,
                player, "strongLegs",
                "§3Sickly Touch",
                "§7Gain a 0.4% chance every level to inflict 5s Poison I",
                "§7on any damaged entities.",
                renderLoreLevel(player, "sicklyTouch"));
        ItemStack witch = newItemStack(Material.DRAGON_BREATH, 1,
                player, "sicklyTouch",
                "§3Witch",
                "§7Drinkable potions last 2.5% longer every level.",
                renderLoreLevel(player, "witch"));

        // RANDOM OFF-TO-THE-SIDE UPGRADES
        ItemStack strongArrows = newItemStack(Material.CROSSBOW, 1,
                player, "enhancedEnergy",
                "§3Strong Arrows",
                "§7Increase arrow damage by 1% every level.",
                renderLoreLevel(player, "strongArrows"));
        ItemStack grimTouch = newItemStack(Material.BONE, 1,
                player, "strongArrows",
                "§3Grim Touch",
                "§7Gain a 0.4% chance every level to inflict 5s Weakness I",
                "§7on any damaged entities.",
                renderLoreLevel(player, "grimTouch"));
        ItemStack reviver = newItemStack(Material.TOTEM_OF_UNDYING, 1,
                player, "strongLegs",
                "§3Strong Revive",
                "§7When reviving, regenerate for 3s longer every level.",
                renderLoreLevel(player, "reviver"));

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

    private ItemStack newItemStack(Material material, int amount, Player player, String prerequisite, String name, String... lore) {
        if (prerequisite != null && plugin.descent.getDescentUpgradeLevel(player.getUniqueId(), prerequisite) == 0) {
            ItemStack stack = new ItemStack(Material.BARRIER, 1);
            ItemMeta im = stack.getItemMeta();
            im.setDisplayName("§cLocked!");
            stack.setItemMeta(im);
            return stack;
        }
        ItemStack stack = new ItemStack(material, amount);
        ItemMeta im = stack.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lore));
        stack.setItemMeta(im);
        return stack;
    }

}
