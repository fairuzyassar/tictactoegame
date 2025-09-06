package com.game.tictactoe;

public class MoveResponse {
    private boolean isSuccess;
    private Integer nextPlayerTurn;

    public MoveResponse(boolean moveStatus, Integer nextPlayerTurn) {
        this.isSuccess = moveStatus;
        this.nextPlayerTurn = nextPlayerTurn;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public Integer getNextPlayerTurn() {
        return nextPlayerTurn;
    }
}
