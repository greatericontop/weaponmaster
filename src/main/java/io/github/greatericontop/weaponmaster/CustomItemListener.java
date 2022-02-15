package io.github.greatericontop.weaponmaster;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class CustomItemListener implements Listener {

    private final CustomItems customItems;
    public CustomItemListener() {
        customItems = new CustomItems();
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntityType() != EntityType.ELDER_GUARDIAN) { return; }
        if (Math.random() < 0.12) {
            ItemStack leviathan = customItems.generateLeviathanHeartItemStack();
            event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), leviathan);
            Player killer = event.getEntity().getKiller();
            if (killer != null) {
                killer.sendMessage("§eRARE DROP! " + customItems.LEVIATHAN_HEART_NAME);
            }
        }
    }

}
