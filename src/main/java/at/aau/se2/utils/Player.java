package at.aau.se2.utils;

import at.aau.se2.model.Actioncard;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class Player {
    @Getter
    private Lobby lobby;
    @Getter
    private WebSocketSession session;
    @Getter
    @Setter
    private int points;
    // TODO username
//    @Getter
//    private final String username;
    @Getter
    private final String playerID; // Session ID
    @Getter
    private List<Actioncard> cards;
    public Player(WebSocketSession session, Lobby lobby){
        //this.username = username;
        this.playerID = session.getId();
        this.session = session;
        this.lobby = lobby;
        this.points = 0;
        this.cards = new ArrayList<>();
    }
}
