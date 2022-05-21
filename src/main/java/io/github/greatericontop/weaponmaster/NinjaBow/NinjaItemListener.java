package io.github.greatericontop.weaponmaster.NinjaBow;

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
import io.github.greatericontop.weaponmaster.utils.InaccuracyAdder;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

public class NinjaItemListener implements Listener {

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
    public Arrow spawnArrow(World world, Location location, Vector direction, double velocity, double maxInaccuracyMagnitude, double damageValue, int punchValue, Player shooterPlayer, AbstractArrow.PickupStatus pickupStatus) {
        Arrow arrow = (Arrow) world.spawnEntity(location, EntityType.ARROW);
        Vector normalVelocity = direction.clone().normalize().multiply(velocity);
        arrow.setVelocity(normalVelocity.add(InaccuracyAdder.generateInaccuracy(maxInaccuracyMagnitude)));
        arrow.setDamage(damageValue);
        arrow.setKnockbackStrength(punchValue);
        arrow.setShooter(shooterPlayer);
        arrow.setPickupStatus(pickupStatus);
        return arrow;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBowShoot(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        if (util.checkForNinjaBow(event.getBow())) {
            ((Player) event.getEntity()).sendMessage("§cYou need to use LEFT CLICK to shoot this.");
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLeftClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.LEFT_CLICK_AIR && event.getAction() != Action.LEFT_CLICK_BLOCK) { return; }
        Player player = event.getPlayer();
        if (!util.checkForNinjaBow(player.getInventory().getItemInMainHand())) { return; }
        if (player.getGameMode() != GameMode.CREATIVE) {
            if (!player.getInventory().contains(Material.ARROW)) {
                return;
            }
            player.getInventory().removeItem(new ItemStack(Material.ARROW, 1));
        }

        ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
        double damageValue = 2.0 + (im.hasEnchant(Enchantment.ARROW_DAMAGE) ? 0.5 : 0.0) + (im.getEnchantLevel(Enchantment.ARROW_DAMAGE) * 0.5);
        int punchValue = im.getEnchantLevel(Enchantment.ARROW_KNOCKBACK);
        double INACCURACY = 0.0155;

        Location eyeLoc = player.getEyeLocation();
        Vector mainArrowDirection = eyeLoc.getDirection();
        Arrow mainArrow = spawnArrow(eyeLoc.getWorld(), eyeLoc.clone().add(mainArrowDirection.clone().multiply(0.1)), mainArrowDirection, 3.0, INACCURACY, damageValue, punchValue, player, AbstractArrow.PickupStatus.DISALLOWED);
        Vector arrow1Direction = mainArrowDirection.clone().rotateAroundY(Math.PI / 12);
        Arrow sideArrow1 = spawnArrow(eyeLoc.getWorld(), eyeLoc.clone().add(arrow1Direction.clone().multiply(0.2)), arrow1Direction, 3.0, INACCURACY * 2.4, damageValue*0.75, Math.max(punchValue-1, 0), player, AbstractArrow.PickupStatus.DISALLOWED);
        Vector arrow2Direction = mainArrowDirection.clone().rotateAroundY(-Math.PI / 12);
        Arrow sideArrow2 = spawnArrow(eyeLoc.getWorld(), eyeLoc.clone().add(arrow2Direction.clone().multiply(0.2)), arrow2Direction, 3.0, INACCURACY * 2.4, damageValue*0.75, Math.max(punchValue-1, 0), player, AbstractArrow.PickupStatus.DISALLOWED);
    }

}
