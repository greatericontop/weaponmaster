package io.github.greatericontop.weaponmaster.items.life_helmet;

import io.github.greatericontop.weaponmaster.other_crafts.CustomItems;
import io.github.greatericontop.weaponmaster.util.Util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

public class LifeHelmetRecipe {

    private final Util util;
    private final CustomItems customItems;
    public LifeHelmetRecipe() {
        this.util = new Util(null);
        this.customItems = new CustomItems();
    }
    
    public void regRecipe() {
        ItemStack lifeHelmet = util.generateMeta(util.LIFE_HELMET_LORE, util.LIFE_HELMET_NAME, Material.DIAMOND_HELMET);
        ShapedRecipe recipe = new ShapedRecipe(NamespacedKey.minecraft("life_helmet"), lifeHelmet);
        recipe.shape("dnd",
                     "dCd",
                     "hTr");
        recipe.setIngredient('d', Material.DIAMOND_BLOCK);
        recipe.setIngredient('n', Material.NETHERITE_BLOCK);
        recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);

        ItemStack healPotion = new ItemStack(Material.LINGERING_POTION, 1);
        PotionMeta im1 = (PotionMeta) healPotion.getItemMeta();
        im1.setBasePotionData(new PotionData(PotionType.INSTANT_HEAL, false, true));
        healPotion.setItemMeta(im1);
        recipe.setIngredient('h', new RecipeChoice.ExactChoice(healPotion));

        ItemStack regenPotion = new ItemStack(Material.LINGERING_POTION, 1);
        PotionMeta im2 = (PotionMeta) regenPotion.getItemMeta();
        im2.setBasePotionData(new PotionData(PotionType.REGEN, true, false));
        regenPotion.setItemMeta(im2);
        recipe.setIngredient('r', new RecipeChoice.ExactChoice(regenPotion));

        recipe.setIngredient('C', new RecipeChoice.ExactChoice(customItems.generateLifeCoreItemStack()));
        Bukkit.getServer().addRecipe(recipe);
    }
}
