package org.kayteam.ptc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.kayteam.ptc.PTC;

public class GamePlayerDeathEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private final Player gamePlayer;

    public Player getPlayer() {
        return gamePlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public GamePlayerDeathEvent(Player gamePlayer) {
        this.gamePlayer = gamePlayer;
    }
}
