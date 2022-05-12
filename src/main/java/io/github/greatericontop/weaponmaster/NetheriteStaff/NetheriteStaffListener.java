package io.github.greatericontop.weaponmaster.NetheriteStaff;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class NetheriteStaffListener implements Listener {

    Random rand = new Random();

    private final WeaponMasterMain plugin;
    private final Util util;

    public NetheriteStaffListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler
    public void OnAttack(EntityDamageByEntityEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        System.out.printf("You are an idiot");
        Player player = (Player) event.getDamager();
        if (!util.checkForNetheriteStaff(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.netheritestaff.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.netheritestaff.use§3.");
            return;
        }
        Player attacked = (Player) event.getEntity();
        int duration = rand.nextInt(520) + 60;
        int amplifier = rand.nextInt(10);
        PotionEffectType effectType = PotionEffectType.getById(rand.nextInt(32) + 1);
        System.out.printf("PT: %s, d=%d, a=%d", effectType, duration, amplifier);
        PotionEffect effect = new PotionEffect(effectType, duration, amplifier);
        attacked.addPotionEffect(effect);
    }
}
