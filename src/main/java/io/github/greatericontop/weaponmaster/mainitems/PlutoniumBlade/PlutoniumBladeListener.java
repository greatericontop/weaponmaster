package io.github.greatericontop.weaponmaster.mainitems.PlutoniumBlade;

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
import io.github.greatericontop.weaponmaster.mainitems.DragonSword.DragonSwordUpgradeListener;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class PlutoniumBladeListener implements Listener {
    private static final int MAX_DURABILITY = 2031;
    private static final int RAYS = 10000; // on my pc these only take like 1-4 ms per iteration
    private static final double STEP = 0.4;
    private static final double GOLDEN_ANGLE = 3.88322207745093; // pi * (root5 - 1)
    private static final double DAMAGE = 70.0;
    private static final double KNOCKBACK_STRENGTH = 9.0; // length of velocity vector added to things attacked

    private Map<UUID, Boolean> cooldown = new HashMap<>();

    private final WeaponMasterMain plugin;
    private final Util util;
    private final DragonSwordUpgradeListener dragonUpgrade;
    public PlutoniumBladeListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.util = new Util(plugin);
        this.dragonUpgrade = new DragonSwordUpgradeListener(plugin);
    }

    @EventHandler(priority = EventPriority.HIGH) // runs nearer to the end to stack bonuses
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER)  return;
        Player player = (Player) event.getDamager();
        if (!util.checkForPlutoniumBlade(player.getInventory().getItemInMainHand()))  return;
        if (!player.hasPermission("weaponmaster.plutoniumblade.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.plutoniumblade.use§3.");
            return;
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.CUSTOM)  return; // so it doesn't apply to the ability
        // You just have to be falling actually
        if (player.getFallDistance() > 0.01F && (!player.isOnGround()) && (!player.isClimbing())) {
            event.setDamage(event.getDamage() * 1.2);
            player.getWorld().playSound(player.getLocation(), Sound.ITEM_MACE_SMASH_GROUND_HEAVY, 1.0F, 1.0F);
        }
    }

    @EventHandler()
    public void onShiftRightClickLookingDown(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND)  return;
        Player player = event.getPlayer();
        ItemStack stack = player.getInventory().getItemInMainHand();
        if (!util.checkForPlutoniumBlade(stack))  return;
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)  return;
        if (!player.isSneaking())  return;
        if (!player.hasPermission("weaponmaster.plutoniumblade.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.plutoniumblade.use§3.");
            return;
        }
        if (!cooldown.getOrDefault(player.getUniqueId(), true)) {
            player.sendMessage("§cOn cooldown!");
            return;
        }
        Vector lookDirection = player.getEyeLocation().getDirection().normalize();
        // Look straight down (or really close), horizontal radius can't be more than 0.08
        if (lookDirection.getX()*lookDirection.getX() + lookDirection.getZ()*lookDirection.getZ() > 0.0064 || lookDirection.getY() > 0)  return;
        Damageable im = (Damageable) stack.getItemMeta();
        if (im.getDamage() > MAX_DURABILITY - 300) {
            player.sendMessage("§cYour blade is too damaged to use this ability!");
            return;
        }
        if (player.getGameMode() != GameMode.CREATIVE) {
            int durability = 1 + 99/(1+im.getEnchantLevel(Enchantment.UNBREAKING)); // rounds up essentially
            im.setDamage(im.getDamage() + durability);
            stack.setItemMeta(im);
        }

        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);

        new BukkitRunnable() {
            int i = 0;
            Location startLoc;
            Set<UUID> alreadyHit = new HashSet<>();
            public void run() {
                i++;
                if (i == 1) {
                    this.startLoc = player.getEyeLocation().clone();
                    this.alreadyHit.add(player.getUniqueId()); // so player doesn't get hit by own ability
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0F, 1.0F);
                }
                // each STEP = 0.4 blocks, so 20 blocks = 50 steps (100 ticks)
                if (i > 50) {
                    player.playSound(player.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 0.6F, 1.0F); // ending sound played for player
                    this.cancel();
                    return;
                }
                // fibonacci rays
                long time = System.currentTimeMillis();
                for (int ray = 0; ray < RAYS; ray++) {
                    double dy = 1.0 - 2.0 * ((double) ray / (double) (RAYS - 1));
                    double radius = Math.sqrt(1.0 - dy * dy);
                    double theta = GOLDEN_ANGLE * ray;
                    double dx = Math.cos(theta) * radius;
                    double dz = Math.sin(theta) * radius;
                    Vector offset = new Vector(dx, dy, dz).multiply(STEP * i); // length of STEP * i
                    Location loc = startLoc.clone().add(offset);
                    // dumb entity detection
                    // 10k rays seems to be 1 block apart at 20 blocks, so 0.5 radius cuboids should catch basically everything
                    for (Entity e : player.getWorld().getNearbyEntities(loc, 0.5, 0.5, 0.5, e -> e instanceof LivingEntity)) {
                        LivingEntity le = (LivingEntity) e;
                        if (alreadyHit.contains(le.getUniqueId()))  continue;
                        le.setVelocity(le.getVelocity().add(offset.normalize().multiply(KNOCKBACK_STRENGTH)));
                        le.setFireTicks(200);
                        if (plugin.minorItemListener.withers.contains(le.getUniqueId())) {
                            le.damage(DAMAGE*0.4, player); // less ability damage to wither challenge
                        } else {
                            le.damage(DAMAGE, player);
                        }
                        alreadyHit.add(le.getUniqueId());
                    }
                    loc.getWorld().spawnParticle(Particle.SMALL_FLAME, loc, 1, 0.0, 0.0, 0.0, 0.0);
                }
            }
        }.runTaskTimer(plugin, 15L, 2L); // thing starts / sound plays after 15 ticks

        cooldown.put(player.getUniqueId(), false);
        new BukkitRunnable() {
            public void run() {
                cooldown.put(player.getUniqueId(), true);
            }
        }.runTaskLater(plugin, 400L);
    }

}