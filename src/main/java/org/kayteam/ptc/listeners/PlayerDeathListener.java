package org.kayteam.ptc.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.events.GamePlayerDeathEvent;
import org.kayteam.ptc.player.GamePlayer;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player deathPlayer = event.getEntity();
        if(PTC.getPlayerManager().isGamePlayer(deathPlayer)){
            GamePlayer gamePlayer = PTC.getPlayerManager().getGamePlayer(deathPlayer);
            Bukkit.getServer().getPluginManager().callEvent(new GamePlayerDeathEvent(gamePlayer));
        }
    }
}
