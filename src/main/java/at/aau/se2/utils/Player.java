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
    private int points, cheatingRoundsLeft;

    @Getter
    private String username;

    @Getter
    private boolean usernameSet, isCheatToggleOn, isCheating;

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
        this.isCheatToggleOn = false;
        this.cheatingRoundsLeft = 0;
        this.isCheating = false;
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


    public void setCheatingRoundsLeft(int cheatingRoundsLeft) {
        this.cheatingRoundsLeft = cheatingRoundsLeft;
    }

    public void setCheatToggleOn(boolean cheatToggleOn) {
        isCheatToggleOn = cheatToggleOn;
    }

    public void setCheating(boolean cheating) {
        isCheating = cheating;
    }
    public void decrementCheatingRounds() {
        if (isCheating) {
            cheatingRoundsLeft--;
            if (cheatingRoundsLeft == 0) {
                isCheating = false;
            }
        }
    }


}
