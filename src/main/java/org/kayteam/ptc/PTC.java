package org.kayteam.ptc;

import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.ptc.arena.ArenaManager;
import org.kayteam.ptc.game.GameManager;
import org.kayteam.ptc.player.PlayerManager;

public final class PTC extends JavaPlugin {

    public static Yaml messages = new Yaml(PTC.getPTC(), "messages");

    @Override
    public void onEnable() {
        registerFiles();
        PTC.ptc = this;
    }

    private void registerFiles(){
        messages.registerFileConfiguration();
    }

    @Override
    public void onDisable() {
    }

    // Debug

    private static boolean debug = true;

    public static boolean isDebug(){
        return debug;
    }

    // Plugin instance

    private static PTC ptc;

    public static PTC getPTC(){
        return PTC.ptc;
    }

    // Managers

    private static ArenaManager arenaManager = new ArenaManager();

    private static GameManager gameManager = new GameManager();

    private static PlayerManager playerManager = new PlayerManager();

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

    private static GeneralConfigurations generalConfigurations = new GeneralConfigurations();

    public static GeneralConfigurations getGeneralConfigurations() {
        return generalConfigurations;
    }
}
