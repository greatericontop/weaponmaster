package io.github.greatericontop.weaponmaster.CavemanSword;

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
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class CavemanItemListener implements Listener {

    private final Map<UUID, Integer> xpCooldown = new HashMap<UUID, Integer>();
    private final double MULTIPLIER = 0.25;

    private final WeaponMasterMain plugin;
    private final Util util;
    public CavemanItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    private int parseExpInt(String s) {
        // §6Experience: §b<DATA>
        String data = s.substring(16, s.length());
        return Integer.parseInt(data);
    }
    private int parseLevelInt(String s) {
        // §6Sharpness Level: §b<DATA>
        String data = s.substring(21, s.length());
        return Integer.parseInt(data);
    }


    private int getRequirementToLevelUp(int level) {
        if (level >= 25) {
            return 2147483600;
        }
        return new int[]{
                1,
                10, // 1
                13,
                16,
                20,
                25, // 5
                31,
                38,
                46,
                55,
                65, // 10
                77,
                91,
                108,
                128,
                144, // 15
                173,
                208,
                250,
                300,
                360, // 20
                432,
                517,
                617,
                757
        }[level] * 1000;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        if (!util.checkForCavemanSword(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.cavemansword.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.cavemansword.use§3.");
            return;
        }
        int amount = (int) (Math.min((event.getFinalDamage() + 1) * event.getFinalDamage() * event.getDamage(), 16000.0) * MULTIPLIER);
        int lastAmount = xpCooldown.getOrDefault(player.getUniqueId(), 0);
        if (amount > lastAmount) {
            ItemMeta iMeta = player.getInventory().getItemInMainHand().getItemMeta();
            List<String> lore = iMeta.getLore();
            int exp = parseExpInt(lore.get(util.CAVEMAN_EXP_I));
            int level = parseLevelInt(lore.get(util.CAVEMAN_LVL_I));

            amount -= lastAmount;
            exp += amount;
            player.sendMessage(String.format("§a+%d experience", amount));

            if (exp >= getRequirementToLevelUp(level)) {
                exp = 0;
                level++;
                iMeta.removeEnchant(Enchantment.DAMAGE_ALL);
                iMeta.addEnchant(Enchantment.DAMAGE_ALL, level, true);
                player.sendMessage(String.format("§9Your %s §9is now level §6%d.", util.CAVEMAN_SWORD_NAME, level));
                lore.set(util.CAVEMAN_LVL_I, String.format("§6Sharpness Level: §b%d", level));
            }
            lore.set(util.CAVEMAN_EXP_I, String.format("§6Experience: §b%d", exp));
            double xpPercent = (100.0 * exp) / getRequirementToLevelUp(level);
            lore.set(util.CAVEMAN_REQ_I, String.format("§6Required Experience: §b%d §6(§b%.1f§6%%)", getRequirementToLevelUp(level), xpPercent));

            iMeta.setLore(lore);
            player.getInventory().getItemInMainHand().setItemMeta(iMeta);

            xpCooldown.put(player.getUniqueId(), lastAmount - 1);
            new BukkitRunnable() {
                @Override
                public void run() {
                    xpCooldown.put(player.getUniqueId(), 0);
                }
            }.runTaskLater(plugin, 10L);
        }
    }

}