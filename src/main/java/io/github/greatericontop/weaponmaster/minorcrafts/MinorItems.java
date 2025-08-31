

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
    public final String CRUDE_PLUTONIUM_NAME = "§8Crude Plutonium";
    public final String WEAPONS_GRADE_PLUTONIUM_NAME = "§8Weapons-Grade Plutonium";
    public final String MASTER_DYE_NAME = "§6Master Dye";
    public final String WITHER_DYE_NAME = "§8Wither Dye";
    public final String DIAMOND_DYE_NAME = "§bDiamond Dye";
    public final String EMERALD_DYE_NAME = "§aEmerald Dye";
    public final String CRYSTAL_DYE_NAME = "§dCrystal Dye";
    public final String LAPIS_DYE_NAME = "§9Lapis Dye";
    public final String DARK_DIAMOND_DYE_NAME = "§2Dark Diamond Dye";
    public final String GOLD_DYE_NAME = "§6Gold Dye";
    public final String REDSTONE_DYE_NAME = "§cRedstone Dye";
    public final String BLOOD_DYE_NAME = "§4Blood Dye";
    public final String LEVIATHAN_DYE_NAME = "§3Leviathan Dye";
    public final String EXPERT_DYE_NAME = "§6Expert Dye";
    public final String DRAGON_DYE_NAME = "§5Dragon Dye";
    public final String DIAMOND_APEX_NAME = "§bDiamond Apex";
    public final String EMERALD_APEX_NAME = "§aEmerald Apex";
    public final String REDSTONE_APEX_NAME = "§cRedstone Apex";

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
    public List<String> CRUDE_PLUTONIUM_LORE = new ArrayList<>();
    public List<String> WEAPONS_GRADE_PLUTONIUM_LORE = new ArrayList<>();
    public List<String> MASTER_DYE_LORE = new ArrayList<>();
    public List<String> WITHER_DYE_LORE = new ArrayList<>();
    public List<String> DIAMOND_DYE_LORE = new ArrayList<>();
    public List<String> EMERALD_DYE_LORE = new ArrayList<>();
    public List<String> CRYSTAL_DYE_LORE = new ArrayList<>();
    public List<String> LAPIS_DYE_LORE = new ArrayList<>();
    public List<String> DARK_DIAMOND_DYE_LORE = new ArrayList<>();
    public List<String> GOLD_DYE_LORE = new ArrayList<>();
    public List<String> REDSTONE_DYE_LORE = new ArrayList<>();
    public List<String> BLOOD_DYE_LORE = new ArrayList<>();
    public List<String> LEVIATHAN_DYE_LORE = new ArrayList<>();
    public List<String> EXPERT_DYE_LORE = new ArrayList<>();
    public List<String> DRAGON_DYE_LORE = new ArrayList<>();
    public List<String> DIAMOND_APEX_LORE = new ArrayList<>();
    public List<String> EMERALD_APEX_LORE = new ArrayList<>();
    public List<String> REDSTONE_APEX_LORE = new ArrayList<>();

    public static final String WITHER_DYE_KEY = "§8Dark Gray Color";
    public static final String DIAMOND_DYE_KEY = "§bAqua Color";
    public static final String EMERALD_DYE_KEY = "§aGreen Color";
    public static final String CRYSTAL_DYE_KEY = "§dLight Purple Color";
    public static final String LAPIS_DYE_KEY = "§9Blue Color";
    public static final String DARK_DIAMOND_DYE_KEY = "§3Dark Aqua Color";
    public static final String GOLD_DYE_KEY = "§6Gold Color";
    public static final String REDSTONE_DYE_KEY = "§cRed Color";
    public static final String BLOOD_DYE_KEY = "§4Dark Red Color";
    public static final String LEVIATHAN_DYE_KEY = "§9L§3e§9v§3i§9a§3t§9h§3a§9n §3C§9o§3l§9o§3r";
    public static final String EXPERT_DYE_KEY = "§6E§ex§6p§ee§6r§et §6C§eo§6l§eo§6r";
    public static final String DRAGON_DYE_KEY = "§5D§dr§5a§dg§5o§dn §5C§do§5l§do§5r";

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

        CRUDE_PLUTONIUM_LORE.add("id: CRUDE_PLUTONIUM");
        CRUDE_PLUTONIUM_LORE.add("§7It seems pretty radioactive, but I'm not sure if it");
        CRUDE_PLUTONIUM_LORE.add("§7is pure enough to sustain a nuclear reaction.");

        WEAPONS_GRADE_PLUTONIUM_LORE.add("id: WEAPONS_GRADE_PLUTONIUM");
        WEAPONS_GRADE_PLUTONIUM_LORE.add("§7This plutonium is weapons-grade purity.");

        MASTER_DYE_LORE.add("id: MASTER_DYE");
        MASTER_DYE_LORE.add("§eThis dye can change the color of your item to anything!");
        MASTER_DYE_LORE.add("§eUse the §f& §esymbol for color codes.");
        MASTER_DYE_LORE.add("§eUse it wisely, as there is no way to undo this action!");

        WITHER_DYE_LORE.add("id: DYE");
        WITHER_DYE_LORE.add(WITHER_DYE_KEY);
        WITHER_DYE_LORE.add("§7Move this dye onto another item to color it!");
        WITHER_DYE_LORE.add("§eA dark dye infused with the essence of the Wither.");
        WITHER_DYE_LORE.add("§eThis is the base dye. It can be crafted into other colors!");
        DIAMOND_DYE_LORE.add("id: DYE");
        DIAMOND_DYE_LORE.add(DIAMOND_DYE_KEY);
        DIAMOND_DYE_LORE.add("§7Move this dye onto another item to color it!");
        EMERALD_DYE_LORE.add("id: DYE");
        EMERALD_DYE_LORE.add(EMERALD_DYE_KEY);
        EMERALD_DYE_LORE.add("§7Move this dye onto another item to color it!");
        CRYSTAL_DYE_LORE.add("id: DYE");
        CRYSTAL_DYE_LORE.add(CRYSTAL_DYE_KEY);
        CRYSTAL_DYE_LORE.add("§7Move this dye onto another item to color it!");
        LAPIS_DYE_LORE.add("id: DYE");
        LAPIS_DYE_LORE.add(LAPIS_DYE_KEY);
        LAPIS_DYE_LORE.add("§7Move this dye onto another item to color it!");
        DARK_DIAMOND_DYE_LORE.add("id: DYE");
        DARK_DIAMOND_DYE_LORE.add(DARK_DIAMOND_DYE_KEY);
        DARK_DIAMOND_DYE_LORE.add("§7Move this dye onto another item to color it!");
        GOLD_DYE_LORE.add("id: DYE");
        GOLD_DYE_LORE.add(GOLD_DYE_KEY);
        GOLD_DYE_LORE.add("§7Move this dye onto another item to color it!");
        REDSTONE_DYE_LORE.add("id: DYE");
        REDSTONE_DYE_LORE.add(REDSTONE_DYE_KEY);
        REDSTONE_DYE_LORE.add("§7Move this dye onto another item to color it!");
        BLOOD_DYE_LORE.add("id: DYE");
        BLOOD_DYE_LORE.add(BLOOD_DYE_KEY);
        BLOOD_DYE_LORE.add("§7Move this dye onto another item to color it!");
        LEVIATHAN_DYE_LORE.add("id: DYE");
        LEVIATHAN_DYE_LORE.add(LEVIATHAN_DYE_KEY);
        LEVIATHAN_DYE_LORE.add("§7Move this dye onto another item to color it!");
        EXPERT_DYE_LORE.add("id: DYE");
        EXPERT_DYE_LORE.add(EXPERT_DYE_KEY);
        EXPERT_DYE_LORE.add("§7Move this dye onto another item to color it!");
        DRAGON_DYE_LORE.add("id: DYE");
        DRAGON_DYE_LORE.add(DRAGON_DYE_KEY);
        DRAGON_DYE_LORE.add("§7Move this dye onto another item to color it!");

        DIAMOND_APEX_LORE.add("id: DIAMOND_APEX");
        DIAMOND_APEX_LORE.add("§7It can only be extracted from some of the best ores.");
        DIAMOND_APEX_LORE.add("§7This is one of the purest diamonds you've ever seen...");

        EMERALD_APEX_LORE.add("id: EMERALD_APEX");
        EMERALD_APEX_LORE.add("§7It can only be extracted from some of the best ores.");
        EMERALD_APEX_LORE.add("§7This is one of the purest emeralds you've ever seen...");

        REDSTONE_APEX_LORE.add("id: REDSTONE_APEX");
        REDSTONE_APEX_LORE.add("§7It can only be extracted from some of the best ores.");
        REDSTONE_APEX_LORE.add("§7This is one of the purest redstones you've ever seen...");
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
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
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

    public ItemStack generateCrudePlutoniumItemStack() {
        ItemStack stack = new ItemStack(Material.GUNPOWDER, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(CRUDE_PLUTONIUM_NAME);
        iMeta.setLore(CRUDE_PLUTONIUM_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateWeaponsGradePlutoniumItemStack() {
        ItemStack stack = new ItemStack(Material.GUNPOWDER, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
        iMeta.setDisplayName(WEAPONS_GRADE_PLUTONIUM_NAME);
        iMeta.setLore(WEAPONS_GRADE_PLUTONIUM_LORE);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateMasterDye() {
        ItemStack stack = new ItemStack(Material.YELLOW_DYE, 1);
        generateMasterDye(stack);
        return stack;
    }
    public void generateMasterDye(ItemStack stack) {
        stack.setType(Material.YELLOW_DYE);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
        iMeta.setDisplayName(MASTER_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(MASTER_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
    }

    public ItemStack generateWitherDye() {
        ItemStack stack = new ItemStack(Material.GRAY_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(WITHER_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(WITHER_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateDiamondDye() {
        ItemStack stack = new ItemStack(Material.LIGHT_BLUE_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(DIAMOND_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(DIAMOND_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateEmeraldDye() {
        ItemStack stack = new ItemStack(Material.LIME_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(EMERALD_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(EMERALD_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateCrystalDye() {
        ItemStack stack = new ItemStack(Material.MAGENTA_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(CRYSTAL_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(CRYSTAL_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateLapisDye() {
        ItemStack stack = new ItemStack(Material.BLUE_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(LAPIS_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(LAPIS_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateDarkDiamondDye() {
        ItemStack stack = new ItemStack(Material.CYAN_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(DARK_DIAMOND_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(DARK_DIAMOND_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateGoldDye() {
        ItemStack stack = new ItemStack(Material.ORANGE_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(GOLD_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(GOLD_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateRedstoneDye() {
        ItemStack stack = new ItemStack(Material.RED_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(REDSTONE_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(REDSTONE_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateBloodDye() {
        ItemStack stack = new ItemStack(Material.RED_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(BLOOD_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(BLOOD_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateLeviathanDye() {
        ItemStack stack = new ItemStack(Material.CYAN_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(LEVIATHAN_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(LEVIATHAN_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateExpertDye() {
        ItemStack stack = new ItemStack(Material.YELLOW_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(EXPERT_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(EXPERT_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateDragonDye() {
        ItemStack stack = new ItemStack(Material.PURPLE_DYE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(DRAGON_DYE_NAME);
        List<String> lore = new ArrayList<>();
        lore.addAll(DRAGON_DYE_LORE);
        iMeta.setLore(lore);
        iMeta.setMaxStackSize(1);
        stack.setItemMeta(iMeta);
        return stack;
    }

    public ItemStack generateDiamondApexItemStack() {
        ItemStack stack = new ItemStack(Material.DIAMOND, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(DIAMOND_APEX_NAME);
        iMeta.setLore(DIAMOND_APEX_LORE);
        iMeta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateEmeraldApexItemStack() {
        ItemStack stack = new ItemStack(Material.EMERALD, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(EMERALD_APEX_NAME);
        iMeta.setLore(EMERALD_APEX_LORE);
        iMeta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
        stack.setItemMeta(iMeta);
        return stack;
    }
    public ItemStack generateRedstoneApexItemStack() {
        ItemStack stack = new ItemStack(Material.REDSTONE, 1);
        ItemMeta iMeta = stack.getItemMeta();
        iMeta.setDisplayName(REDSTONE_APEX_NAME);
        iMeta.setLore(REDSTONE_APEX_LORE);
        iMeta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
        stack.setItemMeta(iMeta);
        return stack;
    }

}
