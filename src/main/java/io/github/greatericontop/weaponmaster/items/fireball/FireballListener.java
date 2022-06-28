package io.github.greatericontop.weaponmaster.items.fireball;

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
import io.github.greatericontop.weaponmaster.util.Util;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

public class FireballListener implements Listener {

    private final float VELOCITY = 2.5F;
    private final float POWER = 3.0F;

    private final WeaponMasterMain plugin;
    private final Util util;
    public FireballListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }


    @EventHandler(priority = EventPriority.NORMAL)
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        if (Util.checkForInteractableBlock(event)) { return; }
        Player player = event.getPlayer();
        if (!util.checkForFireball(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.fireball.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.fireball.use§3.");
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            event.setCancelled(true);
        }

        Location eyeLocation = player.getEyeLocation();
        Location spawnLoc = eyeLocation.add(eyeLocation.getDirection().multiply(0.9));
        World world = player.getWorld();
        if (Math.random() < 0.02) {
            DragonFireball dragonFireballEntity = (DragonFireball) world.spawnEntity(spawnLoc, EntityType.DRAGON_FIREBALL);
            dragonFireballEntity.setVelocity(eyeLocation.getDirection().multiply(VELOCITY));
            event.getPlayer().sendMessage("§3You summoned a dragon fireball!");
        } else {
            Fireball fireball = (Fireball) world.spawnEntity(spawnLoc, EntityType.FIREBALL);
            fireball.setVelocity(eyeLocation.getDirection().multiply(VELOCITY));
            fireball.setYield(POWER);
            fireball.setShooter(player);
            new BukkitRunnable() {
                public void run() {
                    if (fireball.isDead()) {
                        cancel();
                        return;
                    }
                    Location fireballLoc = fireball.getLocation();
                    fireball.getWorld().spawnParticle(Particle.FLAME, fireballLoc, 10, 0.0, 0.0, 0.0, 0.001);
                    /*List<Entity> nearEntities = fireball.getNearbyEntities(SEEKING, SEEKING, SEEKING);
                    nearEntities.sort(
                            (Entity a, Entity b) -> (int) (1000.0 * (a.getLocation().distanceSquared(fireballLoc) - b.getLocation().distanceSquared(fireballLoc)))
                    );
                    for (Entity target : nearEntities) {
                        if (player.hasLineOfSight(target) && target instanceof Player && (!target.isDead()) && target.getEntityId() != player.getEntityId()) {
                            fireball.setVelocity(target.getLocation().toVector().subtract(fireballLoc.toVector()).normalize().multiply(VELOCITY));
                            break;
                        }
                    }*/
                }
            }.runTaskTimer(plugin, 1L, 1L);
        }
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            player.updateInventory();
        }
    }
}
