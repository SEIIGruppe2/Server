package at.aau.se2.handler.game.subhandlers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

public class SwitchCardDeckHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg){

    }
    public String[][] readInfosFromMessage(JsonNode msg){
        return new String[0][0];
    }
}
