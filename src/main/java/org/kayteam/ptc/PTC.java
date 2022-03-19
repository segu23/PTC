package org.kayteam.ptc;

import me.neznamy.tab.api.TabAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.ptc.arena.ArenaManager;
import org.kayteam.ptc.commands.ArenaCommand;
import org.kayteam.ptc.commands.PTCCommand;
import org.kayteam.ptc.commands.TestCommand;
import org.kayteam.ptc.game.GameManager;
import org.kayteam.ptc.listeners.*;
import org.kayteam.ptc.listeners.custom.*;
import org.kayteam.ptc.placeholderexpansion.PTCExpansion;
import org.kayteam.ptc.player.PlayerManager;
import org.kayteam.api.yaml.Yaml;

public final class PTC extends JavaPlugin {

    public static Yaml messages;
    public static Yaml inventories;
    private static boolean debug = true;
    private static PTC ptc;
    private static ArenaManager arenaManager;
    private static GameManager gameManager;
    private static PlayerManager playerManager;
    private static GeneralConfigurations generalConfigurations;
    private static TabAPI tabAPI;

    @Override
    public void onEnable() {
        ptc = this;
        registerFiles();
        registerListeners();
        registerCommands();
        tabAPI = TabAPI.getInstance();
        generalConfigurations = new GeneralConfigurations();
        arenaManager = new ArenaManager();
        gameManager = new GameManager();
        playerManager = new PlayerManager();
        new PTCExpansion().register();
        loadOnlinePlayers();
        getLogger().info("The plugin has been loaded successfully");
    }

    public static void loadOnlinePlayers(){
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            PTC.getPlayerManager().loadPlayer(player);
        }
    }

    private void registerFiles(){
        messages = new Yaml(PTC.getPTC(), "messages");
        inventories = new Yaml(PTC.getPTC(), "inventories");
        messages.registerFileConfiguration();
        inventories.registerFileConfiguration();
    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
        // Custom events
        getServer().getPluginManager().registerEvents(new CoreDestroyListener(), this);
        getServer().getPluginManager().registerEvents(new GameEndListener(), this);
        getServer().getPluginManager().registerEvents(new GamePlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new GameStartListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinArenaListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveArenaListener(), this);
    }

    private void registerCommands(){
        TestCommand testCommand = new TestCommand();
        PTCCommand ptcCommand = new PTCCommand();
        ArenaCommand arenaCommand = new ArenaCommand();
        getCommand("test").setExecutor(testCommand);
        getCommand("arena").setExecutor(arenaCommand);
        getCommand("ptc").setExecutor(ptcCommand);
        getCommand("arena").setTabCompleter(arenaCommand);
        getCommand("ptc").setTabCompleter(ptcCommand);
    }

    @Override
    public void onDisable() {
    }

    // Debug

    public static boolean isDebug(){
        return debug;
    }

    // Plugin instance

    public static PTC getPTC(){
        return PTC.ptc;
    }

    // Managers

    public static ArenaManager getArenaManager() {
        return arenaManager;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    // Configurations

    public static GeneralConfigurations getGeneralConfigurations() {
        return generalConfigurations;
    }

    // API

    public static TabAPI getTabAPI(){
        return tabAPI;
    }
}
