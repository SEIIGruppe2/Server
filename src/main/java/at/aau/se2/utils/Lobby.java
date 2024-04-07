package at.aau.se2.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Lobby {
    private List<Player> players;
    private final GameState gameState;
    public Lobby(GameState gameState){
        this.gameState = gameState;
        this.players = new ArrayList<>();
    }
}
