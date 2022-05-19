package io.github.greatericontop.weaponmaster.NapalmMissile;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NapalmItemListener implements Listener {

    private final float MISSILE_EXPLOSIVE_POWER = 6.0F;
    private final float MISSILE_FIRE_POWER = 14.0F;

    private final List<UUID> missiles = new ArrayList<UUID>();
    private final WeaponMasterMain plugin;
    private final Util util;
    public NapalmItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.util = new Util(plugin);
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        Location eventLoc = event.getEntity().getLocation();
        Vector velocity = event.getEntity().getVelocity();
        if (missiles.contains(event.getEntity().getUniqueId())) {
            Player player = (Player) event.getEntity().getShooter();
            double magnitude = Math.min(velocity.length()*0.1, 0.2);
            Location explosionLocation = eventLoc.clone().subtract(velocity.normalize().multiply(magnitude));

            eventLoc.getWorld().createExplosion(eventLoc, MISSILE_EXPLOSIVE_POWER, false, true);
            eventLoc.getWorld().createExplosion(eventLoc, MISSILE_FIRE_POWER, true, false);
            player.sendMessage("§3FWOOM!");
        }
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        if (event.getEntity().getType() != EntityType.SNOWBALL) { return; }
        Player player = (Player) event.getEntity().getShooter();
        if (player == null) { return; }
        if (!util.checkForNapalmMissile(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.napalm.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.napalm.use§3.");
            return;
        }
        missiles.add(event.getEntity().getUniqueId());
    }

}

