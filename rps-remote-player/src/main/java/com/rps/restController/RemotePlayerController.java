package com.rps.restController;

import com.rps.types.PlayMode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by rmata on 3/29/17.
 */
@Controller
@RequestMapping("/remote-play")
public class RemotePlayerController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String getRemotePlay() {
        PlayMode playMode = PlayMode.REMOTE;

        return "playFromTheRemotePlayer" + playMode.name();
    }

}
