package com.rps.models;

import com.rps.services.PlayerPlayService;
import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;
import org.springframework.beans.factory.annotation.Autowired;

public class AdversaryPlayer extends Player {
    public AdversaryPlayer() {}

    public AdversaryPlayer(PlayerPlayService playerPlayService) {
        super(playerPlayService);
    }

    public PlayerPlay play(GameMode gameMode) {
        PlayerPlay adversaryPlayerPlay = playerPlayService.getAdversaryPlayerPlay(gameMode);
        System.out.println("Adversary Player play: " + adversaryPlayerPlay);
        return adversaryPlayerPlay;
    }

}
