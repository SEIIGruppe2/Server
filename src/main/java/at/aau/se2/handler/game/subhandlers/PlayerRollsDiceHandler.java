package at.aau.se2.handler.game.subhandlers;


import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import java.util.List;
import java.util.logging.Logger;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;



public class PlayerRollsDiceHandler implements ActionHandler {
    private static final Logger logger = Logger.getLogger(PlayerRollsDiceHandler.class.getName());
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try {
            Player playerToRoll = selectPlayerToRoll(lobby);
            if (playerToRoll != null) {
                String username = GameHandler.getUsernames().get(0);
                sendDiceRollRequest(playerToRoll.getSession(), username);
                movePlayerToEndOfQueue(lobby, playerToRoll);
            }
        }
        catch (Exception e){
            logger.warning("No players available to roll the dice.");
        }
    }


    private Player selectPlayerToRoll(Lobby lobby) {
        List<Player> players = lobby.getPlayers();
        return !players.isEmpty() ? players.get(0) : null;
    }

    private void movePlayerToEndOfQueue(Lobby lobby, Player player) {
        List<Player> players = lobby.getPlayers();
        if (players.remove(player)) {
            players.add(player);
        }
    }

    private void sendDiceRollRequest(WebSocketSession session, String username) {
        ObjectNode messageNode = objectMapper.createObjectNode();
        messageNode.put("type", "REQUEST_ROLL");
        messageNode.put("username", username);
        try {
            session.sendMessage(new TextMessage(messageNode.toString()));
        } catch (IOException e) {
            logger.severe("Failed to send dice roll request: " + e.getMessage());
        }
    }
}