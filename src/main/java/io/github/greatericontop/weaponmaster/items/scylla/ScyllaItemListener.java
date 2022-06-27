package io.github.greatericontop.weaponmaster.scylla;

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
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class ScyllaItemListener implements Listener {

    private final double HEALTH_THRESHOLD = 8.0;
    private final double MAX_REDUCTION = 0.35;

    private final WeaponMasterMain plugin;
    private final Util util;

    public ScyllaItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    private double getResistanceAmount(Player player, double damage) {
        double health = player.getHealth();
        double closeness = 1 - (health / HEALTH_THRESHOLD); // in the range [0,1); more closeness = less health
        double reduction = Math.pow(closeness, 0.7); // more d/dx when health is low
        return reduction * MAX_REDUCTION;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        if (event.getCause() == EntityDamageEvent.DamageCause.VOID) { return; }
        Player player = (Player) event.getEntity();
        if (!util.checkForScylla(player.getInventory().getChestplate())) { return; }
        if (!player.hasPermission("weaponmaster.scylla.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.scylla.use§3.");
            return;
        }

        if (player.getHealth() <= HEALTH_THRESHOLD) {
            double damageReduction = getResistanceAmount(player, event.getDamage());
            event.setDamage(event.getDamage() * (1 - damageReduction));
            player.sendMessage(String.format("§7[Last Wind] Reduced by %.1f%% to %.1f.",
                    damageReduction*100, event.getDamage()));
        }

    }

}