package io.github.greatericontop.weaponmaster.mainitems.AgriculturalAbomination;

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
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

import java.util.List;

public class AgriculturalAbominationListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public AgriculturalAbominationListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    private static boolean containsAndDecrement(List<Item> items, Material mat) {
        for (Item item : items) {
            if (item.getItemStack().getType() == mat) {
                item.getItemStack().setAmount(item.getItemStack().getAmount() - 1);
                return true;
            }
        }
        return false;
    }

    @EventHandler()
    public void onBreak(BlockDropItemEvent event) {
        Player player = event.getPlayer();
        if (!util.checkForAgriculturalAbomination(player.getInventory().getItemInMainHand()))  return;
        if (!player.hasPermission("weaponmaster.agriculturalabomination.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.agriculturalabomination.use§3.");
            return;
        }
        Material mat = event.getBlockState().getType(); // before it was broken
        switch (mat) {
            case WHEAT -> {
                if (containsAndDecrement(event.getItems(), Material.WHEAT_SEEDS)) {
                    event.getBlock().setType(Material.WHEAT);
                }
            }
            case CARROTS -> {
                if (containsAndDecrement(event.getItems(), Material.CARROT)) {
                    event.getBlock().setType(Material.CARROTS);
                }
            }
            case POTATOES -> {
                if (containsAndDecrement(event.getItems(), Material.POTATO)) {
                    event.getBlock().setType(Material.POTATOES);
                }
            }
            case BEETROOTS -> {
                if (containsAndDecrement(event.getItems(), Material.BEETROOT_SEEDS)) {
                    event.getBlock().setType(Material.BEETROOTS);
                }
            }
            case NETHER_WART -> {
                if (containsAndDecrement(event.getItems(), Material.NETHER_WART)) {
                    event.getBlock().setType(Material.NETHER_WART);
                }
            }
            case SUGAR_CANE -> {
                Material matOfBlockBelow = event.getBlock().getLocation().add(0, -1, 0).getBlock().getType();
                if (matOfBlockBelow != Material.SUGAR_CANE && containsAndDecrement(event.getItems(), Material.SUGAR_CANE)) {
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        if (event.getBlock().getType() == Material.AIR) {
                            event.getBlock().setType(Material.SUGAR_CANE);
                        }
                    }, 2L); // must be 2 ticks because the next cane breaks on tick 1
                }
            }
        }
    }

}
