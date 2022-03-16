package org.kayteam.ptc.task;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.api.scheduler.Task;
import org.kayteam.ptc.PTC;

public class ScoreboardTask extends Task {

    private final Player player;

    public ScoreboardTask(JavaPlugin plugin, long ticks, Player player) {
        super(PTC.getPTC(), 20);
        this.player = player;
    }

    @Override
    public void actions() {

    }
}
