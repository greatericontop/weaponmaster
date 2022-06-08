package io.github.greatericontop.weaponmaster.Scylla;

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

import io.github.greatericontop.weaponmaster.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class ScyllaRecipe {

    private final Util util;
    public ScyllaRecipe() {
        util = new Util(null);
    }

    public void regRecipe() {
        ItemStack scylla = util.generateMeta(util.SCYLLA_CHESTPLATE_LORE, util.SCYLLA_CHESTPLATE_NAME, Material.DIAMOND_CHESTPLATE);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("scylla_chestplate"), scylla);
        recipe.shape("sNs",
                     "PCP",
                     "sNs");
        recipe.setIngredient('C', Material.DIAMOND_CHESTPLATE);
        recipe.setIngredient('N', Material.NETHERITE_INGOT);
        ItemStack resistance = new ItemStack(Material.POTION, 1);
        PotionMeta im = (PotionMeta) resistance.getItemMeta();
        im.setBasePotionData(new PotionData(PotionType.TURTLE_MASTER, false, true));
        resistance.setItemMeta(im);
        recipe.setIngredient('P', new RecipeChoice.ExactChoice(resistance));
        recipe.setIngredient('s', Material.SPONGE);
        Bukkit.getServer().addRecipe(recipe);
    }
}
