package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.model.Actioncard;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class SwitchCardPlayerHandler implements ActionHandler {

    String user ="Testuser";
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        try {
            String[] textfrommessage= inhaltvonnachricht(msg);
            System.out.println("Inhalt von nachricht");
            for(String a:textfrommessage){
                System.out.println(a);
            }
            List<Actioncard> cards = UtilityMethods.findPlayer(session, lobby).getCards();
            Actioncard currentcard;

            // TODO: karte vom spieler herausfinden, karte an anderen spieler senden mit anfrage zum tausch und dem eigenen usernamen
            //
            for(Actioncard c : cards){
                if(c.getId() == Integer.parseInt(textfrommessage[3])){
                    //für später gebrauchen wenn karte ersetzt wird cards.add(cards.indexOf(c), newCard);
                    currentcard=c;
                    break;
                }
            }
            List<String> usernames = GameHandler.getUsernames();
            List<Player> players = GameHandler.getPlayersofGame();
            for(Player p : players){
                /*if(p.getUsername == Integer.parseInt(textfrommessage[1])){
                    //für später gebrauchen wenn karte ersetzt wird cards.add(cards.indexOf(c), newCard);
                    currentcard=c;
                    break;
                }*/
            }
            //session.sendMessage(new TextMessage(convertToJson(currentcard)));

        }
        catch(PlayerNotFoundException p){
            Logger.getLogger("global")
                    .info("PLAYER NOT IN LOBBY (SwitchCardDeckHandler)");
        }


    }
    public String[]  inhaltvonnachricht(JsonNode msg){
        String[] infos = new String[4];
        infos[0] = msg.path("type").asText();
        infos[1] = msg.path("switchedWith").asText();
        infos[2] = msg.path("cardGiven").asText();
        infos[3] = msg.path("cardGivenP").asText();
        return infos;
    }

    public String convertToJson(Actioncard card){
        return "{'type':'SWITCH_CARD_DECK_RESPONSE', " +
                card.convertToJson() + "}";
    }

    public String convertToJSONrequest(Actioncard card){
        return "{'type':'SWITCH_CARD_DECK_RESPONSE', 'switchedWith':'"+user+"', " +
                card.convertToJson() + "}";
    }

    public String[][] readInfosFromMessage(JsonNode msg){
        return new String[0][0];
    }
}
