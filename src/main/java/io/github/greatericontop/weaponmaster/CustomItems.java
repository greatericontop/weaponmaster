package io.github.greatericontop.weaponmaster;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomItems {

    public List<String> LEVIATHAN_HEART_LORE = new ArrayList<String>();
    public final String LEVIATHAN_HEART_NAME = "§9Heart of Leviathan";

    public CustomItems() {
        LEVIATHAN_HEART_LORE.add("id: LEVIATHAN_HEART");
        LEVIATHAN_HEART_LORE.add("§9The heart of a sea monster.");
        LEVIATHAN_HEART_LORE.add("§712% chance to drop on elder guardian kills");
    }

    public ItemStack generateLeviathanHeartItemStack() {
        ItemStack stack = new ItemStack(Material.BLUE_ICE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(LEVIATHAN_HEART_NAME);
        iMeta.setLore(LEVIATHAN_HEART_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

}
