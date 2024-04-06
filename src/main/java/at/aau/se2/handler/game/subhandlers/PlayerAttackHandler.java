package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.model.Actioncard;
import at.aau.se2.model.characters.Archer;
import at.aau.se2.model.characters.Fighter;
import at.aau.se2.model.characters.Hero;
import at.aau.se2.model.characters.Knight;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;


public class PlayerAttackHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        String[] m = readInfosFromMessage(msg);
        try{
            Actioncard card = UtilityMethods
                                .findPlayer(session, lobby)
                                .getCards()
                                .get(Integer.parseInt(m[1]));
            card.doesDmg(lobby.getGameState().getMonsters().get(Integer.parseInt(m[0])));
            if(card instanceof Archer)
                lobby.getGameState().decreaseCardAmount(0);
            else if(card instanceof Fighter)
                lobby.getGameState().decreaseCardAmount(1);
            else if(card instanceof Knight)
                lobby.getGameState().decreaseCardAmount(2);
            else if(card instanceof Hero)
                lobby.getGameState().decreaseCardAmount(3);
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
