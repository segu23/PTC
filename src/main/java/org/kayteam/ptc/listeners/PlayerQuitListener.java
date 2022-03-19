package org.kayteam.ptc.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.kayteam.ptc.PTC;

public class PlayerQuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        PTC.getPlayerManager().unloadPlayer(event.getPlayer());
    }
}
