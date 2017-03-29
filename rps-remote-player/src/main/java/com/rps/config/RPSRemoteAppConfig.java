package com.rps.config;

import com.rps.services.PlayerPlayService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RPSRemoteAppConfig {

    @Bean
    public PlayerPlayService playerPlayService() {
        return new PlayerPlayService();
    }
}
