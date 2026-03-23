package com.example.pen;

public class Pen {
    private Refill refill;
    private boolean isOpen;

    public Pen() {
        this.refill = new Refill();
        this.isOpen = false;
    }

    public void start() {
        if (!isOpen) {
            isOpen = true;
            System.out.println("Pen is ready to write.");
        } else {
            System.out.println("Pen is already open.");
        }
    }

    public void write(String text) {
        if (!isOpen) {
            System.out.println("Open the pen first.");
            return;
        }

        if (!refill.hasInk()) {
            System.out.println("No ink. Please refill.");
            return;
        }

        System.out.print("Writing: ");
        for (char c : text.toCharArray()) {
            if (!refill.hasInk()) {
                System.out.println("\nInk finished mid-writing!");
                return;
            }
            System.out.print(c);
            refill.useInk();
        }
        System.out.println();
    }

    public void close() {
        if (isOpen) {
            isOpen = false;
            System.out.println("Pen closed.");
        } else {
            System.out.println("Pen is already closed.");
        }
    }

    public void refill() {
        refill.refillInk();
    }
}