package org.kayteam.ptc.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.player.GamePlayer;

public class GamePlayerDeathEvent extends Event {

    private static final HandlerList handlerList = new HandlerList();

    private final GamePlayer gamePlayer;

    public GamePlayerDeathEvent(GamePlayer gamePlayer) {
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
