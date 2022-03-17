package org.kayteam.ptc.task;

import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.scoreboard.Scoreboard;
import org.bukkit.entity.Player;
import org.kayteam.api.scheduler.Task;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.player.GamePlayer;
import org.kayteam.ptc.player.GamePlayerStatus;

public class ScoreboardTask extends Task {

    private final GamePlayer gamePlayer;

    public ScoreboardTask(Player player) {
        super(PTC.getPTC(), 100);
        this.gamePlayer = PTC.getPlayerManager().getGamePlayer(player);
    }

    @Override
    public void actions() {
        GamePlayerStatus gamePlayerStatus = gamePlayer.getGamePlayerStatus();
        TabPlayer tabPlayer = PTC.getTabAPI().getPlayer(gamePlayer.getPlayer().getUniqueId());
        Scoreboard scoreboard = PTC.getGeneralConfigurations().scoreboardStatus.get(gamePlayerStatus);
        PTC.getTabAPI().getScoreboardManager().showScoreboard(tabPlayer, scoreboard);
    }
}
