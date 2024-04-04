package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.gameutils.Player;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Setter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.web.socket.WebSocketSession;

public class SwitchCardDeckHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg){

    }
    public String[][] readInfosFromMessage(JsonNode msg){
        return new String[0][0];
    }
}
