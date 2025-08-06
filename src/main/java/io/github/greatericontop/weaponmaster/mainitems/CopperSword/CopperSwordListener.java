package io.github.greatericontop.weaponmaster.mainitems.CopperSword;

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
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class CopperSwordListener implements Listener {

    Random rnd = new Random();
    private final WeaponMasterMain plugin;
    private final Util util;
    private final NamespacedKey pdcWaxKey;
    private final NamespacedKey pdcOxidizeKey;
    public CopperSwordListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
        pdcWaxKey = new NamespacedKey(plugin, "WM_COPPER_SWORD_WAX");
        pdcOxidizeKey = new NamespacedKey(plugin, "WM_COPPER_SWORD_OXIDIZE");
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void OnHit(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER)  return;
        Player player = (Player) event.getDamager();
        if (!util.checkForCopperSword(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.coppersword.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.coppersword.use§3.");
            return;
        }

        ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
        PersistentDataContainer pdc = im.getPersistentDataContainer();
        if (!pdc.has(pdcOxidizeKey, PersistentDataType.STRING)) { pdc.set(pdcOxidizeKey, PersistentDataType.STRING, "normal"); }
        if (!pdc.has(pdcWaxKey, PersistentDataType.INTEGER)) { pdc.set(pdcWaxKey, PersistentDataType.INTEGER, 0); }

        if (Math.random() < 0.05 && pdc.get(pdcWaxKey, PersistentDataType.INTEGER) != 1) {
            List<String> lore = im.getLore();
            String oxidizeLevel = pdc.get(pdcOxidizeKey, PersistentDataType.STRING);
            if (oxidizeLevel.equals("normal")) {
                lore.set(8, "§bEXPOSED");
                pdc.set(pdcOxidizeKey, PersistentDataType.STRING, "exposed");
                im.removeEnchant(Enchantment.SHARPNESS);
                im.removeEnchant(Enchantment.UNBREAKING);
                im.addEnchant(Enchantment.SHARPNESS, 2, false);
                im.addEnchant(Enchantment.UNBREAKING, 8, true);
                player.sendMessage( "§cOh no, your Copper Sword Oxidized.");
            } else if (oxidizeLevel.equals("exposed")) {
                lore.set(8, "§bOXIDIZED");
                pdc.set(pdcOxidizeKey, PersistentDataType.STRING, "oxidized");
                im.removeEnchant(Enchantment.SHARPNESS);
                im.removeEnchant(Enchantment.UNBREAKING);
                im.addEnchant(Enchantment.SHARPNESS, 1, false);
                im.addEnchant(Enchantment.UNBREAKING, 7, true);
                player.sendMessage( "§cOh no, your Copper Sword Oxidized.");
            }
            im.setLore(lore);
            player.getInventory().getItemInMainHand().setItemMeta(im);
        }

        if (player.getAttackCooldown() != 1.0) { return; }
        if (Math.random() > 0.15) { return; }
        if (!(event.getEntity() instanceof LivingEntity)) { return; }
        LivingEntity attacked = (LivingEntity) event.getEntity();
        int duration = rnd.nextInt(41) + 40;
        attacked.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, duration, 127));
        attacked.addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, duration, 9));
        attacked.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration, 0));
        // player.playSound(player, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
        plugin.paperUtils.sendActionBar(player, String.format("§3You stunned your enemy for %d seconds.", duration / 20), true);
        if (attacked.getType() == EntityType.PLAYER) {
            Player attackedPlayer = (Player) event.getEntity();
            attackedPlayer.playSound(attackedPlayer, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            plugin.paperUtils.sendActionBar(attackedPlayer, String.format("§3You were stunned for %d seconds.", duration / 20), true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void OnRepair(PrepareAnvilEvent event) {
        if (!util.checkForCopperSword(event.getInventory().getItem(0))) { return; }
        if (event.getInventory().getItem(1) == null) { return; }
        Player player = (Player) event.getView().getPlayer();
        if (event.getInventory().getItem(1).getType() == Material.GOLD_INGOT) {
            event.setResult(new ItemStack(Material.AIR, 1));
            event.getInventory().setRepairCost(0);
        }
        if (event.getInventory().getItem(1).getType() == Material.HONEYCOMB) {
            ItemStack outputItem = new ItemStack(Material.GOLDEN_SWORD, 1);
            ItemMeta im = event.getInventory().getItem(0).getItemMeta();
            List<String> lore = im.getLore();
            PersistentDataContainer pdc = im.getPersistentDataContainer();
            pdc.set(pdcWaxKey, PersistentDataType.INTEGER, 1);
            lore.set(9, "§6WAXED");
            im.setLore(lore);
            outputItem.setItemMeta(im);
            event.setResult(outputItem);
        }
        if (event.getInventory().getItem(1).getType() == Material.COPPER_BLOCK) {
            if (event.getInventory().getItem(1).getAmount() != 4) {
                player.sendMessage("§cYou must have exactly §b4 §cof §6Copper Block §cto execute this operation.");
            } else {
                ItemStack outputItem = new ItemStack(Material.GOLDEN_SWORD, 1);
                ItemMeta im = event.getInventory().getItem(0).getItemMeta();
                List<String> lore = im.getLore();
                PersistentDataContainer pdc = im.getPersistentDataContainer();
                pdc.set(pdcOxidizeKey, PersistentDataType.STRING, "normal");
                lore.set(8, "§bNORMAL");
                im.setLore(lore);
                im.addEnchant(Enchantment.SHARPNESS, 3, false);
                im.removeEnchant(Enchantment.UNBREAKING);
                im.addEnchant(Enchantment.UNBREAKING, 9, true);
                outputItem.setItemMeta(im);
                event.setResult(outputItem);
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onAnvilClick(InventoryClickEvent event) {
        if (event.getCurrentItem() == null) { return; }
        if (event.getView().getType() != InventoryType.ANVIL) { return; }
        Player player = (Player) event.getWhoClicked();
        if (!(event.getRawSlot() == 2 && util.checkForCopperSword(event.getInventory().getItem(0)))) { return; }

        if (event.getInventory().getItem(1).getType() == Material.GOLD_INGOT) {
            event.setCancelled(true);
            player.sendMessage("§cYou're not allowed to execute this anvil operation on " + util.COPPER_SWORD_NAME + "§c. This item can't be repaired.");
        } else if (event.getInventory().getItem(1).getType() == Material.HONEYCOMB || event.getInventory().getItem(1).getType() == Material.COPPER_BLOCK) {
            event.getWhoClicked().setItemOnCursor(event.getCurrentItem());
            event.getClickedInventory().clear();
        }
    }
}
