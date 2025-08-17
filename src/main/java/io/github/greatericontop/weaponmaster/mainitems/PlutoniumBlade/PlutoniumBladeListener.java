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
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlutoniumBladeListener implements Listener {

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
        // You just have to be falling actually
        if (player.getFallDistance() > 0.01F && (!player.isOnGround()) && (!player.isClimbing())) {
            event.setDamage(event.getDamage() * 1.2);
            player.getWorld().playSound(player.getLocation(), Sound.ITEM_MACE_SMASH_GROUND_HEAVY, 1.0F, 1.0F);
        }
    }

}