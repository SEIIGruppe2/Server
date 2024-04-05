package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.gameutils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

public class SwitchCardPlayer implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        // communication between two players
    }

    public String[][] readInfosFromMessage(JsonNode msg){
        return new String[0][0];
    }
}
