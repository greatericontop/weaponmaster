package io.github.greatericontop.weaponmaster.AssaultRifle_dead_dont_use;

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
import io.github.greatericontop.weaponmaster.utils.InaccuracyAdder;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class AssaultListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public AssaultListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }
    private ArrayList<String> currentlyFiring = new ArrayList<String>();
    private final double SPREAD_RADIUS = 0.2778; // 3.1m at 100 blocks;  sp / ( 100 / (v / 20) )

    private Arrow fireOneRound(Player player) {
        Location eyeLoc = player.getEyeLocation();
        Vector velocityVector = eyeLoc.getDirection().multiply(46.3); // 46.3 block/tick is 926 meter/s is 3038 feet/s
        velocityVector.add(InaccuracyAdder.generateInaccuracy(SPREAD_RADIUS));
        Arrow arrow = (Arrow) player.getWorld().spawnEntity(eyeLoc, EntityType.ARROW);
        arrow.setDamage(15.0);
        arrow.setKnockbackStrength(4);
        arrow.setVelocity(velocityVector);
        arrow.setShooter(player);
        player.getWorld().spawnParticle(Particle.FIREWORKS_SPARK, eyeLoc, 10);
        return arrow;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!util.checkForRPGLauncher(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.assaultrifle.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.assaultrifle.use§3.");
            return;
        }
        if (currentlyFiring.contains(player.getUniqueId().toString())) {
            player.sendMessage("§3You can't fire while a burst is currently taking place!");
            return;
        }
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            // Fire a burst of 3 rounds, 4 ticks each (0.6s, 300rpm)
            currentlyFiring.add(player.getUniqueId().toString());
            new BukkitRunnable() {
                int arrowsLeftToFire = 3;
                @Override
                public void run() {
                    if (player.getInventory().containsAtLeast(new ItemStack(Material.ARROW), 1)) {
                        fireOneRound(player);
                        if (player.getGameMode() != GameMode.CREATIVE) {
                            player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
                        }
                    }
                    arrowsLeftToFire--;
                    if (arrowsLeftToFire == 0) {
                        currentlyFiring.remove(player.getUniqueId().toString());
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 1L, 4L);
        } else if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            // Fire a burst of 25 rounds, 1 tick each (1.25s, 1200rpm)
            currentlyFiring.add(player.getUniqueId().toString());
            new BukkitRunnable() {
                int arrowsLeftToFire = 25;
                @Override
                public void run() {
                    if (player.getInventory().containsAtLeast(new ItemStack(Material.ARROW), 1)) {
                        fireOneRound(player);
                        if (player.getGameMode() != GameMode.CREATIVE) {
                            player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
                        }
                    }
                    arrowsLeftToFire--;
                    if (arrowsLeftToFire == 0) {
                        currentlyFiring.remove(player.getUniqueId().toString());
                        cancel();
                    }
                }
            }.runTaskTimer(plugin, 1L, 1L);
        }
    }

//    @EventHandler(priority = EventPriority.NORMAL)
//    public void onLeftClick(PlayerInteractEvent event) {
//        //event.getPlayer().sendMessage("[Debug] Event caught, but no check yet");
//        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) { return; }
//        Player player = event.getPlayer();
//        //player.sendMessage("[Debug] Got PIE");//
//        if (!util.checkForRPGLauncher(player.getInventory().getItemInMainHand())) { return; }
//        //player.sendMessage("[Debug] Passed check");//
//        if (!player.hasPermission("weaponmaster.rpgl.use")) {
//            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.rpgl.use§3.");
//            return;
//        }
//        // Okay time to do the JUICY STUFF
//        // TODO: Actually make this thing cost an arrow/grenade to fire instead of firing blindly.
//        Arrow arrow = fireOneArrow(player);
//        projectilesInFlightUUIDs.add(arrow.getUniqueId().toString()); // a special projectile to be updated later
//        // Recoil against the player, throwing them back at a speed of 6.5 m/s
//        player.setVelocity(player.getVelocity().subtract(player.getEyeLocation().getDirection().multiply(0.325)));
//        // Spawn smoke while the arrow is in the air
//        new BukkitRunnable() {
//            @Override
//            public void run() {
//                if (arrow.isDead()) {
//                    cancel();
//                } else {
//                    player.getWorld().spawnParticle(Particle.SMOKE_LARGE, arrow.getLocation(), 330);
//                }
//            }
//        }.runTaskTimer(plugin, 1L, 1L);
//    }
}
