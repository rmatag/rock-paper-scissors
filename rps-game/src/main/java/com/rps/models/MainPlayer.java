package com.rps.models;

import com.rps.services.PlayerPlayService;
import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;

public class MainPlayer extends Player {

    public MainPlayer(PlayerPlayService playerPlayService) {
        super(playerPlayService);
    }

    public PlayerPlay play(GameMode gameMode) {
        PlayerPlay mainPlayerPlay = playerPlayService.getMainPlayerPlay(gameMode);
        logger.debug("Main player plays: " + mainPlayerPlay);
        return mainPlayerPlay;

    }
}

