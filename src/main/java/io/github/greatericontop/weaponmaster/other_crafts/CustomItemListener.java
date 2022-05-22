package io.github.greatericontop.weaponmaster.other_crafts;

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

import io.github.greatericontop.weaponmaster.other_crafts.CustomItems;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.Random;
import java.util.UUID;

public class CustomItemListener implements Listener {

    private final Random rnd = new Random();
    private final CustomItems customItems;
    private final Util util;
    public CustomItemListener() {
        customItems = new CustomItems();
        util = new Util(null);
    }

    public void modifyAttributeModifier(AttributeInstance instance, UUID withUUID, double amountDelta, double min, double max) {
        AttributeModifier savedAM = null;
        double amount = 0;
        for (AttributeModifier am : instance.getModifiers()) {
            if (am.getUniqueId().equals(withUUID)) {
                double oldAmount = am.getAmount();
                amount = Math.min(Math.max(oldAmount + amountDelta, min), max);
                savedAM = am;
                break;
            }
        }
        AttributeModifier newAM;
        if (savedAM == null) {
            newAM = new AttributeModifier(withUUID, "weaponmaster", Math.min(Math.max(amountDelta, min), max), AttributeModifier.Operation.ADD_NUMBER);
        } else {
            instance.removeModifier(savedAM);
            newAM = new AttributeModifier(withUUID, "weaponmaster", amount, AttributeModifier.Operation.ADD_NUMBER);
        }
        instance.addModifier(newAM);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntityType() == EntityType.ELDER_GUARDIAN) {
            if (Math.random() < 0.12) {
                ItemStack leviathan = customItems.generateLeviathanHeartItemStack();
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), leviathan);
                Player killer = event.getEntity().getKiller();
                if (killer != null) {
                    killer.sendMessage("§eRARE DROP! " + customItems.LEVIATHAN_HEART_NAME);
                }
            }
        } else if (event.getEntityType() == EntityType.WITHER_SKELETON) {
            if (Math.random() < 0.01) {
                ItemStack core = customItems.generateCoreStaffItemStack();
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getEyeLocation(), core);
                Player killer = event.getEntity().getKiller();
                if (killer != null) {
                    killer.sendMessage("§eRARE DROP! " + customItems.CORE_STAFF_NAME);
                }
            }
        } else if (event.getEntityType() == EntityType.ENDER_DRAGON) {
            if (Math.random() < 0.04) {
                int locX = rnd.nextInt(141) - 70;
                int locZ = rnd.nextInt(141) - 70;
                Location newBlockLocation = event.getEntity().getWorld().getHighestBlockAt(locX, locZ).getLocation().add(0, 1, 0);
                if (newBlockLocation.getY() < 10) {
                    newBlockLocation.setY(10);
                }
                Block topBlock = newBlockLocation.getBlock();
                topBlock.setType(Material.TRAPPED_CHEST);
                Chest chest = (Chest) topBlock.getState();
                chest.getBlockInventory().setItem(13, customItems.generateDragonScaleItemStack());
                Bukkit.getServer().getLogger().info(String.format("Placing chest for DRAGON SCALE at %d, %d, %d", locX, (int)newBlockLocation.getY(), locZ));
                Player killer = event.getEntity().getKiller();
                if (killer != null) {
                    killer.sendMessage("§eRARE DROP! " + customItems.DRAGON_SCALE_NAME);
                }
            }
        }

        if (event.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            modifyAttributeModifier(player.getAttribute(Attribute.GENERIC_MAX_HEALTH), customItems.ENERGY_MODIFIER_UUID, -4.0, 0.0, 12.0);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEat(PlayerItemConsumeEvent event) {
        if (!util.checkFor(event.getItem(), null, 0, "id: MAGIC_ENERGY_BAR")) { return; }
        Player player = event.getPlayer();
        modifyAttributeModifier(player.getAttribute(Attribute.GENERIC_MAX_HEALTH), customItems.ENERGY_MODIFIER_UUID, 2.0, 0.0, 12.0);
        player.sendMessage("§3Successfully gained a heart!");
    }

}
