package io.github.greatericontop.weaponmaster;

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

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WeaponMasterCommandTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            List<String> mainCommands = Arrays.asList("attributemodifier", "illegalstack", "forceenchant", "addpotioneffect");
            ArrayList<String> result = StringUtil.copyPartialMatches(args[0], mainCommands, new ArrayList<String>(mainCommands.size()));
            return result;
        }

        if (args[0].equals("attributemodifier")) {
            if (args.length == 2) {
                return Arrays.asList("<attribute name>");
            }
            if (args.length == 3) {
                return Arrays.asList("<operation>", "ADD_NUMBER", "ADD_SCALAR", "MULTIPLY_SCALAR_1");
            }
            if (args.length == 4) {
                return Arrays.asList("<double>");
            }
            if (args.length == 5) {
                List<String> commands = Arrays.asList("HEAD", "CHEST", "LEGS", "FEET", "HAND", "OFF_HAND");
                ArrayList<String> result = StringUtil.copyPartialMatches(args[4], commands, new ArrayList<String>(commands.size()));
                result.add(0, "<slot>");
                return result;
            }
            if (args.length == 6) {
                return Arrays.asList("[<uuid>]");
            }
        }

        if (args[0].equals("illegalstack")) {
            if (args.length == 2) {
                List<String> commands = Arrays.asList("0", "1", "16", "64", "99", "max");
                ArrayList<String> result = StringUtil.copyPartialMatches(args[1], commands, new ArrayList<String>(commands.size()));
                result.add(0, " <number>");
                return result;
            }
        }

        if (args[0].equals("forceenchant")) {
            if (args.length == 2) {
                return Arrays.asList("<enchantment>");
            }
            if (args.length == 3) {
                List<String> commands = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "max");
                ArrayList<String> result = StringUtil.copyPartialMatches(args[2], commands, new ArrayList<String>(commands.size()));
                result.add(0, " <level>");
                return result;
            }
        }

        if (args[0].equals("addpotioneffect")) {
            if (args.length == 2) {
                return Arrays.asList("<effect type>");
            }
            if (args.length == 3) {
                List<String> commands = Arrays.asList("0.05", "30", "180", "480", "max");
                ArrayList<String> result = StringUtil.copyPartialMatches(args[2], commands, new ArrayList<String>(commands.size()));
                result.add(0, " <duration (seconds)>");
                return result;
            }
            if (args.length == 4) {
                List<String> commands = Arrays.asList("0", "1", "2", "3", "4", "max");
                ArrayList<String> result = StringUtil.copyPartialMatches(args[3], commands, new ArrayList<String>(commands.size()));
                result.add(0, " <amplifier>");
                return result;
            }
        }

        return null;
    }

}
