package org.kayteam.ptc.game;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.kayteam.api.world.WorldUtil;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.arena.Arena;
import org.kayteam.ptc.player.GamePlayer;
import org.kayteam.ptc.player.GamePlayerStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameManager {

    private final HashMap<Arena, List<Game>> activeGames = new HashMap<>();

    private final HashMap<Arena, Game> waitingGames = new HashMap<>();

    public HashMap<Arena, List<Game>> getActiveGames() {
        return activeGames;
    }

    public HashMap<Arena, Game> getWaitingGames() {
        return waitingGames;
    }

    public void joinGame(Player player, String arenaName){
        joinGame(player, PTC.getArenaManager().getArena(arenaName));
    }

    public void joinGame(Player player, Arena arena){
        Game game;
        if(waitingGames.get(arena) != null){
            game = waitingGames.get(arena);
        }else{
            game = createNewGame(arena);
        }
        if(game.canJoin()){
            GamePlayer gamePlayer = PTC.getPlayerManager().getGamePlayer(player);
            gamePlayer.setGamePlayerStatus(GamePlayerStatus.WAITING);
            game.getTeams().get(TeamColour.NONE).add(gamePlayer);
            Location waitingLobby = arena.getWaitingLobby();
            waitingLobby.setWorld(game.getWorld());
            player.teleport(waitingLobby);
        }
    }

    public Game createNewGame(Arena arena){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            World gameWorld = WorldUtil.createWorldCopyFromTemplate(arena.getWorldTemplateDir().getPath(), arena.getName(), "ptc-"+arena.getName());
            if(gameWorld != null){
                Game game = new Game(arena, gameWorld);
                if(PTC.isDebug()){
                    Bukkit.getLogger().info("New game has been created of arena "+arena.getName());
                }
                return game;
            }else{
                // todo world null unload arena
                return null;
            }
        });
        return null;
    };

    public void startGame(Game game){
        game.setGameStatus(GameStatus.STARTING);
        createNewGame(game.getArena());
        for(GamePlayer gamePlayer : game.getTeams().get(TeamColour.NONE)) {
            Random random = new Random();
            int teamIndex = random.nextInt(4);
            TeamColour selectedTeam = TeamColour.values()[teamIndex];
            gamePlayer.setTeamColour(selectedTeam);
        }
        for(TeamColour teamColour : game.getArena().getCoreLocations().keySet()){
            Location coreLocation = game.getArena().getCoreLocations().get(teamColour);
            coreLocation.setWorld(game.getWorld());
            GameArenaCore gameArenaCore = new GameArenaCore(coreLocation, PTC.getGeneralConfigurations().initialCoreLives, teamColour, game);
            game.getCoreLives().put(coreLocation, gameArenaCore);
        }
        for(TeamColour teamColour : game.getTeams().keySet()){
            Location spawnLocation = game.getArena().getSpawnLocations().get(teamColour);
            spawnLocation.setWorld(game.getWorld());
            for(GamePlayer gamePlayer : game.getTeams().get(teamColour)){
                Player player = gamePlayer.getPlayer();
                player.teleport(spawnLocation);
            }
        }
        game.getGameTask().startScheduler();
        game.setGameStatus(GameStatus.PLAYING);
    }
}
