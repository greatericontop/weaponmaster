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
import io.github.greatericontop.weaponmaster.utils.MathHelper;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

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

        // living entities only
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) event.getEntity();

            // silkyTouch
            int silkyTouch = descent.getUpgrade(player, "silkyTouch");
            if (silkyTouch > 0) {
                // TODO: fix %
                double activationChance = 0.2 * silkyTouch;
                if (Math.random() < activationChance) {
                    target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 0, true));
                }
            }

            // sicklyTouch
            int sicklyTouch = descent.getUpgrade(player, "sicklyTouch");
            if (sicklyTouch > 0) {
                // TODO: fix %
                double activationChance = 0.2 * sicklyTouch;
                if (Math.random() < activationChance) {
                    target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 0, true));
                }
            }
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

    private final List<Material> ARMOR_MATERIALS = Arrays.asList(
            Material.LEATHER_HELMET, Material.LEATHER_CHESTPLATE, Material.LEATHER_LEGGINGS, Material.LEATHER_BOOTS,
            Material.CHAINMAIL_HELMET, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_LEGGINGS, Material.CHAINMAIL_BOOTS,
            Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS,
            Material.GOLDEN_HELMET, Material.GOLDEN_CHESTPLATE, Material.GOLDEN_LEGGINGS, Material.GOLDEN_BOOTS,
            Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS,
            Material.NETHERITE_HELMET, Material.NETHERITE_CHESTPLATE, Material.NETHERITE_LEGGINGS, Material.NETHERITE_BOOTS
    );

    @EventHandler
    public void onDurability(PlayerItemDamageEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        // shieldedArmor
        int durability = descent.getUpgrade(player, "shieldedArmor");
        if (durability > 0) {
            if (ARMOR_MATERIALS.contains(item.getType())) {
                double multi = 1.0 - 0.015*durability;
                int newDamage = MathHelper.roundProbability(event.getDamage() * multi);
                event.setDamage(newDamage);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPotion(EntityPotionEffectEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getEntity();

        // witch
        int witch = descent.getUpgrade(player, "witch");
        if (witch > 0) {
            if ((event.getAction() == EntityPotionEffectEvent.Action.ADDED || event.getAction() == EntityPotionEffectEvent.Action.CHANGED)
                    && event.getCause() == EntityPotionEffectEvent.Cause.POTION_DRINK) {
                PotionEffect eventEffect = event.getNewEffect();
                double multi = 1 + 0.025*witch;
                int newDuration = MathHelper.roundProbability(eventEffect.getDuration() * multi);
                PotionEffect extendedEffect = new PotionEffect(eventEffect.getType(), newDuration, eventEffect.getAmplifier(), eventEffect.isAmbient(), eventEffect.hasParticles(), eventEffect.hasIcon());
                // since we can't change the effect used in the event, simply add it here, and it will overwrite it
                event.setCancelled(true);
                player.addPotionEffect(extendedEffect);
            }
        }

        // reviver
        int reviver = descent.getUpgrade(player, "reviver");
        if (reviver > 0) {
            if (event.getAction() == EntityPotionEffectEvent.Action.ADDED && event.getCause() == EntityPotionEffectEvent.Cause.TOTEM) {
                PotionEffect eventEffect = event.getNewEffect();
                // for funny reasons .equals and == don't work here
                if (eventEffect.getType().getId() == PotionEffectType.REGENERATION.getId()) {
                    if (eventEffect.getDuration() != 900) {
                        plugin.getLogger().warning("Regeneration effect from totem is not 900t/45s (likely due to an update)! Please nag us about it!");
                    }
                    int strongDuration = 12 * reviver; // 1 extra health point per level
                    int extendDuration = strongDuration + 900 + 60*reviver; // 45s + 3s per level
                    PotionEffect extendedEffect = new PotionEffect(PotionEffectType.REGENERATION, extendDuration, 1, eventEffect.isAmbient(), eventEffect.hasParticles(), eventEffect.hasIcon());
                    PotionEffect strongEffect = new PotionEffect(PotionEffectType.REGENERATION, strongDuration, 2, eventEffect.isAmbient(), eventEffect.hasParticles(), eventEffect.hasIcon());
                    event.setCancelled(true);
                    player.addPotionEffect(extendedEffect);
                    player.addPotionEffect(strongEffect); // if the strong one is added second, both stack
                }
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
            float multi = 1.0F - 0.02F*enhancedEnergy;
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
            double newAmount = event.getAmount() * multi;
            event.setAmount(MathHelper.roundProbability(newAmount));
        }
    }

}
