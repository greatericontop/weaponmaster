package io.github.greatericontop.weaponmaster.mainitems.NinjaMastersBow;

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
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class NinjaMastersItemListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public NinjaMastersItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    /*
     * World.spawnArrow *sucks* so it's time to do it myself
     * Vanilla bows fire with 3.0 velocity, 0.0075 inaccuracy
     * Extra: Minecraft inaccuracy is cube-shaped and uses gaussian values often exceeding 1.0x damage
     */
    public Arrow spawnArrow(World world, Location location, Vector direction, double velocity, double maxInaccuracyMagnitude, double damageValue, int punchValue, boolean isFire, Player shooterPlayer, AbstractArrow.PickupStatus pickupStatus) {
        Arrow arrow = (Arrow) world.spawnEntity(location, EntityType.ARROW);
        Vector normalVelocity = direction.clone().normalize().multiply(velocity);
        arrow.setVelocity(normalVelocity.add(InaccuracyAdder.generateInaccuracy(maxInaccuracyMagnitude))); // this is slightly different from vanilla's method, but good enough
        arrow.setDamage(damageValue);
        arrow.setKnockbackStrength(punchValue);
        arrow.setShooter(shooterPlayer);
        arrow.setPickupStatus(pickupStatus);
        if (isFire) {
            arrow.setFireTicks(Integer.MAX_VALUE);
        }
        return arrow;
    }

    @EventHandler()
    public void onBowShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player))  return;
        Player player = (Player) event.getEntity();
        if (util.checkForNinjaMastersBow(event.getBow())) {
            player.sendMessage("§cYou need to use LEFT CLICK to shoot this.");
            event.setCancelled(true);
        }
    }

    private static final double DTHETA = Math.PI / 18;

    @EventHandler()
    public void onLeftClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND)  return;
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK)  return;
        Player player = event.getPlayer();
        if (!util.checkForNinjaMastersBow(player.getInventory().getItemInMainHand()))  return;
        ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
        // 2 + (0.5 if we have power) + (0.5 per level), so it goes 2, 3, 3.5, 4, 4.5, 5
        double damageValue = 2.0 + (im.hasEnchant(Enchantment.POWER) ? 0.5 : 0.0) + (im.getEnchantLevel(Enchantment.POWER) * 0.5);
        boolean isFire = im.hasEnchant(Enchantment.FLAME);
        boolean hasInfinity = im.hasEnchant(Enchantment.INFINITY);
        int punchValue = im.getEnchantLevel(Enchantment.PUNCH);
        // check/remove arrows
        if (player.getGameMode() != GameMode.CREATIVE) {
            if (!player.getInventory().contains(Material.ARROW)) {
                return;
            }
            if (!hasInfinity) {
                player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
            }
        }

        Location eyeLoc = player.getEyeLocation();
        Vector mainDirection = eyeLoc.getDirection();
        double mainYaw = Math.atan2(mainDirection.getZ(), mainDirection.getX()); // (x, z) -> arctan z/x
        double r = Math.sqrt(mainDirection.getX() * mainDirection.getX() + mainDirection.getZ() * mainDirection.getZ());
        double mainPitch = Math.atan2(mainDirection.getY(), r); // (y, r) -> arctan y/r

        for (int deltaPitch = -2; deltaPitch <= 2; deltaPitch++) {
            double pitch = mainPitch + deltaPitch * DTHETA;
            for (int deltaYaw = -2; deltaYaw <= 2; deltaYaw++) {
                // works fine when pitch exceeds +/- pi/2
                // there is some distortion inherent to the calculation though (you can see it if you shoot straight down)
                double yaw = mainYaw + deltaYaw * DTHETA;
                Vector direction = new Vector(Math.cos(pitch) * Math.cos(yaw), Math.sin(pitch), Math.cos(pitch) * Math.sin(yaw));
                Arrow arrow = spawnArrow(eyeLoc.getWorld(), eyeLoc.clone().add(direction.clone().multiply(0.15)),
                        direction, 3.0,
                        0.0, damageValue, punchValue, isFire,
                        player, AbstractArrow.PickupStatus.DISALLOWED);
                arrow.setCritical(deltaPitch == 0 && deltaYaw == 0); // only the main arrow is critical
            }
        }

    }

}
