package com.rps.models;

import com.rps.services.PlayerPlayService;
import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;

public class AdversaryPlayer extends Player {

    public AdversaryPlayer(PlayerPlayService playerPlayService) {
        super(playerPlayService);
    }

    public PlayerPlay play(GameMode gameMode) {
        PlayerPlay adversaryPlayerPlay = playerPlayService.getAdversaryPlayerPlay(gameMode);
        logger.debug("Adversary Player plays: " + adversaryPlayerPlay);
        return adversaryPlayerPlay;
    }

}
