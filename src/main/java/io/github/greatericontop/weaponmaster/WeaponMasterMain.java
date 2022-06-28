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

import io.github.greatericontop.weaponmaster.dragon_manager.FightManager;
import io.github.greatericontop.weaponmaster.items.anduril.AndurilCommand;
import io.github.greatericontop.weaponmaster.items.anduril.AndurilItemListener;
import io.github.greatericontop.weaponmaster.items.anduril.AndurilRecipe;
import io.github.greatericontop.weaponmaster.items.artemis_bow.ArtemisCommand;
import io.github.greatericontop.weaponmaster.items.artemis_bow.ArtemisItemListener;
import io.github.greatericontop.weaponmaster.items.artemis_bow.ArtemisRecipe;
import io.github.greatericontop.weaponmaster.items.assault_rifle.AssaultCommand;
import io.github.greatericontop.weaponmaster.items.assault_rifle.AssaultListener;
import io.github.greatericontop.weaponmaster.items.atom_bomb.AtomBombCommand;
import io.github.greatericontop.weaponmaster.items.atom_bomb.AtomBombItemListener;
import io.github.greatericontop.weaponmaster.items.caveman_sword.CavemanSwordCommand;
import io.github.greatericontop.weaponmaster.items.caveman_sword.CavemanSwordItemListener;
import io.github.greatericontop.weaponmaster.items.caveman_sword.CavemanSwordRecipe;
import io.github.greatericontop.weaponmaster.items.copper_sword.CopperSwordCommand;
import io.github.greatericontop.weaponmaster.items.copper_sword.CopperSwordListener;
import io.github.greatericontop.weaponmaster.items.copper_sword.CopperSwordRecipe;
import io.github.greatericontop.weaponmaster.items.death_scythe.DeathScytheCommand;
import io.github.greatericontop.weaponmaster.items.death_scythe.DeathScytheItemListener;
import io.github.greatericontop.weaponmaster.items.death_scythe.DeathScytheRecipe;
import io.github.greatericontop.weaponmaster.items.dragon_armor.DragonArmorCommand;
import io.github.greatericontop.weaponmaster.items.dragon_armor.DragonArmorListener;
import io.github.greatericontop.weaponmaster.items.dragon_armor.DragonArmorRecipe;
import io.github.greatericontop.weaponmaster.items.dragon_elytra.DragonElytraUpgradeListener;
import io.github.greatericontop.weaponmaster.items.dragon_elytra.DragonElytraCommand;
import io.github.greatericontop.weaponmaster.items.dragon_elytra.DragonElytraItemListener;
import io.github.greatericontop.weaponmaster.items.dragon_sword.DragonSwordCommand;
import io.github.greatericontop.weaponmaster.items.dragon_sword.DragonSwordItemListener;
import io.github.greatericontop.weaponmaster.items.dragon_sword.DragonSwordRecipe;
import io.github.greatericontop.weaponmaster.items.dragon_sword.DragonSwordUpgradeListener;
import io.github.greatericontop.weaponmaster.items.excalibur.ExcaliburCommand;
import io.github.greatericontop.weaponmaster.items.excalibur.ExcaliburItemListener;
import io.github.greatericontop.weaponmaster.items.excalibur.ExcaliburRecipe;
import io.github.greatericontop.weaponmaster.items.exodus.ExodusCommand;
import io.github.greatericontop.weaponmaster.items.exodus.ExodusItemListener;
import io.github.greatericontop.weaponmaster.items.exodus.ExodusRecipe;
import io.github.greatericontop.weaponmaster.items.fireball.FireballCommand;
import io.github.greatericontop.weaponmaster.items.fireball.FireballListener;
import io.github.greatericontop.weaponmaster.items.fireball.FireballRecipe;
import io.github.greatericontop.weaponmaster.items.helios.HeliosCommand;
import io.github.greatericontop.weaponmaster.items.helios.HeliosItemListener;
import io.github.greatericontop.weaponmaster.items.helios.HeliosRecipe;
import io.github.greatericontop.weaponmaster.items.hermes_boots.HermesBootsCommand;
import io.github.greatericontop.weaponmaster.items.hermes_boots.HermesBootsItemListener;
import io.github.greatericontop.weaponmaster.items.hermes_boots.HermesBootsRecipe;
import io.github.greatericontop.weaponmaster.items.life_helmet.LifeHelmetCommand;
import io.github.greatericontop.weaponmaster.items.life_helmet.LifeHelmetListener;
import io.github.greatericontop.weaponmaster.items.life_helmet.LifeHelmetRecipe;
import io.github.greatericontop.weaponmaster.items.miner_blessing.MinerBlessingCommand;
import io.github.greatericontop.weaponmaster.items.miner_blessing.MinerBlessingItemListener;
import io.github.greatericontop.weaponmaster.items.miner_blessing.MinerBlessingRecipe;
import io.github.greatericontop.weaponmaster.items.napalm_missile.NapalmCommand;
import io.github.greatericontop.weaponmaster.items.napalm_missile.NapalmItemListener;
import io.github.greatericontop.weaponmaster.items.netherite_staff.NetheriteStaffCommand;
import io.github.greatericontop.weaponmaster.items.netherite_staff.NetheriteStaffListener;
import io.github.greatericontop.weaponmaster.items.netherite_staff.NetheriteStaffRecipe;
import io.github.greatericontop.weaponmaster.items.ninja_bow.NinjaBowCommand;
import io.github.greatericontop.weaponmaster.items.ninja_bow.NinjaBowItemListener;
import io.github.greatericontop.weaponmaster.items.ninja_bow.NinjaBowRecipe;
import io.github.greatericontop.weaponmaster.items.pilot_sword.PilotSwordCommand;
import io.github.greatericontop.weaponmaster.items.pilot_sword.PilotSwordItemListener;
import io.github.greatericontop.weaponmaster.items.poseidon_trident.PoseidonTridentCommand;
import io.github.greatericontop.weaponmaster.items.poseidon_trident.PoseidonTridentListener;
import io.github.greatericontop.weaponmaster.items.poseidon_trident.PoseidonTridentRecipe;
import io.github.greatericontop.weaponmaster.items.rocket_stick.RocketStickCommand;
import io.github.greatericontop.weaponmaster.items.rocket_stick.RocketStickItemListener;
import io.github.greatericontop.weaponmaster.items.rpg_launcher.RPGLauncherCommand;
import io.github.greatericontop.weaponmaster.items.rpg_launcher.RPGLauncherItemListener;
import io.github.greatericontop.weaponmaster.items.scylla.ScyllaCommand;
import io.github.greatericontop.weaponmaster.items.scylla.ScyllaItemListener;
import io.github.greatericontop.weaponmaster.items.scylla.ScyllaRecipe;
import io.github.greatericontop.weaponmaster.items.shredded_axe.ShreddedAxeCommand;
import io.github.greatericontop.weaponmaster.items.shredded_axe.ShreddedAxeListener;
import io.github.greatericontop.weaponmaster.items.shredded_axe.ShreddedAxeRecipe;
import io.github.greatericontop.weaponmaster.items.sniper_rifle.SniperRifleCommand;
import io.github.greatericontop.weaponmaster.items.sniper_rifle.SniperRifleItemListener;
import io.github.greatericontop.weaponmaster.items.valkyrie_axe.ValkyrieAxeCommand;
import io.github.greatericontop.weaponmaster.items.valkyrie_axe.ValkyrieAxeItemListener;
import io.github.greatericontop.weaponmaster.items.valkyrie_axe.ValkyrieAxeRecipe;
import io.github.greatericontop.weaponmaster.items.vamp_axe.VampAxeCommand;
import io.github.greatericontop.weaponmaster.items.vamp_axe.VampAxeItemListener;
import io.github.greatericontop.weaponmaster.items.vamp_axe.VampAxeRecipe;
import io.github.greatericontop.weaponmaster.items.warlock_pants.WarlockPantsCommand;
import io.github.greatericontop.weaponmaster.items.warlock_pants.WarlockPantsItemListener;
import io.github.greatericontop.weaponmaster.items.warlock_pants.WarlockPantsRecipe;
import io.github.greatericontop.weaponmaster.other_crafts.CoreStaffRecipe;
import io.github.greatericontop.weaponmaster.other_crafts.CustomItemListener;
import io.github.greatericontop.weaponmaster.other_crafts.FlaskRecipe;
import io.github.greatericontop.weaponmaster.other_crafts.HideLeviathanRecipe;
import io.github.greatericontop.weaponmaster.other_crafts.MinorItemCommand;
import io.github.greatericontop.weaponmaster.util.PaperUtils;

import org.bukkit.plugin.java.JavaPlugin;

public class WeaponMasterMain extends JavaPlugin {

    public PaperUtils paperUtils = null;
    public FightManager dragonManager = null;

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
        this.getLogger().info("Copyright (C) greateric 2021-2022. Licensed under GPL v3.");
        this.getLogger().info("Initializing WeaponMaster by greateric");
        this.getLogger().info("--------------------");

        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);

        paperUtils = new PaperUtils(this);

        this.getCommand("weaponmaster").setExecutor(new WeaponMasterCommand(this));
        this.getCommand("weaponmaster").setTabCompleter(new WeaponMasterCommandTabCompleter());
        // RPG Launcher
        this.getServer().getPluginManager().registerEvents(new RPGLauncherItemListener(this), this);
        this.getCommand("rpgl").setExecutor(new RPGLauncherCommand());
        // Vamp Axe
        this.getServer().getPluginManager().registerEvents(new VampAxeItemListener(this), this);
        this.getCommand("vampaxe").setExecutor(new VampAxeCommand());
        new VampAxeRecipe().regRecipe();
        // Scythe
        this.getServer().getPluginManager().registerEvents(new DeathScytheItemListener(this), this);
        this.getCommand("scythe").setExecutor(new DeathScytheCommand());
        new DeathScytheRecipe().regRecipe();
        // Dragon Sword
        this.getServer().getPluginManager().registerEvents(new DragonSwordItemListener(this), this);
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
        this.getCommand("rocketstick").setExecutor(new RocketStickCommand());
        this.getServer().getPluginManager().registerEvents(new RocketStickItemListener(this), this);
        // Helios
        this.getCommand("helios").setExecutor(new HeliosCommand());
        this.getServer().getPluginManager().registerEvents(new HeliosItemListener(this), this);
        new HeliosRecipe().regRecipe();
        // Scylla's Chestplate
        this.getCommand("scylla").setExecutor(new ScyllaCommand());
        this.getServer().getPluginManager().registerEvents(new ScyllaItemListener(this), this);
        new ScyllaRecipe().regRecipe();
        // Hermes' Boots
        this.getCommand("hermesboots").setExecutor(new HermesBootsCommand());
        this.getServer().getPluginManager().registerEvents(new HermesBootsItemListener(this), this);
        new HermesBootsRecipe().regRecipe();
        // Helmet of Life
        LifeHelmetListener lifeHelmetListener = new LifeHelmetListener(this);
        this.getCommand("lifehelmet").setExecutor(new LifeHelmetCommand(lifeHelmetListener));
        this.getServer().getPluginManager().registerEvents(lifeHelmetListener, this);
        new LifeHelmetRecipe().regRecipe();
        // Caveman Sword
        this.getCommand("cavemansword").setExecutor(new CavemanSwordCommand());
        this.getServer().getPluginManager().registerEvents(new CavemanSwordItemListener(this), this);
        new CavemanSwordRecipe().regRecipe();
        // Warlock Pants
        this.getCommand("warlockpants").setExecutor(new WarlockPantsCommand());
        new WarlockPantsItemListener(this).regWarlockRunnable();
        new WarlockPantsRecipe().regRecipe();
        // Fireball
        this.getCommand("fireball").setExecutor(new FireballCommand());
        this.getServer().getPluginManager().registerEvents(new FireballListener(this), this);
        new FireballRecipe().regRecipe();
        // Atom Bomb
        this.getCommand("atombomb").setExecutor(new AtomBombCommand());
        this.getServer().getPluginManager().registerEvents(new AtomBombItemListener(this), this);
        // Netherite Staff
        this.getCommand("netheritestaff").setExecutor(new NetheriteStaffCommand());
        this.getServer().getPluginManager().registerEvents(new NetheriteStaffListener(this), this);
        new NetheriteStaffRecipe().regRecipe();
        // Sniper Rifle
        this.getCommand("sniperrifle").setExecutor(new SniperRifleCommand());
        this.getServer().getPluginManager().registerEvents(new SniperRifleItemListener(this), this);
        // Miner's Blessing
        MinerBlessingItemListener minerListener = new MinerBlessingItemListener(this);
        this.getCommand("minersblessing").setExecutor(new MinerBlessingCommand());
        this.getServer().getPluginManager().registerEvents(minerListener, this);
        minerListener.regHasteRunnable();
        new MinerBlessingRecipe().regRecipe();
        // Pilot's Sword
        this.getCommand("pilotsword").setExecutor(new PilotSwordCommand());
        this.getServer().getPluginManager().registerEvents(new PilotSwordItemListener(this), this);
        //new PilotRecipe().regRecipe();
        // Shredded Axe
        this.getCommand("shreddedaxe").setExecutor(new ShreddedAxeCommand());
        this.getServer().getPluginManager().registerEvents(new ShreddedAxeListener(this), this);
        new ShreddedAxeRecipe().regRecipe();
        // Napalm Missile
        this.getCommand("napalm").setExecutor(new NapalmCommand());
        this.getServer().getPluginManager().registerEvents(new NapalmItemListener(this), this);
        // Ninja Bow
        this.getCommand("ninjabow").setExecutor(new NinjaBowCommand());
        this.getServer().getPluginManager().registerEvents(new NinjaBowItemListener(this), this);
        new NinjaBowRecipe().regRecipe();
        // Dragon Armor
        this.getCommand("dragonarmor").setExecutor(new DragonArmorCommand());
        this.getServer().getPluginManager().registerEvents(new DragonArmorListener(this), this);
        new DragonArmorRecipe().registerAll();
        // Copper Sword
        this.getCommand("coppersword").setExecutor(new CopperSwordCommand());
        this.getServer().getPluginManager().registerEvents(new CopperSwordListener(this), this);
        new CopperSwordRecipe().regRecipe();
        // Poseidon's Trident
        PoseidonTridentListener tridentListener = new PoseidonTridentListener(this);
        this.getCommand("poseidontrident").setExecutor(new PoseidonTridentCommand());
        this.getServer().getPluginManager().registerEvents(tridentListener, this);
        tridentListener.regTridentRunnable();
        new PoseidonTridentRecipe().regRecipe();
        // Valkyrie Axe
        this.getCommand("valkyrieaxe").setExecutor(new ValkyrieAxeCommand());
        this.getServer().getPluginManager().registerEvents(new ValkyrieAxeItemListener(this), this);
        new ValkyrieAxeRecipe().regRecipe();
        // Assault Rifle
        this.getCommand("assaultrifle").setExecutor(new AssaultCommand());
        this.getServer().getPluginManager().registerEvents(new AssaultListener(this), this);
        // Dragon Elytra
        this.getCommand("dragonelytra").setExecutor(new DragonElytraCommand());
        this.getServer().getPluginManager().registerEvents(new DragonElytraItemListener(this).regDragonElytraRunnable(), this);
        this.getServer().getPluginManager().registerEvents(new DragonElytraUpgradeListener(this), this);
        // Custom Items
        this.getCommand("minoritem").setExecutor(new MinorItemCommand());
        new HideLeviathanRecipe().regRecipe();
        new FlaskRecipe().regRecipe();
        new CoreStaffRecipe().regRecipe();
        // Custom Item Listener
        this.getServer().getPluginManager().registerEvents(new CustomItemListener(this), this);
        // Dragon Fight
        dragonManager = new FightManager(this);
        this.getServer().getPluginManager().registerEvents(dragonManager, this);

        this.getLogger().info(String.format("Finished setting up! [%d ms]", System.currentTimeMillis()-t));
    }

}
