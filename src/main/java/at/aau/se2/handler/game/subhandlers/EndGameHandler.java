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


public class EndGameHandler implements ActionHandler {

    private static final Logger logger = Logger.getLogger(EndGameHandler.class.getName());
    String hasWinner = " ";

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        String type = msg.path("type").asText();
        if(type.equals("END_GAME")) {
            hasWinner = msg.path("hasWinner").asText();
            notifyAllPlayers(lobby);
        }

    }


    private void notifyAllPlayers(Lobby lobby) {

        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode messageNode = objectMapper.createObjectNode();
        messageNode.put("type", "END_GAME");
        messageNode.put("hasWinner",hasWinner);
        String message = messageNode.toString();

        for (Player player : lobby.getPlayers()) {
            try {
                synchronized( player.getSession()) {
                    player.getSession().sendMessage(new TextMessage(message));

                }
            } catch (IOException e) {
                logger.severe("Failed to notify player " + ": " + e.getMessage());
            }
        }
    }
}