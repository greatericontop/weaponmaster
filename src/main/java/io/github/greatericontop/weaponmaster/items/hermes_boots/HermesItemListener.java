package io.github.greatericontop.weaponmaster.items.hermes_boots;

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
import io.github.greatericontop.weaponmaster.util.Util;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HermesItemListener implements Listener {

    private final double ACTIVATE_CHANCE = 0.12;

    private Map<UUID, Boolean> cooldown = new HashMap<UUID, Boolean>();
    private final WeaponMasterMain plugin;
    private final Util util;

    public HermesItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getEntity();
        if (!util.checkForHermesBoots(player.getInventory().getBoots())) { return; }
        if (!player.hasPermission("weaponmaster.hermesboots.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.hermesboots.use§3.");
            return;
        }
        if (Math.random() < ACTIVATE_CHANCE) {
            if (cooldown.getOrDefault(player.getUniqueId(), true)) {
                event.setCancelled(true);
                player.sendMessage("§6You dodged this attack!");
                if (event.getDamager() instanceof Player) {
                    ((Player) event.getDamager()).sendMessage("§6Your attack was dodged!");
                }

                cooldown.put(player.getUniqueId(), false);
                new BukkitRunnable() {
                    public void run() {
                        cooldown.put(player.getUniqueId(), true);
                    }
                }.runTaskLater(plugin, 320L); // after 16 seconds
            }
        }
    }

}