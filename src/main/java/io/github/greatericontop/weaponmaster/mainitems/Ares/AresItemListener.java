package io.github.greatericontop.weaponmaster.mainitems.Ares;

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
import io.github.greatericontop.weaponmaster.utils.TrueDamageHelper;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AresItemListener implements Listener {
    private final Set<UUID> arrows = new HashSet<>();

    private final WeaponMasterMain plugin;
    private final Util util;
    public AresItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler()
    public void onBowShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) { return; }
        Player player = (Player) event.getEntity();
        if (!util.checkForAres(event.getBow())) { return; }
        if (!player.hasPermission("weaponmaster.ares.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.artemisbow.use§3.");
            return;
        }
        if (event.getForce() < 0.99) { // only allow fully charged shots
            player.sendMessage("§7The sky strike requires a fully charged shot!");
            return;
        }
        arrows.add(event.getProjectile().getUniqueId());
    }

    @EventHandler()
    public void onArrowHit(EntityDamageByEntityEvent event) {
        if (!arrows.contains(event.getDamager().getUniqueId())) { return; }
        if (event.getEntity() instanceof LivingEntity) {
            LivingEntity target = (LivingEntity) event.getEntity();
            TrueDamageHelper.dealTrueDamage(target, 2.0);
            target.getWorld().strikeLightningEffect(target.getLocation());
        }
        arrows.remove(event.getDamager().getUniqueId());
    }

}
