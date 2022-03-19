package org.kayteam.ptc.task;

import org.kayteam.api.scheduler.Task;
import org.kayteam.ptc.PTC;
import org.kayteam.ptc.game.Game;

public class GameTask extends Task {

    private final Game game;
    private int elapsedTime = 0;
    private int timeLimit = 0;

    public GameTask(Game game) {
        super(PTC.getPTC(), 20);
        this.game = game;
    }

    @Override
    public void actions() {
        if(elapsedTime == timeLimit){
            // todo game finish
        }
        elapsedTime++;
    }

    public int getGameProgress(){
        return (elapsedTime*100)/timeLimit;
    }

    public int getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(int elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getRemainTime(){
        return timeLimit-elapsedTime;
    }
}
