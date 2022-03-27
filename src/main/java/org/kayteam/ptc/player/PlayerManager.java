package org.kayteam.ptc.player;

import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.kayteam.api.simple.yaml.SimpleYaml;
import org.kayteam.ptc.PTC;

import java.time.Instant;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PlayerManager {

    private final HashMap<Player, GamePlayer> gamePlayers = new HashMap<>();

    public HashMap<Player, GamePlayer> getGamePlayers() {
        return gamePlayers;
    }

    public boolean isGamePlayer(Player player){
        return gamePlayers.containsKey(player);
    }

    public GamePlayer loadPlayer(Player player){
        if(PTC.getGeneralConfigurations().lobbyLocation != null){
            player.teleport(PTC.getGeneralConfigurations().lobbyLocation);
        }
        SimpleYaml playerFile = getPlayerFile(player.getName());
        GamePlayer gamePlayer = new GamePlayer(player);
        gamePlayer.setPoints(playerFile.getInt("points", 0));
        gamePlayer.setDefeats(playerFile.getInt("defeats", 0));
        gamePlayer.setVictories(playerFile.getInt("victories", 0));
        gamePlayer.setDestroyedCores(playerFile.getInt("destroyedCores", 0));
        gamePlayer.setLongerStreak(playerFile.getInt("longerStreak", 0));
        gamePlayer.setGamePlayerStatus(GamePlayerStatus.IN_LOBBY);
        saveGamePlayer(gamePlayer);

        GamePlayerStatus gamePlayerStatus = gamePlayer.getGamePlayerStatus();
        Scoreboard scoreboard = PTC.getGeneralConfigurations().scoreboardStatus.get(gamePlayerStatus);
        Bukkit.getServer().getScheduler().runTaskLater(PTC.getPTC(), () -> {
            TabPlayer tabPlayer = PTC.getTabAPI().getPlayer(gamePlayer.getPlayer().getUniqueId());
            PTC.getTabAPI().getScoreboardManager().showScoreboard(tabPlayer, scoreboard);
        }, 40L);

        if(PTC.isDebug()){
            PTC.getPTC().getLogger().info("Player "+player.getName()+" data has been loaded successfully");
        }
        return gamePlayer;
    }

    public void saveGamePlayer(GamePlayer gamePlayer){
        SimpleYaml playerFile = getPlayerFile(gamePlayer.getPlayer().getName());
        playerFile.set("points", gamePlayer.getPoints());
        playerFile.set("defeats", gamePlayer.getDefeats());
        playerFile.set("victories", gamePlayer.getVictories());
        playerFile.set("destroyedCores", gamePlayer.getDestroyedCores());
        playerFile.set("longerStreak", gamePlayer.getLongerStreak());
        playerFile.saveYamlFile();
    }

    public SimpleYaml getPlayerFile(String playerName){
        SimpleYaml playerFile = new SimpleYaml(PTC.getPTC(), "players", playerName);
        playerFile.registerYamlFile();
        return playerFile;
    }

    public void loadOnlinePlayers(){
        for(Player player : Bukkit.getServer().getOnlinePlayers()){
            loadPlayer(player);
        }
    }

    public void unloadPlayer(Player player, boolean save){
        if(save){
            saveGamePlayer(getGamePlayer(player));
        }
        // todo remove from game
        gamePlayers.remove(player);
    }

    public void unloadGamePlayer(GamePlayer gamePlayer, boolean save){
        unloadPlayer(gamePlayer.getPlayer(), save);
    }

    public GamePlayer getGamePlayer(Player player){
        return gamePlayers.get(player);
    }

    public void unloadPlayers(){
        gamePlayers.values().forEach((gamePlayer) -> unloadGamePlayer(gamePlayer, true));
    }

    public void reloadGamePlayers(){
        long initialTime = Instant.now().toEpochMilli();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            unloadPlayers();
            loadOnlinePlayers();
        });
        long finalTime = Instant.now().toEpochMilli();
        long elapsedTime = finalTime-initialTime;
        PTC.getPTC().getLogger().info("All players data have been reloaded in "+elapsedTime+"ms");
    }
}
