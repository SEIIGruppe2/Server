package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

public class MonsterAttackHandler implements ActionHandler {
    public String[][] readInfosFromMessage(JsonNode msg){
        return new String[0][0];
    }

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {

    }
}
