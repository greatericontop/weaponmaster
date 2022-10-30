package io.github.greatericontop.weaponmaster.dragonmanager;

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
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class DescentDataManager {
    public final String DESCENT_GUI_NAME = "§7§k~~ ~~§r§b Dragon's Descent §7§k~~ ~~";
    public final int MAX_LEVEL = 5;

    private final WeaponMasterMain plugin;
    private final File descentFile;
    private final YamlConfiguration config;

    public DescentDataManager(WeaponMasterMain plugin) {
        this.plugin = plugin;
        this.descentFile = new File(plugin.getDataFolder(), "descent.yml");
        config = YamlConfiguration.loadConfiguration(descentFile);
    }

    public void saveDataToConfig() {
        try {
            config.save(descentFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear(UUID target) {
        config.set("descent."+target, null);
        // TODO: again IO is precious
        saveDataToConfig();
    }

    public int getDescentUpgradeLevel(UUID target, String upgradeName) {
        String path = "descent."+target+"."+upgradeName;
        return config.getInt(path, 0);
    }

    public void setDescentUpgradeLevel(UUID target, String upgradeName, int newValue) {
        String path = "descent."+target+"."+upgradeName;
        config.set(path, newValue);
        // TODO: don't save EVERY SINGLE MOMENT because io is precious
        // TODO: never gonna give you up, never gonna let you down
        saveDataToConfig();
    }

    /*
     * Return whether the incrementation was successful or not.
     */
    public boolean incrementDescent(UUID target, String upgradeName) {
        // purchases starts off at 0, so we want it 1 for the first purchase, and we increment it permanently later
        int price = getPrice(getPurchases(target) + 1);
        if (getDragonPower(target) < price) {
            return false;
        }
        setPurchases(target, getPurchases(target) + 1);
        setDragonPower(target, getDragonPower(target) - price);
        setDescentUpgradeLevel(target, upgradeName, getDescentUpgradeLevel(target, upgradeName) + 1);
        return true;
    }

    /*
     * 600 shards -> 1 dragon power
     */
    public void updateShards(UUID target) {
        int powerAmount = getShards(target) / 600;
        if (powerAmount > 0) {
            setDragonPower(target, getDragonPower(target) + powerAmount);
            setShards(target, getShards(target) - 600*powerAmount);
        }
    }

    public int getShards(UUID target) {
        return getDescentUpgradeLevel(target, "__shards__");
    }
    public void setShards(UUID target, int value) {
        setDescentUpgradeLevel(target, "__shards__", value);
    }
    public int getDragonPower(UUID target) {
        return getDescentUpgradeLevel(target, "__power__");
    }
    public void setDragonPower(UUID target, int value) {
        setDescentUpgradeLevel(target, "__power__", value);
    }
    public int getPurchases(UUID target) {
        return getDescentUpgradeLevel(target, "__purchases__");
    }
    public void setPurchases(UUID target, int value) {
        setDescentUpgradeLevel(target, "__purchases__", value);
    }

    /*
     * Return the price, which gets more expensive as you have more purchases.
     * :purchase: is the CURRENT number, so if it's the first purchase it should be 1
     */
    public int getPrice(int purchase) {
        if (purchase > 60)  return 6;
        if (purchase > 50)  return 5;
        if (purchase > 40)  return 4;
        if (purchase > 30)  return 3;
        if (purchase > 10)  return 2;
        return 1;
    }

}
