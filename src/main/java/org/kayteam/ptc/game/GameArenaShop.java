package org.kayteam.ptc.game;

import org.bukkit.Location;

public class GameArenaShop {

    private final Location location;
    private final TeamColour teamColour;
    private final Game game;

    public GameArenaShop(Location location, TeamColour teamColour, Game game) {
        this.location = location;
        this.teamColour = teamColour;
        this.game = game;
    }

    public Location getLocation() {
        return location;
    }

    public TeamColour getTeamColour() {
        return teamColour;
    }

    public Game getGame() {
        return game;
    }
}
