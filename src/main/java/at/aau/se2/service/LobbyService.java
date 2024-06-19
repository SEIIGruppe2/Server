package at.aau.se2.service;

import at.aau.se2.utils.GameState;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

public class LobbyService {
    public static Lobby currentlobby;

    /**
     * Creates a new lobby with a new game state.
     *
     * @return a new Lobby instance
     */
    public static Lobby createLobby(){
        return new Lobby(new GameState());
    }

    /**
     * Moves a player to the specified lobby.
     * Adds the player to both the players list and the lobby's player list,
     * and sends a lobby assignment message to the player's session.
     *
     * @param session the WebSocket session of the player to be moved
     * @param lobby the lobby to which the player will be moved
     * @param players the list of all players
     * @throws IOException if an I/O error occurs while sending the message
     */
    public static void movePlayerToLobby(WebSocketSession session, Lobby lobby, List<Player> players) throws IOException {
        Player player = new Player(session, lobby);
        players.add(player);
        lobby.getPlayers().add(player);
        session.sendMessage(new TextMessage("{ 'type':'LOBBY_ASSIGNED' }"));
    }

    /**
     * Finds an appropriate lobby for the player associated with the given session.
     * If the current lobby is null or full, a new lobby is created.
     * The player is then moved to the appropriate lobby.
     *
     * @param session the WebSocket session of the player to be assigned to a lobby
     * @param players the list of all players
     * @throws IOException if an I/O error occurs while sending the message
     */
    public static void findLobby(WebSocketSession session, List<Player> players) throws IOException {
        if(currentlobby == null|| currentlobby.getPlayers().size()==4){
            currentlobby = createLobby();
        }
        movePlayerToLobby(session,currentlobby,players);
    }

}
