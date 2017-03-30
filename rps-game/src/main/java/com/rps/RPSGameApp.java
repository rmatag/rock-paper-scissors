package com.rps;

import com.rps.models.AdversaryPlayer;
import com.rps.models.MainPlayer;
import com.rps.models.PlayerPair;
import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;
import com.rps.types.PlayResult;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.EnumMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class RPSGameApp {
    private static final Integer NUM_ITERATIONS = 10;
    private static final Logger logger = LoggerFactory.getLogger(RPSGameApp.class);

    @Autowired
    protected PlayerPair<MainPlayer, AdversaryPlayer> players;

    @Autowired
    protected Map<PlayerPlay, Map<PlayerPlay, Integer>> payoffMatrix;

    protected Map<PlayResult, Integer> results = new EnumMap<>(PlayResult.class);

    protected Integer userChoice;

    public static void main(String[] args) throws Throwable {
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(RPSGameApp.class)
                .run(args);

        RPSGameApp app = context.getBean(RPSGameApp.class);
        app.start(context);
    }

    protected void start(ConfigurableApplicationContext context) {
        GameMode gameMode = null;
        results.put(PlayResult.MAIN_PLAYER_WINS, 0);
        results.put(PlayResult.ADVERSARY_PLAYER_WINS, 0);
        results.put(PlayResult.DRAW, 0);
        showPayoffMatrix();
        gameMode = getGameModeFromConsole(context, gameMode);
        makePlayersPlay(gameMode);
    }

    protected void makePlayersPlay(GameMode gameMode) {
        if (userChoice < 4) {
            logger.info("GameMode chosen: " + gameMode.name());
            for (int i = 0; i < NUM_ITERATIONS; i++) {
                logger.info("PLAYING ITERATION: " + (i + 1) );

                Pair<PlayerPlay, PlayerPlay> playersPlayPair = players.play(gameMode);
                logger.info("Main Player play: " + playersPlayPair.getKey() + " | Adversary Player play: " + playersPlayPair.getValue());
                verifyPlayResult(playersPlayPair);

                logger.info("\n\n");
            }

            logger.info("TOTAL RESULTS:");
            logger.info("MAIN PLAYER GAINS: " + results.get(PlayResult.MAIN_PLAYER_WINS));
            logger.info("ADVERSARY PLAYER GAINS: " + results.get(PlayResult.ADVERSARY_PLAYER_WINS));
            logger.info("DRAWS: " + results.get(PlayResult.DRAW));
        }
    }

    protected void verifyPlayResult(Pair<PlayerPlay, PlayerPlay> playersPlayPair) {
        Integer matrixValue = payoffMatrix.get(playersPlayPair.getKey()).get(playersPlayPair.getValue());
        String playResultStr;

        if (matrixValue > 0) {
            playResultStr = "Main Player wins";
            Integer currentResult = results.get(PlayResult.MAIN_PLAYER_WINS);
            results.put(PlayResult.MAIN_PLAYER_WINS, currentResult + 1);
        } else if (matrixValue == 0) {
            playResultStr = "Draw";
            Integer currentResult = results.get(PlayResult.DRAW);
            results.put(PlayResult.DRAW, currentResult + 1);
        } else {
            playResultStr = "Adversary Player wins";
            Integer currentResult = results.get(PlayResult.ADVERSARY_PLAYER_WINS);
            results.put(PlayResult.ADVERSARY_PLAYER_WINS, currentResult + 1);
        }
        logger.info("Result: " + playResultStr);
    }

    protected GameMode getGameModeFromConsole(ConfigurableApplicationContext context, GameMode gameMode) {
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
        return gameMode;
    }

    private void showPayoffMatrix() {

        for (PlayerPlay player1Play : payoffMatrix.keySet()) {
            Map<PlayerPlay, Integer> playerPlayIntegerMap = payoffMatrix.get(player1Play);
            for (PlayerPlay player2Play : playerPlayIntegerMap.keySet()) {
                Integer value = playerPlayIntegerMap.get(player2Play);
                System.out.print(value.toString() + "\t");
            }
            System.out.println();
        }
    }

    private static int showMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

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
