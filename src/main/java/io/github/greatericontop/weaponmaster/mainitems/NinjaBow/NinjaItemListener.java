package io.github.greatericontop.weaponmaster.mainitems.NinjaBow;

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
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NinjaItemListener implements Listener {

    private Map<UUID, Boolean> cooldown = new HashMap<>();
    private final WeaponMasterMain plugin;
    private final Util util;
    public NinjaItemListener(WeaponMasterMain plugin) {
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
        if (util.checkForNinjaBow(event.getBow())) {
            player.sendMessage("§cYou need to use LEFT CLICK to shoot this.");
            event.setCancelled(true);
        }
    }

    private final double INACCURACY = 0.01675; // minecraft uses a normal distribution multiplied by 0.0075
                                               // we are using a circle with average radius (r * sqrt2/2): ~0.0118

    @EventHandler()
    public void onLeftClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) { return; }
        Player player = event.getPlayer();
        if (!util.checkForNinjaBow(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.ninjabow.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.ninjabow.use§3.");
            return;
        }
        if (!cooldown.getOrDefault(player.getUniqueId(), true)) { return; }

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
        Vector mainArrowDirection = eyeLoc.getDirection();
        Vector arrow1Direction = mainArrowDirection.clone().rotateAroundY(Math.PI / 12);
        Vector arrow2Direction = mainArrowDirection.clone().rotateAroundY(-Math.PI / 12);

        Arrow mainArrow = spawnArrow(eyeLoc.getWorld(), eyeLoc.clone().add(mainArrowDirection.clone().multiply(0.1)),
                mainArrowDirection, 3.0,
                INACCURACY, damageValue, punchValue, isFire,
                player, AbstractArrow.PickupStatus.DISALLOWED); // you can't pick them up regardless of infinity or not
        mainArrow.setCritical(true);
        Arrow sideArrow1 = spawnArrow(eyeLoc.getWorld(), eyeLoc.clone().add(arrow1Direction.clone().multiply(0.2)),
                arrow1Direction, 3.0,
                INACCURACY * 3.75, damageValue * 0.6, punchValue, isFire,
                player, AbstractArrow.PickupStatus.DISALLOWED);
        Arrow sideArrow2 = spawnArrow(eyeLoc.getWorld(), eyeLoc.clone().add(arrow2Direction.clone().multiply(0.2)),
                arrow2Direction, 3.0,
                INACCURACY * 3.75, damageValue * 0.6, punchValue, isFire,
                player, AbstractArrow.PickupStatus.DISALLOWED);

        cooldown.put(player.getUniqueId(), false);
        new BukkitRunnable() {
            public void run() {
                cooldown.put(player.getUniqueId(), true);
            }
        }.runTaskLater(plugin, 8L);
    }

}
