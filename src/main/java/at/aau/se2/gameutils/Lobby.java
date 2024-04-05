package at.aau.se2.gameutils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Lobby {
    @Setter
    private List<Player> players;
    private final GameState gameState;
    @Setter
    private int roundCount;
    public Lobby(GameState gameState){
        this.gameState = gameState;
        this.players = new ArrayList<>();
    }
}
