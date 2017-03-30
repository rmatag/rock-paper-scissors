package com.rps.config;

import static com.rps.types.PlayerPlay.ROCK;
import static com.rps.types.PlayerPlay.PAPER;
import static com.rps.types.PlayerPlay.SCISSORS;

import com.rps.models.AdversaryPlayer;
import com.rps.models.MainPlayer;
import com.rps.models.PlayerPair;
import com.rps.services.PlayerPlayService;
import com.rps.services.PlayerPlayServiceImpl;
import com.rps.types.PlayerPlay;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.EnumMap;
import java.util.Map;

@SpringBootConfiguration
public class RPSRemoteAppConfig {

    @Bean
    public PlayerPlayService playerPlayService() {
        return new PlayerPlayServiceImpl();
    }

    @Bean
    public MainPlayer mainPlayer() {
        return new MainPlayer(playerPlayService());
    }

    @Bean
    public AdversaryPlayer adversaryPlayer() {
        return new AdversaryPlayer(playerPlayService());
    }

    @Bean
    public PlayerPair<MainPlayer, AdversaryPlayer> players() {
        return new PlayerPair<>(mainPlayer(), adversaryPlayer());
    }

    @Bean
    public Map<PlayerPlay, Map<PlayerPlay, Integer>> payoffMatrix() {
        Map<PlayerPlay, Map<PlayerPlay, Integer>> matrix = new EnumMap<>(PlayerPlay.class);

        Map<PlayerPlay, Integer> p2RockColumn = new EnumMap<>(PlayerPlay.class);
        p2RockColumn.put(ROCK, 0);
        p2RockColumn.put(PAPER, -1);
        p2RockColumn.put(SCISSORS, 1);

        Map<PlayerPlay, Integer> p2PaperColumn = new EnumMap<>(PlayerPlay.class);
        p2PaperColumn.put(ROCK, 1);
        p2PaperColumn.put(PAPER, 0);
        p2PaperColumn.put(SCISSORS, -1);

        Map<PlayerPlay, Integer> p2ScissorsColumn = new EnumMap<>(PlayerPlay.class);
        p2ScissorsColumn.put(ROCK, -1);
        p2ScissorsColumn.put(PAPER, 1);
        p2ScissorsColumn.put(SCISSORS, 0);

        matrix.put(ROCK, p2RockColumn);
        matrix.put(PAPER, p2PaperColumn);
        matrix.put(SCISSORS, p2ScissorsColumn);

        return matrix;
    }

}
