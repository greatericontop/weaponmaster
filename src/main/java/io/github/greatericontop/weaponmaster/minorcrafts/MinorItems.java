

package io.github.greatericontop.weaponmaster.minorcrafts;

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

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MinorItems {

    public final UUID ENERGY_MODIFIER_UUID = UUID.fromString("00000000-1111-0000-0000-c61288850beb");

    public final String LEVIATHAN_HEART_NAME = "§9Heart of Leviathan";
    public final String CORE_STAFF_NAME = "§4Nether Reactor Core";
    public final String FLASK_ICHOR_NAME = "§cFlask of Ichor";
    public final String DRAGON_SCALE_NAME = "§dDragon Scale";
    public final String MAGIC_ENERGY_BAR_NAME = "§eMagic Energy Bar";
    public final String EXPERT_SEAL_NAME = "§6§lExpert Seal";
    public final String DRAGON_WING_NAME = "§dDragon Wing";
    public final String DRAGON_HORN_NAME = "§dDragon Horn";
    public final String SILKY_STRING_NAME = "§5Silky String";
    public final String LIFE_CORE_NAME = "§bLife Core";
    public final String END_ARTIFACT_NAME = "§5End Artifact";
    public final String SUPER_XP_BOTTLE_NAME = "§eSuper XP Bottle";

    public List<String> LEVIATHAN_HEART_LORE = new ArrayList<>();
    public List<String> CORE_STAFF_LORE = new ArrayList<>();
    public List<String> DRAGON_SCALE_LORE = new ArrayList<>();
    public List<String> MAGIC_ENERGY_BAR_LORE = new ArrayList<>();
    public List<String> EXPERT_SEAL_LORE = new ArrayList<>();
    public List<String> DRAGON_WING_LORE = new ArrayList<>();
    public List<String> DRAGON_HORN_LORE = new ArrayList<>();
    public List<String> SILKY_STRING_LORE = new ArrayList<>();
    public List<String> LIFE_CORE_LORE = new ArrayList<>();
    public List<String> END_ARTIFACT_LORE = new ArrayList<>();
    public List<String> SUPER_XP_BOTTLE_LORE = new ArrayList<>();

    public MinorItems() {
        LEVIATHAN_HEART_LORE.add("id: LEVIATHAN_HEART");
        LEVIATHAN_HEART_LORE.add("§9The heart of a sea monster.");
        LEVIATHAN_HEART_LORE.add("§712% chance to drop on elder guardian kills");

        CORE_STAFF_LORE.add("id: CORE_STAFF");
        CORE_STAFF_LORE.add("§9The powerful core from the depths of the Nether.");
        CORE_STAFF_LORE.add("§7Crafted using the finest magic.");

        DRAGON_SCALE_LORE.add("id: DRAGON_SCALE");
        DRAGON_SCALE_LORE.add("§dA magical artifact stolen from the dragon as it was dying.");
        DRAGON_SCALE_LORE.add("§dThis dragon scale carries an immense magical power of the dragon.");
        DRAGON_SCALE_LORE.add("§dIt can be added to other dragon items to multiply their strength.");
        DRAGON_SCALE_LORE.add("§7Can upgrade §fDragon Sword §7and §fDragon Elytra§7.");

        MAGIC_ENERGY_BAR_LORE.add("id: MAGIC_ENERGY_BAR");
        MAGIC_ENERGY_BAR_LORE.add("§9Consume this item to gain an additional heart.");
        MAGIC_ENERGY_BAR_LORE.add("§7You can accumulate up to 6 hearts, but 2 are lost per death.");

        EXPERT_SEAL_LORE.add("id: EXPERT_SEAL");
        EXPERT_SEAL_LORE.add("§9Move this item over another to increase all enchantments");
        EXPERT_SEAL_LORE.add("§9in the target item by 1 level!");
        EXPERT_SEAL_LORE.add("§7Careful! Any item can only be upgraded once!");

        DRAGON_WING_LORE.add("id: DRAGON_WING");
        DRAGON_WING_LORE.add("§dA magical artifact stolen from the dragon as it was dying.");
        DRAGON_WING_LORE.add("§dThe wing of the dragon can be used to craft powerful dragon items.");

        DRAGON_HORN_LORE.add("id: DRAGON_HORN");
        DRAGON_HORN_LORE.add("§dA magical artifact stolen from the dragon as it was dying.");
        DRAGON_HORN_LORE.add("§dThe horn of the dragon can be used to craft powerful dragon items.");

        SILKY_STRING_LORE.add("id: SILKY_STRING");
        SILKY_STRING_LORE.add("§dA rare drop from a naturally-spawning spider.");
        SILKY_STRING_LORE.add("§dA spider might have a rare mutation, but you can't tell...");

        LIFE_CORE_LORE.add("id: LIFE_CORE");
        LIFE_CORE_LORE.add("§9The secrets of life are stored in this core.");
        LIFE_CORE_LORE.add("§9It was created with the power of the Evoker.");

        END_ARTIFACT_LORE.add("id: END_ARTIFACT");
        END_ARTIFACT_LORE.add("§5A mysterious artifact from the End. It is said to have");
        END_ARTIFACT_LORE.add("§5the power to harness the energy of the dimension.");

        SUPER_XP_BOTTLE_LORE.add("id: SUPER_XP_BOTTLE");
        SUPER_XP_BOTTLE_LORE.add("§3A bottle filled with a §elot §3of experience.");
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
        iMeta.addEnchant(Enchantment.SOUL_SPEED, 1, false);
        iMeta.setDisplayName(CORE_STAFF_NAME);
        iMeta.setLore(CORE_STAFF_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateFlaskIchorItemStack() {
        ItemStack stack = new ItemStack(Material.SPLASH_POTION, 1);
        PotionMeta iMeta = (PotionMeta) stack.getItemMeta();
        iMeta.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 1, 3), true);
        iMeta.setColor(Color.MAROON);
        iMeta.setDisplayName(FLASK_ICHOR_NAME);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateDragonScaleItemStack() {
        ItemStack stack = new ItemStack(Material.PRISMARINE_SHARD, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(DRAGON_SCALE_NAME);
        List<String> lore = new ArrayList<String>();
        lore.addAll(DRAGON_SCALE_LORE);
        lore.add(String.format("§7#%s", UUID.randomUUID())); // make similar items unstackable - they are bulk deleted in anvils
        iMeta.setLore(lore);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateMagicEnergyBarItemStack() {
        ItemStack stack = new ItemStack(Material.COOKIE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(MAGIC_ENERGY_BAR_NAME);
        iMeta.setLore(MAGIC_ENERGY_BAR_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateExpertSealItemStack() {
        ItemStack stack = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(EXPERT_SEAL_NAME);
        List<String> lore = new ArrayList<String>();
        lore.addAll(EXPERT_SEAL_LORE);
        lore.add(String.format("§7#%s", UUID.randomUUID()));
        iMeta.setLore(lore);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateDragonWingItemStack() {
        ItemStack stack = new ItemStack(Material.PHANTOM_MEMBRANE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(DRAGON_WING_NAME);
        iMeta.setLore(DRAGON_WING_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateDragonHornItemStack() {
        ItemStack stack = new ItemStack(Material.END_ROD, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(DRAGON_HORN_NAME);
        iMeta.setLore(DRAGON_HORN_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateSilkyStringItemStack() {
        ItemStack stack = new ItemStack(Material.STRING, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(SILKY_STRING_NAME);
        iMeta.setLore(SILKY_STRING_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateLifeCoreItemStack() {
        ItemStack stack = new ItemStack(Material.TOTEM_OF_UNDYING, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(LIFE_CORE_NAME);
        iMeta.setLore(LIFE_CORE_LORE);
        iMeta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateEndArtifactItemStack() {
        ItemStack stack = new ItemStack(Material.ENDER_EYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(END_ARTIFACT_NAME);
        iMeta.setLore(END_ARTIFACT_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateSuperXPBottleItemStack() {
        ItemStack stack = new ItemStack(Material.EXPERIENCE_BOTTLE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(SUPER_XP_BOTTLE_NAME);
        iMeta.setLore(SUPER_XP_BOTTLE_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

}
