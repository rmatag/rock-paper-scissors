package com.rps.restController;

import com.rps.types.PlayerPlay;
import com.rps.utils.PlayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(PlayUtils.class)
public class RemotePlayerControllerTest {

    private RemotePlayerController controller = new RemotePlayerController();

    @Test
    public void testGetRemotePlay() {
        PowerMockito.mockStatic(PlayUtils.class);
        PowerMockito.when(PlayUtils.getRandomPlay()).thenReturn(PlayerPlay.PAPER);

        PlayerPlay remotePlay = controller.getRemotePlay();

        assertThat(remotePlay, is(PlayerPlay.PAPER));

    }
}
