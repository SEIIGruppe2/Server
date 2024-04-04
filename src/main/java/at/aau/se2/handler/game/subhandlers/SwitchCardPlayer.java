package at.aau.se2.handler.game.subhandlers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.web.socket.WebSocketSession;

public class SwitchCardPlayer implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg){
        // communication between two players
    }

    public String[][] readInfosFromMessage(JsonNode msg){
        return new String[0][0];
    }
}
