package io.github.greatericontop.weaponmaster.mainitems.PoseidonTrident;

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
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class TridentListener implements Listener {
    private final Set<UUID> activeTridents = new HashSet<>();

    private final WeaponMasterMain plugin;
    private final Util util;
    public TridentListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    public void regTridentRunnable() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!player.hasPermission("weaponmaster.poseidontrident.use")) { continue; }
                    if (!util.checkForPoseidonTrident(player.getInventory().getItemInMainHand())) { continue; }
                    player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, 39, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 39, 0));
                }
            }
        }.runTaskTimer(plugin, 200L, 5L);
    }

    @EventHandler()
    public void tridentThrow(ProjectileLaunchEvent event) {
        if (!(event.getEntity().getShooter() instanceof Player))  return;
        Player player = (Player) event.getEntity().getShooter();
        if (!util.checkForPoseidonTrident(player.getInventory().getItemInMainHand()))  return;
        if (!player.hasPermission("weaponmaster.poseidontrident.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.poseidontrident.use§3.");
            return;
        }
        player.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, player.getEyeLocation().add(player.getEyeLocation().getDirection().multiply(0.9)), 15);
        activeTridents.add(event.getEntity().getUniqueId());
    }

    @EventHandler()
    public void onProjHit(ProjectileHitEvent event) {
        if (!activeTridents.contains(event.getEntity().getUniqueId()))  return;
        if (!(event.getEntity().getShooter() instanceof Player))  return;
        if (event.getHitEntity() == null)  return;
        activeTridents.remove(event.getEntity().getUniqueId());
        if (Math.random() < 0.1) {
            event.getHitEntity().getWorld().spawnEntity(event.getHitEntity().getLocation(), EntityType.LIGHTNING_BOLT);
        }
    }
}
