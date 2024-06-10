package at.aau.se2.service;

import at.aau.se2.exceptions.CardNotFoundException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.model.Actioncard;
import at.aau.se2.utils.Player;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public class SCPHService {

    public static int passiveSwitch=0;
    public static String findUsernameOfPlayer(WebSocketSession session) throws PlayerNotFoundException {
        for(Player a: GameHandler.getPlayers()){
            if(a.getSession().equals(session)){
                return a.getUsername();

            }
        }
        throw new PlayerNotFoundException();
    }

    public static Player findPlayerByUsername(String username) throws PlayerNotFoundException {
        for(Player p: GameHandler.getPlayers()){
            if(p.getUsername().equals(username)){
                return p;
            }
        }
        throw new PlayerNotFoundException();
    }

    public static Actioncard cardRemoval(int id, List<Actioncard> cards) throws CardNotFoundException {
        for(Actioncard c : cards){
            if(c.getId() == id){
                cards.remove(c);
                return c;
            }
        }
        throw new CardNotFoundException("SwitchCardPlayerHandler handleMessage Method: Card not found!");
    }

    public static String[] getMessageContent(JsonNode node){
        String[] content = new String[4];
        content[0] = node.path("type").asText();
        content[1] = node.path("switchedWith").asText();
        content[2] = node.path("cardGiven").asText();
        content[3] = node.path("cardGivenP").asText();
        return content;
    }

    public static int getCardId(String[] content){
        return (content[2].equals("null")) ?
                Integer.parseInt(content[3])
                : Integer.parseInt(content[2]);
    }
    public static void setPassiveSwitch(String[] content){
        if(content[2].equals("null")){
            passiveSwitch=1;
        }else{
            passiveSwitch=0;
        }
    }

    public static String convertToJson(Actioncard card){

        return "{'type':'SWITCH_CARD_PLAYER_RESPONSE', " +
                card.convertToJson() + "}";

    }

    public static String convertToJSONrequest(Actioncard card, String username){
        if(passiveSwitch==0) {
            return "{'type':'SWITCH_CARD_PLAYER_RESPONSE1', 'switchedWith':'" + username + "', " +
                    card.convertToJson() + "}";
        }
        return "{'type':'SWITCH_CARD_PLAYER_RESPONSE', 'switchedWith':'" + username + "', " +
                card.convertToJson() + "}";
    }
}
