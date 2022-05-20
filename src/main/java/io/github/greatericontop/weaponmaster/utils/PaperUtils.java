package io.github.greatericontop.weaponmaster.utils;

/*
    Copyright (C) 2021 greateric.

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

import io.github.greatericontop.weaponmaster.WeaponMasterMain;
import org.bukkit.Server;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PaperUtils {

    public static boolean HAS_PAPER = false;
    public static void setHasPaperStatus(WeaponMasterMain plugin) {
        try {
            Class serverClass = plugin.getServer().getClass();
            Method paperExclusiveMethod = serverClass.getMethod("get");
        } catch (NoSuchMethodException e) {
            HAS_PAPER = false;
        }
    }

    public static void sendActionBar(Player player, String text) {
        Method sendActionBarMethod = null;
        try {
            sendActionBarMethod = player.getClass().getMethod("sendActionBar", String.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        try {
            sendActionBarMethod.invoke(player, text);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

    }

}
