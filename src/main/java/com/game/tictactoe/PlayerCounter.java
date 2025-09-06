package com.game.tictactoe;

import java.util.HashMap;
import java.util.Map;

public class PlayerCounter {
    private final int boardSize;
    private final Map<Integer, Integer> rowCounter;
    private final Map<Integer, Integer> columnCounter;
    private int diagonalCounter;
    private int antiDiagonalCounter;

    public PlayerCounter(int boardSize) {
        this.boardSize = boardSize;
        this.rowCounter = new HashMap<>();
        this.columnCounter = new HashMap<>();
        this.diagonalCounter = 0;
        this.antiDiagonalCounter = 0;
    }

    public void incrementCounter(int row, int col) {
        rowCounter.put(row, rowCounter.getOrDefault(row, 0) + 1);
        columnCounter.put(col, columnCounter.getOrDefault(col, 0) + 1);

        if (row == col) {
            diagonalCounter += 1;
        }

        if (row + col == boardSize - 1) {
            antiDiagonalCounter += 1;
        }
    }

    public boolean isWin(int row, int col) {
        boolean isRowCompleted = rowCounter.getOrDefault(row, 0) == boardSize;
        boolean isColCompleted = columnCounter.getOrDefault(col, 0) == boardSize;
        boolean isDiagonalCompleted = (row == col) && (diagonalCounter == boardSize);
        boolean isAntiDiagonalCompleted = (row + col == boardSize - 1) && (antiDiagonalCounter == boardSize);

        return isRowCompleted || isColCompleted || isDiagonalCompleted || isAntiDiagonalCompleted;

    }
}
