package com.rps.models;

import com.rps.services.PlayerPlayService;
import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;
import org.springframework.beans.factory.annotation.Autowired;

public class MainPlayer extends Player {
    public MainPlayer() {}

    public MainPlayer(PlayerPlayService playerPlayService) {
        super(playerPlayService);
    }

    public PlayerPlay play(GameMode gameMode) {
        PlayerPlay mainPlayerPlay = playerPlayService.getMainPlayerPlay(gameMode);
        System.out.println("Main player play: " + mainPlayerPlay);
        return mainPlayerPlay;

    }
}

