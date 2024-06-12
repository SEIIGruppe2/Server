package at.aau.se2.utils;

import at.aau.se2.model.Actioncard;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The Player class represents a player in the game.
 * It contains information about the player, such as username, points, and cards.
 */
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

    /**
     * Constructs a new Player object with the given WebSocket session and Lobby.
     * Initializes player attributes including username, player ID, session, lobby, points, cards, and cheating status.
     *
     * @param session the WebSocket session associated with the player
     * @param lobby the Lobby object to which the player belongs
     */
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

    /**
     * Sets the points of the player by adding the specified points to the current points.
     *
     * @param points the points to be added to the player's current points
     */
    public void setPoints(int points){
        this.points += points;
    }

    /**
     * Sets the username of the player if it has not already been set.
     * Logs a message if the username has already been set.
     *
     * @param username the username to be set for the player
     */
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
