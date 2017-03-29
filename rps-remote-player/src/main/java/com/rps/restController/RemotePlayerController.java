package com.rps.restController;

import com.rps.types.PlayerPlay;
import com.rps.utils.PlayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/remote-play")
public class RemotePlayerController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody PlayerPlay getRemotePlay() {
        PlayerPlay remotePlayerPlay = PlayUtils.getRandomPlay();
        System.out.println("Remote player playing: " + remotePlayerPlay);
        return remotePlayerPlay;
    }

}
