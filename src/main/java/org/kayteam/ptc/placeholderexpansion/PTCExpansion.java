package org.kayteam.ptc.placeholderexpansion;

import com.avaje.ebean.text.StringFormatter;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.kayteam.ptc.PTC;

import java.text.DecimalFormat;
import java.util.Date;

public class PTCExpansion extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "ptc";
    }

    @Override
    public @NotNull String getAuthor() {
        return PTC.getPTC().getDescription().getAuthors().get(0);
    }

    @Override
    public @NotNull String getVersion() {
        return PTC.getPTC().getDescription().getVersion();
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        switch (params.toLowerCase()){
            case "coins":{
                return String.valueOf(PTC.getPlayerManager().getGamePlayer(player.getPlayer()).getPoints());
            }
            case "victories":{
                return String.valueOf(PTC.getPlayerManager().getGamePlayer(player.getPlayer()).getVictories());
            }
            case "defeats":{
                return String.valueOf(PTC.getPlayerManager().getGamePlayer(player.getPlayer()).getDefeats());
            }
            case "cores":{
                return String.valueOf(PTC.getPlayerManager().getGamePlayer(player.getPlayer()).getDestroyedCores());
            }
            case "game_remain_time":{
                Date remainTimeDate = new Date(PTC.getPlayerManager().getGamePlayer(player.getPlayer()).getGame().getGameTask().getRemainTime());
                String remainTime;
                DecimalFormat decimalFormat = new DecimalFormat("00");
                if(remainTimeDate.getHours() > 0){
                    remainTime = decimalFormat.format(remainTimeDate.getHours()) + ":" + decimalFormat.format(remainTimeDate.getMinutes()) + ":" + decimalFormat.format(remainTimeDate.getSeconds());
                }else if(remainTimeDate.getMinutes() > 0){
                    remainTime = decimalFormat.format(remainTimeDate.getMinutes()) + ":" + decimalFormat.format(remainTimeDate.getSeconds());
                }else{
                    remainTime = decimalFormat.format(remainTimeDate.getSeconds());
                }
                return remainTime;
            }
            case "game_progress":{
                return String.valueOf(PTC.getPlayerManager().getGamePlayer(player.getPlayer()).getGame().getGameTask().getGameProgress());
            }
        }
        return "Invalid Placeholder";
    }
}
