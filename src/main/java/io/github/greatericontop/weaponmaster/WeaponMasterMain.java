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

import io.github.greatericontop.weaponmaster.dragondescent.AttributeChanger;
import io.github.greatericontop.weaponmaster.dragondescent.DescentCommand;
import io.github.greatericontop.weaponmaster.dragondescent.DescentDataManager;
import io.github.greatericontop.weaponmaster.dragondescent.DescentEvents;
import io.github.greatericontop.weaponmaster.dragondescent.DescentGUIListener;
import io.github.greatericontop.weaponmaster.dragondescent.DescentManagementCommand;
import io.github.greatericontop.weaponmaster.dragonmanager.FightManager;
import io.github.greatericontop.weaponmaster.mainitems.AgriculturalAbomination.AgriculturalAbominationCommand;
import io.github.greatericontop.weaponmaster.mainitems.AgriculturalAbomination.AgriculturalAbominationListener;
import io.github.greatericontop.weaponmaster.mainitems.Anduril.AndurilCommand;
import io.github.greatericontop.weaponmaster.mainitems.Anduril.AndurilItemListener;
import io.github.greatericontop.weaponmaster.mainitems.Anduril.AndurilRecipe;
import io.github.greatericontop.weaponmaster.mainitems.Ares.AresCommand;
import io.github.greatericontop.weaponmaster.mainitems.Ares.AresItemListener;
import io.github.greatericontop.weaponmaster.mainitems.ArtemisBow.ArtemisCommand;
import io.github.greatericontop.weaponmaster.mainitems.ArtemisBow.ArtemisItemListener;
import io.github.greatericontop.weaponmaster.mainitems.ArtemisBow.ArtemisRecipe;
import io.github.greatericontop.weaponmaster.mainitems.AssassinsBlade.AssassinCommand;
import io.github.greatericontop.weaponmaster.mainitems.AssassinsBlade.AssassinsBladeListener;
import io.github.greatericontop.weaponmaster.mainitems.AssaultRifle.AssaultCommand;
import io.github.greatericontop.weaponmaster.mainitems.AssaultRifle.AssaultListener;
import io.github.greatericontop.weaponmaster.mainitems.AtomBomb.AtomCommand;
import io.github.greatericontop.weaponmaster.mainitems.AtomBomb.AtomItemListener;
import io.github.greatericontop.weaponmaster.mainitems.BombCannon.BombCannonCommand;
import io.github.greatericontop.weaponmaster.mainitems.BombCannon.BombCannonListener;
import io.github.greatericontop.weaponmaster.mainitems.CavemanSword.CavemanCommand;
import io.github.greatericontop.weaponmaster.mainitems.CavemanSword.CavemanItemListener;
import io.github.greatericontop.weaponmaster.mainitems.CavemanSword.CavemanRecipe;
import io.github.greatericontop.weaponmaster.mainitems.CopperSword.CopperSwordCommand;
import io.github.greatericontop.weaponmaster.mainitems.CopperSword.CopperSwordListener;
import io.github.greatericontop.weaponmaster.mainitems.CopperSword.CopperSwordRecipe;
import io.github.greatericontop.weaponmaster.mainitems.DeathRod.DeathRodCommand;
import io.github.greatericontop.weaponmaster.mainitems.DeathRod.DeathRodListener;
import io.github.greatericontop.weaponmaster.mainitems.DeathScythe.ScytheCommand;
import io.github.greatericontop.weaponmaster.mainitems.DeathScythe.ScytheItemListener;
import io.github.greatericontop.weaponmaster.mainitems.DeathScythe.ScytheRecipe;
import io.github.greatericontop.weaponmaster.mainitems.DragonArmor.DragonArmorCommand;
import io.github.greatericontop.weaponmaster.mainitems.DragonArmor.DragonArmorListener;
import io.github.greatericontop.weaponmaster.mainitems.DragonArmor.DragonArmorRecipe;
import io.github.greatericontop.weaponmaster.mainitems.DragonArmor.DragonArmorUpgradeListener;
import io.github.greatericontop.weaponmaster.mainitems.DragonElytra.DragonElytraRecipe;
import io.github.greatericontop.weaponmaster.mainitems.DragonElytra.DragonElytraUpgradeListener;
import io.github.greatericontop.weaponmaster.mainitems.DragonElytra.ElytraCommand;
import io.github.greatericontop.weaponmaster.mainitems.DragonElytra.ElytraItemListener;
import io.github.greatericontop.weaponmaster.mainitems.DragonSword.DragonSwordCommand;
import io.github.greatericontop.weaponmaster.mainitems.DragonSword.DragonSwordListener;
import io.github.greatericontop.weaponmaster.mainitems.DragonSword.DragonSwordRecipe;
import io.github.greatericontop.weaponmaster.mainitems.DragonSword.DragonSwordUpgradeListener;
import io.github.greatericontop.weaponmaster.mainitems.EndArmor.EndArmorCommand;
import io.github.greatericontop.weaponmaster.mainitems.EndArmor.EndArmorListener;
import io.github.greatericontop.weaponmaster.mainitems.EndArmor.EndArmorRecipe;
import io.github.greatericontop.weaponmaster.mainitems.EndSword.EndPowerManager;
import io.github.greatericontop.weaponmaster.mainitems.EndSword.EndSwordCommand;
import io.github.greatericontop.weaponmaster.mainitems.EndSword.EndSwordListener;
import io.github.greatericontop.weaponmaster.mainitems.EndSword.EndSwordRecipe;
import io.github.greatericontop.weaponmaster.mainitems.Excalibur.ExcaliburCommand;
import io.github.greatericontop.weaponmaster.mainitems.Excalibur.ExcaliburItemListener;
import io.github.greatericontop.weaponmaster.mainitems.Excalibur.ExcaliburRecipe;
import io.github.greatericontop.weaponmaster.mainitems.Exodus.ExodusCommand;
import io.github.greatericontop.weaponmaster.mainitems.Exodus.ExodusItemListener;
import io.github.greatericontop.weaponmaster.mainitems.Exodus.ExodusRecipe;
import io.github.greatericontop.weaponmaster.mainitems.Fireball.FireballCommand;
import io.github.greatericontop.weaponmaster.mainitems.Fireball.FireballListener;
import io.github.greatericontop.weaponmaster.mainitems.Fireball.FireballRecipe;
import io.github.greatericontop.weaponmaster.mainitems.GuidedMissile.GuidedMissileCommand;
import io.github.greatericontop.weaponmaster.mainitems.GuidedMissile.GuidedMissileManager;
import io.github.greatericontop.weaponmaster.mainitems.HeavyAxe.HeavyAxeCommand;
import io.github.greatericontop.weaponmaster.mainitems.HeavyAxe.HeavyAxeItemListener;
import io.github.greatericontop.weaponmaster.mainitems.HeavyAxe.HeavyAxeRecipe;
import io.github.greatericontop.weaponmaster.mainitems.Helios.HeliosCommand;
import io.github.greatericontop.weaponmaster.mainitems.Helios.HeliosItemListener;
import io.github.greatericontop.weaponmaster.mainitems.Helios.HeliosRecipe;
import io.github.greatericontop.weaponmaster.mainitems.HermesBoots.HermesCommand;
import io.github.greatericontop.weaponmaster.mainitems.HermesBoots.HermesItemListener;
import io.github.greatericontop.weaponmaster.mainitems.HermesBoots.HermesRecipe;
import io.github.greatericontop.weaponmaster.mainitems.LifeHelmet.LifeHelmetCommand;
import io.github.greatericontop.weaponmaster.mainitems.LifeHelmet.LifeHelmetListener;
import io.github.greatericontop.weaponmaster.mainitems.LifeHelmet.LifeHelmetRecipe;
import io.github.greatericontop.weaponmaster.mainitems.MinerBlessing.MinerCommand;
import io.github.greatericontop.weaponmaster.mainitems.MinerBlessing.MinerItemListener;
import io.github.greatericontop.weaponmaster.mainitems.MinerBlessing.MinerRecipe;
import io.github.greatericontop.weaponmaster.mainitems.NapalmMissile.NapalmCommand;
import io.github.greatericontop.weaponmaster.mainitems.NapalmMissile.NapalmItemListener;
import io.github.greatericontop.weaponmaster.mainitems.NetheriteStaff.NetheriteStaffCommand;
import io.github.greatericontop.weaponmaster.mainitems.NetheriteStaff.NetheriteStaffListener;
import io.github.greatericontop.weaponmaster.mainitems.NetheriteStaff.NetheriteStaffRecipe;
import io.github.greatericontop.weaponmaster.mainitems.NinjaBow.NinjaCommand;
import io.github.greatericontop.weaponmaster.mainitems.NinjaBow.NinjaItemListener;
import io.github.greatericontop.weaponmaster.mainitems.NinjaBow.NinjaRecipe;
import io.github.greatericontop.weaponmaster.mainitems.NinjaMastersBow.NinjaMastersCommand;
import io.github.greatericontop.weaponmaster.mainitems.NinjaMastersBow.NinjaMastersItemListener;
import io.github.greatericontop.weaponmaster.mainitems.PilotSword.PilotCommand;
import io.github.greatericontop.weaponmaster.mainitems.PilotSword.PilotItemListener;
import io.github.greatericontop.weaponmaster.mainitems.PilotSword.PilotRecipe;
import io.github.greatericontop.weaponmaster.mainitems.PlutoniumBlade.PlutoniumBladeCommand;
import io.github.greatericontop.weaponmaster.mainitems.PlutoniumBlade.PlutoniumBladeListener;
import io.github.greatericontop.weaponmaster.mainitems.PlutoniumBlade.PlutoniumBladeRecipe;
import io.github.greatericontop.weaponmaster.mainitems.PoseidonTrident.TridentCommand;
import io.github.greatericontop.weaponmaster.mainitems.PoseidonTrident.TridentListener;
import io.github.greatericontop.weaponmaster.mainitems.PoseidonTrident.TridentRecipe;
import io.github.greatericontop.weaponmaster.mainitems.RPGLauncher.LauncherCommand;
import io.github.greatericontop.weaponmaster.mainitems.RPGLauncher.RPGItemListener;
import io.github.greatericontop.weaponmaster.mainitems.RocketStick.RocketCommand;
import io.github.greatericontop.weaponmaster.mainitems.RocketStick.RocketItemListener;
import io.github.greatericontop.weaponmaster.mainitems.ScorpionBow.ScorpionCommand;
import io.github.greatericontop.weaponmaster.mainitems.ScorpionBow.ScorpionItemListener;
import io.github.greatericontop.weaponmaster.mainitems.Scylla.ScyllaCommand;
import io.github.greatericontop.weaponmaster.mainitems.Scylla.ScyllaItemListener;
import io.github.greatericontop.weaponmaster.mainitems.Scylla.ScyllaRecipe;
import io.github.greatericontop.weaponmaster.mainitems.ShreddedAxe.ShreddedCommand;
import io.github.greatericontop.weaponmaster.mainitems.ShreddedAxe.ShreddedListener;
import io.github.greatericontop.weaponmaster.mainitems.ShreddedAxe.ShreddedRecipe;
import io.github.greatericontop.weaponmaster.mainitems.SniperRifle.SniperCommand;
import io.github.greatericontop.weaponmaster.mainitems.SniperRifle.SniperItemListener;
import io.github.greatericontop.weaponmaster.mainitems.ThrowingKnife.ThrowingKnifeCommand;
import io.github.greatericontop.weaponmaster.mainitems.ThrowingKnife.ThrowingKnifeListener;
import io.github.greatericontop.weaponmaster.mainitems.ThrowingKnife.ThrowingKnifeRecipe;
import io.github.greatericontop.weaponmaster.mainitems.ValkyrieAxe.ValkyrieCommand;
import io.github.greatericontop.weaponmaster.mainitems.ValkyrieAxe.ValkyrieItemListener;
import io.github.greatericontop.weaponmaster.mainitems.ValkyrieAxe.ValkyrieRecipe;
import io.github.greatericontop.weaponmaster.mainitems.VampAxe.VampCommand;
import io.github.greatericontop.weaponmaster.mainitems.VampAxe.VampItemListener;
import io.github.greatericontop.weaponmaster.mainitems.VampAxe.VampRecipe;
import io.github.greatericontop.weaponmaster.mainitems.WarlockPants.WarlockCommand;
import io.github.greatericontop.weaponmaster.mainitems.WarlockPants.WarlockItemListener;
import io.github.greatericontop.weaponmaster.mainitems.WarlockPants.WarlockRecipe;
import io.github.greatericontop.weaponmaster.mainitems.WitchSword.WitchSwordCommand;
import io.github.greatericontop.weaponmaster.mainitems.WitchSword.WitchSwordListener;
import io.github.greatericontop.weaponmaster.mainitems.WitherKingStaff.WitherKingCommand;
import io.github.greatericontop.weaponmaster.mainitems.WitherKingStaff.WitherKingItemListener;
import io.github.greatericontop.weaponmaster.mainitems.WitherKingStaff.WitherKingStaffRecipe;
import io.github.greatericontop.weaponmaster.mainitems.WitherStaff.WitherItemListener;
import io.github.greatericontop.weaponmaster.mainitems.WitherStaff.WitherStaffCommand;
import io.github.greatericontop.weaponmaster.mainitems.WitherStaff.WitherStaffRecipe;
import io.github.greatericontop.weaponmaster.minorcrafts.CoreStaffRecipe;
import io.github.greatericontop.weaponmaster.minorcrafts.DyeRecipes;
import io.github.greatericontop.weaponmaster.minorcrafts.FlaskRecipe;
import io.github.greatericontop.weaponmaster.minorcrafts.HideLeviathanRecipe;
import io.github.greatericontop.weaponmaster.minorcrafts.MinorItemCommand;
import io.github.greatericontop.weaponmaster.minorcrafts.MinorItemListener;
import io.github.greatericontop.weaponmaster.minorcrafts.MoveOversListener;
import io.github.greatericontop.weaponmaster.minorcrafts.WeaponsPlutoniumRecipe;
import io.github.greatericontop.weaponmaster.utils.PaperUtils;
import org.bukkit.plugin.java.JavaPlugin;

public class WeaponMasterMain extends JavaPlugin {

    public PaperUtils paperUtils = null;
    public FightManager dragonManager = null;
    public DescentDataManager descent = null;

    public MinorItemListener minorItemListener = null;

    @Override
    public void onEnable() {
        long t = System.currentTimeMillis();

        this.getLogger().info("--------------------");
        this.getLogger().info("#######################################################################################################################");
        this.getLogger().info("# ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄  ▄▄▄▄▄▄▄▄▄▄▄ #");
        this.getLogger().info("#▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌#");
        this.getLogger().info("#▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌ ▀▀▀▀█░█▀▀▀▀ ▐░█▀▀▀▀▀▀▀▀▀ #");
        this.getLogger().info("#▐░▌          ▐░▌       ▐░▌▐░▌          ▐░▌       ▐░▌     ▐░▌     ▐░▌          ▐░▌       ▐░▌     ▐░▌     ▐░▌          #");
        this.getLogger().info("#▐░▌ ▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌     ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░█▄▄▄▄▄▄▄█░▌     ▐░▌     ▐░▌          #");
        this.getLogger().info("#▐░▌▐░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌     ▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌     ▐░▌     ▐░▌          #");
        this.getLogger().info("#▐░▌ ▀▀▀▀▀▀█░▌▐░█▀▀▀▀█░█▀▀ ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀▀▀▀█░▌     ▐░▌     ▐░█▀▀▀▀▀▀▀▀▀ ▐░█▀▀▀▀█░█▀▀      ▐░▌     ▐░▌          #");
        this.getLogger().info("#▐░▌       ▐░▌▐░▌     ▐░▌  ▐░▌          ▐░▌       ▐░▌     ▐░▌     ▐░▌          ▐░▌     ▐░▌       ▐░▌     ▐░▌          #");
        this.getLogger().info("#▐░█▄▄▄▄▄▄▄█░▌▐░▌      ▐░▌ ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌       ▐░▌     ▐░▌     ▐░█▄▄▄▄▄▄▄▄▄ ▐░▌      ▐░▌  ▄▄▄▄█░█▄▄▄▄ ▐░█▄▄▄▄▄▄▄▄▄ #");
        this.getLogger().info("#▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░▌       ▐░▌     ▐░▌     ▐░░░░░░░░░░░▌▐░▌       ▐░▌▐░░░░░░░░░░░▌▐░░░░░░░░░░░▌#");
        this.getLogger().info("# ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀       ▀       ▀▀▀▀▀▀▀▀▀▀▀  ▀         ▀  ▀▀▀▀▀▀▀▀▀▀▀  ▀▀▀▀▀▀▀▀▀▀▀ #");
        this.getLogger().info("#######################################################################################################################");
        this.getLogger().info("");
        this.getLogger().info("WeaponMaster");
        this.getLogger().info("");
        this.getLogger().info("Copyright (C) greateric 2021-present. Licensed under GPL v3.");
        this.getLogger().info("Initializing WeaponMaster by greateric");
        this.getLogger().info("--------------------");

        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

        paperUtils = new PaperUtils(this);

        this.getCommand("weaponmaster").setExecutor(new WeaponMasterCommand(this));
        this.getCommand("weaponmaster").setTabCompleter(new WeaponMasterCommandTabCompleter());
        // RPG Launcher
        this.getServer().getPluginManager().registerEvents(new RPGItemListener(this), this);
        this.getCommand("rpgl").setExecutor(new LauncherCommand());
        // Vamp Axe
        this.getServer().getPluginManager().registerEvents(new VampItemListener(this), this);
        this.getCommand("vampaxe").setExecutor(new VampCommand());
        new VampRecipe().regRecipe();
        // Scythe
        this.getServer().getPluginManager().registerEvents(new ScytheItemListener(this), this);
        this.getCommand("scythe").setExecutor(new ScytheCommand());
        new ScytheRecipe().regRecipe();
        // Dragon Sword
        this.getServer().getPluginManager().registerEvents(new DragonSwordListener(this), this);
        this.getCommand("dragonsword").setExecutor(new DragonSwordCommand());
        new DragonSwordRecipe().regRecipe();
        this.getServer().getPluginManager().registerEvents(new DragonSwordUpgradeListener(this), this);
        // Artemis Bow
        this.getServer().getPluginManager().registerEvents(new ArtemisItemListener(this), this);
        this.getCommand("artemis").setExecutor(new ArtemisCommand());
        new ArtemisRecipe().regRecipe();
        // Anduril
        this.getCommand("anduril").setExecutor(new AndurilCommand());
        new AndurilItemListener(this).regAndurilRunnable();
        new AndurilRecipe().regRecipe();
        // Excalibur
        this.getCommand("excalibur").setExecutor(new ExcaliburCommand());
        this.getServer().getPluginManager().registerEvents(new ExcaliburItemListener(this), this);
        new ExcaliburRecipe().regRecipe();
        // Exodus
        this.getCommand("exodus").setExecutor(new ExodusCommand());
        this.getServer().getPluginManager().registerEvents(new ExodusItemListener(this), this);
        new ExodusRecipe().regRecipe();
        // Rocket Stick
        this.getCommand("rocketstick").setExecutor(new RocketCommand());
        this.getServer().getPluginManager().registerEvents(new RocketItemListener(this), this);
        // Helios
        this.getCommand("helios").setExecutor(new HeliosCommand());
        this.getServer().getPluginManager().registerEvents(new HeliosItemListener(this), this);
        new HeliosRecipe().regRecipe();
        // Scylla's Chestplate
        this.getCommand("scylla").setExecutor(new ScyllaCommand());
        this.getServer().getPluginManager().registerEvents(new ScyllaItemListener(this), this);
        new ScyllaRecipe().regRecipe();
        // Hermes' Boots
        this.getCommand("hermesboots").setExecutor(new HermesCommand());
        this.getServer().getPluginManager().registerEvents(new HermesItemListener(this), this);
        new HermesRecipe().regRecipe();
        // Helmet of Life
        LifeHelmetListener lifeHelmetListener = new LifeHelmetListener(this);
        this.getCommand("lifehelmet").setExecutor(new LifeHelmetCommand(lifeHelmetListener));
        this.getServer().getPluginManager().registerEvents(lifeHelmetListener, this);
        new LifeHelmetRecipe().regRecipe();
        // Caveman Sword
        this.getCommand("cavemansword").setExecutor(new CavemanCommand());
        this.getServer().getPluginManager().registerEvents(new CavemanItemListener(this), this);
        new CavemanRecipe().regRecipe();
        // Warlock Pants
        this.getCommand("warlockpants").setExecutor(new WarlockCommand());
        new WarlockItemListener(this).regWarlockRunnable();
        new WarlockRecipe().regRecipe();
        // Fireball
        this.getCommand("fireball").setExecutor(new FireballCommand());
        this.getServer().getPluginManager().registerEvents(new FireballListener(this), this);
        new FireballRecipe().regRecipe();
        // Atom Bomb
        this.getCommand("atombomb").setExecutor(new AtomCommand());
        this.getServer().getPluginManager().registerEvents(new AtomItemListener(this), this);
        // Netherite Staff
        this.getCommand("netheritestaff").setExecutor(new NetheriteStaffCommand());
        this.getServer().getPluginManager().registerEvents(new NetheriteStaffListener(this), this);
        new NetheriteStaffRecipe().regRecipe();
        // Sniper Rifle
        this.getCommand("sniperrifle").setExecutor(new SniperCommand());
        this.getServer().getPluginManager().registerEvents(new SniperItemListener(this), this);
        // Miner's Blessing
        MinerItemListener minerListener = new MinerItemListener(this);
        this.getCommand("minersblessing").setExecutor(new MinerCommand());
        this.getServer().getPluginManager().registerEvents(minerListener, this);
        minerListener.regHasteRunnable();
        new MinerRecipe().regRecipe();
        // Pilot's Sword
        this.getCommand("pilotsword").setExecutor(new PilotCommand());
        this.getServer().getPluginManager().registerEvents(new PilotItemListener(this), this);
        new PilotRecipe().regRecipe();
        // Shredded Axe
        this.getCommand("shreddedaxe").setExecutor(new ShreddedCommand());
        this.getServer().getPluginManager().registerEvents(new ShreddedListener(this), this);
        new ShreddedRecipe().regRecipe();
        // Napalm Missile
        this.getCommand("napalm").setExecutor(new NapalmCommand());
        this.getServer().getPluginManager().registerEvents(new NapalmItemListener(this), this);
        // Ninja Bow
        this.getCommand("ninjabow").setExecutor(new NinjaCommand());
        this.getServer().getPluginManager().registerEvents(new NinjaItemListener(this), this);
        new NinjaRecipe().regRecipe();
        // Dragon Armor
        this.getCommand("dragonarmor").setExecutor(new DragonArmorCommand());
        this.getServer().getPluginManager().registerEvents(new DragonArmorListener(this), this);
        new DragonArmorRecipe().registerAll();
        this.getServer().getPluginManager().registerEvents(new DragonArmorUpgradeListener(this), this);
        // Copper Sword
        this.getCommand("coppersword").setExecutor(new CopperSwordCommand());
        this.getServer().getPluginManager().registerEvents(new CopperSwordListener(this), this);
        new CopperSwordRecipe().regRecipe();
        // Poseidon's Trident
        TridentListener tridentListener = new TridentListener(this);
        this.getCommand("poseidontrident").setExecutor(new TridentCommand());
        this.getServer().getPluginManager().registerEvents(tridentListener, this);
        tridentListener.regTridentRunnable();
        new TridentRecipe().regRecipe();
        // Valkyrie Axe
        this.getCommand("valkyrieaxe").setExecutor(new ValkyrieCommand());
        this.getServer().getPluginManager().registerEvents(new ValkyrieItemListener(this), this);
        new ValkyrieRecipe().regRecipe();
        // Assault Rifle
        this.getCommand("assaultrifle").setExecutor(new AssaultCommand());
        this.getServer().getPluginManager().registerEvents(new AssaultListener(this), this);
        // Dragon Elytra
        this.getCommand("dragonelytra").setExecutor(new ElytraCommand());
        this.getServer().getPluginManager().registerEvents(new ElytraItemListener(this).regDragonElytraRunnable(), this);
        this.getServer().getPluginManager().registerEvents(new DragonElytraUpgradeListener(this), this);
        new DragonElytraRecipe().regRecipe();
        // Guided Missile
        this.getCommand("guidedmissile").setExecutor(new GuidedMissileCommand());
        GuidedMissileManager guidedMissileManager = new GuidedMissileManager(this);
        guidedMissileManager.regGuidedMissileRunnable();
        this.getServer().getPluginManager().registerEvents(guidedMissileManager, this);
        // Assassin's Blade
        this.getCommand("assassinsblade").setExecutor(new AssassinCommand());
        this.getServer().getPluginManager().registerEvents(new AssassinsBladeListener(this), this);
        // Wither Staff
        this.getCommand("witherstaff").setExecutor(new WitherStaffCommand());
        this.getServer().getPluginManager().registerEvents(new WitherItemListener(this), this);
        new WitherStaffRecipe().regRecipe();
        // Bomb Cannon
        this.getCommand("bombcannon").setExecutor(new BombCannonCommand());
        this.getServer().getPluginManager().registerEvents(new BombCannonListener(this), this);
        // Throwing Knife
        this.getCommand("throwingknife").setExecutor(new ThrowingKnifeCommand());
        this.getServer().getPluginManager().registerEvents(new ThrowingKnifeListener(this), this);
        new ThrowingKnifeRecipe().regRecipe();
        // Witch Sword
        this.getCommand("witchsword").setExecutor(new WitchSwordCommand());
        this.getServer().getPluginManager().registerEvents(new WitchSwordListener(this), this);
        // End Sword
        this.getCommand("endsword").setExecutor(new EndSwordCommand());
        EndPowerManager endPowerManager = new EndPowerManager();
        endPowerManager.registerEndPowerManagerTask(this);
        this.getServer().getPluginManager().registerEvents(new EndSwordListener(this, endPowerManager), this);
        new EndSwordRecipe().regRecipe();
        // End Armor
        this.getCommand("endarmor").setExecutor(new EndArmorCommand());
        this.getServer().getPluginManager().registerEvents(new EndArmorListener(), this);
        new EndArmorRecipe().registerAll();
        // Scorpion Bow
        this.getCommand("scorpionbow").setExecutor(new ScorpionCommand());
        this.getServer().getPluginManager().registerEvents(new ScorpionItemListener(this), this);
        // Ares
        this.getCommand("ares").setExecutor(new AresCommand());
        this.getServer().getPluginManager().registerEvents(new AresItemListener(this), this);
        // Ninja Master's Bow
        this.getCommand("ninjamastersbow").setExecutor(new NinjaMastersCommand());
        this.getServer().getPluginManager().registerEvents(new NinjaMastersItemListener(this), this);
        // Death Rod
        this.getCommand("deathrod").setExecutor(new DeathRodCommand());
        this.getServer().getPluginManager().registerEvents(new DeathRodListener(this), this);
        // Plutonium Blade
        this.getCommand("plutoniumblade").setExecutor(new PlutoniumBladeCommand());
        this.getServer().getPluginManager().registerEvents(new PlutoniumBladeListener(this), this);
        new PlutoniumBladeRecipe().regRecipe();
        // Wither King Staff
        this.getCommand("witherkingstaff").setExecutor(new WitherKingCommand());
        this.getServer().getPluginManager().registerEvents(new WitherKingItemListener(this), this);
        new WitherKingStaffRecipe().regRecipe();
        // Agricultural Abomination
        this.getCommand("agriculturalabomination").setExecutor(new AgriculturalAbominationCommand());
        this.getServer().getPluginManager().registerEvents(new AgriculturalAbominationListener(this), this);
        // Heavy Axe
        this.getCommand("heavyaxe").setExecutor(new HeavyAxeCommand());
        this.getServer().getPluginManager().registerEvents(new HeavyAxeItemListener(this), this);
        new HeavyAxeRecipe().regRecipe();

        // Custom Items
        this.getCommand("minoritem").setExecutor(new MinorItemCommand());
        new DyeRecipes().regRecipes();
        new CoreStaffRecipe().regRecipe();
        new FlaskRecipe().regRecipe();
        new HideLeviathanRecipe().regRecipe();
        new WeaponsPlutoniumRecipe().regRecipe();
        // Custom Item Listeners
        minorItemListener = new MinorItemListener(this);
        this.getServer().getPluginManager().registerEvents(minorItemListener, this);
        this.getServer().getPluginManager().registerEvents(new MoveOversListener(this), this);

        // Dragon Fight
        dragonManager = new FightManager(this);
        this.getServer().getPluginManager().registerEvents(dragonManager, this);

        // Descent
        descent = new DescentDataManager(this, this.getConfig().getBoolean("dragonDescent.enable"));
        this.getCommand("descent-management").setExecutor(new DescentManagementCommand(this));
        this.getCommand("descent").setExecutor(new DescentCommand(this));
        if (descent.isEnabled) {
            this.getServer().getPluginManager().registerEvents(new DescentEvents(this), this);
            AttributeChanger attributeChanger = new AttributeChanger(this);
            attributeChanger.registerUpdateEveryone();
            this.getServer().getPluginManager().registerEvents(attributeChanger, this);
        }
        this.getServer().getPluginManager().registerEvents(new DescentGUIListener(this), this);

        this.getLogger().info(String.format("Finished setting up! [%d ms]", System.currentTimeMillis()-t));
    }

    @Override
    public void onDisable() {
        descent.saveDataToConfig();
    }

}
