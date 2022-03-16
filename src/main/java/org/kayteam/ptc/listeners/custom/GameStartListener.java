package org.kayteam.ptc.listeners.custom;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.kayteam.ptc.events.GameStartEvent;

public class GameStartListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onGameStart(GameStartEvent event) {
    }
}
