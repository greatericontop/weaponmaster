package io.github.greatericontop.weaponmaster.mainitems.DragonArmor;

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
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Arrays;
import java.util.List;

public class DragonArmorUpgradeListener implements Listener {
    public static final NamespacedKey DRAGON_ARMOR_UPGRADE_KEY = new NamespacedKey("weaponmaster", "dragon_armor_upgrade");
    public static final int LORE_LINE_1 = 11;
    public static final int LORE_LINE_2 = 12;
    public static final int LORE_LINE_3 = 13;
    public static final int LORE_LINE_4 = 14;

    private final WeaponMasterMain plugin;
    private final Util util;
    public DragonArmorUpgradeListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.util = new Util(plugin);
    }

    public int getUpgradeCount(ItemMeta im) {
        return im.getPersistentDataContainer().getOrDefault(DRAGON_ARMOR_UPGRADE_KEY, PersistentDataType.INTEGER, 0);
    }

    public int getLevelsForItem(int lvl) {
        return 70 * lvl;
    }

    @EventHandler()
    public void onAnvil(PrepareAnvilEvent event) {
        ItemStack dragonArmor = event.getInventory().getItem(0);
        ItemStack scale = event.getInventory().getItem(1);
        if (!util.checkForDragonArmor(dragonArmor))  return;
        if (!util.checkFor(scale, 0, "id: DRAGON_SCALE"))  return;

        ItemMeta armorIM = dragonArmor.getItemMeta();
        int currentUpgradeLevel = getUpgradeCount(armorIM);
        if (currentUpgradeLevel >= 5) {
            ItemStack barrier = new ItemStack(Material.BARRIER, 1);
            ItemMeta barrierIM = barrier.getItemMeta();
            barrierIM.setDisplayName("§cError");
            barrierIM.setLore(Arrays.asList("§7You have already reached the maximum level!"));
            event.setResult(barrier);
            event.getResult().setItemMeta(barrierIM);
            return;
        }
        currentUpgradeLevel++;

        ItemMeta newIM = armorIM.clone();
        List<String> newLore = newIM.getLore();
        String lvls = String.format("§4§l[!] §eWeaponMaster: §a§oThis operation will cost §b%d §a§olevels.", getLevelsForItem(currentUpgradeLevel));
        String upgradingTo = String.format("§4§l[!] §eWeaponMaster: §3Upgrading to level §b%d%s§3!", currentUpgradeLevel, currentUpgradeLevel == 5 ? " §2(MAXED)" : "");
        if (currentUpgradeLevel == 1) {
            newLore.add(LORE_LINE_1, "");
            newLore.add(LORE_LINE_2, lvls);
            newLore.add(LORE_LINE_3, upgradingTo);
            newLore.add(LORE_LINE_4, "");
        } else {
            newLore.set(LORE_LINE_1, "");
            newLore.set(LORE_LINE_2, lvls);
            newLore.set(LORE_LINE_3, upgradingTo);
            newLore.set(LORE_LINE_4, "");
        }
        newIM.getPersistentDataContainer().set(DRAGON_ARMOR_UPGRADE_KEY, PersistentDataType.INTEGER, currentUpgradeLevel);
        newIM.setLore(newLore);
        ItemStack newItemStack = new ItemStack(dragonArmor.getType(), 1);
        event.setResult(newItemStack);
        event.getResult().setItemMeta(newIM);
    }

    @EventHandler()
    private void onPickingResultingItem(InventoryClickEvent event) {
        if (event.getCurrentItem() == null)  return;
        if (event.getView().getType() != InventoryType.ANVIL)  return;
        Player player = (Player) event.getWhoClicked();
        if (event.getRawSlot() == 2
                && util.checkForDragonArmor(event.getInventory().getItem(0))
                && util.checkFor(event.getInventory().getItem(1), 0, "id: DRAGON_SCALE")
        ) {
            ItemMeta im = event.getCurrentItem().getItemMeta();
            int lvl = getUpgradeCount(im);
            int levelsRequired = getLevelsForItem(lvl);
            if (player.getLevel() < levelsRequired) {
                player.sendMessage(String.format("§cYou must have §b%d §clevels to perform this action.", levelsRequired));
                event.setCancelled(true);
                return;
            }
            player.setLevel(player.getLevel()-levelsRequired);
            List<String> newLore = im.getLore();
            newLore.set(LORE_LINE_1, "");
            newLore.set(LORE_LINE_2, String.format("§6Upgrade Level: §b%d%s", lvl, lvl >= 5 ? " §a(MAXED!)" : ""));
            newLore.set(LORE_LINE_3, String.format("§9BONUS: §3Reduces damage taken by another §b%.1f%%§3. [%.1f%%]", lvl*0.5, 5+lvl*0.5));
            newLore.set(LORE_LINE_4, String.format("§9BONUS: §3Increases damage dealt by another §b%.1f%%§3. [%.1f%%]", lvl*0.2, 2+lvl*0.2));
            im.setLore(newLore);
            event.getCurrentItem().setItemMeta(im);
            event.getWhoClicked().setItemOnCursor(event.getCurrentItem());
            event.getClickedInventory().clear();
        }
    }

}
