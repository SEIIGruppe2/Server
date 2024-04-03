package at.aau.se2.handler.game;

import at.aau.se2.gameutils.GameState;
import at.aau.se2.gameutils.Lobby;
import at.aau.se2.gameutils.Player;
import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameHandler implements WebSocketHandler {
    /*private Map<String, WebSocketSession> sessions = new HashMap<>();
    private List<String> connectionOrder = new ArrayList<>();
    private List<Lobby> lobbies = new ArrayList();*/
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        /*connectionOrder.add(session.getId());
        sessions.put(session.getId(), session);
        if(connectionOrder.size() % 4 == 0){
            Lobby lobby = createLobby();
            lobbies.add(lobby);
            for(int i = 0; i < 4; i++){
                String sesKey = connectionOrder.remove(0);
                Player player = new Player(sesKey, lobby);
                lobby.getPlayers().add(player);
            }
        }*/
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        /*for(Lobby lobby : lobbies){
            for(Player player : lobby.getPlayers()){
                if(player.getPlayerID() == session.getId()){
                    for(Player exit : lobby.getPlayers()){
                        lobby.getPlayers().remove(exit);
                        session = sessions.remove(exit.getPlayerID());
                        session.close();
                    }
                    lobbies.remove(lobby);
                    break;
                }
            }
        }*/
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    public void broadcastChangedGameState(WebSocketSession session){
        //String gameStateJson = gameStateToJson()
       /* for(Lobby lobby : lobbies){
            for(Player player : lobby.getPlayers()){
                if(player.getPlayerID() == session.getId()){
                    for(Player update : lobby.getPlayers()){
                        session.sendMessage(new WebSocketMessage() {
                        });
                    }
                }
            }
        }*/
    }
    public Lobby createLobby(){
        return new Lobby(new GameState());
    }
    public void movePlayerToLobby(WebSocketSession session, Lobby lobby){
        // Player player = new Player(session, lobby);
        //lobby.getPlayers().add(player);
    }

    public void handlePlayerAttack(Monster monster, Actioncard card){
        card.doesDmg(monster);
    }
}
