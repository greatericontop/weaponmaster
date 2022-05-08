package io.github.greatericontop.weaponmaster.Fireball;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Random;

public class FireballListener implements Listener {
    private final WeaponMasterMain plugin;
    private final Util util;

    public FireballListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }


    @EventHandler
    public void OnRightClick(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Player player = (Player) event.getPlayer();
            if (event.getHand() != EquipmentSlot.HAND) { return; }
            if (util.checkForFireball(player.getInventory().getItemInMainHand())) {
                Location location = player.getEyeLocation();
                World world = (World) player.getWorld();
                Random chance = new Random();
                if (chance.nextInt(999) <= 25) {
                    world.spawnEntity(location, EntityType.DRAGON_FIREBALL);
                    event.getPlayer().sendMessage("§6§BSuper Rare Event: You summoned a dragon fireball!");
                } else {
                    Fireball fireballEntity = (Fireball) world.spawnEntity(location, EntityType.FIREBALL);
                    fireballEntity.setVelocity(location.getDirection().multiply(1.5F));
                    fireballEntity.setYield(3);
                }
                if (event.getPlayer().getGameMode() == GameMode.CREATIVE) { return; }
                player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
                player.updateInventory();
            }
        }
    }
}
