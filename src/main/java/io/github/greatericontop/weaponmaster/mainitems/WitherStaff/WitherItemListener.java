package io.github.greatericontop.weaponmaster.mainitems.WitherStaff;

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
import io.github.greatericontop.weaponmaster.utils.InaccuracyAdder;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class WitherItemListener implements Listener {

    private final Map<UUID, Boolean> cooldowns = new HashMap<>();

    private final WeaponMasterMain plugin;
    private final Util util;
    public WitherItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler()
    public void onLeftClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        Player player = event.getPlayer();
        if (!util.checkForWitherStaff(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.witherstaff.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.witherstaff.use§3.");
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        if (Util.checkForInteractableBlock(event)) { return; }
        if (!cooldowns.getOrDefault(player.getUniqueId(), true)) {
            return;
        }
        cooldowns.put(player.getUniqueId(), false);

        Vector velocity = player.getLocation().getDirection().normalize().multiply(0.35).add(InaccuracyAdder.generateInaccuracy(0.02));
        WitherSkull witherSkull = (WitherSkull) player.getLocation().getWorld().spawnEntity(player.getEyeLocation(), EntityType.WITHER_SKULL);
        witherSkull.setVelocity(velocity);
        witherSkull.setShooter(player);
        witherSkull.setCharged(Math.random() < plugin.getConfig().getDouble("witherStaff.chargedChance", 0.04));

        new BukkitRunnable() {
            public void run() {
                cooldowns.put(player.getUniqueId(), true);
            }
        }.runTaskLater(plugin, plugin.getConfig().getLong("witherStaff.cooldownTicks", 20L));
    }

}