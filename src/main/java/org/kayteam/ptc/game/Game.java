package org.kayteam.ptc.game;

import org.bukkit.World;
import org.kayteam.ptc.arena.Arena;
import org.kayteam.ptc.player.GamePlayer;

import java.util.HashMap;
import java.util.List;

public class Game {

    private final Arena arena;
    private final HashMap<TeamColour, List<GamePlayer>> teams = new HashMap<>();
    private GameTask gameTask = new GameTask(this);
    private final World world;

    public Game(Arena arena, World world) {
        this.arena = arena;
        this.world = world;
    }

    public Arena getArena() {
        return arena;
    }

    public HashMap<TeamColour, List<GamePlayer>> getTeams() {
        return teams;
    }

    public GameTask getGameTask() {
        return gameTask;
    }

    public World getWorld() {
        return world;
    }
}
