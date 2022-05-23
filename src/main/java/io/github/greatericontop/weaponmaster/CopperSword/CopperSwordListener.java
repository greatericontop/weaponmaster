package io.github.greatericontop.weaponmaster.CopperSword;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class CopperSwordListener implements Listener {

    Random rnd = new Random();
    int duration = rnd.nextInt(40) + 20;

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
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.netheritestaff.use§3.");
            return;
        }
        if (Math.random() <= 0.30) {
            LivingEntity attacked = (LivingEntity) event.getEntity();
            PotionEffect slowness = new PotionEffect(PotionEffectType.SLOW, duration, 126);
            PotionEffect weakness = new PotionEffect(PotionEffectType.WEAKNESS, duration, 126);
            attacked.addPotionEffect(slowness);
            attacked.addPotionEffect(weakness);
            plugin.paperUtils.sendActionBar(player, String.format("§bYou have been stunned your enemy for %d", (int) duration/20), true);
            if (attacked.getType() == EntityType.PLAYER) {
                plugin.paperUtils.sendActionBar((Player) attacked, String.format("§bYou have been stunned for %d", (int) duration/20), true);
            }
        }
    }
}
