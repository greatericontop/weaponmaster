package io.github.greatericontop.weaponmaster.Fireball;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.DragonFireball;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class FireballListener implements Listener {

    private final float VELOCITY = 1.5F;
    private final float POWER = 3.0F;

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
        Player player = event.getPlayer();
        if (!util.checkForFireball(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.fireball.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.fireball.use§3.");
            return;
        }

        Location eyeLocation = player.getEyeLocation();
        Location spawnLoc = eyeLocation.add(eyeLocation.getDirection().multiply(0.9));
        World world = player.getWorld();
        if (Math.random() < 0.025) {
            DragonFireball draogonFireballEntity = (DragonFireball) world.spawnEntity(spawnLoc, EntityType.DRAGON_FIREBALL);
            draogonFireballEntity.setVelocity(eyeLocation.getDirection().multiply(VELOCITY));
            event.getPlayer().sendMessage("§3You summoned a dragon fireball!");
        } else {
            Fireball fireballEntity = (Fireball) world.spawnEntity(spawnLoc, EntityType.FIREBALL);
            fireballEntity.setVelocity(eyeLocation.getDirection().multiply(VELOCITY));
            fireballEntity.setYield(POWER);
        }
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
            player.updateInventory();
        }
    }
}
