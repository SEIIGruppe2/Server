package at.aau.se2.utils;

import at.aau.se2.exceptions.LobbyNotFoundException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public class UtilityMethods {
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

    public static String findusernameofPlayer(WebSocketSession session){
        for(Player a: GameHandler.getPlayersofGame()){
            if(a.getSession().equals(session)){
                return a.getUsername();

            }
        }
        return "Player not found";
    }

    public static Lobby findLobby(WebSocketSession session, List<Player> players) throws LobbyNotFoundException {
        for(Player player : players){
            if(player.getSession() == session){
                return player.getLobby();
            }
        }
        throw new LobbyNotFoundException();
    }

    public static boolean checkUsername(String username){
        for(String u : GameHandler.getUsernames()){
            if(username.equals(u)) return true;
        }
        return false;
    }
}
