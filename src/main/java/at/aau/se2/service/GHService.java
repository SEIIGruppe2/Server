package at.aau.se2.service;

import at.aau.se2.exceptions.LobbyNotFoundException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.handler.game.subhandlers.*;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.Map;


import static at.aau.se2.service.LobbyService.findLobby;
import static at.aau.se2.utils.UtilityMethods.findPlayer;
import static at.aau.se2.utils.UtilityMethods.logi;

public class GHService {
    // region Members
    private final GameHandler handler;
    private final List<Player> players;
    private static int nextPlayer = 0;
    private final Map<String, ActionHandler> handlers;

    // endregion

    // region Constructor
    /**
     * Constructs a new GHService with the specified GameHandler instance.
     * Initializes the handler map with predefined action handlers.
     *
     * @param handler the GameHandler instance to be used by this service
     */
    public GHService(GameHandler handler){
        this.handler = handler;
        this.handlers = handler.getHandlers();
        this.players = GameHandler.getPlayers();
        fillHandlerMap();
    }

    // endregion

    // region Methods
    /**
     * Sets the next player index by adding the specified value.
     *
     * @param val the value to be added to the next player index
     */
    public static void setNextPlayer(int val){
        nextPlayer += val;
    }

    /**
     * Fills the handler map with predefined action handlers for various game actions.
     * Each handler is associated with a specific action key.
     */
    private void fillHandlerMap(){
        this.handlers.put(
                "DRAW_CARD",
                new DrawCardHandler()
        );
        this.handlers.put(
                "SWITCH_CARD_DECK",
                new SwitchCardDeckHandler()
        );
        this.handlers.put(
                "SWITCH_CARD_PLAYER",
                new SwitchCardPlayerHandler()
        );
        this.handlers.put(
                "MONSTER_ATTACK",
                new MonsterAttackHandler()
        );
        this.handlers.put(
                "REGISTER_USERNAME",
                new RegisterUsernameHandler()
        );
        this.handlers.put(
                "REQUEST_USERNAMES",
                new RequestUsernamesHandler()
        );
        this.handlers.put(
                "SPAWN_MONSTER",
                new SpawnMonsterHandler()
        );
        this.handlers.put(
                "PLAYER_ROLL_DICE",
                new PlayerRollsDiceHandler()
        );
        this.handlers.put(
                "ROUND_COUNTER",
                new GameRoundHandler()
        );
        this.handlers.put(
                "END_TURN",
                new TurnHandler()
        );
        this.handlers.put(
                "SHOW_MONSTERS",
                new ShowMonstersHandler()
        );
        this.handlers.put(
                "CARD_ATTACK_MONSTER",
                new PlayerAttackHandler()
        );
        this.handlers.put(
                "REQUEST_USERNAMES_SWITCH",
                new RequestUsernamesForSwitchHandler()
        );
        this.handlers.put(
                "END_GAME",
                new EndGameHandler()
        );
        this.handlers.put(
                    "CHEAT_MODE",
                new CheatModeHandler()
        );
        this.handlers.put(
                "PLAYER_TROPHIES",
                new PlayerTrophiesHandler()
        );
        this.handlers.put(
                "ACCUSATION_MSG",
                new AccusationHandler()
        );

    }

    /**
     * Parses the payload of a WebSocketMessage into a JsonNode.
     *
     * @param message the WebSocketMessage containing the JSON payload
     * @return a JsonNode representing the parsed JSON content
     * @throws JsonProcessingException if an error occurs while processing the JSON payload
     */
    public JsonNode getMessage(WebSocketMessage<?> message) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree((String)message.getPayload());
    }

    /**
     * Routes a message based on its type to the appropriate handler.
     *
     * @param type the type of message to route
     * @param session the WebSocket session from which the message was received
     * @param node the JsonNode representing the message content
     * @throws LobbyNotFoundException if the lobby associated with the session is not found
     * @throws PlayerNotFoundException if the player associated with the session is not found
     */
    public void makeRouting(String type, WebSocketSession session, JsonNode node)
            throws LobbyNotFoundException, PlayerNotFoundException {
        if(type.equals("DRAW_CARD")){
            for(int i = findPlayer(session, this.players).getCards().size()-1;i < 5; i++) {
                logi("DrawCARD handler will be called");
                handlers.get(type).handleMessage(session, node, UtilityMethods.findLobby(session, this.players));
            }
        }
        else if (type.equals("ROUND_END")){
            findPlayer(session, this.players).getLobby().getGameState().increaseRound();
        }
        else {
            ActionHandler handler = handlers.get(type);
            handler.handleMessage(session, node, UtilityMethods.findLobby(session, this.players));
        }
    }

    /**
     * Broadcasts the updated game state to all players in the lobby.
     *
     * @param session the WebSocket session of the player initiating the broadcast
     * @throws IOException if an I/O error occurs while sending the message
     * @throws LobbyNotFoundException if the lobby associated with the session is not found
     */
    public void broadcastChangedGameState(WebSocketSession session)
            throws IOException, LobbyNotFoundException {
        Lobby lobby = UtilityMethods.findLobby(session, this.players);
        if(lobby != null) {
            for (Player player : lobby.getPlayers()) {
                player.getSession().sendMessage(new TextMessage(lobby.getGameState().convertToJson()));
            }
        }
        else
            sendMessage(session, "ERROR_GAMESTATUS", "GameStatus Update nicht möglich");
    }

    /**
     * Sends a message to a WebSocket session.
     *
     * @param session the WebSocket session to which the message will be sent
     * @param type the type of message being sent
     * @param content the content of the message
     * @throws IOException if an I/O error occurs while sending the message
     */
    public void sendMessage(WebSocketSession session, String type, String content)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode messageNode = mapper.createObjectNode();
        messageNode.put("type", type);
        messageNode.put("content", content);
        session.sendMessage(new TextMessage(messageNode.toString()));
    }

    /**
     * Sends a waiting message to a player after a connection is established.
     *
     * @param session the WebSocket session of the player who established the connection
     * @throws IOException if an I/O error occurs while sending the message
     */
    public void helpAfterConnectionEstablished(WebSocketSession session)
            throws IOException {
        findLobby(session, players);
        this.sendMessage(session, "WAITING_FOR_PLAYERS", "Waiting for other players to connect.");
    }

    /**
     * Handles the necessary cleanup and notifications after a connection is closed.
     *
     * @param session the WebSocket session that was closed
     * @throws LobbyNotFoundException if the lobby associated with the session is not found
     * @throws IOException if an I/O error occurs while sending messages
     */
    public void helpAfterConnectionClosed(WebSocketSession session)
            throws LobbyNotFoundException, IOException {
        Lobby lobby = UtilityMethods.findLobby(session, players);
        if(lobby != null){
            for(Player player : lobby.getPlayers()){
                this.handler.getConnectionOrder().remove(player.getSession());
                setNextPlayer(-1);
                this.sendMessage(player.getSession(), "GAME_FINISHED", "The game has finished, you will be disconnected");
                player.getSession().close();

                players.remove(player);
            }
        }
    }

    // endregion
}
