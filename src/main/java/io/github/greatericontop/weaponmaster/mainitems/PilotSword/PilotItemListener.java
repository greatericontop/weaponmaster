package io.github.greatericontop.weaponmaster.mainitems.PilotSword;

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
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class PilotItemListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public PilotItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    private double calculateDamage(Player player, ItemMeta pilotSword) {
        double damage = 4.5; // base damage
        int sharpnessLevel = pilotSword.getEnchantLevel(Enchantment.DAMAGE_ALL);
        damage += 0.25 * sharpnessLevel; // +0.25 per sharp level, up to +1.25
//        PotionEffect strengthEffect = player.getPotionEffect(PotionEffectType.INCREASE_DAMAGE);
//        int strengthLevel = strengthEffect == null ? 0 : strengthEffect.getAmplifier() + 1;
//        damage += strengthLevel; // +1 per strength level
//        PotionEffect weaknessEffect = player.getPotionEffect(PotionEffectType.WEAKNESS);
//        int weaknessLevel = weaknessEffect == null ? 0 : weaknessEffect.getAmplifier() + 1;
//        damage += -weaknessLevel; // -1 per weakness level
        return damage;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        if (!util.checkForPilotSword(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.pilotsword.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.pilotsword.use§3.");
            return;
        }
        // TODO: make pilot sword NOT SUPER BROKEN and make a craft for it
        double damageAmount = calculateDamage(player, player.getInventory().getItemInMainHand().getItemMeta());
        event.setDamage(damageAmount);
        player.sendMessage(String.format("§7[Debug] calculated amount: %.1f, final damage is %.1f", damageAmount, event.getFinalDamage()));

        LivingEntity victim = (LivingEntity) event.getEntity();
        new BukkitRunnable() {
            public void run() {
                if (victim.isDead()) {
                    cancel();
                    return;
                }
                victim.setNoDamageTicks(0);
            }
        }.runTaskLater(plugin, 1L);
    }

}