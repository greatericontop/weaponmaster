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

/*enum PotionEffects {
    BLINDNESS,
}*/
public class NetheriteStaffListener implements Listener {

    Random rand = new Random();
    int duration = rand.nextInt(101);
    int amplifier = rand.nextInt(10);

    private final WeaponMasterMain plugin;
    private final Util util;

    public NetheriteStaffListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler
    public void OnAttack(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        LivingEntity attacked = (LivingEntity) event.getEntity();
        PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, duration, amplifier);
        attacked.addPotionEffect(effect);
    }
}
