package io.github.greatericontop.weaponmaster.dragon_manager;

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
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class FightManager implements Listener {

    public EnderDragon currentlyActiveDragon = null;
    private double damageDealtToDragonThroughExplosions = 0.0;
    private final WeaponMasterMain plugin;
    public FightManager(WeaponMasterMain plugin) {
        this.plugin = plugin;
    }


    @EventHandler(priority = EventPriority.NORMAL)
    public void onDragonSpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof EnderDragon)) { return; }
        // TODO: only trigger sometimes, maybe 20%
        this.currentlyActiveDragon = (EnderDragon) entity;
        new MidFightTasks(plugin, currentlyActiveDragon).startFightTasks();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDragonExplosionDamage(EntityDamageEvent event) {
        if (!event.getEntity().getUniqueId().equals(currentlyActiveDragon.getUniqueId())) { return; }
        if (!(event.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION || event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)) { return; }
        if (damageDealtToDragonThroughExplosions >= 50.0) {
            System.out.println("debug: reducing, orig was "+event.getDamage()+", final was"+event.getFinalDamage()+", dmg"+damageDealtToDragonThroughExplosions);
            event.setDamage(event.getDamage() * 0.1);
        }
        damageDealtToDragonThroughExplosions += event.getFinalDamage();
    }

}
