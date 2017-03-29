package com.rps;

import com.rps.models.AdversaryPlayer;
import com.rps.models.MainPlayer;
import com.rps.models.PlayerPair;
import com.rps.types.GameMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class RPSGameApp {
    private static final Integer NUM_ITERATIONS = 10;

    @Autowired
    MainPlayer mainPlayer;

    @Autowired
    AdversaryPlayer adversaryPlayer;

    public static void main(String[] args) throws Throwable {
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(RPSGameApp.class)
                .run(args);

        RPSGameApp app = context.getBean(RPSGameApp.class);
        app.start(context);
    }

    private void start(ConfigurableApplicationContext context) {
        int userChoice;
        GameMode gameMode = null;
        userChoice = showMenu();

        switch (userChoice) {
        case 1:
        case 2:
        case 3:
            gameMode = GameMode.values()[userChoice - 1];
            break;
        case 4:
            context.close();
            break;
        }

        System.out.println("GameMode chosen: " + gameMode.name());
        if (userChoice != 4) {
            for (int i = 0; i < NUM_ITERATIONS; i++) {
                System.out.println("PLAYING ITERATION: " + (i + 1) );
                PlayerPair<MainPlayer, AdversaryPlayer> players = new PlayerPair<>(mainPlayer, adversaryPlayer);
                players.getMainPlayer().play(gameMode);
                players.getAdversaryPlayer().play(gameMode);
                System.out.println();
                System.out.println();
                System.out.println();
            }
        }

    }

    private static int showMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Choose one Game Mode");
        System.out.println("-------------------------\n");
        System.out.println("1 - FAIR");
        System.out.println("2 - UNFAIR");
        System.out.println("3 - REMOTE");
        System.out.println("4 - QUIT");

        selection = input.nextInt();
        return selection;
    }

}
