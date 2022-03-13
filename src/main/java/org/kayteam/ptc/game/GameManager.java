package org.kayteam.ptc.game;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.kayteam.api.world.WorldUtil;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.arena.Arena;
import org.kayteam.ptc.player.GamePlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class GameManager {

    private final List<Game> activeGames = new ArrayList<>();

    private final HashMap<Arena, List<Game>> waitingGames = new HashMap<>();

    public List<Game> getActiveGames() {
        return activeGames;
    }

    public HashMap<Arena, List<Game>> getWaitingGames() {
        return waitingGames;
    }

    public void joinGame(Player player, Arena arena){
        Game game;
        if(waitingGames.get(arena).size() > 0){
            game = waitingGames.get(arena).get(0);
        }else{
            game = createNewGame(arena);
        }
        GamePlayer gamePlayer = new GamePlayer(player, game);
        game.getTeams().get(TeamColour.NONE).add(gamePlayer);
        Location waitingLobby = arena.getWaitingLobby();
        waitingLobby.setWorld(game.getWorld());
        player.teleport(waitingLobby);

    }

    public Game createNewGame(Arena arena){
        World gameWorld = WorldUtil.createWorldCopyFromTemplate(arena.getWorldTemplateDir().getPath(), arena.getName(), "ptc-"+arena.getName());
        if(gameWorld != null){
            Game game = new Game(arena, gameWorld);
            return true;
        }else{
            // todo world null unload arena
        }
        return false;
    }

    public void startGame(Game game){
        game.setGameStatus(GameStatus.STARTING);
        for(GamePlayer gamePlayer : game.getTeams().get(TeamColour.NONE)) {
            Random random = new Random();
            int teamIndex = random.nextInt(4);
            TeamColour selectedTeam = TeamColour.values()[teamIndex];
            gamePlayer.setTeamColour(selectedTeam);
        }
        for(Location coreLocation : game.getArena().getCoreLocations().values()){
            game.getCoreLives().put(coreLocation, PTC.getGeneralConfigurations().initialCoreLives);
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
