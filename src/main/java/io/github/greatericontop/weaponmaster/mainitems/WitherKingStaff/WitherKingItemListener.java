package io.github.greatericontop.weaponmaster.mainitems.WitherKingStaff;

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
import io.github.greatericontop.weaponmaster.utils.InaccuracyAdder;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WitherKingItemListener implements Listener {

    private final Map<UUID, Boolean> cooldowns = new HashMap<>();
    private final Map<UUID, Boolean> healCooldowns = new HashMap<>();

    private final WeaponMasterMain plugin;
    private final Util util;
    public WitherKingItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    // same code as before but for right click
    @EventHandler()
    public void onLeftClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND)  return;
        Player player = event.getPlayer();
        if (!util.checkForWitherKingStaff(player.getInventory().getItemInMainHand()))  return;
        if (!player.hasPermission("weaponmaster.witherkingstaff.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.witherkingstaff.use§3.");
            return;
        }
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK)  return;
        if (!cooldowns.getOrDefault(player.getUniqueId(), true))  return;
        cooldowns.put(player.getUniqueId(), false);

        // 0.35/0.02 + 30% -> 0.455/0.026
        Vector velocity = player.getLocation().getDirection().normalize().multiply(0.455).add(InaccuracyAdder.generateInaccuracy(0.026));
        WitherSkull witherSkull = (WitherSkull) player.getLocation().getWorld().spawnEntity(player.getEyeLocation(), EntityType.WITHER_SKULL);
        witherSkull.setVelocity(velocity);
        witherSkull.setShooter(player);
        witherSkull.setCharged(Math.random() < plugin.getConfig().getDouble("witherStaff.chargedChance", 0.06));

        long cooldown = plugin.getConfig().getLong("witherStaff.cooldownTicksUpgraded", 4L);
        if (cooldown > 0) {
            Bukkit.getScheduler().runTaskLater(plugin, () -> cooldowns.put(player.getUniqueId(), true), cooldown);
        } else {
            cooldowns.put(player.getUniqueId(), true);
        }
    }

    private static final double SHIELD_AMOUNT = 10.0;
    @EventHandler()
    public void onRightClickHeal(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND)  return;
        Player player = event.getPlayer();
        if (!util.checkForWitherKingStaff(player.getInventory().getItemInMainHand()))  return;
        if (!player.hasPermission("weaponmaster.witherkingstaff.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.witherkingstaff.use§3.");
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK)  return;
        if (!healCooldowns.getOrDefault(player.getUniqueId(), true))  return;
        healCooldowns.put(player.getUniqueId(), false);

        player.removePotionEffect(PotionEffectType.ABSORPTION);
        player.setAbsorptionAmount(SHIELD_AMOUNT);
        player.playSound(player.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 1.0F, 1.0F);

        new BukkitRunnable() {
            public void run() {
                if (player.getPotionEffect(PotionEffectType.ABSORPTION) != null) {
                    // if you got the absorption effect and gave yourself more hearts
                    return;
                }
                double absorptionAmount = player.getAbsorptionAmount();
                player.setAbsorptionAmount(0.0);
                player.setHealth(Math.min(player.getHealth() + absorptionAmount, player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()));
            }
        }.runTaskLater(plugin, 200L);

        Bukkit.getScheduler().runTaskLater(plugin, () -> healCooldowns.put(player.getUniqueId(), true), 300L);
    }

}