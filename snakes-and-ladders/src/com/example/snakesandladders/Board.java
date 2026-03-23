package com.example.snakesandladders;

import java.util.*;

public class Board {
    private final int size;
    private final int targetPosition;
    private final Map<Integer, Integer> snakes;
    private final Map<Integer, Integer> ladders;

    public Board(int n, String difficultyLevel) {
        this.size = n;
        this.targetPosition = n * n;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        initializeBoard(n, difficultyLevel);
    }

    private void initializeBoard(int n, String difficultyLevel) {
        Random random = new Random();
        Set<Integer> occupiedCells = new HashSet<>();
        occupiedCells.add(targetPosition); 

        int availableCells = targetPosition - 2; 
        if (availableCells < 0) availableCells = 0;
        int maxEntities = availableCells / 2;
        
        int numSnakes = Math.min(n, maxEntities / 2);
        int numLadders = Math.min(n, maxEntities / 2);

        for (int i = 0; i < numSnakes; ) {
            int head = random.nextInt(targetPosition - 3) + 3;
            int tail = random.nextInt(head - 2) + 2;

            if (!occupiedCells.contains(head) && !occupiedCells.contains(tail)) {
                snakes.put(head, tail);
                occupiedCells.add(head);
                occupiedCells.add(tail);
                i++;
            }
        }

        for (int i = 0; i < numLadders; ) {
            int start = random.nextInt(targetPosition - 3) + 2;
            int end = random.nextInt((targetPosition - 1) - start) + start + 1;

            if (!occupiedCells.contains(start) && !occupiedCells.contains(end)) {
                ladders.put(start, end);
                occupiedCells.add(start);
                occupiedCells.add(end);
                i++;
            }
        }
    }

    public int getTargetPosition() {
        return targetPosition;
    }

    public int getNewPosition(int position) {
        if (snakes.containsKey(position)) {
            System.out.println("\n   -> Bitten by a snake at " + position + "! Sliding down to " + snakes.get(position) + "\n");
            return snakes.get(position);
        }
        if (ladders.containsKey(position)) {
            System.out.println("\n   -> Climbed a ladder at " + position + "! Moving up to " + ladders.get(position) + "\n");
            return ladders.get(position);
        }
        return position;
    }
}
