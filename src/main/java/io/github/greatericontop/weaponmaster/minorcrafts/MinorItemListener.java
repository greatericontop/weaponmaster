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

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import io.github.greatericontop.weaponmaster.utils.Util;
import io.github.greatericontop.weaponmaster.utils.VersionSpecificUtil;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class MinorItemListener implements Listener {

    private final Set<UUID> withers = new HashSet<>();

    private final Random rnd = new Random();
    private final MinorItems minorItems;
    private final Util util;
    private final WeaponMasterMain plugin;
    public MinorItemListener(WeaponMasterMain plugin) {
        this.minorItems = new MinorItems();
        this.plugin = plugin;
        this.util = new Util(plugin);
    }

    @EventHandler()
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntityType() == EntityType.ELDER_GUARDIAN) {
            if (Math.random() < plugin.getConfig().getDouble("rng.leviathanHeart")) {
                ItemStack leviathan = minorItems.generateLeviathanHeartItemStack();
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), leviathan);
                Player killer = event.getEntity().getKiller();
                if (killer != null) {
                    killer.sendMessage("§eRARE DROP! " + minorItems.LEVIATHAN_HEART_NAME);
                }
            }
        } else if (event.getEntityType() == EntityType.PIGLIN_BRUTE) {
            if (Math.random() < plugin.getConfig().getDouble("rng.coreStaff")) {
                ItemStack core = minorItems.generateCoreStaffItemStack();
                Item coreEntity = event.getEntity().getWorld().dropItemNaturally(event.getEntity().getEyeLocation(), core);
                coreEntity.setInvulnerable(true);
                Player killer = event.getEntity().getKiller();
                if (killer != null) {
                    killer.sendMessage("§eRARE DROP! " + minorItems.CORE_STAFF_NAME);
                }
            }
        } else if (event.getEntityType() == EntityType.CAVE_SPIDER) {
            if (Math.random() < plugin.getConfig().getDouble("rng.silkyString")) {
                ItemStack silky = minorItems.generateSilkyStringItemStack();
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), silky);
                Player killer = event.getEntity().getKiller();
                if (killer != null) {
                    killer.sendMessage("§eRARE DROP! " + minorItems.SILKY_STRING_NAME);
                }
            }
        } else if (event.getEntityType() == EntityType.EVOKER) {
            if (Math.random() < plugin.getConfig().getDouble("rng.lifeCore")) {
                ItemStack life = minorItems.generateLifeCoreItemStack();
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), life);
                Player killer = event.getEntity().getKiller();
                if (killer != null) {
                    killer.sendMessage("§eRARE DROP! " + minorItems.LIFE_CORE_NAME);
                }
            }
        } else if (event.getEntityType() == EntityType.WITHER) {
            if (Math.random() < plugin.getConfig().getDouble("rng.expertSeal")) {
                ItemStack seal = minorItems.generateExpertSealItemStack();
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), seal);
                Player killer = event.getEntity().getKiller();
                if (killer != null) {
                    killer.sendMessage("§eRARE DROP! " + minorItems.EXPERT_SEAL_NAME);
                }
            }
        } else if (event.getEntityType() == EntityType.ENDERMITE) {
            if (Math.random() < plugin.getConfig().getDouble("rng.endArtifact")) {
                ItemStack endArtifact = minorItems.generateEndArtifactItemStack();
                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), endArtifact);
                Player killer = event.getEntity().getKiller();
                if (killer != null) {
                    killer.sendMessage("§eRARE DROP! " + minorItems.END_ARTIFACT_NAME);
                }
            }
        }

        if (event.getEntityType() == EntityType.PLAYER) {
            Player player = (Player) event.getEntity();
            VersionSpecificUtil.modifyAttributeModifier(player.getAttribute(Attribute.GENERIC_MAX_HEALTH), minorItems.ENERGY_MODIFIER_UUID, -4.0, 0.0, 12.0);
        }
    }

    @EventHandler()
    public void onEat(PlayerItemConsumeEvent event) {
        if (!util.checkFor(event.getItem(), 0, "id: MAGIC_ENERGY_BAR"))  return;
        Player player = event.getPlayer();
        VersionSpecificUtil.modifyAttributeModifier(player.getAttribute(Attribute.GENERIC_MAX_HEALTH), minorItems.ENERGY_MODIFIER_UUID, 2.0, 0.0, 12.0);
        player.sendMessage("§3Successfully gained a heart!");
    }

    @EventHandler()
    public void onExpertSeal(InventoryClickEvent event) {
        if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR)  return;
        if (event.getCursor() == null || event.getCursor().getType() == Material.AIR)  return;
        Player player = (Player) event.getWhoClicked();
        if (!util.checkFor(event.getCursor(), 0, "id: EXPERT_SEAL"))  return;
        ItemMeta targetItem = event.getCurrentItem().getItemMeta();
        if (targetItem == null || !targetItem.hasEnchants()) {
            player.sendMessage("§cYou can't use Expert Seal on this item!");
            return;
        }
        final NamespacedKey key = new NamespacedKey(plugin, "expert_seal");
        if (targetItem.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
            player.sendMessage("§cYou can only upgrade once!");
            return;
        }
        Map<Enchantment, Integer> enchants = targetItem.getEnchants();
        for (Enchantment enchant : enchants.keySet()) {
            targetItem.addEnchant(enchant, enchants.get(enchant) + 1, true);
        }
        targetItem.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
        event.getCurrentItem().setItemMeta(targetItem);
        event.setCancelled(true);
        player.updateInventory();
        if (event.getCursor().getAmount() > 1) {
            ItemStack newExpertSeals = event.getCursor();
            newExpertSeals.setAmount(newExpertSeals.getAmount() - 1);
            event.setCursor(newExpertSeals);
        } else {
            event.setCursor(new ItemStack(Material.AIR));
        }
        player.sendMessage("§3Success!");
    }

    @EventHandler()
    public void rightClickBlock(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK)  return;
        Player player = event.getPlayer();
        if (util.checkFor(player.getInventory().getItemInMainHand(), 0, "id: SILKY_STRING") ||
                util.checkFor(player.getInventory().getItemInMainHand(), 0, "id: LEVIATHAN_HEART") ||
                util.checkFor(player.getInventory().getItemInMainHand(), 0, "id: DRAGON_HORN")) {
            event.setCancelled(true);
        }
    }

//    @EventHandler()
//    public void onXPBottleThrow(ProjectileLaunchEvent event) {
//        if (!(event.getEntity() instanceof ThrownExpBottle))  return;
//        ThrownExpBottle bottle = (ThrownExpBottle) event.getEntity();
//        ItemStack item = bottle.getItem();
//        if (util.checkFor(item, 0, "id: SUPER_XP_BOTTLE")) {
//            thrownXPBottles.add(bottle.getUniqueId());
//        }
//    }

    @EventHandler()
    public void onXPBottleSmash(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof ThrownExpBottle))  return;
        ThrownExpBottle bottle = (ThrownExpBottle) event.getEntity();
        ItemStack item = bottle.getItem();
        if (util.checkFor(item, 0, "id: SUPER_XP_BOTTLE")) {
            ExperienceOrb orb = event.getEntity().getWorld().spawn(event.getEntity().getLocation(), ExperienceOrb.class);
            orb.setExperience(1000 + rnd.nextInt(241));
            // 160 bottles worth of xp (161 total since bottle itself is not canceled)
        }
    }

    @EventHandler()
    public void onDeepslateCoalBreak(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.DEEPSLATE_COAL_ORE)  return;
        if (event.getExpToDrop() == 0)  return; // if broken with silk touch or the incorrect tool
        if (Math.random() < plugin.getConfig().getDouble("rng.plutonium")) {
            ItemStack item = minorItems.generateCrudePlutoniumItemStack();
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation().add(0.5, 0.5, 0.5), item);
            event.getPlayer().sendMessage("§eRARE DROP! " + minorItems.CRUDE_PLUTONIUM_NAME);
        }
    }

    private static final double RANGE = 80.0;
    @EventHandler()
    public void onWitherSpawn(EntitySpawnEvent event) {
        if (event.getEntityType() != EntityType.WITHER)  return;
        Wither wither = (Wither) event.getEntity();
        withers.add(wither.getUniqueId());
        new BukkitRunnable() {
            public void run() {
                if (wither.isDead()) {
                    // if the wither died and this is still running, profit!
                    wither.getWorld().dropItemNaturally(wither.getLocation(), minorItems.generateExpertSealItemStack());
                    if (wither.getKiller() != null) {
                        wither.getKiller().sendMessage("§eW§co§ew§c! §bYou are truly an expert! You have been given " + minorItems.EXPERT_SEAL_NAME);
                    }
                    withers.remove(wither.getUniqueId());
                    this.cancel();
                    return;
                }
                Collection<Entity> nearbyPlayers = wither.getWorld().getNearbyEntities(wither.getLocation(), RANGE, RANGE, RANGE, e -> e instanceof Player);
                if (nearbyPlayers.isEmpty()) {
                    // prevents issues if the wither unloads
                    // also causes a death to cancel the reward
                    withers.remove(wither.getUniqueId());
                    this.cancel();
                    return;
                }
                if (nearbyPlayers.size() > 1) {
                    // must be solo
                    withers.remove(wither.getUniqueId());
                    this.cancel();
                    return;
                }
                Player player = (Player) nearbyPlayers.stream().iterator().next();
                ItemStack[] armor = player.getInventory().getArmorContents();
                if ((armor[0] != null && armor[0].getType() != Material.AIR)
                        || (armor[1] != null && armor[1].getType() != Material.AIR)
                        || (armor[2] != null && armor[2].getType() != Material.AIR)
                        || (armor[3] != null && armor[3].getType() != Material.AIR)
                ) {
                    // if any player is wearing armor, cancel
                    this.cancel();
                    return;
                }
                // if the wither is stuck in bedrock, teleport it to the player
                for (int dx = -2; dx <= 2; dx++) {
                    for (int dy = -3; dy <= 3; dy++) {
                        for (int dz = -2; dz <= 2; dz++) {
                            if (wither.getLocation().add(dx, dy, dz).getBlock().getType() == Material.BEDROCK) {
                                wither.teleport(player);
                            }
                        }
                    }
                }
            }
        }.runTaskTimer(plugin, 1L, 1L);
    }

    @EventHandler()
    public void onWitherExplosionDamage(EntityDamageEvent event) {
        if (event.getEntityType() != EntityType.WITHER)  return;
        if (!withers.contains(event.getEntity().getUniqueId()))  return;
        if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION && event.getCause() != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)  return;
        event.setDamage(event.getDamage() * 0.25); // explosives damage is nerfed by 1/4
    }

}