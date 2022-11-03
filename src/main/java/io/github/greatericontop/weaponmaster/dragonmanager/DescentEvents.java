package io.github.greatericontop.weaponmaster.dragonmanager;

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
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DescentEvents implements Listener {

    private final WeaponMasterMain plugin;
    private final DescentDataManager descent;
    public DescentEvents(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.descent = plugin.descent;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getEntity();

        // allDamageResistance
        int allDamageResistance = descent.getUpgrade(player, "allDamageResistance");
        if (allDamageResistance > 0) {
            double multi = 1.0 - 0.005*allDamageResistance;
            event.setDamage(event.getDamage() * multi);
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamagePlayer(EntityDamageByEntityEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getEntity();

        // mightyStrength
        int mightyStrength = descent.getUpgrade(player, "mightyStrength");
        if (mightyStrength > 0) {
            // TODO: fix %
            double activationChance = 0.04 * mightyStrength;
            if (Math.random() < activationChance) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 100, 0, true));
            }
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamageEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
    }

}
