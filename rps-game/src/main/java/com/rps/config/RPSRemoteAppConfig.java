package com.rps.config;

import com.rps.models.AdversaryPlayer;
import com.rps.models.MainPlayer;
import com.rps.services.PlayerPlayService;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class RPSRemoteAppConfig {

    @Bean
    public PlayerPlayService playerPlayService() {
        return new PlayerPlayService();
    }

    @Bean
    public MainPlayer mainPlayer() {
        return new MainPlayer(playerPlayService());
    }

    @Bean
    public AdversaryPlayer adversaryPlayer() {
        return new AdversaryPlayer(playerPlayService());
    }
}
