package at.aau.se2.handler.game.subhandlers;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.springframework.messaging.MessagingException;
import org.springframework.web.socket.WebSocketSession;
@Data
public class DrawCardHandler implements ActionHandler {
    private WebSocketSession session;
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg){
        // generate a new card and send it to client
    }
}
