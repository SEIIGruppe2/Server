package at.aau.se2.utils;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * The Lobby class represents a lobby in the game.
 * It contains information about the players and the game state.
 * The class is annotated with Lombok's @Getter annotation to automatically generate getter methods for its fields.
 */
@Getter
public class Lobby {
    private final List<Player> players;
    private final GameState gameState;

    /**
     * Constructs a new Lobby object with the given GameState.
     * Initializes the GameState and creates an empty list of players.
     *
     * @param gameState the initial GameState for the Lobby
     */
    public Lobby(GameState gameState){
        this.gameState = gameState;
        this.players = new ArrayList<>();
    }
}
