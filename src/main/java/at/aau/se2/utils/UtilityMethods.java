package at.aau.se2.utils;

import at.aau.se2.exceptions.LobbyNotFoundException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.logging.Logger;

public class UtilityMethods {
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    public static Player findPlayer(WebSocketSession session, Lobby lobby) throws PlayerNotFoundException {
        for(Player player : lobby.getPlayers()){
            if(player.getSession() == session) return player;
        }
        throw new PlayerNotFoundException();
    }

    public static Player findPlayer(WebSocketSession session, List<Player> players) throws PlayerNotFoundException {
        for(Player player : players){
            if(player.getSession() == session){
                return player;
            }
        }
        throw new PlayerNotFoundException();
    }

    public static Lobby findLobby(WebSocketSession session, List<Player> players) throws LobbyNotFoundException {
        for(Player player : players){
            if(player.getSession() == session){
                return player.getLobby();
            }
        }
        throw new LobbyNotFoundException();
    }

    public static void logi(String msg){
        logger.info(msg);
    }
    public static void logd(String msg){
        logger.warning(msg);
    }
    public static void logs(String msg) {
        logger.severe(msg);
    }

    public static boolean checkUsername(String username){
        for(String u : GameHandler.getUsernames()){
            if(username.equals(u)) return true;
        }
        return false;
    }
}
