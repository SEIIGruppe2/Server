package at.aau.se2.gameutils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Lobby {
    @Getter
    @Setter
    private List<Player> players;
    @Getter
    private final GameState gameState;
    public Lobby(GameState gameState){
        this.gameState = gameState;
        this.players = new ArrayList<>();
    }
}
