package io.github.greatericontop.weaponmaster.RealHoe;

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class RealHoeItemListener implements Listener{

    private final WeaponMasterMain plugin;
    private final Util util;
    public RealHoeItemListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onLandTill(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!util.checkForRealHoe(player.getInventory().getItemInMainHand())) { return; }
        if (!player.hasPermission("weaponmaster.realhoe.use")) {
            player.sendMessage("§3Sorry, you cannot use this item yet. You need the permission §4weaponmaster.realhoe.use§3.");
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) { return; }
        Block farmland = event.getClickedBlock();
        if (farmland.getType() == Material.DIRT || farmland.getType() == Material.GRASS_BLOCK || farmland.getType() == Material.DIRT_PATH || farmland.getType() == Material.ROOTED_DIRT || farmland.getType() == Material.COARSE_DIRT) {
            Location target = farmland.getLocation().add(0, 1, 0);
            ItemStack sugar = new ItemStack(Material.SUGAR, 1);
            farmland.getWorld().dropItemNaturally(target, sugar);
            player.playSound(target, Sound.ENTITY_CHICKEN_EGG, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (Math.random() < 0.125) {
                farmland.setType(Material.POWDER_SNOW);
                player.playSound(target, Sound.BLOCK_HONEY_BLOCK_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
    }

}
