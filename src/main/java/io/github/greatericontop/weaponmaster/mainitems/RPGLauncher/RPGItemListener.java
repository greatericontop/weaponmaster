package io.github.greatericontop.weaponmaster.mainitems.RPGLauncher;

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
import io.github.greatericontop.weaponmaster.utils.InaccuracyAdder;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
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
        velocityVector.add(inaccuracy);
        Arrow arrow = (Arrow) player.getWorld().spawnEntity(eyeLoc.add(velocityVector.clone().multiply(0.1)), EntityType.ARROW);
        arrow.setVelocity(velocityVector);
        arrow.setShooter(player);
        return arrow;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLeftClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) { return; }
        Player player = event.getPlayer();
        if (!util.checkForRPGLauncher(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.rpgl.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.rpgl.use§3.");
            return;
        }
        // TODO: Actually make this thing cost an arrow/grenade to fire instead of firing blindly.
        Arrow arrow = fireOneGrenade(player);
        projectilesInFlightUUIDs.add(arrow.getUniqueId().toString());
        player.setVelocity(player.getVelocity().subtract(player.getEyeLocation().getDirection().multiply(0.2)));
        new BukkitRunnable() {
            @Override
            public void run() {
                if (arrow.isDead()) {
                    cancel();
                } else {
                    player.getWorld().spawnParticle(Particle.LARGE_SMOKE, arrow.getLocation(), 190);
                }
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile entity = event.getEntity();
        if (!projectilesInFlightUUIDs.contains(entity.getUniqueId().toString())) { return; } // we added the UUID earlier, so there shouldn't be a player NPE
        Player player = (Player) entity.getShooter();
        // We want to detonate it slightly before it hits the block
        // Pull it back by :magnitude: blocks, in the reverse direction that its last velocity came from
        double magnitude = Math.min(entity.getVelocity().length()*0.1, 0.2);
        Location explosionLocation = entity.getLocation().subtract(entity.getVelocity().normalize().multiply(magnitude));
        entity.getLocation().getWorld().createExplosion(explosionLocation, 5.0F, true, true, player);
        entity.remove(); // stop spawning smoke above
        player.sendMessage("§3FWOOM!");
        player.sendMessage(String.format("§7[Debug] pulled back by %.3f", magnitude));
}

}
