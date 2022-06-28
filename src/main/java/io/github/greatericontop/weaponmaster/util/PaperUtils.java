package io.github.greatericontop.weaponmaster.utils;

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
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PaperUtils {

    public final WeaponMasterMain plugin;
    public boolean WARN_NO_PAPER = false;
    public boolean HAS_PAPER = false;
    public PaperUtils(WeaponMasterMain plugin) {
        this.plugin = plugin;
        try {
            Class.forName("com.destroystokyo.paper.event.server.ServerTickStartEvent");
            this.HAS_PAPER = true;
        } catch (ClassNotFoundException e) {
            this.HAS_PAPER = false;
        }
        this.WARN_NO_PAPER = plugin.getConfig().getBoolean("warnOnNoPaper");
    }

    private void sendActionBar(Player player, String text) {
        Method sendActionBarMethod = null;
        try {
            sendActionBarMethod = player.getClass().getMethod("sendActionBar", String.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        try {
            sendActionBarMethod.invoke(player, text);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void sendActionBar(Player player, String text, boolean sendTextIfUnavailable) {
        if (!HAS_PAPER) {
            if (WARN_NO_PAPER) {
                player.sendMessage("§cPaper methods not detected! Some features have been disabled. (To suppress this warning check config.yml)");
            }
            if (sendTextIfUnavailable) {
                player.sendMessage(text);
            }
            return;
        }
        sendActionBar(player, text);
    }

}
