package at.aau.se2.utils;

import at.aau.se2.exceptions.LobbyNotFoundException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.logging.Logger;

/**
 * The UtilityMethods class provides utility methods for various operations in the game.
 * These methods include helper functions for finding players and lobbies, logging messages,
 * and performing other common tasks.
 */
public class UtilityMethods {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Finds the player associated with the given WebSocket session in the specified lobby.
     *
     * @param session the WebSocket session of the player
     * @param lobby the Lobby object containing the players
     * @return the Player object associated with the given session
     * @throws PlayerNotFoundException if the player associated with the session is not found in the lobby
     */
    public static Player findPlayer(WebSocketSession session, Lobby lobby) throws PlayerNotFoundException {
        for(Player player : lobby.getPlayers()){
            if(player.getSession() == session) return player;
        }
        throw new PlayerNotFoundException();
    }

    /**
     * Finds the player associated with the given WebSocket session in the specified list of players.
     *
     * @param session the WebSocket session of the player
     * @param players the list of Player objects
     * @return the Player object associated with the given session
     * @throws PlayerNotFoundException if the player associated with the session is not found in the list
     */
    public static Player findPlayer(WebSocketSession session, List<Player> players) throws PlayerNotFoundException {
        for(Player player : players){
            if(player.getSession() == session){
                return player;
            }
        }
        throw new PlayerNotFoundException();
    }

    /**
     * Finds the lobby associated with the given WebSocket session in the specified list of players.
     *
     * @param session the WebSocket session of the player
     * @param players the list of Player objects
     * @return the Lobby object associated with the given session
     * @throws LobbyNotFoundException if the lobby associated with the session is not found
     */
    public static Lobby findLobby(WebSocketSession session, List<Player> players) throws LobbyNotFoundException {
        logi("findLobby is called!");
        logi("Triggered by: " + session.getId());
        for(Player player : players){
            if(player.getSession() == session){
                return player.getLobby();
            }
        }
        logi("No player was found!");
        throw new LobbyNotFoundException();
    }

    /**
     * Logs an informational message using the global logger.
     *
     * @param msg the message to be logged
     */
    public static void logi(String msg){
        logger.info(msg);
    }

    /**
     * Logs a warning message using the global logger.
     *
     * @param msg the message to be logged
     */
    public static void logd(String msg){
        logger.warning(msg);
    }

    /**
     * Logs a severe error message using the global logger.
     *
     * @param msg the message to be logged
     */
    public static void logs(String msg) {
        logger.severe(msg);
    }

    /**
     * Checks if the given username already exists in the list of usernames.
     *
     * @param username the username to be checked
     * @return true if the username exists, false otherwise
     */
    public static boolean checkUsername(String username){
        for(String u : GameHandler.getUsernames()){
            if(username.equals(u)) return true;
        }
        return false;
    }
}
