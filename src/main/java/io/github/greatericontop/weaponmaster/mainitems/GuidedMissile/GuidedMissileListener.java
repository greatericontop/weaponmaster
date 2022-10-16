package io.github.greatericontop.weaponmaster.mainitems.GuidedMissile;

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
import org.bukkit.Bukkit;
import org.bukkit.FluidCollisionMode;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.RayTraceResult;

import java.util.HashMap;
import java.util.Map;

public class GuidedMissileListener extends BukkitRunnable {
    private final double MAX_DISTANCE = 96.0;
    private final double RAY_SIZE = 2.5;
    private final int TICKS_TO_LOCK = 45;

    private enum LockState {
        NONE,
        LOCKING,
        LOCKED,
    }

    // every tick pointing towards a target increases :ticksOnTarget: by 1
    // TODO: find balanced value other than 25 & tolerances
    private final Map<Player, LockState> lockStates = new HashMap<>();
    private final Map<Player, LivingEntity> targets = new HashMap<>();
    private final Map<Player, Integer> ticksOnTarget = new HashMap<>();

    private final WeaponMasterMain plugin;
    private final Util util;
    public GuidedMissileListener(WeaponMasterMain plugin) {
        this.plugin = plugin;
        util = new Util(plugin);
    }

    private LockState getLockState(Player player) {
        return lockStates.getOrDefault(player, LockState.NONE);
    }
    private String lockActionBarMessage(int ticks) {
        int x = (ticks * 4) / TICKS_TO_LOCK; // 0 ~ 3 locking, 4 locked
        switch (x) {
            case 0:
                return "§a<§7<<<< §eLocking! §7>>>>§a>";
            case 1:
                return "§a<<§7<<< §eLocking! §7>>>§a>>";
            case 2:
                return "§a<<<§7<< §eLocking! §7>>§a>>>";
            default:
                return "§a<<<<§7< §eLocking! §7>§a>>>>";
        }
    }

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.hasPermission("weaponmaster.guidedmissile.use")) { continue; }
            if (!(util.checkForGuidedMissile(player.getInventory().getItemInMainHand()))) { continue; }

            Location loc = player.getEyeLocation();
            RayTraceResult ray = loc.getWorld().rayTrace(
                    loc, loc.getDirection(), MAX_DISTANCE,
                    FluidCollisionMode.NEVER, true, RAY_SIZE, e -> e instanceof LivingEntity);

            // if there's no/invalid hit, then obviously no target
            // if we're already locking/locked and the target is different, no target
            if (ray == null || ray.getHitEntity() == null ||
                    (!ray.getHitEntity().equals(targets.get(player)) && getLockState(player) != LockState.NONE)) {
                if (getLockState(player) != LockState.NONE) {
                    lockStates.put(player, LockState.NONE);
                    plugin.paperUtils.sendActionBar(player, "§cTarget lost!", true);
                }
                continue;
            }

            LivingEntity target = (LivingEntity) ray.getHitEntity();

            if (getLockState(player) == LockState.LOCKED) {
                plugin.paperUtils.sendActionBar(player, "§c<<<<< §aLocked! §c>>>>>", true);
                player.sendMessage(String.format("§7[Debug] Target: %s", target.getType().getName()));
            } else if (getLockState(player) == LockState.NONE) {
                // new lock acquired
                lockStates.put(player, LockState.LOCKING);
                targets.put(player, target);
                ticksOnTarget.put(player, 0);
                plugin.paperUtils.sendActionBar(player, lockActionBarMessage(0), true);
            } else if (getLockState(player) == LockState.LOCKING) {
                // update current lock
                ticksOnTarget.put(player, ticksOnTarget.get(player) + 1);
                if (ticksOnTarget.get(player) >= TICKS_TO_LOCK) {
                    lockStates.put(player, LockState.LOCKED);
                    plugin.paperUtils.sendActionBar(player, "§c<<<<< §aLocked! §c>>>>>", true);
                } else {
                    plugin.paperUtils.sendActionBar(player, lockActionBarMessage(ticksOnTarget.get(player)), true);
                }
            }
        }
    }

}
