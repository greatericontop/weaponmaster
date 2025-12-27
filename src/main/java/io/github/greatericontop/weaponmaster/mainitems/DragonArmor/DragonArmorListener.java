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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class DragonArmorListener implements Listener {

    private final double PROTECTION_EACH;
    private final double PROTECTION_ENCHANT;
    private final double ATTACK_BONUS_EACH;
    private final float REDUCTION_HUNGER;
    private final double MIN_DAMAGE_MULTIPLIER;
    private final boolean fixStuttering;

    private final WeaponMasterMain plugin;
    private final Util util;
    public DragonArmorListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.util = new Util(plugin);
        PROTECTION_EACH = plugin.getConfig().getDouble("dragonArmor.protection_each", 0.05);
        PROTECTION_ENCHANT = plugin.getConfig().getDouble("dragonArmor.protection_enchant", 0.0075);
        ATTACK_BONUS_EACH = plugin.getConfig().getDouble("dragonArmor.attack_bonus_each", 0.02);
        REDUCTION_HUNGER = (float) plugin.getConfig().getDouble("dragonArmor.reduction_hunger", 0.6666666666666666);
        MIN_DAMAGE_MULTIPLIER = plugin.getConfig().getDouble("dragonArmor.min_damage_multiplier", 0.02);
        fixStuttering = plugin.getConfig().getBoolean("dragonArmor.fixStuttering", true);
    }

    public boolean hasFullSet(PlayerInventory inventory) {
        return util.checkForDragonArmor(inventory.getHelmet())
                && util.checkForDragonArmor(inventory.getChestplate())
                && util.checkForDragonArmor(inventory.getLeggings())
                && util.checkForDragonArmor(inventory.getBoots());
    }

    private int getUpgradeLevel(ItemStack stack) {
        PersistentDataContainer pdc = stack.getItemMeta().getPersistentDataContainer();
        return pdc.getOrDefault(DragonArmorUpgradeListener.DRAGON_ARMOR_UPGRADE_KEY, PersistentDataType.INTEGER, 0);
    }

    @EventHandler(priority = EventPriority.LOW) // runs near the beginning
    public void onDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.VOID)  return;
        if (event.getEntity().getType() != EntityType.PLAYER)  return;
        Player player = (Player) event.getEntity();
        double damageProtection = 1.0;
        if (util.checkForDragonArmor(player.getInventory().getHelmet())) { damageProtection -= PROTECTION_EACH + 0.005*getUpgradeLevel(player.getInventory().getHelmet()); }
        if (util.checkForDragonArmor(player.getInventory().getChestplate())) { damageProtection -= PROTECTION_EACH + 0.005*getUpgradeLevel(player.getInventory().getChestplate()); }
        if (util.checkForDragonArmor(player.getInventory().getLeggings())) { damageProtection -= PROTECTION_EACH + 0.005*getUpgradeLevel(player.getInventory().getLeggings()); }
        if (util.checkForDragonArmor(player.getInventory().getBoots())) { damageProtection -= PROTECTION_EACH + 0.005*getUpgradeLevel(player.getInventory().getBoots()); }
        if (hasFullSet(player.getInventory())) {
            int a = player.getInventory().getHelmet().getEnchantmentLevel(Enchantment.PROTECTION);
            int b = player.getInventory().getChestplate().getEnchantmentLevel(Enchantment.PROTECTION);
            int c = player.getInventory().getLeggings().getEnchantmentLevel(Enchantment.PROTECTION);
            int d = player.getInventory().getBoots().getEnchantmentLevel(Enchantment.PROTECTION);
            damageProtection -= (a + b + c + d) * PROTECTION_ENCHANT;
        }
        if (damageProtection >= 0.999)  return;
        damageProtection = Math.max(damageProtection, MIN_DAMAGE_MULTIPLIER);
        if (!player.hasPermission("weaponmaster.dragonarmor.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.dragonarmor.use§3.");
            return;
        }

        event.setDamage(event.getDamage() * damageProtection);
        // Prevents hurt cam stuttering from e.g. standing in fire or lava where there is incoming damage every tick.
        // - getLastDamage() is the getDamage() amount, not the getFinalDamage() amount.
        // - no damage ticks is set to 20 after taking damage and you can take damage again when it hits 10
        if (fixStuttering && player.getNoDamageTicks() > 0 && player.getNoDamageTicks() != 10 && event.getDamage() < player.getLastDamage() + 0.001) {
            event.setCancelled(true);
            return;
        }
        plugin.paperUtils.sendActionBar(player, String.format("§eDamage was reduced by %.0f%% to %.1f.", 100*(1-damageProtection), event.getDamage()), false);

    }

    @EventHandler(priority = EventPriority.LOW)
    public void onDamageFromEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER)  return;
        Player player = (Player) event.getDamager();
        double damageBonus = 1.0;
        if (util.checkForDragonArmor(player.getInventory().getHelmet())) { damageBonus += ATTACK_BONUS_EACH + 0.002*getUpgradeLevel(player.getInventory().getHelmet()); }
        if (util.checkForDragonArmor(player.getInventory().getChestplate())) { damageBonus += ATTACK_BONUS_EACH + 0.002*getUpgradeLevel(player.getInventory().getChestplate()); }
        if (util.checkForDragonArmor(player.getInventory().getLeggings())) { damageBonus += ATTACK_BONUS_EACH + 0.002*getUpgradeLevel(player.getInventory().getLeggings()); }
        if (util.checkForDragonArmor(player.getInventory().getBoots())) { damageBonus += ATTACK_BONUS_EACH + 0.002*getUpgradeLevel(player.getInventory().getBoots()); }
        if (damageBonus <= 1.001)  return;
        if (!player.hasPermission("weaponmaster.dragonarmor.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.dragonarmor.use§3.");
            return;
        }

        event.setDamage(event.getDamage() * damageBonus);
    }

    @EventHandler(priority = EventPriority.HIGH) // runs near the end
    public void onExhaustion(EntityExhaustionEvent event) {
        Player player = (Player) event.getEntity();
        if (!hasFullSet(player.getInventory()))  return;
        if (!player.hasPermission("weaponmaster.dragonarmor.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.dragonarmor.use§3.");
            return;
        }
        event.setExhaustion(event.getExhaustion() * REDUCTION_HUNGER);
    }

}