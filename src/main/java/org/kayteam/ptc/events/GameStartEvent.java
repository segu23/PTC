package org.kayteam.ptc.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import org.kayteam.ptc.game.Game;

public class GameStartEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private final Game game;

    public GameStartEvent(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }
}