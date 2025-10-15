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
import org.bukkit.inventory.ItemFlag;
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

    private void addItems(Player player, Inventory gui, int shardCount, int dragonPowerCount, int price) {
        // fill everything with dummy items first
        for (int i = 0; i < INVENTORY_SIZE; i++) {
            gui.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE, 1));
        }

        ItemStack shards = newItemStack(Material.DIAMOND, 1,
                false,
                "§bShards",
                "§7You have §c"+shardCount+"§7 shards.",
                String.format("§7You need §b%d §7shards to get", plugin.descent.SHARDS_TO_POWER),
                String.format("§7a §5Dragon Power§7. (§a%.0f%%§7)", (100.0 * shardCount) / plugin.descent.SHARDS_TO_POWER)
        );
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
                1, "%",
                "§3All Damage Resistance",
                "§7Reduce all damage taken by 1% every level.");
        ItemStack dragonExtraRNG = newItemStack("dragonExtraRNG", Material.BLUE_ORCHID,
                player, "allDamageResistance",
                1.5, "%",
                "§3Dragon Extra RNG Drops",
                "§7Receive 1.5% more chance to get dragon drops every level.");
        ItemStack extraAttackSpeed = newItemStack("extraAttackSpeed", Material.GOLDEN_AXE,
                player, "dragonExtraRNG",
                2, "%",
                "§3Extra Attack Speed",
                "§7Increase attack speed by 2% every level.");
        ItemStack mightyStrength = newItemStack("mightyStrength", Material.STONE_HOE,
                player, "extraAttackSpeed",
                0.1, "%",
                "§3Mighty Strength",
                "§7Gain a chance to get 5s of Strength I whenever hit.",
                "§7+0.1% every level");
        ItemStack vitality = newItemStack("vitality", Material.BLAZE_POWDER,
                player, "mightyStrength",
                1, "s",
                "§3Vitality",
                "§7Gain Resistance and Fire Resistance for every kill.",
                "§7+1s every level");
        ItemStack strongAttacks = newItemStack("strongAttacks", Material.IRON_SWORD,
                player, "vitality",
                1, "%",
                "§3Strong Attacks",
                "§7Increase attack damage by 1% every level.");

        // LEFT TREE
        ItemStack heartbleed = newItemStack("heartbleed", Material.REDSTONE,
                player, "extraAttackSpeed",
                20, "%",
                "§3Heartbleed",
                "§7Gain a 20% chance every level to regenerate 1 HP when",
                "§7killing any entity. (2 second cooldown)");
        ItemStack shardSeeker = newItemStack("shardSeeker", Material.DIAMOND_BLOCK,
                player, "heartbleed",
                2.5, "%",
                "§3Shard Seeker",
                "§7Gain 2.5% more shards every level.");
        ItemStack shieldedArmor = newItemStack("shieldedArmor", Material.SHIELD,
                player, "shardSeeker",
                1.5, "%",
                "§3Shielded Armor",
                "§7Your armor will take less durability and last longer.",
                "§7Your armor takes 1.5% less durability damage per level.");
        ItemStack silkyTouch = newItemStack("silkyTouch", Material.COBWEB,
                player, "shieldedArmor",
                0.3, "%",
                "§3Silky Touch",
                "§7Gain a 0.3% chance every level to inflict 5s Slowness I",
                "§7on any damaged entities.");

        // RIGHT TREE
        ItemStack tougherArmor = newItemStack("tougherArmor", Material.NETHERITE_HELMET,
                player, "dragonExtraRNG",
                3, "%",
                "§3Tougher Armor",
                "§7Increase armor toughness by 3% every level.");
        ItemStack enhancedEnergy = newItemStack("enhancedEnergy", Material.CARROT,
                player, "tougherArmor",
                2, "%",
                "§3Enhanced Energy",
                "§7Decrease hunger exhaustion by 2% every level.");
        ItemStack wisdom = newItemStack("wisdom", Material.ENCHANTED_BOOK,
                player, "enhancedEnergy",
                4, "%",
                "§3Wisdom",
                "§7Gain 5% more XP from orbs every level.");
        ItemStack strongLegs = newItemStack("strongLegs", Material.WATER_BUCKET,
                player, "wisdom",
                15, "%",
                "§3Strong Legs",
                "§7Take 15% less fall damage every level.");
        ItemStack sicklyTouch = newItemStack("sicklyTouch", Material.SPIDER_EYE,
                player, "strongLegs",
                0.3, "%",
                "§3Sickly Touch",
                "§7Gain a 0.3% chance every level to inflict 5s Poison I",
                "§7on any damaged entities.");
        ItemStack witch = newItemStack("witch", Material.DRAGON_BREATH,
                player, "sicklyTouch",
                3, "%",
                "§3Witch",
                "§7Drinkable potions last 3% longer every level.");

        // RANDOM OFF-TO-THE-SIDE UPGRADES
        ItemStack strongArrows = newItemStack("strongArrows", Material.CROSSBOW,
                player, "enhancedEnergy",
                1, "%",
                "§3Strong Arrows",
                "§7Increase arrow damage by 1% every level.");
        ItemStack grimTouch = newItemStack("grimTouch", Material.BONE,
                player, "strongArrows",
                0.3, "%",
                "§3Grim Touch",
                "§7Gain a 0.3% chance every level to inflict 5s Weakness I",
                "§7on any damaged entities.");
        ItemStack reviver = newItemStack("reviver", Material.TOTEM_OF_UNDYING,
                player, "strongLegs",
                3, "s",
                "§3Strong Revive",
                "§7When reviving, regenerate for 3s longer every level.",
                "§7A small portion of the time is also converted",
                "§7to a more potent effect.");
        ItemStack runner = newItemStack("runner", Material.SUGAR,
                player, "witch",
                0.8, "%",
                "§3Runner",
                "§7Increase movement speed by 0.8% every level.");

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
        gui.setItem(52, runner);
    }

    // shows decimal point
    private ItemStack newItemStack(String upgradeKey, Material material, Player player, String prerequisite, double displayMulti, String displaySuffix, String name, String... lore) {
        String displayText = String.format("§7You have §2%.1f%s§7.", plugin.descent.getUpgrade(player, upgradeKey)*displayMulti, displaySuffix);
        return newItemStack(upgradeKey, material, player, prerequisite, displayText, name, lore);
    }
    // does not show decimal point
    private ItemStack newItemStack(String upgradeKey, Material material, Player player, String prerequisite, int displayMulti, String displaySuffix, String name, String... lore) {
        String displayText = String.format("§7You have §2%d%s§7.", plugin.descent.getUpgrade(player, upgradeKey)*displayMulti, displaySuffix);
        return newItemStack(upgradeKey, material, player, prerequisite, displayText, name, lore);
    }

    private ItemStack newItemStack(String upgradeKey, Material material, Player player, String prerequisite, String displayText, String name, String... lore) {
        if (prerequisite != null && plugin.descent.getUpgrade(player, prerequisite) == 0) {
            ItemStack stack = new ItemStack(Material.BARRIER, 1);
            ItemMeta im = stack.getItemMeta();
            im.setDisplayName("§cLocked!");
            stack.setItemMeta(im);
            return stack;
        }
        int level = plugin.descent.getUpgrade(player, upgradeKey);
        lore = Arrays.copyOf(lore, lore.length + 3);
        lore[lore.length-3] = "";
        lore[lore.length-2] = String.format("§fLevel: §b%d", level);
        lore[lore.length-1] = displayText;
        boolean isEnchanted = (level > 0);
        return newItemStack(material, Math.max(level, 1), isEnchanted, name, lore);
    }

    private ItemStack newItemStack(Material material, int amount, boolean enchanted, String name, String... lore) {
        ItemStack stack = new ItemStack(material, amount);
        ItemMeta im = stack.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lore));
        im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES); // remove fluff on items (e.g. swords) that have those
        if (enchanted) {
            im.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        stack.setItemMeta(im);
        return stack;
    }

}
