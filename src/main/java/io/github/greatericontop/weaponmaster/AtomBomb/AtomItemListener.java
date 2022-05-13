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
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.Random;

public class AtomItemListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    private final Random rnd = new Random();
    public AtomItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    public void spawnVein(Location loc, Material type, float size, int limitDepth, Random rnd) {
        if (loc.getBlock().getType().getBlastResistance() >= 20) { return; }
        if (loc.getBlock().getType() == Material.AIR) { return; }
        loc.getBlock().setType(type);
        limitDepth--;
        if (limitDepth == 0) {
            return;
        }
        if (rnd.nextFloat() < size)  spawnVein(loc.clone().add(1.0, 0.0, 0.0), type, size, limitDepth, rnd);
        if (rnd.nextFloat() < size)  spawnVein(loc.clone().add(-1.0, 0.0, 0.0), type, size, limitDepth, rnd);
        if (rnd.nextFloat() < size)  spawnVein(loc.clone().add(0.0, 1.0, 0.0), type, size, limitDepth, rnd);
        if (rnd.nextFloat() < size)  spawnVein(loc.clone().add(0.0, -1.0, 0.0), type, size, limitDepth, rnd);
        if (rnd.nextFloat() < size)  spawnVein(loc.clone().add(0.0, 0.0, 1.0), type, size, limitDepth, rnd);
        if (rnd.nextFloat() < size)  spawnVein(loc.clone().add(0.0, 0.0, -1.0), type, size, limitDepth, rnd);
    }

    public float getCustomBlastResistance(Material mat) {
        float blastRes = mat.getBlastResistance();
        // netherite, obsidian, etc
        if (blastRes == 1200.0F) {
            return 20.0F;
        }
        // water, lava, bubble column
        if (blastRes == 100.0F) {
            return 9.0F; // equivalent to end stone
        }
        return blastRes;
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

        int STEPS = 384;
        double STEPGAP = 1.0 / STEPS;
        // An attempt to copy the minecraft explosion algorithm
        // This is O(scary); but it seems to work decently in practice.
        Location at = event.getBlock().getLocation();
        World world = at.getWorld();
        new BukkitRunnable() {
            int y = STEPS;
            public void run() {
                if (y < -STEPS) {
                    player.sendMessage("§6[!] §3You have successfully levelled the landscape.");
                    cancel();
                    return;
                }
                for (int i = 0; i < 2; i++) {
                    for (int x = -STEPS; x <= STEPS; x++) {
                        for (int z = -STEPS; z <= STEPS; z++) {
                            //player.sendMessage(String.format("§7coords: %d %d %d", x, y, z));
                            if (!(x == -STEPS || x == STEPS || y == -STEPS || y == STEPS || z == -STEPS || z == STEPS)) {
                                continue;
                            }
                            double deltaX = x * STEPGAP;
                            double deltaY = y * STEPGAP;
                            double deltaZ = z * STEPGAP;

                            Location loc = at.clone();
                            Vector ray = new Vector(deltaX, deltaY, deltaZ).normalize().multiply(0.6);
                            float rayPower = 53.0F * (0.8F + 0.4F * rnd.nextFloat());
                            while (true) {
                                rayPower -= 0.45F;
                                if (loc.getBlock().getType() != Material.AIR) {
                                    rayPower -= (getCustomBlastResistance(loc.getBlock().getType()) + 0.6F) * 0.6F;
                                }
                                if (rayPower <= 0) {
                                    if (loc.getBlock().getType() != Material.AIR) {
                                        float f = rnd.nextFloat();
                                        if (f < 0.00_1F) {
                                            loc.getBlock().setType(Material.COBBLESTONE);
                                        } else if (f < 0.00_22F) {
                                            spawnVein(loc, Material.COBBLED_DEEPSLATE, 0.2F, 2, rnd);
                                        } else if (f < 0.00_32F) {
                                            loc.getBlock().setType(Material.COBWEB);
                                        } else if (f < 0.00_36F) {
                                            spawnVein(loc, Material.OBSIDIAN, 0.1F, 2, rnd);
                                        } else if (f < 0.00_65F) {
                                            loc.getBlock().setType(Material.FIRE);
                                        }
                                        if (f > 0.99_7 && loc.getBlock().getType() == Material.COAL_ORE) {
                                            spawnVein(loc, Material.DEEPSLATE_DIAMOND_ORE, 0.45F, 4, rnd);
                                        }
                                    }
                                    break;
                                }
                                loc.getBlock().setType(Material.AIR, false);
                                loc = loc.add(ray);
                            }
                        }
                    }
                    y--;
                }
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

}