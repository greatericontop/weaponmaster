package io.github.greatericontop.weaponmaster.othercrafts;

/*
 * WeaponMaster Copyright (C) 2021-present greateric.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty  of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

public class HideLeviathanRecipe {

    private final Map<Enchantment, Integer> hideEnchants = new HashMap<Enchantment, Integer>();
    private CustomItems customItems;

    public HideLeviathanRecipe() {
        hideEnchants.put(Enchantment.PROTECTION_ENVIRONMENTAL, 4);
        hideEnchants.put(Enchantment.PROTECTION_EXPLOSIONS, 4);
        hideEnchants.put(Enchantment.PROTECTION_FIRE, 4);
        hideEnchants.put(Enchantment.PROTECTION_PROJECTILE, 4);
        customItems = new CustomItems();
    }

    public void regRecipe() {
        ItemStack hide = new ItemStack(Material.NETHERITE_LEGGINGS, 1);
        ItemMeta itemMeta = hide.getItemMeta();
        itemMeta.setDisplayName("§9§lHide Of Leviathan");
        hide.setItemMeta(itemMeta);
        hide.addUnsafeEnchantments(hideEnchants);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("hide_of_leviathan"), hide);
        recipe.shape("dnd",
                     "nLn",
                     "did");
        recipe.setIngredient('L', Material.NETHERITE_LEGGINGS);
        recipe.setIngredient('n', Material.NETHERITE_BLOCK);
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        recipe.setIngredient('i', new RecipeChoice.ExactChoice(customItems.generateLeviathanHeartItemStack()));
        Bukkit.getServer().addRecipe(recipe);
    }

}
