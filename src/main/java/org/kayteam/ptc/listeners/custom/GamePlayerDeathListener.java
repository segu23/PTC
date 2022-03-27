package org.kayteam.ptc.listeners.custom;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.events.GamePlayerDeathEvent;
import org.kayteam.ptc.player.GamePlayer;

public class GamePlayerDeathListener implements Listener {

    @EventHandler
    public void onGamePlayerDeath(GamePlayerDeathEvent event) {
        Player player = event.getGamePlayer().getPlayer();
        PTC.getPlayerManager().getGamePlayers().get(player).setDeaths(PTC.getPlayerManager().getGamePlayers().get(player).getDeaths()+1);
        if(player.getKiller() != null){
            Player killer = player.getKiller();
            if(PTC.getPlayerManager().isGamePlayer(killer)){
                PTC.getPlayerManager().getGamePlayers().get(killer).setDeaths(PTC.getPlayerManager().getGamePlayers().get(killer).getKills()+1);
            }
        }
    }
}
