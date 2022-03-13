package org.kayteam.ptc.player;

import org.bukkit.entity.Player;

public class GamePlayer {

    private final Player player;
    private int points;
    private int kills;
    private int deaths;

    public GamePlayer(Player player) {
        this.player = player;
    }
}
