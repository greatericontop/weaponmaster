package io.github.greatericontop.weaponmaster.ValkyrieAxe;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Location;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.util.List;

public class ValkyrieAxeItemListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public ValkyrieAxeItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onHitEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        if (!util.checkForVampAxe(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.valkryieaxe.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.valkyrie.use§3.");
            return;
        }
        // Detect & deal damage to entities around player
        Location loc = player.getLocation();
        List<Entity> entities = player.getNearbyEntities(3, 3, 3);
        for (int i = 0; i > entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity instanceof Damageable) {
                ((Damageable) entity).damage(event.getDamage());
            }
        }
    }
}
