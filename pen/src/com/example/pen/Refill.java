package com.example.pen;

public class Refill {
    private int inkLevel;
    private final int MAX_INK = 100;

    public Refill() {
        this.inkLevel = MAX_INK;
    }

    public boolean hasInk() {
        return inkLevel > 0;
    }

    public void useInk() {
        if (inkLevel > 0) {
            inkLevel--;
        }
    }

    public void refillInk() {
        inkLevel = MAX_INK;
        System.out.println("Refill done. Ink is full.");
    }

    public int getInkLevel() {
        return inkLevel;
    }
}