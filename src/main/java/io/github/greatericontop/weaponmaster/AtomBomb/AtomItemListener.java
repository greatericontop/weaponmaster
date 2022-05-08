package io.github.greatericontop.weaponmaster.AtomBomb;

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
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class AtomItemListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public AtomItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    public void propagate(Location loc, int power) {
        if (power <= 1) { return; }
        power -= Math.random() < 0.4 ? 2 : 1;
        if (loc.getBlock().getType() == Material.AIR) {
            power -= 10;
        }
        loc.getBlock().setType(Material.AIR);
        propagate(loc.clone().add(1.0, 0.0, 0.0), power);
        propagate(loc.clone().add(-1.0, 0.0, 0.0), power);
        propagate(loc.clone().add(0.0, 1.0, 0.0), power);
        propagate(loc.clone().add(0.0, -1.0, 0.0), power);
        propagate(loc.clone().add(0.0, 0.0, 1.0), power);
        propagate(loc.clone().add(0.0, 0.0, -1.0), power);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!util.checkForAtomBomb(event.getItemInHand())) { return; }
        if (!player.hasPermission("weaponmaster.atombomb.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.atombomb.use§3.");
            return;
        }
        if (event.getBlockAgainst().getType() != Material.REDSTONE_BLOCK) {
            player.sendMessage("§cYou must place it on a redstone block!");
            event.setCancelled(true);
            return;
        }

        Location at = event.getBlock().getLocation();
        propagate(at, 30);

        player.sendMessage("§6[!] §3You have successfully levelled the landscape.");
    }

}