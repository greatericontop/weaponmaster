package io.github.greatericontop.weaponmaster.mainitems.EndArmor;

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
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EndArmorListener implements Listener {
    private static final double SKIP_CHANCE = 0.3;

    private final Util util;
    public EndArmorListener() {
        this.util = new Util(null);
    }

    public boolean hasFullSet(PlayerInventory inventory) {
        return util.checkForEndArmor(inventory.getHelmet())
                && util.checkForEndArmor(inventory.getChestplate())
                && util.checkForEndArmor(inventory.getLeggings())
                && util.checkForEndArmor(inventory.getBoots());
    }

    @EventHandler()
    public void onPearlThrow(ProjectileLaunchEvent event) {
        if (!(event.getEntity().getType() == EntityType.ENDER_PEARL))  return;
        if (!(event.getEntity().getShooter() instanceof Player))  return;
        Player player = (Player) event.getEntity().getShooter();
        if (!hasFullSet(player.getInventory()))  return;
        if (!player.hasPermission("weaponmaster.endarmor.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.endarmor.use§3.");
            return;
        }
        if (Math.random() < SKIP_CHANCE) {
            player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
        }
    }


}