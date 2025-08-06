package io.github.greatericontop.weaponmaster.mainitems.WitchSword;

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
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class WitchSwordListener implements Listener {
    private static final double HEAL_FACTOR = 0.15;
    private static final PotionEffect WITCH_EFFECT = new PotionEffect(PotionEffectType.UNLUCK, 200, 100, true, true, true);

    private final WeaponMasterMain plugin;
    private final Util util;

    public WitchSwordListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler()
    public void onPlayerAttackEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) {
            return;
        }
        Player player = (Player) event.getDamager();
        if (!util.checkForWitchSword(player.getInventory().getItemInMainHand())) {
            return;
        }
        if (!player.hasPermission("weaponmaster.witchsword.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.witchsword.use§3.");
            return;
        }
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        LivingEntity victim = (LivingEntity) event.getEntity();
        victim.addPotionEffect(WITCH_EFFECT);
        event.setDamage(event.getDamage() * 0.7);
        if (victim instanceof Player) {
            ((Player) victim).playSound(victim.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1.0F, 1.0F);
        }
    }

    @EventHandler(priority = EventPriority.LOW) // runs before the other one
    public void onCursedEntityAttacked(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof LivingEntity)) {
            return;
        }
        if (!(event.getDamager() instanceof LivingEntity)) {
            return;
        }
        LivingEntity victim = (LivingEntity) event.getEntity();
        LivingEntity attacker = (LivingEntity) event.getDamager();
        PotionEffect effect = victim.getPotionEffect(PotionEffectType.UNLUCK);
        if (effect != null && effect.getAmplifier() == WITCH_EFFECT.getAmplifier() && effect.isAmbient()) {
            // Heal the attacker
            double maxHealth = attacker.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
            double newHealth = attacker.getHealth() + (maxHealth * HEAL_FACTOR);
            attacker.setHealth(Math.min(newHealth, maxHealth));
            if (attacker instanceof Player) {
                ((Player) attacker).playSound(attacker.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
            }
        }
    }

}