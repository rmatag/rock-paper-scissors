package com.rps.models;

import com.rps.services.PlayerPlayService;
import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;

/**
 * Created by rmata on 3/29/17.
 */
public class MainPlayer extends Player {

    public PlayerPlay play(GameMode gameMode) {
        PlayerPlay mainPlayerPlay = new PlayerPlayService().getMainPlayerPlay(gameMode);
        System.out.println("Main player play: " + mainPlayerPlay);
        return mainPlayerPlay;

    }
}

