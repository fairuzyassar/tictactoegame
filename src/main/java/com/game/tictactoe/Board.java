package com.game.tictactoe;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int size;
    private final int[][] grid;
    private int availableSpot;
    private final Map<Integer, PlayerCounter> counterMap;

    public Board(int size) {
        this.size = size;
        this.grid = new int[size][size];
        buildGrid();
        this.availableSpot = size * size;
        this.counterMap = new HashMap<>();
    }

    private void buildGrid() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = -1;
            }
        }
    }

    public void registerPlayer(int playerId) {
        counterMap.put(playerId, new PlayerCounter(size));
    }

    public boolean move(int row, int col, int player) {
        if (row < 0 || col < 0 || row >= size || col >= size) {
            return false;
        }

        boolean isOccupied = grid[row][col] != -1;
        if (isOccupied) {
            return false;
        }

        availableSpot -= 1;
        grid[row][col] = player;
        PlayerCounter counter = counterMap.get(player);
        counter.incrementCounter(row, col);

        return true;
    }

    public int getAvailableSpot() {
        return availableSpot;
    }

    public boolean checkWinner(int row, int col, int player) {
        PlayerCounter counter = counterMap.get(player);
        return counter.isWin(row, col);
    }

    public String[][] getVisualGrid() {
        String[][] visualizationGrid = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int currentCell = grid[i][j];
                visualizationGrid[i][j] = currentCell == -1 ? "." : String.valueOf(currentCell);
            }
        }
        return visualizationGrid;
    }
}
