package org.kayteam.ptc.listeners.custom;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kayteam.ptc.events.PlayerJoinArenaEvent;

public class PlayerJoinArenaListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoinArena(PlayerJoinArenaEvent event) {
    }
}
