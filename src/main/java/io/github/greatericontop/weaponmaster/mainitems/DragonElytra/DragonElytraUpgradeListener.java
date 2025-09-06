package io.github.greatericontop.weaponmaster.mainitems.DragonElytra;

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
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class DragonElytraUpgradeListener implements Listener {
    UUID modifierUUID = UUID.fromString("00000000-1111-0000-0000-200c8453f6de");

    private final WeaponMasterMain plugin;
    private final Util util;
    public DragonElytraUpgradeListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.util = new Util(plugin);
    }

    public int getUpgradeCount(ItemMeta im) {
        Collection<AttributeModifier> modifiers = im.getAttributeModifiers(Attribute.GENERIC_ARMOR);
        if (modifiers == null) {
            return 0;
        }
        if (modifiers.size() > 1) {
            throw new RuntimeException("should only be 1 AM");
        }
        for (AttributeModifier aMod : modifiers) {
            return (int) (aMod.getAmount() / 0.5); // +0.5 armor per upgrade level
        }
        return 0;
    }

    public int getLevelsForItem(ItemMeta im) {
        return 40 * getUpgradeCount(im);
    }

    @EventHandler()
    public void onAnvil(PrepareAnvilEvent event) {
        ItemStack elytra = event.getInventory().getItem(0);
        ItemStack scale = event.getInventory().getItem(1);
        Player player = (Player) event.getViewers().get(0);
        if (!util.checkForDragonElytra(elytra))  return;
        if (!util.checkFor(scale, 0, "id: DRAGON_SCALE"))  return;

        ItemMeta elytraIM = elytra.getItemMeta();
        int currentUpgradeLevel = getUpgradeCount(elytraIM);
        if (currentUpgradeLevel >= 10) {
            ItemStack barrier = new ItemStack(Material.BARRIER, 1);
            ItemMeta barrierIM = barrier.getItemMeta();
            barrierIM.setDisplayName("§cError");
            barrierIM.setLore(Arrays.asList("§7You have already reached the maximum level!"));
            event.setResult(barrier);
            event.getResult().setItemMeta(barrierIM);
            return;
        }
        currentUpgradeLevel++;

        ItemMeta newIM = elytraIM.clone();
        List<String> newLore = newIM.getLore();
        newIM.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        newIM.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(modifierUUID, "weaponmaster",
                currentUpgradeLevel*0.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        newLore.add(String.format("§4§l[!] §eWeaponMaster: §a§oThis operation will cost §b%d §a§olevels.", getLevelsForItem(newIM)));
        newLore.add(String.format("§4§l[!] §eWeaponMaster: §3Upgrading to level §b%d%s§3!", currentUpgradeLevel, currentUpgradeLevel == 10 ? " §2(MAXED)" : ""));
        newIM.setLore(newLore);

        ItemStack newItemStack = new ItemStack(Material.ELYTRA, 1);
        event.setResult(newItemStack);
        event.getResult().setItemMeta(newIM);
    }

    public void stripLastLoreLines(ItemStack itemStack) {
        ItemMeta im = itemStack.getItemMeta();
        List<String> lore = im.getLore();
        for (int i = 0; i < 2; i++) {
            lore.remove(lore.size() - 1);
        }
        im.setLore(lore);
        itemStack.setItemMeta(im);
    }

    @EventHandler()
    private void onPickingResultingItem(InventoryClickEvent event) {
        if (event.getCurrentItem() == null)  return;
        if (event.getView().getType() != InventoryType.ANVIL)  return;
        Player player = (Player) event.getWhoClicked();
        if (event.getRawSlot() == 2
                && util.checkForDragonElytra(event.getInventory().getItem(0))
                && util.checkFor(event.getInventory().getItem(1), 0, "id: DRAGON_SCALE")
        ) {
            ItemMeta im = event.getCurrentItem().getItemMeta();
            int levelsRequired = getLevelsForItem(im);
            if (player.getLevel() < levelsRequired) {
                player.sendMessage(String.format("§cYou must have §b%d §clevels to perform this action.", levelsRequired));
                event.setCancelled(true);
                return;
            }
            player.setLevel(player.getLevel()-levelsRequired);
            stripLastLoreLines(event.getCurrentItem());
            event.getWhoClicked().setItemOnCursor(event.getCurrentItem());
            event.getClickedInventory().clear();
        }
    }

}
