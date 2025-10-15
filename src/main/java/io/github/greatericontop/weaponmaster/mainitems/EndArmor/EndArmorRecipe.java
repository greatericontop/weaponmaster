package io.github.greatericontop.weaponmaster.mainitems.EndArmor;

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

import io.github.greatericontop.weaponmaster.minorcrafts.MinorItems;
import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

public class EndArmorRecipe {

    private final Util util;
    private final MinorItems customItems;
    public EndArmorRecipe() {
        util = new Util(null);
        customItems = new MinorItems();
    }

    public void reg(Material mat, String key) {
        ItemStack endArmor = util.generateMeta(util.END_ARMOR_LORE, util.END_ARMOR_NAME, mat);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft(key), endArmor);
        recipe.shape("eXe",
                     "eAe",
                     "eXe");
        recipe.setIngredient('X', new RecipeChoice.ExactChoice(customItems.generateEndArtifactItemStack()));
        recipe.setIngredient('A', mat);
        recipe.setIngredient('e', Material.ENDER_PEARL);
        Bukkit.getServer().addRecipe(recipe);
    }

    public void registerAll() {
        reg(Material.NETHERITE_HELMET, "end_armor_helmet");
        reg(Material.NETHERITE_CHESTPLATE, "end_armor_chestplate");
        reg(Material.NETHERITE_LEGGINGS, "end_armor_leggings");
        reg(Material.NETHERITE_BOOTS, "end_armor_boots");
    }

}
