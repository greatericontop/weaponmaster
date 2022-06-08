package io.github.greatericontop.weaponmaster.VampAxe;

/*
 * WeaponMaster Copyright (C) greateric 2021-present.
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

import io.github.greatericontop.weaponmaster.utils.Util;
import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static java.lang.Math.min;

public class VampItemListener implements Listener {

    private final double VAMP_HEAL_MULTIPLIER = 0.16;

    private final WeaponMasterMain plugin;
    private final Util util;
    public VampItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player)event.getDamager();
        if (!util.checkForVampAxe(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.vampaxe.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.vampaxe.use§3.");
            return;
        }
        // Heal player
        double healAmount = event.getFinalDamage() * VAMP_HEAL_MULTIPLIER;
        double previousHealth = player.getHealth();
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        player.setHealth(min(previousHealth+healAmount, maxHealth));
        plugin.paperUtils.sendActionBar(player, String.format("§3Healed you for §4%.1f§3.", player.getHealth()-previousHealth), true);
    }

}