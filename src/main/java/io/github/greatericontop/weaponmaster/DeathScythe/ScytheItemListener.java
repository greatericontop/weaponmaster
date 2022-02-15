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

import io.github.greatericontop.weaponmaster.utils.Util;
import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ScytheItemListener implements Listener {

    private final Util util;
    public ScytheItemListener(WeaponMasterMain plugin) {
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player)event.getDamager();
        if (!util.checkForDeathScythe(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.deathscythe.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4rpgl.use§3.");
            return;
        }
        // Damage target
        LivingEntity target = (LivingEntity)event.getEntity();
        double targetHealth = target.getHealth();
        target.setHealth(target.getHealth() * 0.5);
        player.sendMessage("§3Dealt §4" + targetHealth*0.5 + " §3damage.");
    }

}