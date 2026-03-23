package com.example.snakesandladders;

import java.util.*;

public class Game {
    private final Board board;
    private final Dice dice;
    private final Queue<Player> players;
    private final List<Player> winners;

    public Game(int n, int numPlayers, String difficultyLevel) {
        this.board = new Board(n, difficultyLevel);
        this.dice = new Dice();
        this.players = new LinkedList<>();
        this.winners = new ArrayList<>();

        for (int i = 1; i <= numPlayers; i++) {
            players.offer(new Player("Player " + i));
        }
    }

    public void play() {
        while (players.size() >= 2) {
            Player currentPlayer = players.poll();
            int currentPos = currentPlayer.getPosition();
            int roll = dice.roll();
            
            int newPos = currentPos + roll;
            System.out.print(currentPlayer.getName() + " rolled a " + roll + " and moved from " + currentPos);

            if (newPos > board.getTargetPosition()) {
                System.out.println(" but cannot move beyond " + board.getTargetPosition() + ". Turn skipped.");
                players.offer(currentPlayer);
                continue;
            } 
            
            System.out.println(" to " + newPos + ".");
            newPos = board.getNewPosition(newPos);
            currentPlayer.setPosition(newPos);

            if (newPos == board.getTargetPosition()) {
                System.out.println("\n" + currentPlayer.getName() + " reached " + board.getTargetPosition() + " and won.\n");
                winners.add(currentPlayer);
            } else {
                players.offer(currentPlayer);
            }
        }
        
        if (!players.isEmpty()) {
            System.out.println(players.poll().getName() + " is the last player remaining and lost the game.");
        }
        System.out.println("\nGame Over.\n");
    }
}
