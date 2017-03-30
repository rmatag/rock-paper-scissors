package com.rps;

import static com.rps.types.PlayResult.MAIN_PLAYER_WINS;
import static com.rps.types.PlayResult.ADVERSARY_PLAYER_WINS;
import static com.rps.types.PlayResult.DRAW;

import com.rps.models.AdversaryPlayer;
import com.rps.models.MainPlayer;
import com.rps.models.PlayerPair;
import com.rps.types.GameMode;
import com.rps.types.PlayResult;
import com.rps.types.PlayerPlay;
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

        initializeResults();
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

                updateResults(playersPlayPair);
                logger.info("\n\n");
            }

            logger.info("TOTAL RESULTS:");
            logger.info("MAIN PLAYER GAINS: " + results.get(MAIN_PLAYER_WINS));
            logger.info("ADVERSARY PLAYER GAINS: " + results.get(ADVERSARY_PLAYER_WINS));
            logger.info("DRAWS: " + results.get(DRAW));
        }
    }

    protected void updateResults(Pair<PlayerPlay, PlayerPlay> playersPlayPair) {
        Integer matrixValue = payoffMatrix.get(playersPlayPair.getKey()).get(playersPlayPair.getValue());
        String playResultStr;

        if (matrixValue > 0) {
            playResultStr = "Main Player wins";
            updateMainPlayerResults();
        } else if (matrixValue == 0) {
            playResultStr = "Draw";
            updateDrawResults();
        } else {
            playResultStr = "Adversary Player wins";
            updateAdversaryPlayerResults();
        }
        logger.info("Result: " + playResultStr);
    }

    protected void updateAdversaryPlayerResults() {
        Integer currentResult = results.get(ADVERSARY_PLAYER_WINS);
        results.put(ADVERSARY_PLAYER_WINS, currentResult + 1);
    }

    protected void updateDrawResults() {
        Integer currentResult = results.get(DRAW);
        results.put(DRAW, currentResult + 1);
    }

    protected void updateMainPlayerResults() {
        Integer currentResult = results.get(MAIN_PLAYER_WINS);
        results.put(MAIN_PLAYER_WINS, currentResult + 1);
    }

    private GameMode getGameModeFromConsole(ConfigurableApplicationContext context, GameMode gameMode) {
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

    private void initializeResults() {
        results.put(MAIN_PLAYER_WINS, 0);
        results.put(ADVERSARY_PLAYER_WINS, 0);
        results.put(DRAW, 0);
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
