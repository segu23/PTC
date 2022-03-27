package org.kayteam.ptc.listeners;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.kayteam.ptc.ChatMode;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.game.Game;
import org.kayteam.ptc.player.GamePlayer;

public class AsyncPlayerChatListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        GamePlayer gamePlayer = PTC.getPlayerManager().getGamePlayer(event.getPlayer());
        event.getRecipients().clear();
        Game game = gamePlayer.getGame();
        event.setFormat("%2$s");
        String format;
        if(game != null){
            // In Game Chat
            if(event.getMessage().startsWith("!")){
                // Global Game Message
                game.getTeams().get(gamePlayer.getTeamColour()).forEach(gp -> event.getRecipients().add(gp.getPlayer()));
                format = PTC.getGeneralConfigurations().chatFormats.get(ChatMode.IN_GAME_GLOBAL)
                        .replaceAll("%message%", event.getMessage())
                        .replaceAll("%teamColour%", gamePlayer.getTeamColour().toString());
            }else{
                // Team Game Message
                game.getTeams().forEach((colour, team) -> team.forEach(gp -> event.getRecipients().add(gp.getPlayer())));
                format = PTC.getGeneralConfigurations().chatFormats.get(ChatMode.IN_GAME_TEAM)
                        .replaceAll("%message%", event.getMessage());
            }
        }else{
            // Global Lobby Message
            event.getRecipients().addAll(event.getPlayer().getWorld().getPlayers());
            format = PTC.getGeneralConfigurations().chatFormats.get(ChatMode.DEFAULT)
                    .replaceAll("%message%", event.getMessage());
        }
        if(event.getPlayer().hasPermission("ptc.chat.color")){
            format = ChatColor.translateAlternateColorCodes('&', format);
        }
        format = PlaceholderAPI.setPlaceholders(event.getPlayer(), format);
        event.setMessage(format);
    }
}
