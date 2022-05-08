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

        // This is O(scary); but it works well enough
        Location at = event.getBlock().getLocation();
        World world = at.getWorld();
        double MIN = -4.0;
        double MAX = 4.0;
        double MIN_CLOSE = -3.9;
        double MAX_CLOSE = 3.9;
        for (double deltaX = MIN; deltaX <= MAX; deltaX += 0.8) {
            for (double deltaY = MIN; deltaY <= MAX; deltaY += 0.8) {
                for (double deltaZ = MIN; deltaZ <= MAX; deltaZ += 0.8) {
                    if (!(
                            deltaX <= MIN_CLOSE || deltaX >= MAX_CLOSE
                            || deltaY <= MIN_CLOSE || deltaY >= MAX_CLOSE
                            || deltaZ <= MIN_CLOSE || deltaZ >= MAX_CLOSE
                            )) {
                        continue;
                    }
                    Location loc = at.clone();
                    for (int i = 0; i < 20; i++) {
                        loc = loc.add(deltaX, deltaY, deltaZ);
                        world.createExplosion(loc, 6.0F);
                    }
                }
            }
        }

        player.sendMessage("§6[!] §3You have successfully levelled the landscape.");
    }

}