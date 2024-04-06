package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.gameutils.Lobby;
import at.aau.se2.gameutils.Player;
import at.aau.se2.gameutils.UtilityMethods;
import at.aau.se2.model.Monster;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;


public class PlayerAttackHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        String[] m = readInfosFromMessage(msg);
        try{
            UtilityMethods.findPlayer(session, lobby)
                    .getCards()
                    .get(Integer.parseInt(m[1]))
                    .doesDmg(lobby
                            .getGameState()
                            .getMonsters()
                            .get(Integer.parseInt(m[0])));
        }
        catch(PlayerNotFoundException p){
            System.out.println(p.getMessage());
        }
    }
    public String[] readInfosFromMessage(JsonNode msg){
        String[] arr = new String[2];
        arr[0] = msg.path("monsterid").asText();
        arr[1] = msg.path("cardid").asText();
        return arr;
    }


}
