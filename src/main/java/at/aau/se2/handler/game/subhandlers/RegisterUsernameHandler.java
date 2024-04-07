package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.logging.Logger;

public class RegisterUsernameHandler implements ActionHandler{

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        String username = msg.path("username").asText();
        String response;
        if(!UtilityMethods.checkUsername(username)) {
            GameHandler.getUsernames().add(username);
            response = "accepted";
        }
        else{
            response = "denied";
        }
        try {
            session.sendMessage(
                    new TextMessage("{ 'type': 'REGISTER_USERNAME', 'response' : '" +
                            response +
                            "'}"));
        }
        catch(IOException i){
            Logger.getLogger("global")
                    .info(i.getMessage() + "(RegisterUsernameHandler)");
        }
    }
}
