package io.github.greatericontop.weaponmaster.Fireball;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class FireballListener implements Listener {

    private final float VELOCITY = 2.5F;
    private final float POWER = 3.0F;
    private final double SEEKING = 9.0;

    private final WeaponMasterMain plugin;
    private final Util util;
    public FireballListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }


    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        if (util.checkForInteractableBlock(event)) { return; }
        Player player = event.getPlayer();
        if (!util.checkForFireball(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.fireball.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.fireball.use§3.");
            return;
        }

        Location eyeLocation = player.getEyeLocation();
        Location spawnLoc = eyeLocation.add(eyeLocation.getDirection().multiply(0.9));
        World world = player.getWorld();
        if (Math.random() < 0.02) {
            DragonFireball draogonFireballEntity = (DragonFireball) world.spawnEntity(spawnLoc, EntityType.DRAGON_FIREBALL);
            draogonFireballEntity.setVelocity(eyeLocation.getDirection().multiply(VELOCITY));
            event.getPlayer().sendMessage("§3You summoned a dragon fireball!");
        } else {
            Fireball fireball = (Fireball) world.spawnEntity(spawnLoc, EntityType.FIREBALL);
            fireball.setVelocity(eyeLocation.getDirection().multiply(VELOCITY));
            fireball.setYield(POWER);
            new BukkitRunnable() {
                public void run() {
                    if (fireball.isDead()) {
                        cancel();
                        return;
                    }
                    Location fireballLoc = fireball.getLocation();
                    fireball.getWorld().spawnParticle(Particle.FLAME, fireballLoc, 100);
                    /*List<Entity> nearEntities = fireball.getNearbyEntities(SEEKING, SEEKING, SEEKING);
                    nearEntities.sort(
                            (Entity a, Entity b) -> (int) (1000.0 * (a.getLocation().distanceSquared(fireballLoc) - b.getLocation().distanceSquared(fireballLoc)))
                    );
                    for (Entity target : nearEntities) {
                        if (player.hasLineOfSight(target) && target instanceof Player && (!target.isDead()) && target.getEntityId() != player.getEntityId()) {
                            fireball.setVelocity(target.getLocation().toVector().subtract(fireballLoc.toVector()).normalize().multiply(VELOCITY));
                            break;
                        }
                    }*/
                }
            }.runTaskTimer(plugin, 1L, 1L);
        }
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            player.updateInventory();
        }
    }
}
