package com.rps.services;

import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;
import com.rps.utils.PlayUtils;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PlayUtils.class)
public class PlayersPlayServiceImplTest {
    private static final String GET_REMOTE_PLAY_PATH = "http://localhost:8080/remote-play";

    PlayersPlayServiceImpl playersPlayService = new PlayersPlayServiceImpl();

    @Mock
    RestTemplate restTemplateMock;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        playersPlayService.restTemplate = restTemplateMock;
    }

    @Test
    public void testGetPlayersPlayFair() {

        PowerMockito.mockStatic(PlayUtils.class);
        PlayerPlay randomPlayReturned = PlayerPlay.PAPER;

        PowerMockito.when(PlayUtils.getRandomPlay()).thenReturn(randomPlayReturned);

        Pair<PlayerPlay, PlayerPlay> playersPlay = playersPlayService.getPlayersPlay(GameMode.FAIR);
        verify(restTemplateMock, never()).getForObject(GET_REMOTE_PLAY_PATH, PlayerPlay.class);
        PowerMockito.verifyStatic(times(2));

        assertThat(playersPlay.getKey(), is(randomPlayReturned));
        assertThat(playersPlay.getValue(), is(randomPlayReturned));

    }

    @Test
    public void testGetPlayersPlayUnfair() {

        PowerMockito.mockStatic(PlayUtils.class);
        PlayerPlay randomPlayReturned = PlayerPlay.PAPER;

        PowerMockito.when(PlayUtils.getRandomPlay()).thenReturn(randomPlayReturned);

        Pair<PlayerPlay, PlayerPlay> playersPlay = playersPlayService.getPlayersPlay(GameMode.UNFAIR);
        verify(restTemplateMock, never()).getForObject(GET_REMOTE_PLAY_PATH, PlayerPlay.class);
        PowerMockito.verifyStatic(times(1));

        assertThat(playersPlay.getKey(), is(randomPlayReturned));
        assertThat(playersPlay.getValue(), is(PlayerPlay.ROCK));

    }

    @Test
    public void testGetPlayersPlayRemote() {
        PowerMockito.mockStatic(PlayUtils.class);
        PlayerPlay randomPlayReturned = PlayerPlay.PAPER;
        PlayerPlay remotePlayReturned = PlayerPlay.SCISSORS;

        PowerMockito.when(PlayUtils.getRandomPlay()).thenReturn(randomPlayReturned);
        when(restTemplateMock.getForObject(GET_REMOTE_PLAY_PATH, PlayerPlay.class)).thenReturn(remotePlayReturned);

        Pair<PlayerPlay, PlayerPlay> playersPlay = playersPlayService.getPlayersPlay(GameMode.REMOTE);
        verify(restTemplateMock).getForObject(GET_REMOTE_PLAY_PATH, PlayerPlay.class);
        PowerMockito.verifyStatic(times(1));

        assertThat(playersPlay.getKey(), is(randomPlayReturned));
        assertThat(playersPlay.getValue(), is(remotePlayReturned));

    }

}
