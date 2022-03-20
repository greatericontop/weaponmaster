package io.github.greatericontop.weaponmaster;

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

import io.github.greatericontop.weaponmaster.Anduril.AndurilCommand;
import io.github.greatericontop.weaponmaster.Anduril.AndurilItemListener;
import io.github.greatericontop.weaponmaster.ArtemisBow.ArtemisCommand;
import io.github.greatericontop.weaponmaster.ArtemisBow.ArtemisItemListener;
import io.github.greatericontop.weaponmaster.ArtemisBow.ArtemisRecipe;
import io.github.greatericontop.weaponmaster.DeathScythe.ScytheCommand;
import io.github.greatericontop.weaponmaster.DeathScythe.ScytheItemListener;
import io.github.greatericontop.weaponmaster.DragonSword.DragonCommand;
import io.github.greatericontop.weaponmaster.DragonSword.DragonItemListener;
import io.github.greatericontop.weaponmaster.DragonSword.DragonRecipe;
import io.github.greatericontop.weaponmaster.Excalibur.ExcaliburCommand;
import io.github.greatericontop.weaponmaster.Excalibur.ExcaliburItemListener;
import io.github.greatericontop.weaponmaster.Excalibur.ExcaliburRecipe;
import io.github.greatericontop.weaponmaster.Exodus.ExodusCommand;
import io.github.greatericontop.weaponmaster.Exodus.ExodusItemListener;
import io.github.greatericontop.weaponmaster.RPGLauncher.LauncherCommand;
import io.github.greatericontop.weaponmaster.RPGLauncher.RPGItemListener;
import io.github.greatericontop.weaponmaster.RocketStick.RocketCommand;
import io.github.greatericontop.weaponmaster.RocketStick.RocketItemListener;
import io.github.greatericontop.weaponmaster.VampAxe.VampCommand;
import io.github.greatericontop.weaponmaster.VampAxe.VampItemListener;
import io.github.greatericontop.weaponmaster.VampAxe.VampRecipe;
import io.github.greatericontop.weaponmaster.other_crafts.FlaskRecipe;
import io.github.greatericontop.weaponmaster.other_crafts.HideLeviathanRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class WeaponMasterMain extends JavaPlugin {

    private String encryption(String s, byte[] key) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append((char) (s.charAt(i) ^ key[i]));
        }
        return sb.toString();
    }

    @Override
    public void onEnable() {
        // License
        this.saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        byte[] instanceKey = "P2Z3paaFh8mWFJ0VOnzOglqmAeP-xW9VVeykKGPn5SxeXSV1bthIEtenZOur-Heay_DBO3wSBF/UkMhdc9FaobqwOX+U-ZTI1l1R6q-dRNbb5zhXIW1whp-tmGG03vmT".getBytes();
        String instanceName = "greatericWeaponMaster";
        String hostname;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            getLogger().warning("Failed to reach license! Do you have an internet connection?");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        String issuedTo = getConfig().getString("license.issued-to");
        if (issuedTo == null) { issuedTo = "none"; }
        byte[] enc = encryption(instanceName.concat(hostname), instanceKey).getBytes();
        StringBuilder hexencBuilder = new StringBuilder();
        for (byte b : enc) {
            hexencBuilder.append(String.format("%02x", b));
        }
        String hexenc = hexencBuilder.toString();
        if (!getConfig().getString("license.key").equalsIgnoreCase(hexenc)) {
            getLogger().warning("Invalid license! Check license in config.yml");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info("--------------------");
        getLogger().info("#######################################################################################################################");
        getLogger().info("# ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ #");
        getLogger().info("#▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌#");
        getLogger().info("#▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀ ▐░█▀▀▀▀▀▀▀▀▀ #");
        getLogger().info("#▐░▌          ▐░▌       ▐░▌▐░▌          ▐░▌       ▐░▌     ▐░▌     ▐░▌          ▐░▌       ▐░▌     ▐░▌     ▐░▌          #");
        getLogger().info("#▐░▌ ▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌     ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌     ▐░▌     ▐░▌          #");
        getLogger().info("#▐░▌▐░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌     ▐░▌          #");
        getLogger().info("#▐░▌ ▀▀▀▀▀▀█░▌▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌     ▐░▌     ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀█░█▀▀      ▐░▌     ▐░▌          #");
        getLogger().info("#▐░▌       ▐░▌▐░▌     ▐░▌  ▐░▌          ▐░▌       ▐░▌     ▐░▌     ▐░▌          ▐░▌     ▐░▌       ▐░▌     ▐░▌          #");
        getLogger().info("#▐░█▄▄▄▄▄▄▄█░▌▐░▌      ▐░▌ ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌       ▐░▌     ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌      ▐░▌  ▄▄▄▄█░█▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ #");
        getLogger().info("#▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌     ▐░▌     ▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌#");
        getLogger().info("# ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀       ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀ #");
        getLogger().info("#######################################################################################################################");
        getLogger().info("");
        getLogger().info("WeaponMaster");
        getLogger().info("");
        getLogger().info("License verified!");
        getLogger().info("Copyright (C) greateric 2022. Do not distribute!");
        getLogger().info("Initializing WeaponMaster by greateric");
        getLogger().info("---> /rpgl /vampaxe /scythe /dragonsword /artemis /anduril /excalibur");
        getLogger().info("--------------------");

        getCommand("weaponmaster").setExecutor(new WeaponMasterCommand(this));
        // RPG Launcher
        getServer().getPluginManager().registerEvents(new RPGItemListener(this), this);
        getCommand("rpgl").setExecutor(new LauncherCommand());
        // Vamp Axe
        getServer().getPluginManager().registerEvents(new VampItemListener(this), this);
        getCommand("vampaxe").setExecutor(new VampCommand());
        new VampRecipe().regRecipe();
        // Scythe
        getServer().getPluginManager().registerEvents(new ScytheItemListener(this), this);
        getCommand("scythe").setExecutor(new ScytheCommand());
        // Dragon Sword
        getServer().getPluginManager().registerEvents(new DragonItemListener(this), this);
        getCommand("dragonsword").setExecutor(new DragonCommand());
        new DragonRecipe().regRecipe();
        // Artemis Bow
        getServer().getPluginManager().registerEvents(new ArtemisItemListener(this), this);
        getCommand("artemis").setExecutor(new ArtemisCommand());
        new ArtemisRecipe().regRecipe();
        // Anduril
        getCommand("anduril").setExecutor(new AndurilCommand());
        new AndurilItemListener(this).regAndurilRunnable();
        // Excalibur
        getCommand("excalibur").setExecutor(new ExcaliburCommand());
        getServer().getPluginManager().registerEvents(new ExcaliburItemListener(this), this);
        new ExcaliburRecipe().regRecipe();
        // Exodus
        getCommand("exodus").setExecutor(new ExodusCommand());
        getServer().getPluginManager().registerEvents(new ExodusItemListener(this), this);
        // Rocket Stick
        getCommand("rocketstick").setExecutor(new RocketCommand());
        getServer().getPluginManager().registerEvents(new RocketItemListener(this), this);
        // Hide Of Leviathan
        new HideLeviathanRecipe().regRecipe();
        new FlaskRecipe().regRecipe();
        // Custom Item Listener
        getServer().getPluginManager().registerEvents(new CustomItemListener(), this);
        getLogger().info("done");
    }

}
