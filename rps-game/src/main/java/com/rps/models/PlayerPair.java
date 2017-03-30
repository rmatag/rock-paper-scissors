package com.rps.models;

import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;
import javafx.util.Pair;

public class PlayerPair<F extends Player, S extends Player> {
    private F mainPlayer;
    private S adversaryPlayer;

    public PlayerPair(F first, S second) {
        this.mainPlayer = first;
        this.adversaryPlayer = second;
    }

    public Pair<PlayerPlay, PlayerPlay> play(GameMode gameMode) {
        return new Pair<>(this.mainPlayer.play(gameMode), this.adversaryPlayer.play(gameMode));
    }
}
