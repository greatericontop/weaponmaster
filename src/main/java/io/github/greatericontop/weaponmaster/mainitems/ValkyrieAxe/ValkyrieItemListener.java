package io.github.greatericontop.weaponmaster.mainitems.ValkyrieAxe;

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
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
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
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ValkyrieItemListener implements Listener {
    private final float FOOD_COST = 8.0F / 3.0F; // 3 uses = 2 hunger points or 1 hunger bar
    private final double DAMAGE_FACTOR = 0.75;
    private final double FIRESTORM_RADIUS = 25.0;
    private final double FIRESTORM_RADIUS_SQUARED = FIRESTORM_RADIUS * FIRESTORM_RADIUS;
    private final double MAX_ANGLE_DEG = 32.0;
    private final double FIRESTORM_KNOCKBACK = 14.0;
    private final int ITEM_TOTAL_DURABILITY = 250;

    private final WeaponMasterMain plugin;
    private final Util util;
    public ValkyrieItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onHitEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        if (!util.checkForValkyrieAxe(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.valkryieaxe.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.valkyrie.use§3.");
            return;
        }
        for (Entity entity : player.getNearbyEntities(3.0, 3.0, 3.0)) {
            if (!(entity instanceof LivingEntity)) { continue; }
            if (entity.getUniqueId().equals(event.getEntity().getUniqueId())) { continue; } // don't double-attack
            ((LivingEntity) entity).damage(event.getDamage() * DAMAGE_FACTOR, player);
            player.getWorld().spawnParticle(Particle.SWEEP_ATTACK, entity.getLocation(), 3);
            player.getWorld().playSound(entity.getLocation(), Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1.0F, 1.0F);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        if (Util.checkForInteractableBlock(event)) { return; }
        Player player = event.getPlayer();
        if (!util.checkForValkyrieAxe(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.valkyrieaxe.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.valkyrieaxe.use§3.");
            return;
        }
        Damageable im = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
        if (player.getGameMode() != GameMode.CREATIVE) {
            if (im.getDamage() > ITEM_TOTAL_DURABILITY - 50) { // minimum 50 durability
                player.sendMessage("§cNot enough durability!");
                return;
            }
            if (player.getFoodLevel() < 12) { // minimum 6 bars of hunger
                player.sendMessage("§cNot enough hunger!");
                return;
            }
            player.setExhaustion(player.getExhaustion() + FOOD_COST);
            im.setDamage(im.getDamage() + 15);
        }
        player.getInventory().getItemInMainHand().setItemMeta(im);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);

        for (Entity entity : player.getNearbyEntities(FIRESTORM_RADIUS, FIRESTORM_RADIUS, FIRESTORM_RADIUS)) {
            if (!(entity instanceof LivingEntity)) { continue; }
            if (entity.getLocation().distanceSquared(player.getEyeLocation()) > FIRESTORM_RADIUS_SQUARED) { continue; }
            Vector playerToEntity = entity.getLocation().subtract(player.getEyeLocation()).toVector();
            Vector playerLooking = player.getEyeLocation().getDirection();
            double angleDegrees = playerLooking.angle(playerToEntity) * 180.0 / Math.PI;
            if (angleDegrees < MAX_ANGLE_DEG) {
                Vector knockbackVector = playerToEntity.normalize().multiply(FIRESTORM_KNOCKBACK);
                ((LivingEntity) entity).damage(12.0, player);
                entity.setVelocity(knockbackVector);
            }
        }
        new BukkitRunnable() {
            Location currentLoc = player.getEyeLocation();
            Vector lookingAt = currentLoc.getDirection().multiply(0.5);
            int runsLeft = 50;
            public void run() {
                if (runsLeft < 0) {
                    cancel();
                    return;
                }
                for (int i = 0; i < 3; i++) {
                    player.getWorld().spawnParticle(Particle.FLAME, currentLoc, 3, 0.0, 0.0, 0.0, 0.15);
                    currentLoc.add(lookingAt);
                }
                runsLeft--;
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }
}
