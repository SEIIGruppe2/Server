package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

public class SwitchCardDeckHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){

    }
    public String[][] readInfosFromMessage(JsonNode msg){
        return new String[0][0];
    }
}
