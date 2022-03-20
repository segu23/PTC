package org.kayteam.ptc.listeners.custom;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kayteam.api.simple.yaml.SimpleYaml;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.events.CoreBreakEvent;
import org.kayteam.ptc.events.CoreDestroyEvent;
import org.kayteam.ptc.game.Game;
import org.kayteam.ptc.game.GameArenaCore;

public class CoreBreakListener implements Listener {

    @EventHandler
    public void onCoreBreak(CoreBreakEvent event) {
        GameArenaCore gameArenaCore = event.getGameArenaCore();
        gameArenaCore.setLives(gameArenaCore.getLives()-1);
        Game game = gameArenaCore.getGame();
        String message = PTC.messages.getString("coreBreak", new String[][]{
                {"%teamColour%", gameArenaCore.getTeamColour().toString()},
                {"%playerName%", }
        });
        game.getTeams().values().forEach((teamColour) -> teamColour.forEach((gamePlayer) -> SimpleYaml.sendMessage(gamePlayer.getPlayer(), message)));
        if(gameArenaCore.getLives() == 0){
            Bukkit.getServer().getPluginManager().callEvent(new CoreDestroyEvent(gameArenaCore, event.getGamePlayer()));
        }
    }
}
