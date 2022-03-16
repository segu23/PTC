package org.kayteam.ptc.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.kayteam.ptc.PTC;

public class PlayerJoinListener implements Listener {


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PTC.getPlayerManager().loadPlayer(player);
    }


}
