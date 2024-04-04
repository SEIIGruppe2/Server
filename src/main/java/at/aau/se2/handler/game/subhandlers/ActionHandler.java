package at.aau.se2.handler.game.subhandlers;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

public interface ActionHandler {
    public void handleMessage(WebSocketSession session, JsonNode msg);
}
