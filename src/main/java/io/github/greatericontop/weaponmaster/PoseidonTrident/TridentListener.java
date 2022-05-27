package io.github.greatericontop.weaponmaster.PoseidonTrident;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TridentListener implements Listener {

    private final WeaponMasterMain plugin;
    private final Util util;
    public TridentListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler( priority = EventPriority.NORMAL )
    public void holdTrident(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (!util.checkForPoseidonTrident(player.getInventory().getItemInMainHand())) {
            player.removePotionEffect(PotionEffectType.CONDUIT_POWER);
            return;
        }
        if (!player.hasPermission("weaponmaster.poseidontrident.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.poseidontrident.use§3.");
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONDUIT_POWER, Integer.MAX_VALUE, 1));
    }
}
