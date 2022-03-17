package org.kayteam.ptc.placeholderexpansion;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.kayteam.ptc.PTC;

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
        }
        return "Invalid Placeholder";
    }
}
