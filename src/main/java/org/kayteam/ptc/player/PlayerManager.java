package org.kayteam.ptc.player;

import org.bukkit.entity.Player;
import org.kayteam.api.yaml.Yaml;
import org.kayteam.ptc.PTC;

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
        return gamePlayer;
    }

    public Yaml getPlayerFile(String playerName){
        Yaml playerFile = new Yaml(PTC.getPTC(), "players", playerName);
        playerFile.registerFileConfiguration();
        return playerFile;
    }

    public void unloadPlayer(Player player){

    }
}
