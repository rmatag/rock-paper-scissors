package com.rps.utils;

import com.rps.types.PlayerPlay;

import java.util.Random;

public class PlayUtils {
    public static PlayerPlay getRandomPlay() {
        return PlayerPlay.values()[new Random().nextInt(3)];
    }
}
