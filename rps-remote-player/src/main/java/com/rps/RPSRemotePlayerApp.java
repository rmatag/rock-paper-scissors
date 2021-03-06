package com.rps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class RPSRemotePlayerApp extends SpringBootServletInitializer {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(RPSRemotePlayerApp.class, args);
    }
}
