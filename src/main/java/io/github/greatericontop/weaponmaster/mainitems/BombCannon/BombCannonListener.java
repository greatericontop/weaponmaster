package io.github.greatericontop.weaponmaster.mainitems.BombCannon;

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
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BombCannonListener implements Listener {
    private final float ARROW_DAMAGE = 1.25F;

    private final Set<UUID> explosiveArrows = new HashSet<>();
    private final Util util;
    public BombCannonListener(WeaponMasterMain plugin) {
        util = new Util(plugin);
    }

    @EventHandler()
    public void onBowShoot(EntityShootBowEvent event) {
        Entity arrow = event.getProjectile();
        if (!(event.getEntity() instanceof Player))  return;
        Player player = (Player) event.getEntity();
        if (!util.checkForBombCannon(event.getBow())) { return; }
        if (!player.hasPermission("weaponmaster.bombcannon.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.bombcannon.use§3.");
            return;
        }
        if (event.getForce() < 0.99) { // only allow fully charged shots
            player.sendMessage("§7Explosive arrows can only be fired when fully charged.");
            return;
        }

        explosiveArrows.add(arrow.getUniqueId());
    }

    @EventHandler()
    public void onProjectileHit(ProjectileHitEvent event) {
        Projectile arrow = event.getEntity();
        if (explosiveArrows.contains(arrow.getUniqueId())) {
            arrow.getWorld().createExplosion(arrow.getLocation(), ARROW_DAMAGE, false, true, arrow);
            explosiveArrows.remove(arrow.getUniqueId());
            arrow.remove();
        }
    }

}