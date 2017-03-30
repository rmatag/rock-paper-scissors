package com.rps;

import com.rps.services.ConsoleInputScanner;
import com.rps.services.PayoffMatrixService;
import com.rps.services.PlayersPlayService;
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

import static com.rps.types.PlayResult.ADVERSARY_PLAYER_WINS;
import static com.rps.types.PlayResult.DRAW;
import static com.rps.types.PlayResult.MAIN_PLAYER_WINS;

@SpringBootApplication
public class RPSGameApp {
    private static final Logger logger = LoggerFactory.getLogger(RPSGameApp.class);
    private static final Integer NUM_ITERATIONS = 10;

    private Map<PlayerPlay, Map<PlayerPlay, Integer>> payoffMatrix;
    private Integer userChoice;

    @Autowired
    ConsoleInputScanner consoleInputScanner;

    @Autowired
    PlayersPlayService playerPlayService;

    @Autowired
    PayoffMatrixService payoffMatrixService;

    Map<PlayResult, Integer> results = new EnumMap<>(PlayResult.class);

    public static void main(String[] args) throws Throwable {
        ConfigurableApplicationContext context = new SpringApplicationBuilder()
                .sources(RPSGameApp.class)
                .run(args);

        RPSGameApp app = context.getBean(RPSGameApp.class);
        app.start(context);
    }

    private void start(ConfigurableApplicationContext context) {
        initializePayoffMatrix();
        initializeResults();

        showPayoffMatrix();
        GameMode gameMode = getGameModeFromConsole(context);
        makePlayersPlay(gameMode);

    }

    void makePlayersPlay(GameMode gameMode) {
        if (gameMode != null) {
            logger.info("GameMode chosen: " + gameMode.name());
            for (int i = 0; i < NUM_ITERATIONS; i++) {
                logger.info("PLAYING ITERATION: " + (i + 1) );

                Pair<PlayerPlay, PlayerPlay> playersPlayPair = playerPlayService.getPlayersPlay(gameMode);
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


    private void updateResults(Pair<PlayerPlay, PlayerPlay> playersPlayPair) {
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

    private void updateAdversaryPlayerResults() {
        Integer currentResult = results.get(ADVERSARY_PLAYER_WINS);
        results.put(ADVERSARY_PLAYER_WINS, currentResult + 1);
    }

    private void updateDrawResults() {
        Integer currentResult = results.get(DRAW);
        results.put(DRAW, currentResult + 1);
    }

    private void updateMainPlayerResults() {
        Integer currentResult = results.get(MAIN_PLAYER_WINS);
        results.put(MAIN_PLAYER_WINS, currentResult + 1);
    }

    void initializePayoffMatrix() {
        payoffMatrix = payoffMatrixService.buildPayoffMatrix();
    }

    void initializeResults() {
        results.put(MAIN_PLAYER_WINS, 0);
        results.put(ADVERSARY_PLAYER_WINS, 0);
        results.put(DRAW, 0);
    }

    GameMode getGameModeFromConsole(ConfigurableApplicationContext context) {
        GameMode gameMode = null;
        userChoice = consoleInputScanner.getOptionFromUser();

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
        System.out.println("\n\nPAYOFF MATRIX");
        System.out.println("--------------");
        for (PlayerPlay player1Play : payoffMatrix.keySet()) {
            Map<PlayerPlay, Integer> playerPlayIntegerMap = payoffMatrix.get(player1Play);
            for (PlayerPlay player2Play : playerPlayIntegerMap.keySet()) {
                Integer value = playerPlayIntegerMap.get(player2Play);
                System.out.print(value.toString() + "\t");
            }
            System.out.println();
        }
    }

}
