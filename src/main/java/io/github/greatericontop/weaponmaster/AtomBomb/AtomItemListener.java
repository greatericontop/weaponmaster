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
import org.bukkit.util.Vector;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Random;

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

        float MIN_VALUE = -1.0F;
        float MAX_VALUE = 1.0F;
        float STEP = 1.0F / 64.0F;
        float MIN = -0.999F;
        float MAX = 0.999F;
        // An attempt to copy the minecraft explosion algorithm
        // This is O(scary); but it seems to work decently in practice.
        Location at = event.getBlock().getLocation();
        World world = at.getWorld();
        Random rnd = new Random();
        for (float deltaX = MIN_VALUE; deltaX <= MAX_VALUE; deltaX += STEP) {
            for (float deltaY = MIN_VALUE; deltaY <= MAX_VALUE; deltaY += STEP) {
                for (float deltaZ = MIN_VALUE; deltaZ <= MAX_VALUE; deltaZ += STEP) {
                    if (!(deltaX <= MIN || deltaX >= MAX || deltaY <= MIN || deltaY >= MAX || deltaZ <= MIN || deltaZ >= MAX)) {
                        continue;
                    }

                    Location loc = at.clone();
                    Vector ray = new Vector(deltaX, deltaY, deltaZ).normalize().multiply(0.6);
                    float rayPower = 19.0F * (0.9F + 0.2F * rnd.nextFloat());
                    while (true) {
                        rayPower -= 0.45F;
                        if (loc.getBlock().getType() != Material.AIR) {
                            rayPower -= (loc.getBlock().getType().getBlastResistance() + 0.6F) * 0.6F;
                        }
                        if (rayPower <= 0) {
                            break;
                        }
                        loc.getBlock().setType(Material.AIR);
                        loc = loc.add(ray);
                    }
                }
            }
        }


        player.sendMessage("§6[!] §3You have successfully levelled the landscape.");
    }

}