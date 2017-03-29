package com.rps.models;

public class PlayerPair<F extends Player, S extends Player> {
    private F mainPlayer;
    private S adversaryPlayer;

    public PlayerPair(F first, S second) {
        this.mainPlayer = first;
        this.adversaryPlayer = second;
    }

    public void setMainPlayer(F mainPlayer) {
        this.mainPlayer = mainPlayer;
    }

    public void setAdversaryPlayer(S adversaryPlayer) {
        this.adversaryPlayer = adversaryPlayer;
    }

    public F getMainPlayer() {
        return mainPlayer;
    }

    public S getAdversaryPlayer() {
        return adversaryPlayer;
    }
}
