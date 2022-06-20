package io.github.greatericontop.weaponmaster.DragonSword;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.Util.Util;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DragonItemListener implements Listener {

    // triangular distribution parameters
    // on average, if the ability triggers, deal 35% more damage
    private final double A = 0.0;
    private final double B = 0.8;
    private final double C = 0.25;

    private final WeaponMasterMain plugin;
    private final Util util;
    private final DragonUpgradeListener dragonUpgrade;
    public DragonItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.util = new Util(plugin);
        this.dragonUpgrade = new DragonUpgradeListener(plugin);
    }

    private double triangular(double random) {
        // triangular distribution code stolen from https://stackoverflow.com/questions/33220176/triangular-distribution-in-java
        double F = (C - A) / (B - A);
        if (random < F) {
            return A + Math.sqrt(random * (B - A) * (C - A));
        } else {
            return B - Math.sqrt((1-random) * (B - A) * (B - C));
        }
    }

    @EventHandler(priority = EventPriority.HIGH) // runs at the end (before life helmet though) to stack bonuses
    public void onDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager().getType() != EntityType.PLAYER) { return; }
        Player player = (Player)event.getDamager();
        if (!util.checkForDragonSword(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.dragonsword.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.dragonsword.use§3.");
            return;
        }
        // increment damage
        if (Math.random() < dragonUpgrade.abilityTriggerProbability(player.getInventory().getItemInMainHand().getItemMeta().getLore())) {
            double multiplier = 1.0 + triangular(Math.random());
            if (
                    util.checkForDragonArmor(player.getInventory().getHelmet())
                    && util.checkForDragonArmor(player.getInventory().getChestplate())
                    && util.checkForDragonArmor(player.getInventory().getLeggings())
                    && util.checkForDragonArmor(player.getInventory().getBoots())
            ) { // multiplicative increase in damage when a full set of dragon armor is found
                multiplier *= 1.15;
            }
            event.setDamage(event.getDamage()*multiplier);
            plugin.paperUtils.sendActionBar(player, String.format("§3Hit increased by §4%.1f%% §3for §4%.1f§3.", (multiplier-1)*100, event.getDamage()), true);
        }
    }

}