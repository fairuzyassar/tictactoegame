package com.game.tictactoe;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class TicTacToeController {

    private final Map<String, TicTacToeGame> games = new HashMap<>();

    @PostMapping("/new")
    public ResponseEntity<String> newGame(@RequestParam(defaultValue = "3") int size) {
        if (size < 3) {
            return ResponseEntity.badRequest().body("Invalid board size");
        }

        TicTacToeGame ticTacToeGame = new TicTacToeGame(size);
        String gameId = UUID.randomUUID().toString();
        games.put(gameId, ticTacToeGame);

        return ResponseEntity.ok(gameId);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<String[][]> game(@PathVariable String gameId) {
        TicTacToeGame game = games.get(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(game.getBoard());

    }

    @PutMapping("/{gameId}/move")
    public ResponseEntity<String> move(@PathVariable String gameId, @RequestParam int row, @RequestParam int col) {
        TicTacToeGame game = games.get(gameId);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }

        if (game.getStatus() == GameStatus.IN_PROGRESS) {
            MoveResponse moveResponse = game.place(row, col);
            GameStatus status = game.getStatus();
            if (moveResponse.isSuccess() && status == GameStatus.IN_PROGRESS) {
                return ResponseEntity.ok("Successfully place on board with row " + row + " and col " + col + " next turn is player " + moveResponse.getNextPlayerTurn());
            } else if (moveResponse.isSuccess() && status == GameStatus.PLAYERS_WIN) {
                return ResponseEntity.badRequest().body("Win the game");
            } else {
                return ResponseEntity.badRequest().body("Invalid move. Cell is already taken or out of bounds.");
            }
        } else {
            return ResponseEntity.badRequest().body("Game is already over. Status: " + game.getStatus());
        }
    }
}
