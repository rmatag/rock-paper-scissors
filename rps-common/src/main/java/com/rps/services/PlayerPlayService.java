package com.rps.services;

import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class PlayerPlayService {

    private RestTemplate restTemplate = new RestTemplate();

    public PlayerPlay getMainPlayerPlay(GameMode gameMode) {
        switch (gameMode) {
        case UNFAIR:
        case FAIR:
        case REMOTE:
            return getRandomPlay();
        default:
            throw new IllegalArgumentException(getIllegalArgumentExceptionMessage(gameMode));
        }
    }

    public PlayerPlay getAdversaryPlayerPlay(GameMode gameMode) {
        switch (gameMode) {
        case UNFAIR:
            return PlayerPlay.ROCK;
        case FAIR:
            return getRandomPlay();
        case REMOTE:
            return restTemplate.getForObject("http://localhost:8080/remote-play", PlayerPlay.class);
        default:
            throw new IllegalArgumentException(getIllegalArgumentExceptionMessage(gameMode));
        }

    }

    public PlayerPlay getRandomPlay() {
        return PlayerPlay.values()[new Random().nextInt(3)];
    }

    private String getIllegalArgumentExceptionMessage(GameMode gameMode) {
        return "The game mode " + gameMode.name() + " is invalid";
    }
}
