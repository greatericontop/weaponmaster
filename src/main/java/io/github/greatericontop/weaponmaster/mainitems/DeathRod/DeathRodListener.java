package io.github.greatericontop.weaponmaster.mainitems.DeathRod;

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
import org.bukkit.GameMode;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class DeathRodListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public DeathRodListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler()
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER)  return;
        Player player = (Player) event.getDamager();
        ItemStack hand = player.getInventory().getItemInMainHand();
        if (!util.checkForDeathRod(hand))  return;
        if (!player.hasPermission("weaponmaster.deathrod.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.deathrod.use§3.");
            return;
        }
        ((Damageable) event.getEntity()).setHealth(0.0); // bypasses totems
        if (player.getGameMode() != GameMode.CREATIVE) {
            hand.setAmount(hand.getAmount() - 1);
        }
    }

}