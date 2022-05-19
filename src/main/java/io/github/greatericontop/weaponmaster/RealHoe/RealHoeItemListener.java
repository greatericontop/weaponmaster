package io.github.greatericontop.weaponmaster.RealHoe;

import org.bukkit.event.EventPriority;

import org.bukkit.SoundCategory;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Farmland;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockCanBuildEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;

public class RealHoeItemListener implements Listener{

    private final WeaponMasterMain plugin;
    private final Util util;
    public RealHoeItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLandTill(BlockPlaceEvent event) {
        if (event.getPlayer().getType() != EntityType.PLAYER) { return; }
        Player player = (Player) event.getPlayer();
        if (!util.checkForRealHoe(player.getInventory().getItemInMainHand())) {
            return;
        }
        if (!player.hasPermission("weaponmaster.realhoe.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.realhoe.use§3.");
            return;
        }
        Block farmland = (Block) event.getBlockPlaced();
        if (farmland.getType() == Material.FARMLAND) {
            Location target = farmland.getLocation();
            Location real = target.add(0.5, 1.0, 0.5);
            ItemStack sugar = new ItemStack(Material.SUGAR);
            farmland.getWorld().dropItem(real, sugar);
            player.playSound(real, Sound.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 100, 1);
            Random number = new Random();
            if (number.nextInt(12) == 1) {
                farmland.getWorld().getBlockAt(real).setType(Material.POWDER_SNOW);
                player.playSound(real, Sound.BLOCK_HONEY_BLOCK_PLACE, SoundCategory.BLOCKS, 250, (float) 0.5);
            }
        }
    }

}
