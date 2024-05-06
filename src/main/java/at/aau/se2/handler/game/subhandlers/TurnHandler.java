package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.logging.Logger;
public class TurnHandler implements ActionHandler {

    private static final Logger logger = Logger.getLogger(GameRoundHandler.class.getName());
    private Lobby lobby;
    private int currentPlayerIndex = 0;


    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        String type = msg.path("type").asText();
        if ("END_TURN".equals(type)) {
            endCurrentTurn();
            startNextTurn();
            notifyPlayersCurrentTurn();

        }
    }

    private void endCurrentTurn() {
        logger.info("Ending turn for player index " + currentPlayerIndex);
    }

    private void startNextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % lobby.getPlayers().size();
    }

    private void notifyPlayersCurrentTurn() {
        Player currentPlayer = lobby.getPlayers().get(currentPlayerIndex);
        lobby.getPlayers().forEach(player -> sendMessageToPlayer(player.getSession(), currentPlayer));
    }

    private void sendMessageToPlayer(WebSocketSession session, Player currentPlayer) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode messageNode = objectMapper.createObjectNode();
        messageNode.put("type", "CURRENT_PLAYER");
        messageNode.put("username", currentPlayer.getUsername());
        try {
            session.sendMessage(new TextMessage(messageNode.toString()));
        } catch (IOException e) {
            logger.severe("Failed to send current player message: " + e.getMessage());
        }
    }
}

