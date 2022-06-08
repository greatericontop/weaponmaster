package io.github.greatericontop.weaponmaster.dragon_manager;

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
import org.bukkit.Bukkit;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DragonWeightManager {

    private final Map<UUID, Double> dragonDamage = new HashMap<>();
    private final Map<UUID, Double> weightBonus = new HashMap<>();

    private final WeaponMasterMain plugin;
    private final EnderDragon currentlyActiveDragon;
    private boolean enabled;
    public DragonWeightManager(WeaponMasterMain plugin, EnderDragon currentlyActiveDragon) {
        this.plugin = plugin;
        this.currentlyActiveDragon = currentlyActiveDragon;
        this.enabled = true;
    }

    // TODO: limit the total amount of damage, eg only the first 1000 damage can count towards damage weight
    // TODO: limit the total number of crystals in case someone uses pistons, only the first 10 count towards bonus
    public double getDamage(UUID player) {
        return dragonDamage.getOrDefault(player, 0.0);
    }
    public void setDamage(UUID player, double amount) {
        dragonDamage.put(player, amount);
    }
    public void incrementDamage(UUID player, double amount) {
        setDamage(player, getDamage(player) + amount);
    }

    public double getBonus(UUID player) {
        return weightBonus.getOrDefault(player, 0.0);
    }
    public void setBonus(UUID player, double amount) {
        weightBonus.put(player, amount);
    }
    public void giveBonus(UUID player, double amount) {
        setBonus(player, getBonus(player) + amount);
    }

    public DragonWeightManager setEnabled(boolean v) {
        enabled = v;
        return this;
    }

    public void onDamage(EntityDamageByEntityEvent event) {
        // TODO: should non-player damage be attributed to the nearest player?
        if (!enabled) { return; }
        Player player;
        if (event.getDamager() instanceof Player) {
            player = (Player) event.getDamager();
        } else if (event.getDamager() instanceof Projectile) {
            ProjectileSource shooter = ((Projectile) event.getDamager()).getShooter();
            if (!(shooter instanceof Player)) { return; }
            player = (Player) shooter;
        } else {
            return;
        }
        Entity victim = event.getEntity();
        System.out.println("ok working");

        if (victim instanceof EnderDragon && victim.getUniqueId().equals(currentlyActiveDragon.getUniqueId())) {
            Bukkit.getServer().getLogger().info(String.format("%s dealt %.2f damage to dragon", player.getName(), event.getFinalDamage()));
            incrementDamage(player.getUniqueId(), event.getFinalDamage());
        }

        if (victim instanceof EnderCrystal) {
            EnderCrystal crystal = (EnderCrystal) victim;
            //if (event.getFinalDamage() <= 0) { return; }
            if (crystal.isShowingBottom()) { // only naturally generated crystals show the bottom
                Bukkit.getServer().getLogger().info(String.format("%s damaged an end crystal by %.1f", player.getName(), event.getFinalDamage()));
                giveBonus(player.getUniqueId(), 40.0);
            }
        }

        player.sendMessage(String.format("§7You now have %.3f damage + %.1f bonus weight.", getDamage(player.getUniqueId()), getBonus(player.getUniqueId())));
    }

}
