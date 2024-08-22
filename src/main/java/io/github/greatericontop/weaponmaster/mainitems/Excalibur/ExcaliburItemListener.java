package io.github.greatericontop.weaponmaster.mainitems.Excalibur;

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
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExcaliburItemListener implements Listener {

    private Map<UUID, Boolean> cooldowns = new HashMap<UUID, Boolean>();

    private final WeaponMasterMain plugin;
    private final Util util;
    public ExcaliburItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player)event.getDamager();
        if (!util.checkForExcalibur(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.excalibur.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.excalibur.use§3.");
            return;
        }
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity victim = (LivingEntity) event.getEntity();
        if (cooldowns.getOrDefault(player.getUniqueId(), true)) {
            new BukkitRunnable() {
                public void run() {
                    if (victim.isDead()) {
                        cancel();
                        return;
                    }
                    victim.getWorld().createExplosion(victim.getLocation(), 0.0F);
                    victim.getWorld().spawnParticle(Particle.EXPLOSION, victim.getLocation(), 4);
                    TrueDamageHelper.dealTrueDamage(victim, 3.0); // dealing true damage does NOT require no damage ticks
                }
            }.runTaskLater(plugin, 1L);
            cooldowns.put(player.getUniqueId(), false);
            new BukkitRunnable() {
                public void run() {
                    cooldowns.put(player.getUniqueId(), true);
                }
            }.runTaskLater(plugin, 120L);
        }
    }

}