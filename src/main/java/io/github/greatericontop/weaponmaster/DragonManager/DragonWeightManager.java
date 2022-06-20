package io.github.greatericontop.weaponmaster.DragonManager;

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
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class DragonWeightManager {
    private final double DAMAGE_WEIGHT_MAX;

    public final Set<Player> players = new HashSet<>();
    private final Map<UUID, Double> dragonDamage = new HashMap<>();
    private double totalDamageDealt = 0.0;
    private final Map<UUID, Double> weightBonus = new HashMap<>();
    private int crystalsDestroyed = 0;

    private final WeaponMasterMain plugin;
    private final EnderDragon currentlyActiveDragon;
    private boolean enabled;
    public DragonWeightManager(WeaponMasterMain plugin, EnderDragon currentlyActiveDragon, double damageWeightMax) {
        this.plugin = plugin;
        this.currentlyActiveDragon = currentlyActiveDragon;
        this.enabled = true;
        this.DAMAGE_WEIGHT_MAX = damageWeightMax;
    }

    // TODO: limit the total amount of damage, eg only the first 1000 damage can count towards damage weight
    // TODO: limit the total number of crystals in case someone uses pistons, only the first 10 count towards bonus
    public double getDamage(UUID player) {
        return dragonDamage.getOrDefault(player, 0.0);
    }
    public void setDamageRaw(UUID player, double amount) {
        dragonDamage.put(player, amount);
    }
    public void incrementDamage(UUID player, double amount) {
        totalDamageDealt += amount;
        setDamageRaw(player, getDamage(player) + amount);
    }
    public double getDamageDragonWeight(UUID player) {
        double damageDealt = getDamage(player);
        double weightFactor = Math.min(DAMAGE_WEIGHT_MAX / totalDamageDealt, 1.0);
        return damageDealt * weightFactor;
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

    public int getDragonWeight(UUID player) {
        int baseWeight = (int) (getDamageDragonWeight(player) + getBonus(player));
        UUID maxPlayer = null; double maxAmount = 200.0;
        for (UUID p : dragonDamage.keySet()) {
            // your score is lowered to require you to beat people by a margin and to prevent tie abuse
            double pAmount = getDamage(p) - (p.equals(player) ? 30.0 : 0.0);
            if (pAmount > maxAmount) {
                maxAmount = pAmount;
                maxPlayer = p;
            }
        }
        return baseWeight + (maxPlayer != null && maxPlayer.equals(player) ? 75 : 0);
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

        if (victim instanceof EnderDragon && victim.getUniqueId().equals(currentlyActiveDragon.getUniqueId())) {
            if (!players.contains(player)) {
                players.add(player);
            }
            incrementDamage(player.getUniqueId(), event.getFinalDamage());
        }

        if (victim instanceof EnderCrystal) {
            EnderCrystal crystal = (EnderCrystal) victim;
            // only naturally generated crystals show the bottom (no placing them yourself)
            // to prevent people from using pistons to push crystals, only 10 bonuses exist per fight
            // after breaking 10 crystals you can't farm more weight with them
            if (crystal.isShowingBottom() && crystalsDestroyed < 10) {
                Bukkit.getServer().getLogger().info(String.format("%s damaged an end crystal by %.1f", player.getName(), event.getFinalDamage()));
                giveBonus(player.getUniqueId(), 60.0);
                crystalsDestroyed++;
            }
        }

        player.sendMessage(String.format("§7You now have %.3f damage + %.1f bonus weight -> §6%d",
                getDamage(player.getUniqueId()), getBonus(player.getUniqueId()), getDragonWeight(player.getUniqueId())));
    }

}
