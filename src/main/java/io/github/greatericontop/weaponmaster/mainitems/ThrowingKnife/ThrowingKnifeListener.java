package io.github.greatericontop.weaponmaster.mainitems.ThrowingKnife;

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
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class ThrowingKnifeListener implements Listener {
    private static final double KNIFE_SPEED = 0.5;

    Map<UUID, Boolean> isThrowing = new HashMap<>();
    Set<UUID> affectedEntities = new HashSet<>();

    private final WeaponMasterMain plugin;
    private final Util util;
    public ThrowingKnifeListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
        new BukkitRunnable() {
            public void run() {
                affectedEntities.clear();
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER)  return;
        Player player = (Player) event.getDamager();
        if (!util.checkForThrowingKnife(player.getInventory().getItemInMainHand()))  return;
        if (!player.hasPermission("weaponmaster.throwingknife.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.throwingknife.use§3.");
            return;
        }
        if (affectedEntities.contains(event.getEntity().getUniqueId())) {
            return;
        }
        if (isThrowing.getOrDefault(player.getUniqueId(), false)) {
            player.sendMessage("§7The knife is still in the air! You can't stab someone with it, obviously...");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND)  return;
        Player player = event.getPlayer();
        if (!util.checkForThrowingKnife(player.getInventory().getItemInMainHand()))  return;
        if (!player.hasPermission("weaponmaster.throwingknife.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.throwingknife.use§3.");
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)  return;
        if (isThrowing.getOrDefault(player.getUniqueId(), false)) {
            player.sendMessage("§7The knife is still in the air! You can't throw another one, obviously...");
            event.setCancelled(true);
            return;
        }
        isThrowing.put(player.getUniqueId(), true);
        new BukkitRunnable() {
            public void run() {
                isThrowing.put(player.getUniqueId(), false);
            }
        }.runTaskLater(plugin, 120L);

        Location origin = player.getEyeLocation().add(player.getLocation().getDirection().multiply(0.5));
        Vector vector = new Vector(0.0, 0.0, 0.0);
        Vector toAdd = player.getLocation().getDirection().multiply(KNIFE_SPEED);
        new BukkitRunnable() {
            int i = 0;
            public void run() {
                if (i < 120) {
                    Location originalLoc = origin.clone().add(vector);
                    if (i < 60) {
                        vector.add(toAdd);
                    } else {
                        vector.subtract(toAdd);
                    }
                    Location loc = origin.clone().add(vector);
                    RayTraceResult rtxResult = loc.getWorld().rayTraceEntities(originalLoc, vector, KNIFE_SPEED);
                    if (rtxResult != null && rtxResult.getHitEntity() != null) {
                        // limitation: only hits 1
                        if (rtxResult.getHitEntity() instanceof LivingEntity) {
                            LivingEntity living = (LivingEntity) rtxResult.getHitEntity();
                            affectedEntities.add(living.getUniqueId());
                            living.damage(4.0, player);
                        }
                    }
                    loc.getWorld().spawnParticle(Particle.CRIT, loc, 15, 0.1, 0.1, 0.1, 0.11);
                } else {
                    cancel();
                    return;
                }
                i++;
            }
        }.runTaskTimer(plugin, 1L, 1L);

    }

}