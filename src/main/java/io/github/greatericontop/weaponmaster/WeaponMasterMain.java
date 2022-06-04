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
import io.github.greatericontop.weaponmaster.Anduril.AndurilRecipe;
import io.github.greatericontop.weaponmaster.ArtemisBow.ArtemisCommand;
import io.github.greatericontop.weaponmaster.ArtemisBow.ArtemisItemListener;
import io.github.greatericontop.weaponmaster.ArtemisBow.ArtemisRecipe;
import io.github.greatericontop.weaponmaster.AtomBomb.AtomCommand;
import io.github.greatericontop.weaponmaster.AtomBomb.AtomItemListener;
import io.github.greatericontop.weaponmaster.CavemanSword.CavemanCommand;
import io.github.greatericontop.weaponmaster.CavemanSword.CavemanItemListener;
import io.github.greatericontop.weaponmaster.CavemanSword.CavemanRecipe;
import io.github.greatericontop.weaponmaster.CopperSword.CopperSwordCommand;
import io.github.greatericontop.weaponmaster.CopperSword.CopperSwordListener;
import io.github.greatericontop.weaponmaster.CopperSword.CopperSwordRecipe;
import io.github.greatericontop.weaponmaster.DeathScythe.ScytheCommand;
import io.github.greatericontop.weaponmaster.DeathScythe.ScytheItemListener;
import io.github.greatericontop.weaponmaster.DeathScythe.ScytheRecipe;
import io.github.greatericontop.weaponmaster.DragonArmor.DragonArmorCommand;
import io.github.greatericontop.weaponmaster.DragonArmor.DragonArmorListener;
import io.github.greatericontop.weaponmaster.DragonArmor.DragonArmorRecipe;
import io.github.greatericontop.weaponmaster.DragonSword.DragonCommand;
import io.github.greatericontop.weaponmaster.DragonSword.DragonItemListener;
import io.github.greatericontop.weaponmaster.DragonSword.DragonRecipe;
import io.github.greatericontop.weaponmaster.DragonSword.DragonUpgradeListener;
import io.github.greatericontop.weaponmaster.Excalibur.ExcaliburCommand;
import io.github.greatericontop.weaponmaster.Excalibur.ExcaliburItemListener;
import io.github.greatericontop.weaponmaster.Excalibur.ExcaliburRecipe;
import io.github.greatericontop.weaponmaster.Exodus.ExodusCommand;
import io.github.greatericontop.weaponmaster.Exodus.ExodusItemListener;
import io.github.greatericontop.weaponmaster.Exodus.ExodusRecipe;
import io.github.greatericontop.weaponmaster.Fireball.FireballCommand;
import io.github.greatericontop.weaponmaster.Fireball.FireballListener;
import io.github.greatericontop.weaponmaster.Fireball.FireballRecipe;
import io.github.greatericontop.weaponmaster.Helios.HeliosCommand;
import io.github.greatericontop.weaponmaster.Helios.HeliosItemListener;
import io.github.greatericontop.weaponmaster.Helios.HeliosRecipe;
import io.github.greatericontop.weaponmaster.HermesBoots.HermesCommand;
import io.github.greatericontop.weaponmaster.HermesBoots.HermesItemListener;
import io.github.greatericontop.weaponmaster.HermesBoots.HermesRecipe;
import io.github.greatericontop.weaponmaster.LifeHelmet.LifeHelmetCommand;
import io.github.greatericontop.weaponmaster.LifeHelmet.LifeHelmetListener;
import io.github.greatericontop.weaponmaster.LifeHelmet.LifeHelmetRecipe;
import io.github.greatericontop.weaponmaster.MinerBlessing.MinerCommand;
import io.github.greatericontop.weaponmaster.MinerBlessing.MinerItemListener;
import io.github.greatericontop.weaponmaster.MinerBlessing.MinerRecipe;
import io.github.greatericontop.weaponmaster.NetheriteStaff.NetheriteStaffCommand;
import io.github.greatericontop.weaponmaster.NetheriteStaff.NetheriteStaffListener;
import io.github.greatericontop.weaponmaster.NetheriteStaff.NetheriteStaffRecipe;
import io.github.greatericontop.weaponmaster.NinjaBow.NinjaCommand;
import io.github.greatericontop.weaponmaster.NinjaBow.NinjaItemListener;
import io.github.greatericontop.weaponmaster.NinjaBow.NinjaRecipe;
import io.github.greatericontop.weaponmaster.PilotSword.PilotCommand;
import io.github.greatericontop.weaponmaster.PilotSword.PilotItemListener;
import io.github.greatericontop.weaponmaster.PilotSword.PilotRecipe;
import io.github.greatericontop.weaponmaster.PoseidonTrident.TridentCommand;
import io.github.greatericontop.weaponmaster.PoseidonTrident.TridentListener;
import io.github.greatericontop.weaponmaster.PoseidonTrident.TridentRecipe;
import io.github.greatericontop.weaponmaster.RPGLauncher.LauncherCommand;
import io.github.greatericontop.weaponmaster.RPGLauncher.RPGItemListener;
import io.github.greatericontop.weaponmaster.NapalmMissile.NapalmCommand;
import io.github.greatericontop.weaponmaster.NapalmMissile.NapalmItemListener;
import io.github.greatericontop.weaponmaster.RocketStick.RocketCommand;
import io.github.greatericontop.weaponmaster.RocketStick.RocketItemListener;
import io.github.greatericontop.weaponmaster.Scylla.ScyllaCommand;
import io.github.greatericontop.weaponmaster.Scylla.ScyllaItemListener;
import io.github.greatericontop.weaponmaster.Scylla.ScyllaRecipe;
import io.github.greatericontop.weaponmaster.ShreddedAxe.ShreddedCommand;
import io.github.greatericontop.weaponmaster.ShreddedAxe.ShreddedListener;
import io.github.greatericontop.weaponmaster.SniperRifle.SniperCommand;
import io.github.greatericontop.weaponmaster.SniperRifle.SniperItemListener;
import io.github.greatericontop.weaponmaster.ValkyrieAxe.ValkyrieCommand;
import io.github.greatericontop.weaponmaster.ValkyrieAxe.ValkyrieItemListener;
import io.github.greatericontop.weaponmaster.ValkyrieAxe.ValkyrieRecipe;
import io.github.greatericontop.weaponmaster.VampAxe.VampCommand;
import io.github.greatericontop.weaponmaster.VampAxe.VampItemListener;
import io.github.greatericontop.weaponmaster.VampAxe.VampRecipe;
import io.github.greatericontop.weaponmaster.WarlockPants.WarlockCommand;
import io.github.greatericontop.weaponmaster.WarlockPants.WarlockItemListener;
import io.github.greatericontop.weaponmaster.WarlockPants.WarlockRecipe;
import io.github.greatericontop.weaponmaster.dragon_manager.FightManager;
import io.github.greatericontop.weaponmaster.other_crafts.*;
import io.github.greatericontop.weaponmaster.utils.PaperUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class WeaponMasterMain extends JavaPlugin {

    public PaperUtils paperUtils = null;
    public FightManager dragonManager = null;

    @Override
    public void onEnable() {
        long t = System.currentTimeMillis();

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
        getLogger().info("Copyright (C) greateric 2021-2022. Licensed under GPL v3.");
        getLogger().info("Initializing WeaponMaster by greateric");
        getLogger().info("--------------------");

        // Config
        this.saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        this.paperUtils = new PaperUtils(this);

        getCommand("weaponmaster").setExecutor(new WeaponMasterCommand(this));
        getCommand("weaponmaster").setTabCompleter(new WeaponMasterCommandTabCompleter());
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
        new ScytheRecipe().regRecipe();
        // Dragon Sword
        getServer().getPluginManager().registerEvents(new DragonItemListener(this), this);
        getCommand("dragonsword").setExecutor(new DragonCommand());
        new DragonRecipe().regRecipe();
        getServer().getPluginManager().registerEvents(new DragonUpgradeListener(this), this);
        // Artemis Bow
        getServer().getPluginManager().registerEvents(new ArtemisItemListener(this), this);
        getCommand("artemis").setExecutor(new ArtemisCommand());
        new ArtemisRecipe().regRecipe();
        // Anduril
        getCommand("anduril").setExecutor(new AndurilCommand());
        new AndurilItemListener(this).regAndurilRunnable();
        new AndurilRecipe().regRecipe();
        // Excalibur
        getCommand("excalibur").setExecutor(new ExcaliburCommand());
        getServer().getPluginManager().registerEvents(new ExcaliburItemListener(this), this);
        new ExcaliburRecipe().regRecipe();
        // Exodus
        getCommand("exodus").setExecutor(new ExodusCommand());
        getServer().getPluginManager().registerEvents(new ExodusItemListener(this), this);
        new ExodusRecipe().regRecipe();
        // Rocket Stick
        getCommand("rocketstick").setExecutor(new RocketCommand());
        getServer().getPluginManager().registerEvents(new RocketItemListener(this), this);
        // Helios
        getCommand("helios").setExecutor(new HeliosCommand());
        getServer().getPluginManager().registerEvents(new HeliosItemListener(this), this);
        new HeliosRecipe().regRecipe();
        // Scylla's Chestplate
        getCommand("scylla").setExecutor(new ScyllaCommand());
        getServer().getPluginManager().registerEvents(new ScyllaItemListener(this), this);
        new ScyllaRecipe().regRecipe();
        // Herme's Boots
        getCommand("hermesboots").setExecutor(new HermesCommand());
        getServer().getPluginManager().registerEvents(new HermesItemListener(this), this);
        new HermesRecipe().regRecipe();
        // Helmet of Life
        LifeHelmetListener lifeHelmetListener = new LifeHelmetListener(this);
        getCommand("lifehelmet").setExecutor(new LifeHelmetCommand(lifeHelmetListener));
        getServer().getPluginManager().registerEvents(lifeHelmetListener, this);
        //new LifeHelmetRecipe().regRecipe();
        // Caveman Sword
        getCommand("cavemansword").setExecutor(new CavemanCommand());
        getServer().getPluginManager().registerEvents(new CavemanItemListener(this), this);
        //new CavemanRecipe().regRecipe();
        // Warlock Pants
        getCommand("warlockpants").setExecutor(new WarlockCommand());
        new WarlockItemListener(this).regWarlockRunnable();
        new WarlockRecipe().regRecipe();
        // Fireball
        getCommand("fireball").setExecutor(new FireballCommand());
        getServer().getPluginManager().registerEvents(new FireballListener(this), this);
        new FireballRecipe().regRecipe();
        // Atom Bomb
        getCommand("atombomb").setExecutor(new AtomCommand());
        getServer().getPluginManager().registerEvents(new AtomItemListener(this), this);
        // Netherite Staff
        getCommand("netheritestaff").setExecutor(new NetheriteStaffCommand());
        getServer().getPluginManager().registerEvents(new NetheriteStaffListener(this), this);
        new NetheriteStaffRecipe().regRecipe();
        // Sniper Rifle
        getCommand("sniperrifle").setExecutor(new SniperCommand());
        getServer().getPluginManager().registerEvents(new SniperItemListener(this), this);
        // Miner's Blessing
        MinerItemListener minerListener = new MinerItemListener(this);
        getCommand("minersblessing").setExecutor(new MinerCommand());
        getServer().getPluginManager().registerEvents(minerListener, this);
        minerListener.regHasteRunnable();
        new MinerRecipe().regRecipe();
        // Pilot's Sword
        getCommand("pilotsword").setExecutor(new PilotCommand());
        getServer().getPluginManager().registerEvents(new PilotItemListener(this), this);
        //new PilotRecipe().regRecipe();
        // Shredded Axe
        getCommand("shreddedaxe").setExecutor(new ShreddedCommand());
        getServer().getPluginManager().registerEvents(new ShreddedListener(this), this);
        // Napalm Missile
        getCommand("napalm").setExecutor(new NapalmCommand());
        getServer().getPluginManager().registerEvents(new NapalmItemListener(this), this);
        // Ninja Bow
        getCommand("ninjabow").setExecutor(new NinjaCommand());
        getServer().getPluginManager().registerEvents(new NinjaItemListener(this), this);
        new NinjaRecipe().regRecipe();
        // Dragon Armor
        getCommand("dragonarmor").setExecutor(new DragonArmorCommand());
        getServer().getPluginManager().registerEvents(new DragonArmorListener(this), this);
        new DragonArmorRecipe().registerAll();
        // Copper Sword
        getCommand("coppersword").setExecutor(new CopperSwordCommand());
        getServer().getPluginManager().registerEvents(new CopperSwordListener(this), this);
        new CopperSwordRecipe().regRecipe();
        // Poseidon's Trident
        TridentListener tridentListener = new TridentListener(this);
        getCommand("poseidontrident").setExecutor(new TridentCommand());
        getServer().getPluginManager().registerEvents(tridentListener, this);
        tridentListener.regTridentRunnable();
        new TridentRecipe().regRecipe();
        // Valkyrie Axe
        getCommand("valkyrieaxe").setExecutor(new ValkyrieCommand());
        getServer().getPluginManager().registerEvents(new ValkyrieItemListener(this), this);
        new ValkyrieRecipe().regRecipe();
        // Custom Items
        getCommand("minoritem").setExecutor(new MinorItemCommand());
        new HideLeviathanRecipe().regRecipe();
        new FlaskRecipe().regRecipe();
        new CoreStaffRecipe().regRecipe();
        // Custom Item Listener
        getServer().getPluginManager().registerEvents(new CustomItemListener(this), this);
        // Dragon Fight
        this.dragonManager = new FightManager(this);
        getServer().getPluginManager().registerEvents(dragonManager, this);

        getLogger().info(String.format("Finished setting up! [%d ms]", System.currentTimeMillis()-t));
    }

}
