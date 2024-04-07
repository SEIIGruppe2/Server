package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.utils.JsonSerializable;
import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.logging.Logger;

public class RequestUsernamesHandler implements ActionHandler, JsonSerializable {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try{
            session.sendMessage(new TextMessage(convertToJson()));
        }catch(IOException i){
            Logger.getLogger("global")
                    .info(i.getMessage() + "(RequestUsernamesHandler)");
        }
    }

    @Override
    public String convertToJson() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ 'type': 'REQUEST_USERNAMES', 'usernames': [");
        for(String u : GameHandler.getUsernames()){
            if(GameHandler.getUsernames().get(GameHandler.getUsernames().size()-1).equals(u))
                builder.append("'").append(u).append("']}");
            else
                builder.append("'").append(u).append("',");
        }
        return builder.toString();
    }
}
