package org.kayteam.ptc.game;

import org.kayteam.api.scheduler.Task;
import org.kayteam.ptc.PTC;

public class GameTask extends Task {

    private Game game;
    private int elapsedTime = 0;
    private int timeLimit = 0;


    public GameTask(Game game) {
        super(PTC.getPTC(), 20);
        this.game = game;
    }

    @Override
    public void actions() {

    }
}
