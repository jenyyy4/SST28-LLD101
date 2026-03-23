package com.example.pen;

public class Main {
    public static void main(String[] args) {
        Pen pen = new Pen();

        pen.write("Can I write without opening the pen?"); 
        pen.start();

        pen.start();

        pen.write("I'm writing.");
        pen.close();

        pen.refill();
        pen.start();
        pen.write("Writing again after refill.");
    }
}