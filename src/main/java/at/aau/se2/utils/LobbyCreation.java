package at.aau.se2.utils;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

public class LobbyCreation {
    public static Lobby createLobby(){
        return new Lobby(new GameState());
    }
    public static void movePlayerToLobby(WebSocketSession session, Lobby lobby, List<Player> players) throws IOException {
        Player player = new Player(session, lobby);
        players.add(player);
        lobby.getPlayers().add(player);
        session.sendMessage(new TextMessage("{ 'type':'LOBBY_ASSIGNED' }"));
    }
    public static int makeLobby(List<WebSocketSession> connectionOrder, int nextPlayer, List<Player> players) throws IOException {
        Lobby lobby = createLobby();
        for(int i = 0; i < 4; i++){
            // session basierend auf ID ausgeben
            movePlayerToLobby(connectionOrder.get(nextPlayer++), lobby, players);
        }
        return nextPlayer;
    }
}
