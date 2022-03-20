package org.kayteam.ptc.game;

import org.bukkit.Location;

public class GameArenaCore {

    private final Location location;
    private int lives;
    private final TeamColour teamColour;
    private final Game game;

    public GameArenaCore(Location location, int lives, TeamColour teamColour, Game game) {
        this.location = location;
        this.lives = lives;
        this.teamColour = teamColour;
        this.game = game;
    }

    public Location getLocation() {
        return location;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public TeamColour getTeamColour() {
        return teamColour;
    }

    public Game getGame() {
        return game;
    }
}
