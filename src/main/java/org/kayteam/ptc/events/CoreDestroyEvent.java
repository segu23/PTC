package org.kayteam.ptc.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.kayteam.ptc.game.GameArenaCore;
import org.kayteam.ptc.player.GamePlayer;

public class CoreDestroyEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private final GameArenaCore gameArenaCore;
    private final GamePlayer gamePlayer;

    public CoreDestroyEvent(GameArenaCore gameArenaCore, GamePlayer gamePlayer) {
        this.gameArenaCore = gameArenaCore;
        this.gamePlayer = gamePlayer;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public GameArenaCore getGameArenaCore() {
        return gameArenaCore;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}
