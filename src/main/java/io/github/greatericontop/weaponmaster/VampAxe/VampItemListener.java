package io.github.greatericontop.weaponmaster.VampAxe;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.Util.Util;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import static java.lang.Math.min;

public class VampItemListener implements Listener {

    private final double VAMP_HEAL_MULTIPLIER = 0.16;

    private final WeaponMasterMain plugin;
    private final Util util;
    public VampItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player)event.getDamager();
        if (!util.checkForVampAxe(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.vampaxe.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.vampaxe.use§3.");
            return;
        }
        // Heal player
        double healAmount = event.getFinalDamage() * VAMP_HEAL_MULTIPLIER;
        double previousHealth = player.getHealth();
        double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
        player.setHealth(min(previousHealth+healAmount, maxHealth));
        plugin.paperUtils.sendActionBar(player, String.format("§3Healed you for §4%.1f§3.", player.getHealth()-previousHealth), true);
    }

}