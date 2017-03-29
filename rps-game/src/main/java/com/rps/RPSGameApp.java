package com.rps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RPSGameApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(RPSGameApp.class)
                .run(args);

        RPSGameApp app = context.getBean(RPSGameApp.class);
        app.start();
    }


    private void start() {
	System.out.println("Initializing RPSGameApp");
        
    }

}
