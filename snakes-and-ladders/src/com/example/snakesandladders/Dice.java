package com.example.snakesandladders;

import java.util.Random;

public class Dice {
    private final int faces = 6;
    private final Random random = new Random();

    public int roll() {
        return random.nextInt(faces) + 1;
    }
}
