package com.rps.services;

import com.rps.types.PlayerPlay;

import java.util.EnumMap;
import java.util.Map;

import static com.rps.types.PlayerPlay.PAPER;
import static com.rps.types.PlayerPlay.ROCK;
import static com.rps.types.PlayerPlay.SCISSORS;

public class PayoffMatrixService {

    public Map<PlayerPlay, Map<PlayerPlay, Integer>> buildPayoffMatrix() {
        Map<PlayerPlay, Map<PlayerPlay, Integer>> matrix = new EnumMap<>(PlayerPlay.class);

        Map<PlayerPlay, Integer> p2RockColumn = new EnumMap<>(PlayerPlay.class);
        p2RockColumn.put(ROCK, 0);
        p2RockColumn.put(PAPER, -1);
        p2RockColumn.put(SCISSORS, 1);

        Map<PlayerPlay, Integer> p2PaperColumn = new EnumMap<>(PlayerPlay.class);
        p2PaperColumn.put(ROCK, 1);
        p2PaperColumn.put(PAPER, 0);
        p2PaperColumn.put(SCISSORS, -1);

        Map<PlayerPlay, Integer> p2ScissorsColumn = new EnumMap<>(PlayerPlay.class);
        p2ScissorsColumn.put(ROCK, -1);
        p2ScissorsColumn.put(PAPER, 1);
        p2ScissorsColumn.put(SCISSORS, 0);

        matrix.put(ROCK, p2RockColumn);
        matrix.put(PAPER, p2PaperColumn);
        matrix.put(SCISSORS, p2ScissorsColumn);

        return matrix;
    }

}
