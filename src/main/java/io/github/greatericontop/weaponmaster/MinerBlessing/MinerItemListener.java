package io.github.greatericontop.weaponmaster.MinerBlessing;

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

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Random;

public class MinerItemListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    private final Random rnd = new Random();
    public MinerItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    private int parseExpInt(String s) {
        // §6Experience: §b<DATA>
        String data = s.substring(16, s.length());
        return Integer.parseInt(data);
    }
    private int parseLevelInt(String s) {
        // §6Tier: §b<DATA>
        String data = s.substring(10, s.length());
        return Integer.parseInt(data);
    }

    private int getRequirementToLevelUp(int level) {
        if (level >= 8) {
            return 2147483600;
        }
        return new int[]{
                10_000,
                15_000, // 1
                20_000,
                30_000,
                40_000,
                50_000, // 5
                60_000,
                70_000,
        }[level];
//        return new int[]{
//                10_000,
//                15_000, // 1
//                25_000,
//                40_000,
//                60_000,
//                90_000, // 5
//                135_000,
//                180_000,
//        }[level];
    }

    public int xpToAdd(Material mat, List<String> lore) {
        if (getMode(lore).equals("§6Currently set to §9Silk Touch"))  return 1;
        if (mat == Material.DEEPSLATE_COAL_ORE)  return 2700;
        if (mat == Material.DEEPSLATE_EMERALD_ORE || mat == Material.DEEPSLATE_DIAMOND_ORE)  return 1500;
        if (mat == Material.EMERALD_ORE || mat == Material.DIAMOND_ORE)  return 1000;
        if (mat == Material.DEEPSLATE_IRON_ORE || mat == Material.DEEPSLATE_REDSTONE_ORE || mat == Material.DEEPSLATE_GOLD_ORE || mat == Material.DEEPSLATE_LAPIS_ORE)  return 810;
        if (mat == Material.COAL_ORE || mat == Material.IRON_ORE || mat == Material.REDSTONE_ORE || mat == Material.GOLD_ORE || mat == Material.LAPIS_ORE)  return 600;
        if (mat == Material.OBSIDIAN || mat == Material.ANCIENT_DEBRIS)  return 70;
        if (mat == Material.DEEPSLATE)  return 15;
        if (mat == Material.STONE)  return 6;
        if (mat == Material.NETHERRACK) return 2;
        return 1;
    }

    public String getMode(List<String> lore) {
        return lore.get(util.MINER_INSERTION + 3);
    }

    public void runLevelUp(int newTier, ItemMeta im, List<String> lore) {
        switch (newTier) {
            case 1:
                im.addEnchant(Enchantment.DIG_SPEED, 1, false);
                lore.add(util.MINER_INSERTION, "§eEfficiency I");
                break;
            case 2:
                im.removeEnchant(Enchantment.DIG_SPEED);
                im.addEnchant(Enchantment.DIG_SPEED, 2, false);
                lore.set(util.MINER_INSERTION, "§eEfficiency II");
                break;
            case 3:
                im.removeEnchant(Enchantment.DIG_SPEED);
                im.addEnchant(Enchantment.DIG_SPEED, 3, false);
                im.addEnchant(Enchantment.DURABILITY, 1, false);
                lore.set(util.MINER_INSERTION, "§eEfficiency III, Unbreaking I");
                break;
            case 4:
                im.removeEnchant(Enchantment.DIG_SPEED);
                im.addEnchant(Enchantment.DIG_SPEED, 4, false);
                im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                lore.set(util.MINER_INSERTION, "§eEfficiency IV, Unbreaking I, Sharpness I");
                break;
            case 5:
                im.removeEnchant(Enchantment.DIG_SPEED);
                im.removeEnchant(Enchantment.DURABILITY);
                im.removeEnchant(Enchantment.DAMAGE_ALL);
                im.addEnchant(Enchantment.DIG_SPEED, 5, false);
                im.addEnchant(Enchantment.DURABILITY, 2, false);
                im.addEnchant(Enchantment.DAMAGE_ALL, 2, true);
                lore.set(util.MINER_INSERTION, "§eEfficiency V, Unbreaking II, Sharpness II");
                break;
            case 6:
                im.removeEnchant(Enchantment.DURABILITY);
                im.removeEnchant(Enchantment.DAMAGE_ALL);
                im.addEnchant(Enchantment.DURABILITY, 3, false);
                im.addEnchant(Enchantment.DAMAGE_ALL, 3, true);
                lore.set(util.MINER_INSERTION, "§eEfficiency V, Unbreaking III, Sharpness III");
                break;
            case 7:
                lore.add(util.MINER_INSERTION+1, "");
                lore.add(util.MINER_INSERTION+2, "§3RIGHT CLICK to cycle between different modes. §7§oTIER 7");
                lore.add(util.MINER_INSERTION+3, "§a>§b>§c> §6Currently set to §7none");
                break;
            case 8:
                lore.add(util.MINER_INSERTION+4, "");
                lore.add(util.MINER_INSERTION+5, "§aAutomatically smelts some ores and drops additional experience. §7§oTIER 8");
                lore.add(util.MINER_INSERTION+6, "§aNOTE: §7Experience will be greatly reduced in Silk Touch mode.");
                // TODO: Add individual xp table to silk touch so it does not penalize everything
                break;
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!util.checkForMinersBlessing(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.minersblessing.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.minersblessing.use§3.");
            return;
        }

        ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
        List<String> lore = im.getLore();
        int amount = xpToAdd(event.getBlock().getType(), lore);
        int exp = parseExpInt(lore.get(util.MINER_EXP));
        int tier = parseLevelInt(lore.get(util.MINER_LVL));
        exp += amount;
        if (exp >= getRequirementToLevelUp(tier)) {
            exp = 0;
            tier++;
            runLevelUp(tier, im, lore);
            player.sendMessage(String.format("§9Your %s §9is now level §6%d.", util.MINERS_BLESSING_NAME, tier));
            lore.set(util.MINER_LVL, String.format("§6Tier: §b%d", tier));
        }
        lore.set(util.MINER_EXP, String.format("§6Experience: §b%d", exp));
        double xpPercent = (100.0 * exp) / getRequirementToLevelUp(tier);
        lore.set(util.MINER_REQ, String.format("§6Required: §b%d §6(§b%.1f§6%%)", getRequirementToLevelUp(tier), xpPercent));

        if (tier >= 8 && getMode(lore).equals("§6Currently set to §9Smelting Touch")) {
            doSmeltingOres(event, player);
        }

        im.setLore(lore);
        player.getInventory().getItemInMainHand().setItemMeta(im);
    }

    public void doSmeltingOres(BlockBreakEvent event, Player player) {
        ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
        List<String> lore = im.getLore();
        if (lore.get(util.MINER_INSERTION + 3).equals("§6Currently set to §9Silk Touch")) { return; }
        Material mat = event.getBlock().getType();
        World world = event.getBlock().getLocation().getWorld();
        if (mat == Material.COPPER_ORE || mat == Material.DEEPSLATE_COPPER_ORE) {
            event.setDropItems(false);
            int amount = rnd.nextInt(3) + 2;
            world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COPPER_INGOT, amount));
            event.setExpToDrop(3*amount);
        } else if (mat == Material.IRON_ORE || mat == Material.DEEPSLATE_IRON_ORE) {
            event.setDropItems(false);
            int amount = 1;
            world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, amount));
            event.setExpToDrop(3*amount);
        } else if (mat == Material.GOLD_ORE || mat == Material.DEEPSLATE_GOLD_ORE) {
            event.setDropItems(false);
            int amount = 1;
            world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, amount));
            event.setExpToDrop(5*amount);
        } else if (mat == Material.ANCIENT_DEBRIS) {
            event.setDropItems(false);
            world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.NETHERITE_SCRAP, 1));
            event.setExpToDrop(14);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        Player player = event.getPlayer();
        if (!util.checkForMinersBlessing(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.minersblessing.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.minersblessing.use§3.");
            return;
        }
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) { return; }
        ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
        List<String> lore = im.getLore();
        int tier = parseLevelInt(lore.get(util.MINER_LVL));
        if (tier < 7) { return; }

        String text = getMode(lore);
        if (text.equals("§6Currently set to §9Silk Touch")) {
            im.removeEnchant(Enchantment.SILK_TOUCH);
            im.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, false);
            lore.set(util.MINER_INSERTION + 3, "§6Currently set to §9Fortune III");
            player.sendMessage("§6Pickaxe set to §9Fortune III");
        } else if (tier >= 8 && text.equals("§6Currently set to §9Fortune III")) {
            im.removeEnchant(Enchantment.LOOT_BONUS_BLOCKS);
            lore.set(util.MINER_INSERTION+3, "§6Currently set to §9Smelting Touch");
            player.sendMessage("§6Pickaxe set to §9Smelting Touch");
        } else {
            im.removeEnchant(Enchantment.LOOT_BONUS_BLOCKS);
            im.addEnchant(Enchantment.SILK_TOUCH, 1, false);
            lore.set(util.MINER_INSERTION+3, "§6Currently set to §9Silk Touch");
            player.sendMessage("§6Pickaxe set to §9Silk Touch");
        }
        im.setLore(lore);
        player.getInventory().getItemInMainHand().setItemMeta(im);
    }

}