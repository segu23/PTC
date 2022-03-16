package org.kayteam.ptc.game;

import org.bukkit.Location;

public class GameArenaCore {

    private Location location;
    private int lives = 0;
    private TeamColour teamColour;

    public GameArenaCore(Location location, int lives, TeamColour teamColour) {
        this.location = location;
        this.lives = lives;
        this.teamColour = teamColour;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public void setTeamColour(TeamColour teamColour) {
        this.teamColour = teamColour;
    }
}
