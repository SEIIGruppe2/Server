package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.model.Monster;
import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class ShowMonstersHandler implements ActionHandler {
    public ArrayList<String> monsters = new ArrayList<>();


    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        String type = msg.path("type").asText();
        String cardid = msg.path("cardid").asText();
        List<Monster> monsters= lobby.getGameState().getMonsters();



    }


    private String convertToJSON() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ 'type': 'SHOW_MONSTERS', 'monstersid: [");

        for(String m : monsters){
            if(monsters.get(monsters.size()-1).equals(m))
                builder.append("'").append(m).append("']}");
            else
                builder.append("'").append(m).append("',");
        }
        return builder.toString();
    }

}
