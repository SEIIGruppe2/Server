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

public class GameRoundHandler implements ActionHandler {

    // TODO: Refactor to Service, DTO and Handler
    private static final Logger logger = Logger.getLogger(GameRoundHandler.class.getName());
    private ObjectMapper objectMapper = new ObjectMapper();
    private int roundNumber = 0;


    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try {
            String type = msg.path("type").asText();
            if ("ROUND_COUNTER".equals(type)) {
                endRound(lobby);
                startNextRound(lobby);
            }
        } catch (Exception e) {
            logger.severe("An error occurred during round handling: " + e.getMessage());
        }
    }

    private void endRound(Lobby lobby) {
        logger.info("Ending round " + roundNumber);
    }

    private void startNextRound(Lobby lobby) {
        roundNumber++;
        for (Player player : lobby.getPlayers()) {
            sendStartRoundMessage(player.getSession());
        }
        logger.info("Starting round " + roundNumber);
    }

    private void sendStartRoundMessage(WebSocketSession session) {
        ObjectNode messageNode = objectMapper.createObjectNode();
        String roundMessage = String.valueOf(roundNumber);
        messageNode.put("type", "ROUND_COUNTER");
        messageNode.put("round", roundMessage);
        try {
            session.sendMessage(new TextMessage(messageNode.toString()));
        } catch (IOException e) {
            logger.severe("Failed to send start round message: " + e.getMessage());
        }
    }

}
