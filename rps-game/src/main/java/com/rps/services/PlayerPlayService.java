package com.rps.services;

import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;
import com.rps.utils.PlayUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class PlayerPlayService {

    private RestTemplate restTemplate = new RestTemplate();
    private static final String HOST_NAME = "http://localhost:8080";
    private static final String GET_REMOTE_PLAY_PATH = HOST_NAME + "/remote-play";

    public PlayerPlay getMainPlayerPlay(GameMode gameMode) {
        switch (gameMode) {
        case UNFAIR:
        case FAIR:
        case REMOTE:
            return PlayUtils.getRandomPlay();
        default:
            throw new IllegalArgumentException(getIllegalArgumentExceptionMessage(gameMode));
        }
    }

    public PlayerPlay getAdversaryPlayerPlay(GameMode gameMode) {
        switch (gameMode) {
        case UNFAIR:
            return PlayerPlay.ROCK;
        case FAIR:
            return PlayUtils.getRandomPlay();
        case REMOTE:
            return restTemplate.getForObject(GET_REMOTE_PLAY_PATH, PlayerPlay.class);
        default:
            throw new IllegalArgumentException(getIllegalArgumentExceptionMessage(gameMode));
        }

    }

    private String getIllegalArgumentExceptionMessage(GameMode gameMode) {
        return "The game mode " + gameMode.name() + " is invalid";
    }
}
