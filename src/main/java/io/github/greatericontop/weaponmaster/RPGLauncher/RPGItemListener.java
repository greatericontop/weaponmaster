package io.github.greatericontop.weaponmaster.RPGLauncher;

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
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class RPGItemListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public RPGItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }
    private ArrayList<String> projectilesInFlightUUIDs = new ArrayList<String>();
    private final double SPREAD_RADIUS = 0.4495; // 3.1m at 100 blocks;  sp / ( 100 / (v / 20) )

    private Arrow fireOneGrenade(Player player) {
        Location eyeLoc = player.getEyeLocation();
        Vector velocityVector = eyeLoc.getDirection().multiply(14.5); // 14.5 block/tick is 290 meter/s
        Vector inaccuracy = InaccuracyAdder.generateInaccuracy(SPREAD_RADIUS);
        player.sendMessage(String.format("§3[Debug] §7velocityVector: %s", velocityVector));//
        velocityVector.add(inaccuracy);
        player.sendMessage(String.format("§3[Debug] §7velocityVector: %s", velocityVector));//
        Arrow arrow = (Arrow) player.getWorld().spawnEntity(eyeLoc.add(velocityVector.clone().multiply(0.1)), EntityType.ARROW);
        player.sendMessage(String.format("§3[Debug] §7velocityVector: %s; inaccuracy: %s", velocityVector, inaccuracy));//
        arrow.setVelocity(velocityVector);
        arrow.setShooter(player);
        return arrow;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLeftClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) { return; }
        Player player = event.getPlayer();
        if (!util.checkForRPGLauncher(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.rpgl.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.rpgl.use§3.");
            return;
        }
        // TODO: Actually make this thing cost an arrow/grenade to fire instead of firing blindly.
        Arrow arrow = fireOneGrenade(player);
        projectilesInFlightUUIDs.add(arrow.getUniqueId().toString()); // a special projectile to be updated later
        // Recoil against the player, throwing them back at a speed of 6.5 m/s
        player.setVelocity(player.getVelocity().subtract(player.getEyeLocation().getDirection().multiply(0.325)));
        // Spawn smoke while the arrow is in the air
        new BukkitRunnable() {
            @Override
            public void run() {
                if (arrow.isDead()) {
                    cancel();
                } else {
                    player.getWorld().spawnParticle(Particle.SMOKE_LARGE, arrow.getLocation(), 250);
                }
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile entity = event.getEntity();
        if (!projectilesInFlightUUIDs.contains(entity.getUniqueId().toString())) { return; } // we added the UUID earlier, so there shouldn't be a player NPE
        Player player = (Player) entity.getShooter();
        entity.getLocation().getWorld().createExplosion(entity.getLocation(), 5.0F, true, true, player);
        entity.remove(); // stop spawning smoke above
        player.sendMessage("§3FWOOM!");
}

}
