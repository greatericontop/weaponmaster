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

public class LootDropper {

    private final WeaponMasterMain plugin;
    private final Util util;
    private final EnderDragon currentlyActiveDragon;
    public LootDropper(WeaponMasterMain plugin, EnderDragon currentlyActiveDragon) {
        this.plugin = plugin;
        this.util = new Util(plugin);
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

}
