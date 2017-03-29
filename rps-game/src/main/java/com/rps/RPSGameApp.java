package com.rps;

import com.rps.models.AdversaryPlayer;
import com.rps.models.MainPlayer;
import com.rps.models.PlayerPair;
import com.rps.types.GameMode;
import javafx.util.Pair;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class RPSGameApp {

    public static void main(String[] args) throws Throwable {
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(RPSGameApp.class)
                .run(args);

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

        if (userChoice != 4) {

            System.out.println("GameMode chosen: " + gameMode.name());
            PlayerPair<MainPlayer, AdversaryPlayer> players = new PlayerPair<>(new MainPlayer(), new AdversaryPlayer());
            players.getMainPlayer().play(gameMode);
            players.getAdversaryPlayer().play(gameMode);
        }

    }

    public static int showMenu() {

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
