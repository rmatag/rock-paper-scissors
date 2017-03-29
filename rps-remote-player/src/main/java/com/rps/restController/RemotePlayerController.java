package com.rps.restController;

import com.rps.services.PlayerPlayService;
import com.rps.types.GameMode;
import com.rps.types.PlayerPlay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/remote-play")
public class RemotePlayerController {

    @Autowired
    private PlayerPlayService playerPlayService;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody PlayerPlay getRemotePlay() {
        PlayerPlay remotePlayerPlay = playerPlayService.getRandomPlay();
        System.out.println("Remote player playing: " + remotePlayerPlay);
        return remotePlayerPlay;
    }

}
