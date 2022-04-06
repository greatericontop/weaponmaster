package io.github.greatericontop.weaponmaster.DeathScythe;

/*
    Copyright (C) 2021 greateric.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import io.github.greatericontop.weaponmaster.utils.TrueDamageHelper;
import io.github.greatericontop.weaponmaster.utils.Util;
import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerItemMendEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ScytheItemListener implements Listener {

    private final Util util;
    public ScytheItemListener(WeaponMasterMain plugin) {
        util = new Util(plugin);
    }

    private int getStrengthLevel(double damageAmount) {
        if (damageAmount >= 18.0) {
            return 4; // Strength V
        } else if (damageAmount >= 12.0) {
            return 3;
        } else if (damageAmount >= 7.0) {
            return 2;
        } else if (damageAmount >= 3.0) {
            return 1;
        }
        return 0;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onMending(PlayerItemMendEvent event) {
        if (util.checkForDeathScythe(event.getItem())) {
            event.getPlayer().sendMessage("§6Nice try! You cannot mend §f["+util.DEATH_SCYTHE_NAME+"§f]§6.");
            event.setCancelled(true);
        }
    }

    private final int DURABILITY_THRESHOLD = 249;
    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player)event.getDamager();
        if (!util.checkForDeathScythe(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.deathscythe.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.deathscythe.use§3.");
            return;
        }

        ItemStack scythe = player.getInventory().getItemInMainHand();
        Damageable iMeta = (Damageable) scythe.getItemMeta();
        if (iMeta.getDamage() >= DURABILITY_THRESHOLD) { // 1 durability
            player.sendMessage("§3Not enough durability!");
        }

        LivingEntity target = (LivingEntity) event.getEntity();
        double damageAmount = (target.getHealth() + target.getAbsorptionAmount()) * 0.3;
        TrueDamageHelper.dealTrueDamage(target, damageAmount, player);
        int strengthLevel = getStrengthLevel(damageAmount);
        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 300, strengthLevel, true));
        player.sendMessage(String.format("§3Dealt §4%.1f §3damage. You gained §bStrength §c%d§3 for §b15 §3seconds.", damageAmount, strengthLevel+1));


        iMeta.setDamage(iMeta.getDamage() + 26);
        if (iMeta.getDamage() > DURABILITY_THRESHOLD) {
            iMeta.setDamage(DURABILITY_THRESHOLD);
        }
        scythe.setItemMeta(iMeta);
    }

}