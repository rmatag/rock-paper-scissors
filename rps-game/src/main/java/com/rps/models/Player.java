package com.rps.models;

import com.rps.services.PlayerPlayService;
import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Player {
    protected static final Logger logger = LoggerFactory.getLogger(Player.class);

    protected PlayerPlayService playerPlayService;

    protected Player(PlayerPlayService playerPlayService) {
        this.playerPlayService = playerPlayService;
    }

    protected abstract PlayerPlay play(GameMode gameMode);
}
