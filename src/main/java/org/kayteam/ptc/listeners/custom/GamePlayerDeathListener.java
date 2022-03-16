package org.kayteam.ptc.listeners.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.events.GamePlayerDeathEvent;

public class GamePlayerDeathListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onGamePlayerDeath(GamePlayerDeathEvent event) {
        Player gamePlayer = event.getPlayer();
        PTC.getPlayerManager().getGamePlayers().get(gamePlayer).setDeaths(PTC.getPlayerManager().getGamePlayers().get(gamePlayer).getDeaths()+1);
        if(gamePlayer.getKiller() != null){
            Player killer = gamePlayer.getKiller();
            if(PTC.getPlayerManager().isGamePlayer(killer)){
                PTC.getPlayerManager().getGamePlayers().get(killer).setDeaths(PTC.getPlayerManager().getGamePlayers().get(killer).getKills()+1);
            }
        }
    }
}
