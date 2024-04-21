package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.model.Actioncard;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class SwitchCardDeckHandler extends DrawCardHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        try {
            String[] infos = readInfosFromMessage(msg);
            List<Actioncard> cards = UtilityMethods.findPlayer(session, lobby).getCards();
            Actioncard newCard = drawRandomCard(lobby);
            String test = infos[1];
            System.out.println("Test in der handlemessage"+infos[1]);
            for(Actioncard c : cards){
                if(c.getId() == Integer.parseInt(infos[1])){
                    cards.add(cards.indexOf(c), newCard);
                    break;
                }
            }
            session.sendMessage(new TextMessage(convertToJson(newCard)));

        }
        catch(PlayerNotFoundException p){
            Logger.getLogger("global")
                    .info("PLAYER NOT IN LOBBY (SwitchCardPlayerHandler)");
        }
        catch(IOException i){
            Logger.getLogger("global")
                    .info(i.getMessage() + " (SwitchCardPlayerHandler)");
        }

    }
    public String[]  readInfosFromMessage(JsonNode msg){
        String[] infos = new String[2];
        infos[0] = msg.path("type").asText();
        infos[1] = msg.path("cardid").asText();
        return infos;
    }
    @Override
    public String convertToJson(Actioncard card){
        return "{'type':'SWITCH_CARD_DECK_RESPONSE', " +
                card.convertToJson() + "}";
    }
}
