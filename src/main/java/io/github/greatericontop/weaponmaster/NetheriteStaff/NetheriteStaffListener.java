package io.github.greatericontop.weaponmaster.NetheriteStaff;

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
import io.github.greatericontop.weaponmaster.utils.MathHelper;
import io.github.greatericontop.weaponmaster.utils.PaperUtils;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class NetheriteStaffListener implements Listener {

    Random rand = new Random();
    private final double VELOCITY = 4.0;
    private final WeaponMasterMain plugin;
    private final Util util;

    public NetheriteStaffListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        if (!util.checkForNetheriteStaff(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.netheritestaff.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.netheritestaff.use§3.");
            return;
        }
        if (!(event.getEntity() instanceof LivingEntity)) { return; }
        LivingEntity attacked = (LivingEntity) event.getEntity();
        Object[] effectData = EffectPicker.getRandomEffect(false);
        PotionEffect effect = new PotionEffect((PotionEffectType) effectData[0], (int) effectData[1], (int) effectData[2]);
        attacked.addPotionEffect(effect);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        Player player = event.getPlayer();
        if (!util.checkForNetheriteStaff(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.netheritestaff.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.netheritestaff.use§3.");
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK
                && (event.getClickedBlock().getType() == Material.DIRT || event.getClickedBlock().getType() == Material.GRASS_BLOCK)) {
            event.setCancelled(true);
        }
        if (Util.checkForInteractableBlock(event)) { return; }
        Damageable iMeta = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
        if (iMeta.getDamage() > (2031-101)) {
            plugin.paperUtils.sendActionBar(player, "§3Not enough durability to shoot an arrow!", true);
            return;
        }
        if (event.getPlayer().getGameMode() == GameMode.SURVIVAL
                && player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY) < 4) {
            if (!event.getPlayer().getInventory().contains(Material.ARROW)) {
                plugin.paperUtils.sendActionBar(player, "§3You must have arrows in your inventory to shoot!", true);
                return;
            }
        }

        Location eyeLocation = player.getEyeLocation();
        Location spawnLoc = eyeLocation.clone().add(eyeLocation.getDirection().multiply(0.9));
        World world = player.getWorld();

        Arrow arrow = (Arrow) world.spawnEntity(spawnLoc, EntityType.ARROW);
        arrow.setShooter(player);
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
        Object[] effectData = EffectPicker.getRandomEffect(true);
        PotionEffect effect = new PotionEffect((PotionEffectType) effectData[0], (int) effectData[1], (int) effectData[2]);
        arrow.addCustomEffect(effect, true);
        arrow.setVelocity(eyeLocation.getDirection().multiply(VELOCITY));
        arrow.setColor(Color.BLACK);
        arrow.setDamage(1.5F);
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            iMeta.setDamage(iMeta.getDamage() + MathHelper.getDamageWithUnbreaking(5, iMeta));
            if (player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY) < 4
                    && Math.random() + (player.getInventory().getItemInMainHand().getEnchantmentLevel(Enchantment.DURABILITY) * 0.15) < 0.95) {
                player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
            }
            player.getInventory().getItemInMainHand().setItemMeta(iMeta);
        }
    }
}
