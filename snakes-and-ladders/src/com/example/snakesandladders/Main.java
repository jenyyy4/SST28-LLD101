package com.example.snakesandladders;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter board size n (for nxn board): ");
        int n = scanner.nextInt();
        
        System.out.print("Enter number of players x: ");
        int x = scanner.nextInt();
        
        System.out.print("Enter difficulty level (easy/hard): ");
        String difficulty = scanner.next();
        
        System.out.println("\n---------------------------------");
        System.out.println("   Starting Snakes and Ladders");
        System.out.println("---------------------------------\n");
        System.out.println("Board size: " + n + "x" + n + " [Target cell: " + (n * n) + "]");
        System.out.println("Players: " + x);
        System.out.println("Difficulty: " + difficulty + "\n");

        Game game = new Game(n, x, difficulty);
        game.play();
        
        scanner.close();
    }
}
