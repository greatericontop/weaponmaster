package io.github.greatericontop.weaponmaster.mainitems.DragonArmor;

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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExhaustionEvent;
import org.bukkit.inventory.PlayerInventory;

public class DragonArmorListener implements Listener {

    private final double PROTECTION_EACH = 0.05;
    private final double PROTECTION_ENCHANT = 0.00_75;
    private final double BONUS_EACH = 0.03;
    private final float REDUCTION_HUNGER = 0.6666666666666666F;

    private final WeaponMasterMain plugin;
    private final Util util;
    public DragonArmorListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.util = new Util(plugin);
    }

    public boolean hasFullSet(PlayerInventory inventory) {
        return util.checkForDragonArmor(inventory.getHelmet())
                && util.checkForDragonArmor(inventory.getChestplate())
                && util.checkForDragonArmor(inventory.getLeggings())
                && util.checkForDragonArmor(inventory.getBoots());
    }

    @EventHandler(priority = EventPriority.LOW) // runs near the beginning
    public void onDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) { return; }
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getEntity();
        double damageProtection = 1.0;
        if (util.checkForDragonArmor(player.getInventory().getHelmet())) { damageProtection -= PROTECTION_EACH; }
        if (util.checkForDragonArmor(player.getInventory().getChestplate())) { damageProtection -= PROTECTION_EACH; }
        if (util.checkForDragonArmor(player.getInventory().getLeggings())) { damageProtection -= PROTECTION_EACH; }
        if (util.checkForDragonArmor(player.getInventory().getBoots())) { damageProtection -= PROTECTION_EACH; }
        if (hasFullSet(player.getInventory())) {
            int a = player.getInventory().getHelmet().getEnchantmentLevel(Enchantment.PROTECTION);
            int b = player.getInventory().getChestplate().getEnchantmentLevel(Enchantment.PROTECTION);
            int c = player.getInventory().getLeggings().getEnchantmentLevel(Enchantment.PROTECTION);
            int d = player.getInventory().getBoots().getEnchantmentLevel(Enchantment.PROTECTION);
            damageProtection -= (a + b + c + d) * PROTECTION_ENCHANT;
        }
        if (damageProtection >= 0.999) { return; }
        damageProtection = Math.max(damageProtection, 0.02);
        if (!player.hasPermission("weaponmaster.dragonarmor.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.dragonarmor.use§3.");
            return;
        }

        event.setDamage(event.getDamage() * damageProtection);
        plugin.paperUtils.sendActionBar(player, String.format("§eDamage was reduced by %.0f%% to %.1f.", 100*(1-damageProtection), event.getDamage()), false);

    }

    @EventHandler(priority = EventPriority.LOW)
    public void onDamageFromEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        double damageBonus = 1.0;
        if (util.checkForDragonArmor(player.getInventory().getHelmet())) { damageBonus += BONUS_EACH; }
        if (util.checkForDragonArmor(player.getInventory().getChestplate())) { damageBonus += BONUS_EACH; }
        if (util.checkForDragonArmor(player.getInventory().getLeggings())) { damageBonus += BONUS_EACH; }
        if (util.checkForDragonArmor(player.getInventory().getBoots())) { damageBonus += BONUS_EACH; }
        if (damageBonus <= 1.001) { return; }
        if (!player.hasPermission("weaponmaster.dragonarmor.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.dragonarmor.use§3.");
            return;
        }

        event.setDamage(event.getDamage() * damageBonus);
    }

    @EventHandler(priority = EventPriority.HIGH) // runs near the end
    public void onExhaustion(EntityExhaustionEvent event) {
        Player player = (Player) event.getEntity();
        if (!hasFullSet(player.getInventory())) { return; }
        if (!player.hasPermission("weaponmaster.dragonarmor.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.dragonarmor.use§3.");
            return;
        }
        event.setExhaustion(event.getExhaustion() * REDUCTION_HUNGER);
    }

}