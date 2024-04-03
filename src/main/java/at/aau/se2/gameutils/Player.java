package at.aau.se2.gameutils;

import at.aau.se2.model.Actioncard;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    @Getter
    @Setter
    private Lobby lobby;
    @Getter
    @Setter
    private WebSocketSession session;
    @Getter
    @Setter
    private int points;
//    @Getter
//    private final String playerName;
    @Getter
    private final String playerID; // Session ID
    @Getter
    private List<Actioncard> cards;
    public Player(WebSocketSession session, Lobby lobby){
//        this.playerName = playerName;
        this.playerID = session.getId();
        this.session = session;
        this.lobby = lobby;
        this.points = 0;
        this.cards = new ArrayList<>();
    }
}
