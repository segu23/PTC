package org.kayteam.ptc;

import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.ptc.arena.ArenaManager;
import org.kayteam.ptc.game.GameManager;
import org.kayteam.ptc.listeners.BlockBreakListener;
import org.kayteam.ptc.listeners.PlayerDeathListener;
import org.kayteam.ptc.listeners.PlayerJoinListener;
import org.kayteam.ptc.listeners.custom.*;
import org.kayteam.ptc.player.PlayerManager;

public final class PTC extends JavaPlugin {

    public static Yaml messages;
    public static Yaml inventories;
    private static boolean debug = true;
    private static PTC ptc;
    private static ArenaManager arenaManager;
    private static GameManager gameManager;
    private static PlayerManager playerManager;
    private static GeneralConfigurations generalConfigurations;

    @Override
    public void onEnable() {
        ptc = this;
        registerFiles();
        registerListeners();
        generalConfigurations = new GeneralConfigurations();
        arenaManager = new ArenaManager();
        gameManager = new GameManager();
        playerManager = new PlayerManager();
    }

    private void registerFiles(){
        messages = new Yaml(PTC.getPTC(), "messages");
        inventories = new Yaml(PTC.getPTC(), "inventories");
        messages.registerFileConfiguration();
        inventories.registerFileConfiguration();
    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        // Custom events
        getServer().getPluginManager().registerEvents(new CoreDestroyListener(), this);
        getServer().getPluginManager().registerEvents(new GameEndListener(), this);
        getServer().getPluginManager().registerEvents(new GamePlayerDeathListener(), this);
        getServer().getPluginManager().registerEvents(new GameStartListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinArenaListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeaveArenaListener(), this);
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
}
