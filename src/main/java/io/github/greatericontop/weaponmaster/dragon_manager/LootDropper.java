package io.github.greatericontop.weaponmaster.dragon_manager;

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
import io.github.greatericontop.weaponmaster.other_crafts.CustomItems;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class LootDropper {

    private final WeaponMasterMain plugin;
    private final CustomItems customItems;
    private final EnderDragon currentlyActiveDragon;
    public LootDropper(WeaponMasterMain plugin, EnderDragon currentlyActiveDragon) {
        this.plugin = plugin;
        this.customItems = new CustomItems();
        this.currentlyActiveDragon = currentlyActiveDragon;
    }

    public void dropItemAt(World world, Location loc, ItemStack toDrop, UUID owner, int pickupDelay) {
        //Item item = world.dropItem(loc, toDrop);
        Item item = (Item) world.spawnEntity(loc, EntityType.DROPPED_ITEM);
        item.setItemStack(toDrop);
        item.setOwner(owner);
        item.setPickupDelay(pickupDelay);
        item.setVelocity(new Vector(0.0, 0.5, 0.0));
    }

    public void createDrop(World world, int x, int z, ItemStack itemStack, UUID owner) {
        int y = world.getHighestBlockYAt(x, z);
        Location loc = new Location(world, x, y, z).add(0, 1, 0);
        loc.getBlock().setType(Material.PURPUR_BLOCK);
        dropItemAt(world, loc.clone().add(0.5, 1.25, 0.5), itemStack, owner, 60);
    }

    public void createDrop(World world, ItemStack itemStack, UUID owner) {
        int x = ThreadLocalRandom.current().nextInt(-30, 31);
        int z = ThreadLocalRandom.current().nextInt(-30, 31);
        createDrop(world, x, z, itemStack, owner);
    }

    public int doMajorDrops(World world, int weight, UUID owner) {
        double rand = Math.random();
        if (weight >= 600 && rand < 0.04) { // 4%
            createDrop(world, customItems.generateDragonHornItemStack(), owner);
            weight -= 550;
            return weight;
        }
        if (weight >= 550 && 0.04 <= rand && rand < 0.12) { // 8%
            createDrop(world, customItems.generateDragonScaleItemStack(), owner);
            weight -= 600;
            return weight;
        }
        if (weight >= 550 && 0.12 <= rand && rand < 0.22) { // 10%
            createDrop(world, customItems.generateDragonWingItemStack(), owner);
            weight -= 550;
            return weight;
        }
        return weight;
    }

    public int doMinorDrops(World world, int weight, UUID owner) {
        int i = 0;
        while (weight > 0 && i < 50) { // max 50 attempts to spawn items because each only drops 1, and they can fail
            double rand = Math.random();
            if (weight >= 90 && rand < 0.1) { // 10%
                createDrop(world, new ItemStack(Material.SHULKER_SHELL, 1), owner);
                weight -= 90;
            } else if (weight >= 20 && 0.1 <= rand && rand < 0.4) { // 30%
                createDrop(world, new ItemStack(Material.OBSIDIAN, 1), owner);
                weight -= 20;
            } else if (weight >= 20 && 0.4 <= rand && rand < 0.7) { // 30%
                createDrop(world, new ItemStack(Material.ENDER_PEARL, 1), owner);
                weight -= 20;
            }
            i++;
        }
        return weight;
    }

}
