package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Handles the end-game logic for a multiplayer game session.
 * This handler is responsible for processing end-game messages and notifying players
 * about the game outcome based on whether a winner exists or not.
 */
public class EndGameHandler implements ActionHandler {

    private static final Logger logger = Logger.getLogger(EndGameHandler.class.getName());
    private String hasWinner = " ";

    /**
     * Handles incoming messages for the end game scenario.
     * This method checks if the message type is 'END_GAME' and then processes it to determine
     * the game outcome and notify the corresponding player.
     *
     * @param session the WebSocket session object representing the connection to the client.
     * @param msg the JSON node containing the message payload.
     * @param lobby the game lobby containing details about all players and game state.
     */
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        String type = msg.path("type").asText();
        if (type.equals("END_GAME")) {
            hasWinner = msg.path("hasWinner").asText();
            notifyPlayer(session);
        }
    }

    /**
     * Notifies a player about the end game status via WebSocket.
     * This method constructs a JSON message that includes the game outcome and sends it to the player.
     *
     * @param session the WebSocket session through which the message is to be sent.
     */
    private void notifyPlayer(WebSocketSession session) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode messageNode = objectMapper.createObjectNode();
        messageNode.put("type", "END_GAME");
        messageNode.put("hasWinner", hasWinner);
        String message = messageNode.toString();

        try {
            session.sendMessage(new TextMessage(message));
        } catch (IOException e) {
            logger.severe("Failed to notify player: " + e.getMessage());
        }
    }
}
