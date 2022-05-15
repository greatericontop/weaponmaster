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
import io.github.greatericontop.weaponmaster.utils.MathHelper;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemMendEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MinerItemListener extends MinerUtil implements Listener {
    public MinerItemListener(WeaponMasterMain plugin) {
        super(plugin);
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
                lore.add(util.MINER_INSERTION+6, "§7Experience will be greatly reduced in Silk Touch mode.");
                lore.add(util.MINER_INSERTION+7, "§7Fortune cannot be used in this mode.");
                // TODO: Add individual xp table to silk touch so it does not penalize everything
                break;
            case 9:
                lore.add(util.MINER_INSERTION+8, "");
                lore.add(util.MINER_INSERTION+9, "§dWhen breaking some deepslate ores while not in Silk Touch");
                lore.add(util.MINER_INSERTION+10, "§dmode, gain a §41% §dchance to drop a block instead. §7§oTIER 9");
                break;
            case 10:
                lore.set(util.MINER_INSERTION+7, "§aFortune III applies to this mode and smelted ores. §7§oTIER 10");
                break;
            case 11:
                lore.add(util.MINER_INSERTION+11, "§2Small chance for ores to spawn around you. §7§oTIER 11");
                break;
            case 12:
                lore.add(util.MINER_INSERTION+12, "§fIncreased Mending power, no longer limited §7§oTIER 12");
                break;
            case 13:
                lore.add(util.MINER_INSERTION+13, "§ePermanent §e§lHaste I §ewhile holding. §7§oTIER 13");
                break;
            case 14:
                lore.add(util.MINER_INSERTION+14, "");
                lore.add(util.MINER_INSERTION+15, "§cArea Mine: Destroy blocks nearby (30s cooldown) §7§oTIER 14");
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

        if (tier >= 8 && getMode(lore).equals("§a>§b>§c> §6Currently set to §9Smelting Touch")) {
            doSmeltingOres(event, player, tier>=10);
        }
        if (tier >= 9) {
            doDeepslateBlockMultiply(event, player, lore, tier);
        }
        if (tier >= 11) {
            attemptSpawnBlock(event, player);
        }
        if (tier >= 14 && getMode(lore).equals("§a>§b>§c> §6Currently set to §9Area Mine")) {
            areaBlockBreak(event.getBlock().getLocation(), player, player.getInventory().getItemInMainHand(), (Damageable) im);
        }

        fixEnchants(tier, im, player);
        im.setLore(lore);
        player.getInventory().getItemInMainHand().setItemMeta(im);
    }

    public void doSmeltingOres(BlockBreakEvent event, Player player, boolean hasFortune) {
        Material mat = event.getBlock().getType();
        World world = event.getBlock().getLocation().getWorld();
        if (mat == Material.COPPER_ORE || mat == Material.DEEPSLATE_COPPER_ORE) {
            event.setDropItems(false);
            int amount = doFortuneOre(rnd.nextInt(3) + 2, hasFortune);
            world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COPPER_INGOT, amount));
            event.setExpToDrop(3*amount);
        } else if (mat == Material.IRON_ORE || mat == Material.DEEPSLATE_IRON_ORE) {
            event.setDropItems(false);
            int amount = doFortuneOre(1, hasFortune);
            world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT, amount));
            event.setExpToDrop(3*amount);
        } else if (mat == Material.GOLD_ORE || mat == Material.DEEPSLATE_GOLD_ORE) {
            event.setDropItems(false);
            int amount = doFortuneOre(1, hasFortune);
            world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT, amount));
            event.setExpToDrop(5*amount);
        } else if (mat == Material.ANCIENT_DEBRIS) {
            event.setDropItems(false);
            world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.NETHERITE_SCRAP, 1));
            event.setExpToDrop(14);
        }
    }

    public void doDeepslateBlockMultiply(BlockBreakEvent event, Player player, List<String> lore, int tier) {
        if (rnd.nextFloat() >= 0.01F) { return; }
        if (getMode(lore).equals("§a>§b>§c> §6Currently set to §9Silk Touch")) { return; } // prevent abuse
        World world = event.getBlock().getLocation().getWorld();
        event.setExpToDrop(event.getExpToDrop() * 9);
        // drops an extra item (does not invalidate the current one)
        switch (event.getBlock().getType()) {
            case DEEPSLATE_COAL_ORE:
                world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COAL_BLOCK, 1));
                break;
            case DEEPSLATE_COPPER_ORE:
                world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.COPPER_BLOCK, 1));
                break;
            case DEEPSLATE_IRON_ORE:
                world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_BLOCK, 1));
                break;
            case DEEPSLATE_GOLD_ORE:
                world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_BLOCK, 1));
                break;
            case DEEPSLATE_EMERALD_ORE:
                world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.EMERALD_BLOCK, 1));
                break;
            case DEEPSLATE_REDSTONE_ORE:
                world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.REDSTONE_BLOCK, 1));
                break;
            case DEEPSLATE_LAPIS_ORE:
                world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.LAPIS_BLOCK, 1));
                break;
            case DEEPSLATE_DIAMOND_ORE:
                world.dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.DIAMOND_BLOCK, 1));
                break;
        }
    }

    /*
     * Attempt to replace nearby netherrack, stone, deepslate into a random ore of the corresponding type
     */
    public void attemptSpawnBlock(BlockBreakEvent event, Player player) {
        Location loc = event.getBlock().getLocation();
        attemptSpawnBlockSingle(loc.clone().add(1.0, 0.0, 0.0), player, event.getBlock().getType());
        attemptSpawnBlockSingle(loc.clone().add(-1.0, 0.0, 0.0), player, event.getBlock().getType());
        attemptSpawnBlockSingle(loc.clone().add(0.0, 1.0, 0.0), player, event.getBlock().getType());
        attemptSpawnBlockSingle(loc.clone().add(0.0, -1.0, 0.0), player, event.getBlock().getType());
        attemptSpawnBlockSingle(loc.clone().add(0.0, 0.0, 1.0), player, event.getBlock().getType());
        attemptSpawnBlockSingle(loc.clone().add(0.0, 0.0, -1.0), player, event.getBlock().getType());
    }
    final Material[] stoneMats = {Material.COAL_ORE, Material.IRON_ORE, Material.GOLD_ORE, Material.REDSTONE_ORE, Material.LAPIS_ORE};
    final Material[] deepMats = {Material.DEEPSLATE_COAL_ORE, Material.DEEPSLATE_IRON_ORE, Material.DEEPSLATE_GOLD_ORE, Material.DEEPSLATE_REDSTONE_ORE, Material.DEEPSLATE_LAPIS_ORE};
    final Material[] netherMats = {Material.NETHER_QUARTZ_ORE, Material.NETHER_GOLD_ORE};
    private void attemptSpawnBlockSingle(Location loc, Player player, Material blockType) {
        Block blockAt = loc.getBlock();
        // around 4 attempts (max 6) are called for each break
        if (blockAt.getType() == Material.STONE && (blockType == Material.STONE || blockType == Material.ANDESITE || blockType == Material.DIORITE || blockType == Material.GRANITE)) {
            if (rnd.nextFloat() >= 0.00_15F) { return; }
            Material replacement = stoneMats[rnd.nextInt(stoneMats.length)];
            blockAt.setType(replacement);
            player.sendMessage("§7A new ore just spawned!");
        } else if (blockAt.getType() == Material.DEEPSLATE && blockType == Material.DEEPSLATE) {
            if (rnd.nextFloat() >= 0.00_23F) { return; }
            Material replacement = deepMats[rnd.nextInt(deepMats.length)];
            blockAt.setType(replacement);
            player.sendMessage("§7A new ore just spawned!");
        } else if (blockAt.getType() == Material.NETHERRACK && blockType == Material.NETHERRACK) {
            if (rnd.nextFloat() >= 0.00_09F) { return; }
            Material replacement = netherMats[rnd.nextInt(netherMats.length)];
            blockAt.setType(replacement);
            player.sendMessage("§7A new ore just spawned!");
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
        if (Util.checkForInteractableBlock(event)) { return; }
        ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
        List<String> lore = im.getLore();
        int tier = parseLevelInt(lore.get(util.MINER_LVL));
        if (tier < 7) { return; }

        String text = getMode(lore);
        if (text.equals("§a>§b>§c> §6Currently set to §9Silk Touch")) {
            im.removeEnchant(Enchantment.SILK_TOUCH);
            im.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, false);
            lore.set(util.MINER_INSERTION + 3, "§a>§b>§c> §6Currently set to §9Fortune III");
            player.sendMessage("§a>§b>§c> §6Pickaxe set to §9Fortune III");
        } else if (tier >= 8 && text.equals("§a>§b>§c> §6Currently set to §9Fortune III")) {
            im.removeEnchant(Enchantment.LOOT_BONUS_BLOCKS);
            lore.set(util.MINER_INSERTION + 3, "§a>§b>§c> §6Currently set to §9Smelting Touch");
            if (tier >= 10) {
                im.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 3, false);
                player.sendMessage("§a>§b>§c> §6Pickaxe set to §9Smelting Touch + Fortune III");
            } else {
                player.sendMessage("§a>§b>§c> §6Pickaxe set to §9Smelting Touch");
            }
        } else if (tier >= 14 && text.equals("§a>§b>§c> §6Currently set to §9Smelting Touch")) {
            im.removeEnchant(Enchantment.LOOT_BONUS_BLOCKS);
            lore.set(util.MINER_INSERTION + 3, "§a>§b>§c> §6Currently set to §9Area Mine");
            player.sendMessage("§a>§b>§c> §6Pickaxe set to §9§nArea Mine");
        } else {
            im.removeEnchant(Enchantment.LOOT_BONUS_BLOCKS);
            im.addEnchant(Enchantment.SILK_TOUCH, 1, false);
            lore.set(util.MINER_INSERTION+3, "§a>§b>§c> §6Currently set to §9Silk Touch");
            player.sendMessage("§a>§b>§c> §6Pickaxe set to §9Silk Touch");
        }
        im.setLore(lore);
        player.getInventory().getItemInMainHand().setItemMeta(im);
    }

    private final double REPAIR_PENALTY = 1.0 / 7.0;
    @EventHandler(priority = EventPriority.HIGH)
    public void onMending(PlayerItemMendEvent event) {
        if (!util.checkForMinersBlessing(event.getItem())) {
            return;
        }
        ItemMeta im = event.getItem().getItemMeta();
        List<String> lore = im.getLore();
        if (parseLevelInt(lore.get(util.MINER_LVL)) >= 12) {
            return;
        }
        if (Math.random() >= REPAIR_PENALTY) {
            event.setCancelled(true);
        }
    }

    public void regHasteRunnable() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.hasPermission("weaponmaster.minersblessing.use")) { continue; }
                    if (!(util.checkForMinersBlessing(player.getInventory().getItemInMainHand()))) { continue; }
                    List<String> lore = player.getInventory().getItemInMainHand().getItemMeta().getLore();
                    if (parseLevelInt(lore.get(util.MINER_LVL)) >= 13) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 0, true));
                    }
                }
            }
        }.runTaskTimer(plugin, 200L, 60L);
        // switched from 50L and 55 effects
    }

    private Map<UUID, Boolean> cooldown = new HashMap<UUID, Boolean>();
    private final int RANGE = 5;
    public void areaBlockBreak(Location loc, Player player, ItemStack tool, Damageable im) {
        if (!cooldown.getOrDefault(player.getUniqueId(), true)) { return; }
        for (int dx = -RANGE; dx <= RANGE; dx++) {
            for (int dy = RANGE; dy >= -RANGE; dy--) {
                for (int dz = -RANGE; dz <= RANGE; dz++) {
                    Location newLoc = loc.clone().add(dx, dy, dz);
                    Block block = newLoc.getBlock();
                    player.sendMessage(String.format("block type %s, hardness %.1f", block.getType(), block.getType().getHardness()));
                    if (0.0F <= block.getType().getHardness() && block.getType().getHardness() <= 15.0F) {
                        if (block.getType() != Material.AIR && Math.random() < 0.025) { // unbreaking 3: 0.25
                            im.setDamage(im.getDamage() + 1);
                        }
                        block.breakNaturally(tool);
                    }
                }
            }
        }
        cooldown.put(player.getUniqueId(), false);
        new BukkitRunnable() {
            public void run() {
                cooldown.put(player.getUniqueId(), true);
            }
        }.runTaskLater(plugin, 120L); // 600
    }

}