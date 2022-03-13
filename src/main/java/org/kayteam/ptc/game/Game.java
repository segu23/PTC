package org.kayteam.ptc.game;

import org.bukkit.Location;
import org.bukkit.World;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.arena.Arena;
import org.kayteam.ptc.player.GamePlayer;

import java.util.HashMap;
import java.util.List;

public class Game {

    private final Arena arena;
    private final HashMap<TeamColour, List<GamePlayer>> teams = new HashMap<>();
    private final HashMap<Location, Integer> coreLives = new HashMap<>();
    private final GameTask gameTask = new GameTask(this);
    private final World world;
    private GameStatus gameStatus = GameStatus.WAITING;

    public Game(Arena arena, World world) {
        this.arena = arena;
        this.world = world;
        PTC.getGameManager().getWaitingGames().get(arena).add(this);
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

    public HashMap<Location, Integer> getCoreLives() {
        return coreLives;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
