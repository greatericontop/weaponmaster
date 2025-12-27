package io.github.greatericontop.weaponmaster.mainitems.DragonSword;

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

import io.github.greatericontop.weaponmaster.utils.Util;
import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DragonSwordListener implements Listener {

    // triangular distribution parameters
    private final double A;
    private final double B;
    private final double C;
    private final double DRAGON_ARMOR_BUFF;

    private final WeaponMasterMain plugin;
    private final Util util;
    private final DragonSwordUpgradeListener dragonUpgrade;
    public DragonSwordListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.util = new Util(plugin);
        this.dragonUpgrade = new DragonSwordUpgradeListener(plugin);
        A = plugin.getConfig().getDouble("dragonsword.a", 0.0);
        B = plugin.getConfig().getDouble("dragonsword.b", 0.8);
        C = plugin.getConfig().getDouble("dragonsword.c", 0.25);
        DRAGON_ARMOR_BUFF = plugin.getConfig().getDouble("dragonsword.dragon_armor_buff", 1.25);
    }

    private double triangular(double random) {
        // triangular distribution code stolen from https://stackoverflow.com/questions/33220176/triangular-distribution-in-java
        double F = (C - A) / (B - A);
        if (random < F) {
            return A + Math.sqrt(random * (B - A) * (C - A));
        } else {
            return B - Math.sqrt((1-random) * (B - A) * (B - C));
        }
    }

    @EventHandler(priority = EventPriority.HIGH) // runs at the end (before life helmet though) to stack bonuses
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player)event.getDamager();
        if (!util.checkForDragonSword(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.dragonsword.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.dragonsword.use§3.");
            return;
        }
        // increment damage
        if (Math.random() < dragonUpgrade.abilityTriggerProbability(player.getInventory().getItemInMainHand().getItemMeta().getLore())) {
            double multiplier = 1.0 + triangular(Math.random());
            if (
                    util.checkForDragonArmor(player.getInventory().getHelmet())
                    && util.checkForDragonArmor(player.getInventory().getChestplate())
                    && util.checkForDragonArmor(player.getInventory().getLeggings())
                    && util.checkForDragonArmor(player.getInventory().getBoots())
            ) { // multiplicative increase in damage when a full set of dragon armor is found
                multiplier *= DRAGON_ARMOR_BUFF;
            }
            event.setDamage(event.getDamage()*multiplier);
            plugin.paperUtils.sendActionBar(player, String.format("§3Hit increased by §4%.1f%% §3for §4%.1f§3.", (multiplier-1)*100, event.getDamage()), true);
        }
    }

}