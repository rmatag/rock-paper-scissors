package com.rps.models;

import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;

public abstract class Player {
    private Boolean turn;
    public abstract PlayerPlay play(GameMode gameMode);

    public Boolean getTurn() {
        return turn;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }

}
