package io.github.greatericontop.weaponmaster.minorcrafts;

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
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.Map;
import java.util.Random;

public class MoveOversListener implements Listener {

    private final Random rnd = new Random();
    private final MinorItems minorItems;
    private final Util util;
    private final WeaponMasterMain plugin;
    public MoveOversListener(WeaponMasterMain plugin) {
        this.minorItems = new MinorItems();
        this.plugin = plugin;
        this.util = new Util(plugin);
    }


    @EventHandler()
    public void onExpertSeal(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR)  return;
        if (event.getCursor() == null || event.getCursor().getType() == Material.AIR)  return;
        Player player = (Player) event.getWhoClicked();
        if (!util.checkFor(event.getCursor(), 0, "id: EXPERT_SEAL"))  return;
//        if (util.checkFor(event.getCurrentItem(), 0, "id: WITHER_DYE")) {
//            // wither dyes and expert seals are unstackable
//            minorItems.generateExpertDye(event.getCurrentItem());
//            event.setCancelled(true);
//            player.updateInventory();
//            event.setCursor(new ItemStack(Material.AIR));
//            player.sendMessage("§3Success!");
//            return;
//        }
        ItemMeta targetItem = event.getCurrentItem().getItemMeta();
        if (targetItem == null || !targetItem.hasEnchants()) {
            player.sendMessage("§cYou can't use Expert Seal on this item!");
            return;
        }
        final NamespacedKey key = new NamespacedKey(plugin, "expert_seal");
        if (targetItem.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
            player.sendMessage("§cYou can only upgrade once!");
            return;
        }
        Map<Enchantment, Integer> enchants = targetItem.getEnchants();
        for (Enchantment enchant : enchants.keySet()) {
            targetItem.addEnchant(enchant, enchants.get(enchant) + 1, true);
        }
        targetItem.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
        event.getCurrentItem().setItemMeta(targetItem);
        event.setCancelled(true);
        player.updateInventory();
        event.setCursor(new ItemStack(Material.AIR)); // expert seals are unstackable
        player.sendMessage("§3Success!");
    }

    @EventHandler()
    public void onGenericDye(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR)  return;
        if (event.getCursor() == null || event.getCursor().getType() == Material.AIR)  return;
        Player player = (Player) event.getWhoClicked();
        if (!util.checkFor(event.getCursor(), 0, "id: DYE"))  return;
        ItemMeta targetIM = event.getCurrentItem().getItemMeta();
        if (targetIM == null || !targetIM.hasDisplayName()) {
            player.sendMessage("§cGive it a name first!");
            return;
        }
        String name = targetIM.getDisplayName();
        if (name.contains("§")) {
            player.sendMessage("§cThis item already has a color! If you really want to dye it again, remove the color first.");
            return;
        }
        switch (event.getCursor().getItemMeta().getLore().get(1)) {
            case MinorItems.WITHER_DYE_KEY -> targetIM.setDisplayName("§8" + name);
            case MinorItems.DIAMOND_DYE_KEY -> targetIM.setDisplayName("§b" + name);
            case MinorItems.EMERALD_DYE_KEY -> targetIM.setDisplayName("§a" + name);
            case MinorItems.CRYSTAL_DYE_KEY -> targetIM.setDisplayName("§d" + name);
            case MinorItems.LAPIS_DYE_KEY -> targetIM.setDisplayName("§9" + name);
            case MinorItems.DARK_DIAMOND_DYE_KEY -> targetIM.setDisplayName("§3" + name);
            case MinorItems.GOLD_DYE_KEY -> targetIM.setDisplayName("§6" + name);
            case MinorItems.BLOOD_DYE_KEY -> targetIM.setDisplayName("§c" + name);
            case MinorItems.LEVIATHAN_DYE_KEY -> {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < name.length(); i++) {
                    if (i % 2 == 0) {
                        sb.append("§9").append(name.charAt(i));
                    } else {
                        sb.append("§3").append(name.charAt(i));
                    }
                }
                targetIM.setDisplayName(sb.toString());
            }
            case MinorItems.EXPERT_DYE_KEY -> {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < name.length(); i++) {
                    if (i % 2 == 0) {
                        sb.append("§6").append(name.charAt(i));
                    } else {
                        sb.append("§e").append(name.charAt(i));
                    }
                }
                targetIM.setDisplayName(sb.toString());
            }
            case MinorItems.DRAGON_DYE_KEY -> {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < name.length(); i++) {
                    if (i % 2 == 0) {
                        sb.append("§5").append(name.charAt(i));
                    } else {
                        sb.append("§d").append(name.charAt(i));
                    }
                }
                targetIM.setDisplayName(sb.toString());
            }
            default ->  throw new RuntimeException("unknown dye");
        }
        event.getCurrentItem().setItemMeta(targetIM);
        event.setCancelled(true);
        player.updateInventory();
        event.setCursor(new ItemStack(Material.AIR)); // dyes are unstackable
        player.sendMessage("§3Success!");
    }

    @EventHandler()
    public void onMasterDye(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR)  return;
        if (event.getCursor() == null || event.getCursor().getType() == Material.AIR)  return;
        Player player = (Player) event.getWhoClicked();
        if (!util.checkFor(event.getCursor(), 0, "id: MASTER_DYE"))  return;
        ItemMeta targetIM = event.getCurrentItem().getItemMeta();
        if (targetIM == null || !targetIM.hasDisplayName()) {
            player.sendMessage("§cGive it a name first!");
            return;
        }
        String name = targetIM.getDisplayName();
        if (name.contains("§")) {
            player.sendMessage("§cThis item already has a color! If you really want to dye it again, remove the color first.");
            return;
        }
        if (!name.contains("&")) {
            player.sendMessage("§cYou should probably put some formatting codes (&) in the name first!");
            return;
        }
        String nameFixed = ChatColor.translateAlternateColorCodes('&', name);
        targetIM.setDisplayName(nameFixed);
        event.getCurrentItem().setItemMeta(targetIM);
        event.setCancelled(true);
        player.updateInventory();
        event.setCursor(new ItemStack(Material.AIR)); // dyes are unstackable
        player.sendMessage("§3Success!");
    }

}
