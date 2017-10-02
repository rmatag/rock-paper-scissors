package com.rps;

import com.rps.services.ConsoleInputScanner;
import com.rps.services.PayoffMatrixService;
import com.rps.services.PlayersPlayService;
import com.rps.types.GameMode;
import com.rps.types.PlayResult;
import com.rps.types.PlayerPlay;
import javafx.util.Pair;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.EnumMap;
import java.util.Map;

import static com.rps.types.PlayerPlay.ROCK;
import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class RPSGameAppTest {

    @Mock
    ConsoleInputScanner consoleInputScannerMock;

    @Mock
    PlayersPlayService playersPlayServiceMock;

    @Mock
    PayoffMatrixService payoffMatrixServiceMock;

    @Mock
    ConfigurableApplicationContext context;

    @InjectMocks
    RPSGameApp app = new RPSGameApp();

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        //app.consoleInputScanner = consoleInputScannerMock;
        //app.playerPlayService = playersPlayServiceMock;
        //app.payoffMatrixService = payoffMatrixServiceMock;
    }

    @Test
    @Parameters(method = "getGameModeFromConsoleParams")
    public void testGetGameModeFromConsole(Integer userOption, GameMode expectedGameMode) {

        when(consoleInputScannerMock.getOptionFromUser()).thenReturn(userOption);

        final GameMode gameModeFromConsole = app.getGameModeFromConsole(context);
        assertThat(gameModeFromConsole, is(expectedGameMode));

    }

    @Test
    public void testMakePlayersPlay() {
        givenResultsAndMatrixAreInitialized();

        app.makePlayersPlay(GameMode.FAIR);
        verify(playersPlayServiceMock, times(10)).getPlayersPlay(any());

        thenResultsAreUpdatedAccordingly();

    }

    private void thenResultsAreUpdatedAccordingly() {
        Integer result = app.results.get(PlayResult.MAIN_PLAYER_WINS);
        assertThat(result, is(10));
    }

    private void givenResultsAndMatrixAreInitialized() {

        Map<PlayerPlay, Map<PlayerPlay, Integer>> matrix = new EnumMap<>(PlayerPlay.class);
        Map<PlayerPlay, Integer> p2RockColumn = new EnumMap<>(PlayerPlay.class);
        p2RockColumn.put(PlayerPlay.PAPER, 1);
        matrix.put(ROCK, p2RockColumn);

        when(playersPlayServiceMock.getPlayersPlay(any())).thenReturn(new Pair<>(PlayerPlay.ROCK, PlayerPlay.PAPER));
        when(payoffMatrixServiceMock.buildPayoffMatrix()).thenReturn(matrix);

        app.initializePayoffMatrix();
        app.initializeResults();

    }

    private Object[] getGameModeFromConsoleParams() {
        return $(
                $(1, GameMode.FAIR),
                $(2, GameMode.UNFAIR),
                $(3, GameMode.REMOTE),
                $(4, null)
        );
    }
}
