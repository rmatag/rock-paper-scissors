package com.rps.services;

import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;

public interface PlayerPlayService {
    PlayerPlay getMainPlayerPlay(GameMode gameMode);
    PlayerPlay getAdversaryPlayerPlay(GameMode gameMode);

}
