package io.github.greatericontop.weaponmaster.DragonSword;

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
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class DragonUpgradeListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public DragonUpgradeListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.util = new Util(plugin);
    }

    public int getUpgradeCount(List<String> lore) {
        String loreData = lore.get(util.DRAGON_UPGRADE);
        if (loreData.equals("§6LEGENDARY")) {
            lore.add(util.DRAGON_UPGRADE, "");
            lore.add(util.DRAGON_UPGRADE+1, "§7§oUpgrades increase the chance to deal even higher damage.");
            loreData = "";
        }
        if (loreData.length() <= 2) {
            return 0;
        }
        String[] data = loreData.split(" ");
        return Integer.parseInt(data[2].substring(2)); // "§6Upgrade Level: §b2 §a(MAXED!)"
    }

    public double abilityTriggerProbability(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                return 0.6;
            case 2:
                return 0.7;
            case 3:
                return 0.8;
            case 4:
                return 0.9;
            case 5:
                return 1.0;
            default:
                return 0.5;
        }
    }
    public double abilityTriggerProbability(List<String> lore) {
        return abilityTriggerProbability(getUpgradeCount(lore));
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onAnvil(PrepareAnvilEvent event) {
        ItemStack dragon = event.getInventory().getItem(0);
        ItemStack scale = event.getInventory().getItem(1);
        Player player = (Player) event.getViewers().get(0);
        if (!util.checkForDragonSword(dragon)) { return; }
        if (!util.checkFor(scale, null, 0, "id: DRAGON_SCALE")) { return; }

        ItemMeta dragonIM = dragon.getItemMeta();
        List<String> dragonLore = dragonIM.getLore();
        int currentUpgradeLevel = getUpgradeCount(dragonLore);
        if (currentUpgradeLevel >= 5) { return; }
        currentUpgradeLevel++;

        ItemMeta newIM = dragonIM.clone();
        List<String> newLore = newIM.getLore();
        newLore.set(util.DRAGON_UPGRADE, String.format("§6Upgrade Level: §b%d%s", currentUpgradeLevel, currentUpgradeLevel >= 5 ? " §a(MAXED!)" : ""));
        newIM.setLore(newLore);
        for (Enchantment enchant : newIM.getEnchants().keySet()) {
            newIM.removeEnchant(enchant);
        }

        ItemStack newItemStack = new ItemStack(Material.NETHERITE_SWORD, 1);
        event.setResult(newItemStack);
        event.getResult().setItemMeta(newIM);
        player.sendMessage("§7done!");//
    }

    @EventHandler(priority = EventPriority.NORMAL)
    private void onPickingResultingItem(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) { return; }
        if (event.getView().getType() != InventoryType.ANVIL) { return; }
        Player player = (Player) event.getWhoClicked();
        if (event.getRawSlot() == 2 && event.getCurrentItem().getItemMeta().hasLore() && util.checkForDragonSword(event.getCurrentItem())) {
            player.sendMessage("§7caught event!");
            event.setCursor(event.getCurrentItem());
            event.getClickedInventory().clear();
            event.setCancelled(false);
        }
    }

}
