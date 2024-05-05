package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

public class MonsterAttackRequestHandler implements ActionHandler {


    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
       String cardid = readInfosFromMessage(msg);

    }

    public String readInfosFromMessage(JsonNode msg){

        return msg.path("card").asText();
    }

}