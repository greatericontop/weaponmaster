package io.github.greatericontop.weaponmaster.CopperSword;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

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
        if (Math.random() < 0.01 &&
                im.getLore().get(7) == "§6NOT WAXED" &&
                im.getLore().get(6) != "§bOXIDIZED") {
            int lvl = im.getEnchantLevel(Enchantment.DAMAGE_ALL);
            im.removeEnchant(Enchantment.DAMAGE_ALL);
            im.addEnchant(Enchantment.DAMAGE_ALL, lvl - 1, false);
            plugin.paperUtils.sendActionBar(player, "§cOh no, your Copper Sword Oxidized.", true);
            if (im.getLore().get(6) == "§bEXPOSED") {
                im.getLore().set(6, "§bOXIDIZED");
            } else {
                im.getLore().set(6, "§bEXPOSED");
            }
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
        if (event.getInventory().getItem(1).getData().getItemType() == Material.GOLD_INGOT) {
            event.setResult(null);
        }
        if (event.getInventory().getItem(1).getData().getItemType() == Material.HONEYCOMB) {
            ItemStack it = event.getInventory().getItem(0);
            ItemMeta im = it.getItemMeta();
            im.getLore().set(7, "§6WAXED");
            it.setItemMeta(im);
            event.setResult(it);
        }
        if (event.getInventory().getItem(1).getData().getItemType() == Material.COPPER_BLOCK) {
            ItemStack it = event.getInventory().getItem(0);
            ItemMeta im = it.getItemMeta();
            im.getLore().set(6, "§bNORMAL");
            it.setItemMeta(im);
            event.setResult(it);
        }
    }
}
