package io.github.greatericontop.weaponmaster.mainitems.HeavyAxe;

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
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class HeavyAxeItemListener implements Listener {
    // Strings for version compatibility (e.g. pale oak)
    private static final List<String> WOODS = List.of(
            "ACACIA_LOG",
            "BIRCH_LOG",
            "DARK_OAK_LOG",
            "JUNGLE_LOG",
            "OAK_LOG",
            "SPRUCE_LOG",
            "WARPED_STEM",
            "CRIMSON_STEM",
            "MANGROVE_LOG",
            "PALE_OAK_LOG"
    );
    private static final List<String> LEAVES = List.of(
            "ACACIA_LEAVES",
            "BIRCH_LEAVES",
            "DARK_OAK_LEAVES",
            "JUNGLE_LEAVES",
            "OAK_LEAVES",
            "SPRUCE_LEAVES",
            "WARPED_WART_BLOCK",
            "NETHER_WART_BLOCK",
            "MANGROVE_LEAVES",
            "PALE_OAK_LEAVES"
    );

    private final WeaponMasterMain plugin;
    private final Util util;
    public HeavyAxeItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    private static boolean isWood(Material mat) {
        return WOODS.contains(mat.name());
    }
    private static boolean isLeaves(Material mat) {
        return LEAVES.contains(mat.name());
    }

    @EventHandler()
    public void onTreeBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!util.checkForHeavyAxe(player.getInventory().getItemInMainHand()))  return;
        if (!player.hasPermission("weaponmaster.heavyaxe.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.heavyaxe.use§3.");
            return;
        }
        if (!(isWood(event.getBlock().getType()) || isLeaves(event.getBlock().getType())))  return;

        Set<Location> visited = new HashSet<>();
        Queue<Location> locationQueue = new LinkedList<>();
        locationQueue.add(event.getBlock().getLocation());
        int woodLeft = 50;
        int leavesLeft = 100;
        // BFS up to 1 block of distance, including diagonals
        while (!locationQueue.isEmpty() && (woodLeft > 0 || leavesLeft > 0)) {
            Location currentLocation = locationQueue.poll();
            if (isWood(currentLocation.getBlock().getType()) && woodLeft > 0) {
                visited.add(currentLocation);
                woodLeft--;
                currentLocation.getBlock().breakNaturally(player.getInventory().getItemInMainHand());
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            Location newLocation = currentLocation.clone().add(x, y, z);
                            if (!visited.contains(newLocation)) {
                                locationQueue.add(newLocation);
                            }
                        }
                    }
                }
            }
            if (isLeaves(currentLocation.getBlock().getType()) && leavesLeft > 0) {
                visited.add(currentLocation);
                leavesLeft--;
                for (int x = -1; x <= 1; x++) {
                    for (int y = -1; y <= 1; y++) {
                        for (int z = -1; z <= 1; z++) {
                            Location newLocation = currentLocation.clone().add(x, y, z);
                            // Leaves only spread to the exact same leaves, or wood
                            if ((newLocation.getBlock().getType() == currentLocation.getBlock().getType() || isWood(newLocation.getBlock().getType()))
                                    && !visited.contains(newLocation)) {
                                locationQueue.add(newLocation);
                            }
                        }
                    }
                }
                currentLocation.getBlock().breakNaturally(player.getInventory().getItemInMainHand());
            }
        }
    }

}