package at.aau.se2.utils;

import at.aau.se2.model.Actioncard;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class Player {
    @Getter
    private final Lobby lobby;
    @Getter
    private final WebSocketSession session;
    @Getter
    @Setter
    private int points;
    private String username;
    // TODO username
//    @Getter
//    private final String username;
    @Getter
    private final String playerID; // Session ID
    @Getter
    private final List<Actioncard> cards;
    public Player(WebSocketSession session, Lobby lobby){
        //this.username = username;
        this.playerID = session.getId();
        this.session = session;
        this.lobby = lobby;
        this.points = 0;
        this.cards = new ArrayList<>();
    }

    public List<Actioncard> getCards() {
        return cards;
    }

    public void setusername(String username){
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public WebSocketSession getSession() {
        return session;
    }

}
