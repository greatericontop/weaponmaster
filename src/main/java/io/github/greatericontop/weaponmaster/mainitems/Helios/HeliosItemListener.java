package io.github.greatericontop.weaponmaster.mainitems.Helios;

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
import org.bukkit.enchantments.Enchantment;
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
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;

public class HeliosItemListener implements Listener {

    private final double IMPACT_DISTANCE = 5.0;
    private final double IMPACT_DISTANCE_SQUARED = 25.0;
    private final float FOOD_COST = 8.0F / 3.0F; // 4 exhaustion = 1 hunger point (8 = bar)
    private final int MAX_DAMAGE_LEVEL = 80;
    private final double DAMAGE_INCREASE_PER_LEVEL = 0.5 / MAX_DAMAGE_LEVEL;

    private final WeaponMasterMain plugin;
    private final Util util;
    public HeliosItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    private double damageMultiply(Player player, double damage) {
        double multiplier = DAMAGE_INCREASE_PER_LEVEL * Math.min(player.getLevel(), MAX_DAMAGE_LEVEL);
        player.sendMessage(String.format("§7[Debug] Hit increased by %.1f%%, damage %.1f -> %.1f.",
                multiplier*100, damage, damage*(1+multiplier)));
        return damage * (1 + multiplier);
    }

    private double distanceSquaredXZ(Location loc1, Location loc2) {
        double deltaX = loc1.getX() - loc2.getX();
        double deltaZ = loc1.getZ() - loc2.getZ();
        return deltaX*deltaX + deltaZ*deltaZ;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        if (!util.checkForHelios(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.helios.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.helios.use§3.");
            return;
        }
        // increment damage
        event.setDamage(damageMultiply(player, event.getDamage()));
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        Player player = event.getPlayer();
        ItemStack helios = player.getInventory().getItemInMainHand();
        if (!util.checkForHelios(helios)) { return; }
        if (!player.hasPermission("weaponmaster.helios.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.helios.use§3.");
            return;
        }
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) { return; }
        if (Util.checkForInteractableBlock(event)) { return; }
        if (player.getFoodLevel() < 12 && player.getGameMode() != GameMode.CREATIVE) {
            player.sendMessage("§7You don't have enough hunger to use this!");
            return;
        }

        Location loc = player.getLocation();
        List<Entity> entitiesClose = player.getNearbyEntities(IMPACT_DISTANCE, IMPACT_DISTANCE, IMPACT_DISTANCE);
        for (Entity e : entitiesClose) {
            if (!(e instanceof LivingEntity)) { continue; }
            if (distanceSquaredXZ(loc, e.getLocation()) > IMPACT_DISTANCE_SQUARED) { // forces cube shape into cylindrical shape
                continue;
            }
            LivingEntity target = (LivingEntity) e;
            double damage = 5.0 + helios.getItemMeta().getEnchantLevel(Enchantment.DAMAGE_ALL) * 0.25;
            target.damage(damageMultiply(player, damage));
            target.setFireTicks(target.getFireTicks() + 200);
            target.setVelocity(target.getVelocity().add(new Vector(0.0, 0.4, 0.0)));
        }
        if (player.getGameMode() != GameMode.CREATIVE) {
            player.setExhaustion(player.getExhaustion() + FOOD_COST);
        }

        for (int step = 0; step < 280; step++) {
            double theta = Math.PI * step / 140;
            double deltaX = IMPACT_DISTANCE * Math.cos(theta);
            double deltaY = IMPACT_DISTANCE * Math.sin(theta);
            loc.getWorld().spawnParticle(Particle.FLAME, loc.clone().add(deltaX, 0.0, deltaY), 1, 0.0, 0.0, 0.0, 0.001);
        }
    }

}