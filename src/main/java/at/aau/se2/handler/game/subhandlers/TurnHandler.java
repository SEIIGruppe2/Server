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

    private static final Logger logger = Logger.getLogger(TurnHandler.class.getName());
    private int currentPlayerIndex = 0;
    private int playerCount = 4; // Anzahl der Spieler

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        String type = msg.path("type").asText();
        if ("END_TURN".equals(type)) {
            endCurrentTurn();
            startNextTurn();
            notifyAllPlayers(lobby);
        }
    }

    private void endCurrentTurn() {
        logger.info("Ending turn for player index " + currentPlayerIndex);
    }

    private void startNextTurn() {
        currentPlayerIndex = (currentPlayerIndex + 1) % playerCount;
    }

    private void notifyAllPlayers(Lobby lobby) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode messageNode = objectMapper.createObjectNode();
        String currentPlayerIndexString = String.valueOf(currentPlayerIndex);
        messageNode.put("type", "CURRENT_PLAYER");
        messageNode.put("index", currentPlayerIndexString);
        String message = messageNode.toString();

        for (Player player : lobby.getPlayers()) {
            try {
                player.getSession().sendMessage(new TextMessage(message));

            } catch (IOException e) {
                logger.severe("Failed to notify player " + ": " + e.getMessage());
            }
        }
    }
}


