package org.kayteam.ptc.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.kayteam.ptc.player.GamePlayer;

public class PlayerLeaveGameEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private final GamePlayer gamePlayer;

    public PlayerLeaveGameEvent(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}