package io.github.greatericontop.weaponmaster.DragonArmor;

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

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DragonArmorListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public DragonArmorListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.LOW) // runs near the beginning
    public void onDamageByEntity(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getEntity();
        double damageProtection = 1.0;
        if (util.checkForDragonArmor(player.getInventory().getHelmet())) { damageProtection -= 0.05; }
        if (util.checkForDragonArmor(player.getInventory().getChestplate())) { damageProtection -= 0.05; }
        if (util.checkForDragonArmor(player.getInventory().getLeggings())) { damageProtection -= 0.05; }
        if (util.checkForDragonArmor(player.getInventory().getBoots())) { damageProtection -= 0.05; }
        if (damageProtection >= 0.999) { return; }
        if (!player.hasPermission("weaponmaster.dragonarmor.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.dragonarmor.use§3.");
            return;
        }

        event.setDamage(event.getDamage() * damageProtection);
        plugin.paperUtils.sendActionBar(player, String.format("§eDamage was reduced by %.0f%% to %.1f.", 100*(1-damageProtection), event.getDamage()), false);
    }

}