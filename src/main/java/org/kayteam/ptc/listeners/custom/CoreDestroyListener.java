package org.kayteam.ptc.listeners.custom;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kayteam.api.simple.yaml.SimpleYaml;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.events.CoreDestroyEvent;
import org.kayteam.ptc.game.Game;
import org.kayteam.ptc.game.GameArenaCore;
import org.kayteam.ptc.player.GamePlayer;

public class CoreDestroyListener implements Listener {

    @EventHandler
    public void onCoreDestroy(CoreDestroyEvent event) {
        GameArenaCore gameArenaCore = event.getGameArenaCore();
        Game game = gameArenaCore.getGame();
        String message = PTC.messages.getString("coreDestroy", new String[][]{
                {"%teamColour%", gameArenaCore.getTeamColour().toString()},
                {"%playerName%", event.getGamePlayer().getPlayer().getName()}
        });
        game.getTeams().values().forEach((teamColour) -> teamColour.forEach((gamePlayer) -> SimpleYaml.sendMessage(gamePlayer.getPlayer(), (Object) message)));
    }
}
