package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class RegisterUsernameHandler implements ActionHandler{

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        try {String username = msg.path("username").asText();
        String response;
        if(!UtilityMethods.checkUsername(username)) {
            GameHandler.getUsernames().add(username);
           List<Player> players=GameHandler.getPlayersofGame();
           Player currentplayer= UtilityMethods.findPlayer(session,players);
           currentplayer.setUsername(username);


            response = "accepted";

        }
        else{
            response = "denied";
        }

            session.sendMessage(
                    new TextMessage("{ 'type': 'REGISTER_USERNAME', 'response' : '" +
                            response +
                            "'}"));
        }
        catch (PlayerNotFoundException e){
            Logger.getLogger("global")
                    .info(e.getMessage() + "(PlayerNotFoundException)");
        }
        catch(IOException i){
            Logger.getLogger("global")
                    .info(i.getMessage() + "(RegisterUsernameHandler)");
        }
    }
}
