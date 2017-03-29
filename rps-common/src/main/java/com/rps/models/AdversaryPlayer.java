package com.rps.models;

import com.rps.services.PlayerPlayService;
import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;

/**
 * Created by rmata on 3/29/17.
 */
public class AdversaryPlayer extends Player {

    public PlayerPlay play(GameMode gameMode) {
        PlayerPlay adversaryPlayerPlay = new PlayerPlayService().getAdversaryPlayerPlay(gameMode);
        System.out.println("Adversary Player play: " + adversaryPlayerPlay);
        return adversaryPlayerPlay;
    }

}
