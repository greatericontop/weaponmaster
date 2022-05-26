package io.github.greatericontop.weaponmaster.CopperSword;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Material;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Random;

public class CopperSwordListener implements Listener {

    Random rnd = new Random();
    private final WeaponMasterMain plugin;
    private final Util util;
    public CopperSwordListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void OnHit(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        if (!util.checkForCopperSword(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.coppersword.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.coppersword.use§3.");
            return;
        }

        ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
        List<String> lore = im.getLore();
        if (Math.random() < 0.05 &&
                lore.get(7).equals("§6NOT WAXED") &&
                !lore.get(6).equals("§bOXIDIZED")) {
            int sharpLvl = im.getEnchantLevel(Enchantment.DAMAGE_ALL);
            int unbreakingLvl = im.getEnchantLevel(Enchantment.DURABILITY);
            im.removeEnchant(Enchantment.DAMAGE_ALL);
            im.addEnchant(Enchantment.DAMAGE_ALL, sharpLvl - 1, false);
            im.removeEnchant(Enchantment.DURABILITY);
            im.addEnchant(Enchantment.DURABILITY, unbreakingLvl - 1, false);
            player.sendMessage( "§cOh no, your Copper Sword Oxidized.");
            if (lore.get(6).equals("§bEXPOSED")) {
                lore.set(6, "§bOXIDIZED");
            } else {
                lore.set(6, "§bEXPOSED");
            }
            im.setLore(lore);
            player.getInventory().getItemInMainHand().setItemMeta(im);
        }

        if (player.getAttackCooldown() != 1.0) { return; }
        if (Math.random() > 0.15) { return; }
        if (!(event.getEntity() instanceof LivingEntity)) { return; }
        LivingEntity attacked = (LivingEntity) event.getEntity();
        int duration = rnd.nextInt(41) + 40;
        attacked.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, duration, 127));
        attacked.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, duration, 127));
        attacked.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, duration, 0));
        // player.playSound(player, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
        plugin.paperUtils.sendActionBar(player, String.format("§3You stunned your enemy for %d seconds.", duration/20), true);
        if (attacked.getType() == EntityType.PLAYER) {
            Player attackedPlayer = (Player) event.getEntity();
            attackedPlayer.playSound(attackedPlayer, Sound.BLOCK_ANVIL_LAND, 1.0F, 1.0F);
            plugin.paperUtils.sendActionBar(attackedPlayer, String.format("§3You were stunned for %d seconds.", duration / 20), true);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void OnRepair(PrepareAnvilEvent event) {
        if (!util.checkForCopperSword(event.getInventory().getItem(0))) { return; }
        Player player = (Player) event.getView().getPlayer();
        if (event.getInventory().getItem(1).getType() == Material.GOLD_INGOT) {
            event.setResult(new ItemStack(Material.AIR, 1));
            event.getInventory().setRepairCost(0);
        }
        if (event.getInventory().getItem(1).getType() == Material.HONEYCOMB) {
            ItemStack itemStack = event.getInventory().getItem(0);
            ItemMeta im = itemStack.getItemMeta();
            List<String> lore = im.getLore();
            lore.set(7, "§6WAXED");
            im.setLore(lore);
            itemStack.setItemMeta(im);
            event.setResult(itemStack);
        }
        if (event.getInventory().getItem(1).getType() == Material.COPPER_BLOCK) {
            if (event.getInventory().getItem(1).getAmount() != 5) {
                player.sendMessage("§cYou must have exactly §b5 §cof §6Copper Block §cto execute this operation.");
            } else {
                ItemStack itemStack = event.getInventory().getItem(0);
                ItemMeta im = itemStack.getItemMeta();
                List<String> lore = im.getLore();
                lore.set(6, "§bNORMAL");
                im.setLore(lore);
                im.removeEnchant(Enchantment.DAMAGE_ALL);
                im.addEnchant(Enchantment.DAMAGE_ALL, 3, false);
                im.removeEnchant(Enchantment.DURABILITY);
                im.addEnchant(Enchantment.DURABILITY, 9, false);
                itemStack.setItemMeta(im);
                event.setResult(itemStack);
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
            event.setCursor(event.getCurrentItem());
            event.getClickedInventory().clear();
        }
    }
}
