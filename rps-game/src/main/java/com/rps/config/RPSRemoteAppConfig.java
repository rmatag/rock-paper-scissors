package com.rps.config;

import com.rps.services.ConsoleInputScanner;
import com.rps.services.PayoffMatrixService;
import com.rps.services.PlayersPlayService;
import com.rps.services.PlayersPlayServiceImpl;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class RPSRemoteAppConfig {

    @Bean
    public PlayersPlayService playerPlayService() {
        return new PlayersPlayServiceImpl();
    }

    @Bean
    public ConsoleInputScanner consoleInputScanner() {
        return new ConsoleInputScanner();
    }

    @Bean
    public PayoffMatrixService payoffMatrixService() {
        return new PayoffMatrixService();
    }
}
