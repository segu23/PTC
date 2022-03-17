package org.kayteam.ptc.player;

import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import org.bukkit.entity.Player;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.task.ScoreboardTask;

import java.util.HashMap;

public class PlayerManager {

    private final HashMap<Player, GamePlayer> gamePlayers = new HashMap<>();

    public HashMap<Player, GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public boolean isGamePlayer(Player player){
        return gamePlayers.containsKey(player);
    }

    public GamePlayer loadPlayer(Player player){
        Yaml playerFile = getPlayerFile(player.getName());
        GamePlayer gamePlayer = new GamePlayer(player);
        gamePlayer.setPoints(playerFile.getInt("points"));
        //gamePlayer.setDeaths(playerFile.getInt("deaths"));
        //gamePlayer.setKills(playerFile.getInt("kills"));
        gamePlayer.setDefeats(playerFile.getInt("defeats"));
        gamePlayer.setVictories(playerFile.getInt("victories"));
        gamePlayer.setDestroyedCores(playerFile.getInt("destroyedCores"));
        gamePlayer.setGamePlayerStatus(GamePlayerStatus.IN_LOBBY);
        //gamePlayer.setScoreboardTask(new ScoreboardTask(player));
        //gamePlayer.getScoreboardTask().startScheduler();

        GamePlayerStatus gamePlayerStatus = gamePlayer.getGamePlayerStatus();
        TabPlayer tabPlayer = PTC.getTabAPI().getPlayer(gamePlayer.getPlayer().getUniqueId());
        Scoreboard scoreboard = PTC.getGeneralConfigurations().scoreboardStatus.get(gamePlayerStatus);
        PTC.getTabAPI().getScoreboardManager().showScoreboard(tabPlayer, scoreboard);

        if(PTC.isDebug()){
            PTC.getPTC().getLogger().info("Player "+player.getName()+" data has been loaded successfully");
        }
        return gamePlayer;
    }

    public Yaml getPlayerFile(String playerName){
        Yaml playerFile = new Yaml(PTC.getPTC(), "players", playerName);
        playerFile.registerFileConfiguration();
        return playerFile;
    }

    public void unloadPlayer(Player player){

    }

    public GamePlayer getGamePlayer(Player player){
        return gamePlayers.get(player);
    }
}
