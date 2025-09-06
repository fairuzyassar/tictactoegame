package com.game.tictactoe;

public class TicTacToeGame {
    private final Board board;
    private final int numOfPlayers;
    private int currentPlayer;
    private GameStatus status;


    public TicTacToeGame(int size) {
        this.board = new Board(size);
        this.status = GameStatus.IN_PROGRESS;

        //assume player only 2
        this.numOfPlayers = 2;
        registerPlayer();
        this.currentPlayer = 0;
    }

    public GameStatus getStatus() {
        return this.status;
    }

    public String[][] getBoard() {
       return board.getVisualGrid();
    }

    public MoveResponse place(int row, int col) {
        if (status == GameStatus.PLAYERS_WIN || status == GameStatus.DRAW) {
            return new MoveResponse(false, null);
        }

        boolean isSuccess = board.move(row, col, currentPlayer);
        if (!isSuccess) {
            return new MoveResponse(false, currentPlayer);
        }

        boolean isCurrentPlayerWin = board.checkWinner(row, col, currentPlayer);
        if (isCurrentPlayerWin) {
            status = GameStatus.PLAYERS_WIN;
            return new MoveResponse(true, null);
        }

        if (board.getAvailableSpot() == 0) {
            status = GameStatus.DRAW;
            return new MoveResponse(false, null);
        }

        if (status == GameStatus.IN_PROGRESS) {
            switchPlayer();
            return new MoveResponse(true, currentPlayer);
        }

        return new MoveResponse(false, null);
    }

    private void registerPlayer() {
        for (int i = 0; i < this.numOfPlayers; i++) {
            this.board.registerPlayer(i);
        }
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer + 1) % numOfPlayers;
    }
}
