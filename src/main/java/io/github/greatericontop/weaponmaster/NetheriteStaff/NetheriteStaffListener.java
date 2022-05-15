package io.github.greatericontop.weaponmaster.NetheriteStaff;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
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

    @EventHandler(priority = EventPriority.NORMAL)
    public void onAttack(EntityDamageByEntityEvent event) {
        // if (event.getEntity().getType() != EntityType.PLAYER) { return; }
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getDamager();
        if (!util.checkForNetheriteStaff(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.netheritestaff.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.netheritestaff.use§3.");
            return;
        }
        LivingEntity attacked = (LivingEntity) event.getEntity();
        int duration = rand.nextInt(520) + 60;
        int amplifier = rand.nextInt(10);
        PotionEffectType effectType = PotionEffectType.getById(rand.nextInt(32) + 1);
        PotionEffect effect = new PotionEffect(effectType, duration, amplifier);
        attacked.addPotionEffect(effect);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onRightClick(PlayerInteractEvent event) {

        float VELOCITY = 5.0F;

        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        if (util.checkForInteractableBlock(event)) { return; }
        Player player = event.getPlayer();
        if (!util.checkForNetheriteStaff(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.netheritestaff.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.netheritestaff.use§3.");
            return;
        }

        Damageable iMeta = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();

        if (iMeta.getDamage() <= 25) {
            player.sendMessage("§c[Low Durability]: §3You may not shoot an arrow now.");
        }

        Location eyeLocation = player.getEyeLocation();
        Location spawnLoc = eyeLocation.add(eyeLocation.getDirection().multiply(0.9));
        World world = player.getWorld();

        Arrow arrow = (Arrow) world.spawnEntity(spawnLoc, EntityType.ARROW);
        int duration = rand.nextInt(200) + 60;
        int amplifier = rand.nextInt(5);
        PotionEffectType effectType = PotionEffectType.getById(rand.nextInt(32) + 1);
        PotionEffect effect = new PotionEffect(effectType, duration, amplifier);
        arrow.addCustomEffect(effect, true);
        arrow.setVelocity(eyeLocation.getDirection().multiply(VELOCITY));
        arrow.setColor(Color.BLACK);
        arrow.setDamage(0.5F);
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            iMeta.setDamage(iMeta.getDamage() + 5);
            player.getInventory().getItemInMainHand().setItemMeta(iMeta);
            player.updateInventory();
        }
    }
}
