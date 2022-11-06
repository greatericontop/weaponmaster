package io.github.greatericontop.weaponmaster.dragondescent;

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
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DescentEvents implements Listener {
    private final Map<UUID, Boolean> heartbleedCooldown = new HashMap<>();

    private final WeaponMasterMain plugin;
    private final DescentDataManager descent;
    public DescentEvents(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.descent = plugin.descent;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getEntity();

        // allDamageResistance
        int allDamageResistance = descent.getUpgrade(player, "allDamageResistance");
        if (allDamageResistance > 0) {
            double multi = 1.0 - 0.005*allDamageResistance;
            event.setDamage(event.getDamage() * multi);
        }

        // strongLegs
        int strongLegs = descent.getUpgrade(player, "strongLegs");
        if (strongLegs > 0) {
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                double multi = 1.0 - 0.15*strongLegs;
                event.setDamage(event.getDamage() * multi);
            }
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamagePlayer(EntityDamageByEntityEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getEntity();

        // mightyStrength
        int mightyStrength = descent.getUpgrade(player, "mightyStrength");
        if (mightyStrength > 0) {
            double activationChance = 0.001 * mightyStrength;
            if (Math.random() < activationChance) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0, true));
            }
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();

        // strongAttacks
        int strongAttacks = descent.getUpgrade(player, "strongAttacks");
        if (strongAttacks > 0) {
            double multi = 1.0 + 0.005*strongAttacks;
            event.setDamage(event.getDamage() * multi);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityKilledByPlayer(EntityDeathEvent event) {
        if (event.getEntity().getKiller() == null) { return; }
        Player player = event.getEntity().getKiller();

        // vitality
        int vitality = descent.getUpgrade(player, "vitality");
        if (vitality > 0) {
            int ticks = 20 * vitality;
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, ticks, 0, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, ticks, 0, true));
        }

        // heartbleed
        int heartbleed = descent.getUpgrade(player, "heartbleed");
        if (heartbleed > 0) {
            double activationChance = 0.2 * heartbleed;
            if (heartbleedCooldown.getOrDefault(player.getUniqueId(), true) && Math.random() < activationChance) {
                double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
                double newHealth = Math.min(maxHealth, player.getHealth() + 1);
                player.setHealth(newHealth);
                heartbleedCooldown.put(player.getUniqueId(), false);
                new BukkitRunnable() {
                    public void run() {
                        heartbleedCooldown.put(player.getUniqueId(), true);
                    }
                }.runTaskLater(plugin, 40L);
            }
        }
    }



    // other events

    @EventHandler(priority = EventPriority.NORMAL)
    public void onHunger(EntityExhaustionEvent event) {
        Player player = (Player) event.getEntity();
        // enhancedEnergy
        int enhancedEnergy = descent.getUpgrade(player, "enhancedEnergy");
        if (enhancedEnergy > 0) {
            // TODO: fix %
            float multi = 1.0F - 0.1F*enhancedEnergy;
            event.setExhaustion(event.getExhaustion() * multi);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerExperienceGain(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();
        // wisdom
        int wisdom = descent.getUpgrade(player, "wisdom");
        if (wisdom > 0) {
            // TODO: fix %
            double multi = 1.0 + 0.4*wisdom;
            double amount = event.getAmount() * multi;
            int decPart = (int) amount;
            int fracPart = Math.random() < (amount % 1) ? 1 : 0;
            event.setAmount(decPart + fracPart);
        }
    }

}
