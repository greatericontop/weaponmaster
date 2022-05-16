package io.github.greatericontop.weaponmaster;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomItems {

    public List<String> LEVIATHAN_HEART_LORE = new ArrayList<String>();
    public final String LEVIATHAN_HEART_NAME = "§9Heart of Leviathan";

    public List<String> CORE_STAFF_LORE = new ArrayList<String>();
    public final String CORE_STAFF_NAME = "§9 Nether Reactor Core";

    public CustomItems() {
        LEVIATHAN_HEART_LORE.add("id: LEVIATHAN_HEART");
        LEVIATHAN_HEART_LORE.add("§9The heart of a sea monster.");
        LEVIATHAN_HEART_LORE.add("§712% chance to drop on elder guardian kills");

        CORE_STAFF_LORE.add("id: CORE_STAFF");
        CORE_STAFF_LORE.add("§9The Core From the depths of the Nether.");
        CORE_STAFF_LORE.add("§7Crafting using finest magic.");


    }

    public ItemStack generateLeviathanHeartItemStack() {
        ItemStack stack = new ItemStack(Material.BLUE_ICE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(LEVIATHAN_HEART_NAME);
        iMeta.setLore(LEVIATHAN_HEART_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateCoreStaffItemStack() {
        ItemStack stack = new ItemStack(Material.NETHER_BRICK, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(CORE_STAFF_NAME);
        iMeta.setLore(CORE_STAFF_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

}
