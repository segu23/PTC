package org.kayteam.ptc.player;

import org.bukkit.entity.Player;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.game.Game;
import org.kayteam.ptc.game.TeamColour;
import org.kayteam.ptc.task.ScoreboardTask;

public class GamePlayer {

    private final Player player;
    private int points = 0;
    private int kills = 0;
    private int deaths = 0;
    private TeamColour teamColour;
    private Game game;
    private GamePlayerStatus gamePlayerStatus;
    private int victories = 0;
    private int defeats = 0;
    private int destroyedCores = 0;
    private ScoreboardTask scoreboardTask;

    public GamePlayer(Player player) {
        this.player = player;
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

    public GamePlayerStatus getGamePlayerStatus() {
        return gamePlayerStatus;
    }

    public void setGamePlayerStatus(GamePlayerStatus gamePlayerStatus) {
        this.gamePlayerStatus = gamePlayerStatus;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getVictories() {
        return victories;
    }

    public void setVictories(int victories) {
        this.victories = victories;
    }

    public int getDefeats() {
        return defeats;
    }

    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    public int getDestroyedCores() {
        return destroyedCores;
    }

    public void setDestroyedCores(int destroyedCores) {
        this.destroyedCores = destroyedCores;
    }

    public ScoreboardTask getScoreboardTask() {
        return scoreboardTask;
    }

    public void setScoreboardTask(ScoreboardTask scoreboardTask) {
        this.scoreboardTask = scoreboardTask;
    }
}
