package com.rps.models;

import com.rps.services.PlayerPlayService;
import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;

public abstract class Player {
    protected PlayerPlayService playerPlayService;

    protected Player() {}

    protected Player(PlayerPlayService playerPlayService) {
        this.playerPlayService = playerPlayService;
    }
    public abstract PlayerPlay play(GameMode gameMode);
}
