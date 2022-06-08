package io.github.greatericontop.weaponmaster.dragon_manager;

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
import io.github.greatericontop.weaponmaster.other_crafts.CustomItems;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class LootDropper {

    private final WeaponMasterMain plugin;
    private final CustomItems customItems;
    private final DragonWeightManager dragonWeightManager;
    private final EnderDragon currentlyActiveDragon;
    public LootDropper(WeaponMasterMain plugin, DragonWeightManager dragonWeightManager, EnderDragon currentlyActiveDragon) {
        this.plugin = plugin;
        this.customItems = new CustomItems();
        this.dragonWeightManager = dragonWeightManager;
        this.currentlyActiveDragon = currentlyActiveDragon;
    }

    public void dropItemAt(World world, Location loc, ItemStack toDrop, UUID owner, String displayName, int pickupDelay) {
        //Item item = world.dropItem(loc, toDrop);
        Item item = (Item) world.spawnEntity(loc, EntityType.DROPPED_ITEM);
        item.setItemStack(toDrop);
        item.setOwner(owner);
        item.setPickupDelay(pickupDelay);
        item.setVelocity(new Vector(0.0, 0.5, 0.0));
        if (displayName != null) {
            item.setCustomName(displayName);
            item.setCustomNameVisible(true);
        }
    }

    public void createDrop(World world, int x, int z, ItemStack itemStack, UUID owner, String displayName) {
        int y = world.getHighestBlockYAt(x, z);
        Location loc = new Location(world, x, y, z).add(0, 1, 0);
        loc.getBlock().setType(Material.PURPUR_BLOCK);
        dropItemAt(world, loc.clone().add(0.5, 1.25, 0.5), itemStack, owner, displayName, 60);
    }

    public void createDrop(World world, ItemStack itemStack, UUID owner, String displayName) {
        int x = ThreadLocalRandom.current().nextInt(-30, 31);
        int z = ThreadLocalRandom.current().nextInt(-30, 31);
        createDrop(world, x, z, itemStack, owner, displayName);
    }

    public void createDrop(World world, ItemStack itemStack, Player player, String itemName) {
        createDrop(world, itemStack, player.getUniqueId(), String.format("§f%s§7: §a%s", player.getDisplayName(), itemName));
    }

    public int doMajorDrops(World world, int weight, Player player) {
        double rand = Math.random();
        if (weight >= 600 && rand < 0.04) { // 4%
            createDrop(world, customItems.generateDragonHornItemStack(), player, "Dragon Horn");
            weight -= 550;
            return weight;
        }
        if (weight >= 550 && 0.04 <= rand && rand < 0.12) { // 8%
            createDrop(world, customItems.generateDragonScaleItemStack(), player, "Dragon Scale");
            weight -= 600;
            return weight;
        }
        if (weight >= 550 && 0.12 <= rand && rand < 0.22) { // 10%
            createDrop(world, customItems.generateDragonWingItemStack(), player, "Dragon Wing");
            weight -= 550;
            return weight;
        }
        return weight;
    }

    public int doMinorDrops(World world, int weight, Player player) {
        int shulkerShellAmount = 0, obsidianAmount = 0, enderPearlAmount = 0;
        if (weight >= 150) {
            obsidianAmount++;
            enderPearlAmount++;
        }
        int i = 0;
        while (weight > 0 && i < 40) {
            double rand = Math.random();
            if (weight >= 130 && rand < 0.04) { // 4%
                shulkerShellAmount++;
                weight -= 130;
            } else if (weight >= 25 && 0.04 <= rand && rand < 0.34) { // 30%
                obsidianAmount++;
                weight -= 25;
            } else if (weight >= 25 && 0.34 <= rand && rand < 0.64) { // 30%
                enderPearlAmount++;
                weight -= 25;
            }
            i++;
        }
        if (shulkerShellAmount > 0) {
            createDrop(world, new ItemStack(Material.SHULKER_SHELL, shulkerShellAmount), player, "Shulker Shell");
        }
        if (obsidianAmount > 0) {
            createDrop(world, new ItemStack(Material.OBSIDIAN, obsidianAmount), player, "Obsidian");
        }
        if (enderPearlAmount > 0) {
            createDrop(world, new ItemStack(Material.ENDER_PEARL, enderPearlAmount), player, "Ender Pearl");
        }
        return weight;
    }

    public int doAllDrops(World world, int totalWeight, Player player) {
        return doMinorDrops(world, doMajorDrops(world, totalWeight, player), player);
    }

}
