package at.aau.se2.handler.game;

import at.aau.se2.exceptions.LobbyNotFoundException;
import at.aau.se2.utils.GameState;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import at.aau.se2.utils.UtilityMethods;
import at.aau.se2.handler.game.subhandlers.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static at.aau.se2.utils.UtilityMethods.findPlayer;


public class GameHandler implements WebSocketHandler {
    private final Logger logger;
    private static GameHandler GAMEHANDLER;
    private final Map<String, WebSocketSession> sessions = new HashMap<>();
    private final Map<String, ActionHandler> handlers = new HashMap<>();
    private final List<WebSocketSession> connectionOrder = new ArrayList<>();
    private final static List<Player> players = new ArrayList<>();
    @Getter
    private final static List<String> usernames = new ArrayList<>();
    private static int nextPlayer = 0;

    public static void setNextPlayer(int val){
        nextPlayer += val;
    }

    private GameHandler(){
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        handlers.put("DRAW_CARD", new DrawCardHandler());
        handlers.put("SWITCH_CARD_DECK", new SwitchCardDeckHandler());
        handlers.put("SWITCH_CARD_PLAYER", new SwitchCardPlayerHandler());
        handlers.put("PLAYER_ATTACK", new PlayerAttackHandler());
        handlers.put("MONSTER_ATTACK", new MonsterAttackHandler());
        handlers.put("REGISTER_USERNAME", new RegisterUsernameHandler());
        handlers.put("REQUEST_USERNAMES", new RequestUsernamesHandler());
        handlers.put("SPAWN_MONSTER", new SpawnMonsterHandler(new SecureRandom()));
        handlers.put("REQUEST_USERNAMES_SWITCH", new RequestUsernamesForSwitchHandler());
    }

    public static GameHandler getInstance(){
        if(GAMEHANDLER == null)
            GAMEHANDLER = new GameHandler();
        return GAMEHANDLER;
    }



    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        connectionOrder.add(session);
        logger.info("Connection established; SessionID: " + session.getId());
       if(connectionOrder.size() >= 4){
            Lobby lobby = createLobby();
            for(int i = 0; i < 4; i++){
                // session basierend auf ID ausgeben
                movePlayerToLobby(connectionOrder.get(nextPlayer), lobby);
                setNextPlayer(1);
            }
        }
        else {
            session.sendMessage(new TextMessage("Waiting for other players to connect."));
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String msg = (String)message.getPayload();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(msg);
        String type = node.path("type").asText();

        type = type.toUpperCase();
        if(type.equals("DRAW_CARD")){
            for(int i = findPlayer(session, players).getCards().size()-1;i < 5; i++) {
                handlers.get(type).handleMessage(session, node, UtilityMethods.findLobby(session, players));
            }
        }
        else if (type.equals("ROUND_END")){
            findPlayer(session, players).getLobby().getGameState().increaseRound();
        }
        else {
            ActionHandler handler = handlers.get(type);
            handler.handleMessage(session, node, UtilityMethods.findLobby(session, players));
        }
        broadcastChangedGameState(session);
        logger.info("HandleMessage processed");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // No Transport Error Handling yet
        throw new Exception();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("Connection closed, SessionID: " + session.getId());
        Lobby lobby = UtilityMethods.findLobby(session, players);
        if(lobby != null){
            for(Player player : lobby.getPlayers()){
                connectionOrder.remove(player.getSession());
                setNextPlayer(-1);
                player.getSession().sendMessage(new TextMessage("The game has finished, you will be disconnected"));
                player.getSession().close();

                players.remove(player);
            }
        }
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void broadcastChangedGameState(WebSocketSession session) throws IOException, LobbyNotFoundException {
        Lobby lobby = UtilityMethods.findLobby(session, players);
        if(lobby != null) {
            for (Player player : lobby.getPlayers()) {
                player.getSession().sendMessage(new TextMessage(lobby.getGameState().convertToJson()));
            }
        }
        else
            session.sendMessage(new TextMessage("GameStatus Update nicht möglich"));
    }

    public Lobby createLobby(){
        return new Lobby(new GameState());
    }

    public void movePlayerToLobby(WebSocketSession session, Lobby lobby) throws IOException {
        Player player = new Player(session, lobby);
        players.add(player);
        lobby.getPlayers().add(player);
        session.sendMessage(new TextMessage("{ 'type':'LOBBY_ASSIGNED' }"));
    }

    public static List<String> getUsernames() {
        return usernames;
    }

    public static List<Player> getPlayersofGame() {
        return players;
    }
}
