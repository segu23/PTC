package org.kayteam.ptc.player;

import org.bukkit.entity.Player;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.game.Game;
import org.kayteam.ptc.game.TeamColour;

public class GamePlayer {

    private final Player player;
    private int points = 0;
    private int kills = 0;
    private int deaths = 0;
    private TeamColour teamColour;
    private final Game game;

    public GamePlayer(Player player, Game game) {
        this.player = player;
        this.game = game;
        PTC.getPlayerManager().getGamePlayers().put(player, this);
    }

    public Player getPlayer() {
        return player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public TeamColour getTeamColour() {
        return teamColour;
    }

    public void setTeamColour(TeamColour teamColour) {
        this.teamColour = teamColour;
    }

    public Game getGame() {
        return game;
    }
}
