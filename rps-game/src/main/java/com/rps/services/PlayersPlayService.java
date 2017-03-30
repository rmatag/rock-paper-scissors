package com.rps.services;

import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;
import javafx.util.Pair;

public interface PlayersPlayService {
    Pair<PlayerPlay, PlayerPlay> getPlayersPlay(GameMode gameMode);

}
