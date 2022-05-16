package io.github.greatericontop.weaponmaster.NetheriteStaff;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.MathHelper;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.*;
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
    private final double VELOCITY = 4.0;
    private final WeaponMasterMain plugin;
    private final Util util;

    public NetheriteStaffListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onAttack(EntityDamageByEntityEvent event) {
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
        if (event.getHand() != EquipmentSlot.HAND) { return; }
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        Player player = event.getPlayer();
        if (!util.checkForNetheriteStaff(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.netheritestaff.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.netheritestaff.use§3.");
            return;
        }
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK
                && (event.getClickedBlock().getType() == Material.DIRT || event.getClickedBlock().getType() == Material.GRASS_BLOCK)) {
            event.setCancelled(true);
        }
        if (Util.checkForInteractableBlock(event)) { return; }
        Damageable iMeta = (Damageable) player.getInventory().getItemInMainHand().getItemMeta();
        if (iMeta.getDamage() > (2031-101)) {
            player.sendMessage("§3Not enough durability to shoot an arrow!");
            return;
        }

        Location eyeLocation = player.getEyeLocation();
        Location spawnLoc = eyeLocation.clone().add(eyeLocation.getDirection().multiply(1.9));
        World world = player.getWorld();

        Arrow arrow = (Arrow) world.spawnEntity(spawnLoc, EntityType.ARROW);
        arrow.setShooter(player);
        arrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
        int duration = rand.nextInt(180) + 20;
        int amplifier = Math.min(rand.nextInt(3) - 1, 0);
        PotionEffectType effectType = PotionEffectType.getById(rand.nextInt(32) + 1);
        PotionEffect effect = new PotionEffect(effectType, duration, amplifier);
        arrow.addCustomEffect(effect, true);
        arrow.setVelocity(eyeLocation.getDirection().multiply(VELOCITY));
        arrow.setColor(Color.BLACK);
        arrow.setDamage(1.5F);
        if (event.getPlayer().getGameMode() != GameMode.CREATIVE) {
            iMeta.setDamage(iMeta.getDamage() + MathHelper.getDamageWithUnbreaking(5, iMeta));
            player.getInventory().getItemInMainHand().setItemMeta(iMeta);
        }
    }
}
