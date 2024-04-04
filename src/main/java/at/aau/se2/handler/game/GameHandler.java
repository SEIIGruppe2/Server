package at.aau.se2.handler.game;

import at.aau.se2.gameutils.GameState;
import at.aau.se2.gameutils.Lobby;
import at.aau.se2.gameutils.Player;
import at.aau.se2.handler.game.subhandlers.*;
import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameHandler implements WebSocketHandler {
    private Map<String, WebSocketSession> sessions = new HashMap<>();
    private Map<String, ActionHandler> handlers = new HashMap();
    private List<String> connectionOrder = new ArrayList<>();
    private List<Lobby> lobbies = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    public GameHandler(){
        handlers.put("DRAW_CARD", new DrawCardHandler());
        handlers.put("SWITCH_CARD_DECK", new SwitchCardDeckHandler());
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        connectionOrder.add(session.getId());
        sessions.put(session.getId(), session);
        if(connectionOrder.size() % 4 == 0){
            Lobby lobby = createLobby();
            lobbies.add(lobby);
            for(int i = 0; i < 4; i++){
                movePlayerToLobby(sessions.get(connectionOrder.remove(0)), lobby);
            }
        }
        else
            session.sendMessage(new TextMessage("Waiting for other players to connect."));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String msg = (String)message.getPayload();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(msg);
        String type = node.path("type").asText();
        type = type.toUpperCase();
        switch(type){
            case "DRAW_CARD" -> {
                DrawCardHandler handler = new DrawCardHandler();
                handler.handleMessage(session, node);
            }
            default -> { break; }
        }
        broadcastChangedGameState(session);

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // No Transport Error Handling yet
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        Lobby lobby = findLobby(session);
        if(lobby != null){
            for(Player player : lobby.getPlayers()){
                player.getSession().sendMessage(new TextMessage("The game has finished, you will be disconnected"));
                sessions.remove(player.getPlayerID());
                players.remove(player);
            }
            lobbies.remove(lobby);
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
    public void broadcastChangedGameState(WebSocketSession session) throws IOException {
        //String gameStateJson = gameStateToJson()
        Lobby lobby = findLobby(session);
        if(lobby != null) {
            for (Player player : lobby.getPlayers()) {
                //player.getSession().sendMessage(new TextMessage(gameStateJson));
                player.getSession().sendMessage(new TextMessage("GameStatus Update"));
            }
        }
        else
            session.sendMessage(new TextMessage("GameStatus Update nicht möglich"));
    }

    private Lobby findLobby(WebSocketSession session) {
        for(Player player : players){
            if(player.getSession() == session){
                return player.getLobby();
            }
        }
        return null;
    }

    public Lobby createLobby(){
        return new Lobby(new GameState());
    }
    public void movePlayerToLobby(WebSocketSession session, Lobby lobby) throws IOException {
        Player player = new Player(session, lobby);
        players.add(player);
        lobby.getPlayers().add(player);
        session.sendMessage(new TextMessage("You have been loaded into a lobby!"));
    }

    public void handlePlayerAttack(Monster monster, Actioncard card){
        card.doesDmg(monster);
    }
}
