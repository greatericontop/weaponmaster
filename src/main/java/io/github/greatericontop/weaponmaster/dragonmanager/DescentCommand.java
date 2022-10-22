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
        plugin.descent.updateShards(player.getUniqueId());
        Inventory gui = Bukkit.createInventory(player, INVENTORY_SIZE, plugin.descent.DESCENT_GUI_NAME);
        addItems(player, gui,
                plugin.descent.getShards(player.getUniqueId()),
                plugin.descent.getDragonPower(player.getUniqueId()));
        player.openInventory(gui);
        return true;
    }

    private String renderLoreLevel(Player player, String upgradeType) {
        int level = plugin.descent.getDescentUpgradeLevel(player.getUniqueId(), upgradeType);
        return String.format("§fLevel: §b%d §f/ §b%d", level, plugin.descent.MAX_LEVEL);
    }

    private void addItems(Player player, Inventory gui, int shardCount, int dragonPowerCount) {
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
                "§7You have §c"+dragonPowerCount+" §5Dragon Power§7.");
        gui.setItem(27, power);

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

        ItemStack tougherArmor = newItemStack(Material.NETHERITE_HELMET, 1,
                player, "dragonExtraRNG",
                "§3Tougher Armor",
                "§7Increase armor toughness by 3% every level.",
                renderLoreLevel(player, "tougherArmor"));

        ItemStack extraAttackSpeed = newItemStack(Material.GOLDEN_AXE, 1,
                player, "dragonExtraRNG",
                "§3Extra Attack Speed",
                "§7Increase attack speed by 2% every level.",
                renderLoreLevel(player, "extraAttackSpeed"));
        ItemStack strongAttacks = newItemStack(Material.IRON_SWORD, 1,
                player, "extraAttackSpeed",
                "§3Strong Attacks",
                "§7Increase attack damage by 0.5% every level.",
                renderLoreLevel(player, "strongAttacks"));

        gui.setItem(4, allDamageResistance);
        gui.setItem(13, dragonExtraRNG);
        gui.setItem(14, tougherArmor);
        gui.setItem(22, extraAttackSpeed);
        gui.setItem(31, strongAttacks);
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
