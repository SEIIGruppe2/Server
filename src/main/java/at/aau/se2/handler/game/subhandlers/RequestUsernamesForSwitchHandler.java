package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RequestUsernamesForSwitchHandler extends RequestUsernamesHandler{

    public ArrayList<String> usernames = new ArrayList<>();
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try{
            List<Player> players = List.copyOf(GameHandler.getPlayersofGame());

            for(Player a:players){
                if(!a.getSession().equals(session)){
                    usernames.add(a.getUsername());
                }
            }
            session.sendMessage(new TextMessage(convertToJson()));
        }catch(IOException i){
            Logger.getLogger("global")
                    .info(i.getMessage() + "(RequestUsernamesHandler)");
        }
    }

    @Override
    public String convertToJson() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ 'type': 'REQUEST_USERNAMES_SWITCH', 'usernames': [");

        for(String u : usernames){
            if(usernames.get(usernames.size()-1).equals(u))
                builder.append("'").append(u).append("']}");
            else
                builder.append("'").append(u).append("',");
        }
        return builder.toString();
    }
}
