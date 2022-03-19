package org.kayteam.ptc.game;

import me.neznamy.tab.api.bossbar.BarColor;
import me.neznamy.tab.api.bossbar.BarStyle;
import me.neznamy.tab.api.bossbar.BossBar;
import org.bukkit.Location;
import org.bukkit.World;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.arena.Arena;
import org.kayteam.ptc.player.GamePlayer;
import org.kayteam.ptc.task.GameTask;

import java.util.HashMap;
import java.util.List;

public class Game {

    private final Arena arena;
    private final HashMap<TeamColour, List<GamePlayer>> teams = new HashMap<>();
    private final HashMap<Location, GameArenaCore> coreLives = new HashMap<>();
    private final GameTask gameTask = new GameTask(this);
    private final World world;
    private GameStatus gameStatus = GameStatus.WAITING;
    private BossBar bossBar = PTC.getTabAPI().getBossBarManager().createBossBar(PTC.getGeneralConfigurations().bossBarTitle, "%ptc_game_progress%", BarColor.GREEN.toString(), BarStyle.PROGRESS.toString());

    public Game(Arena arena, World world) {
        this.arena = arena;
        this.world = world;
        PTC.getGameManager().getWaitingGames().put(arena, this);
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

    public HashMap<Location, GameArenaCore> getCoreLives() {
        return coreLives;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public boolean canJoin(){
        return ((gameStatus == GameStatus.WAITING) && (teams.values().size() < arena.getMaxTeamPlayers()));
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public void setBossBar(BossBar bossBar) {
        this.bossBar = bossBar;
    }
}
