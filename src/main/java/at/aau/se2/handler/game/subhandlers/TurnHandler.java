package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
public class TurnHandler implements ActionHandler {

    private static final Logger logger = Logger.getLogger(TurnHandler.class.getName());
    private int currentPlayerIndex = 0;
    private int playerCount = 4;
    private int turnCount;



    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        String type = msg.path("type").asText();
        String lastturnCount = msg.path("turn").asText();
        turnCount = Integer.parseInt(lastturnCount);
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

        turnCount++;

        //1==0
        //2==1
        //3==2
        //4==3
        //5==0
        currentPlayerIndex = (turnCount + 3) % playerCount;
        logger.info("Starting next turn for player index " + currentPlayerIndex+ "turn"+turnCount);


    }

    private void notifyAllPlayers(Lobby lobby) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode messageNode = objectMapper.createObjectNode();
        List<Player> players = lobby.getPlayers();
        String currentPlayer = players.get(currentPlayerIndex).getUsername();


        messageNode.put("type", "CURRENT_PLAYER");
        messageNode.put("currentPlayer",currentPlayer);
        messageNode.put("turnCount", turnCount);
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


