package org.kayteam.ptc.player;

import org.bukkit.entity.Player;
import org.kayteam.ptc.game.Game;

import java.util.HashMap;

public class PlayerManager {

    private final HashMap<Player, GamePlayer> gamePlayers = new HashMap<>();

    public HashMap<Player, GamePlayer> getGamePlayers() {
        return gamePlayers;
    }
}
