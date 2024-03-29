package io.github.greatericontop.weaponmaster.mainitems.Exodus;

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
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ExodusItemListener implements Listener {

    private Map<UUID, Boolean> cooldown = new HashMap<UUID, Boolean>();
    private final WeaponMasterMain plugin;
    private final Util util;

    public ExodusItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    public void doExodusHeal(Player player) {
        if (cooldown.getOrDefault(player.getUniqueId(), true)) {
            // health is regenerated at 25 and 50
            PotionEffect effect = new PotionEffect(PotionEffectType.REGENERATION, 60, 1, true);
            player.addPotionEffect(effect);
            cooldown.put(player.getUniqueId(), false);
            new BukkitRunnable() {
                public void run() {
                    cooldown.put(player.getUniqueId(), true);
                }
            }.runTaskLater(plugin, 80L);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntityPlayer(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        if (!util.checkForExodus(player.getInventory().getHelmet())) { return; }
        if (!player.hasPermission("weaponmaster.exodus.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.exodus.use§3.");
            return;
        }
        doExodusHeal(player);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntityProjectile(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Projectile)) { return; }
        Projectile projectile = (Projectile) event.getDamager();
        if (!(projectile.getShooter() instanceof Player)) { return; }
        Player player = (Player) projectile.getShooter();
        if (!util.checkForExodus(player.getInventory().getHelmet())) { return; }
        if (!player.hasPermission("weaponmaster.exodus.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.exodus.use§3.");
            return;
        }
        doExodusHeal(player);
    }

}