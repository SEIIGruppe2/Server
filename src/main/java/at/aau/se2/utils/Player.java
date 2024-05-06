package at.aau.se2.utils;

import at.aau.se2.model.Actioncard;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Player {
    @Getter
    private final Lobby lobby;

    @Getter
    private final WebSocketSession session;

    @Getter
    private int points;

    @Getter
    private String username;

    @Getter
    private boolean usernameSet;

    @Getter
    private final String playerID; // Session ID

    @Getter
    private final List<Actioncard> cards;
    public Player(WebSocketSession session, Lobby lobby){
        this.username = "";
        this.usernameSet = false;
        this.playerID = session.getId();
        this.session = session;
        this.lobby = lobby;
        this.points = 0;
        this.cards = new ArrayList<>();
    }

    public void setPoints(int points){
        this.points += points;
    }

    public void setUsername(String username){
        if(!usernameSet){
            this.username = username;
            this.usernameSet = true;
        }
        else{
            Logger.getLogger("global")
                    .info("Username has already been set!");
        }
    }


}
