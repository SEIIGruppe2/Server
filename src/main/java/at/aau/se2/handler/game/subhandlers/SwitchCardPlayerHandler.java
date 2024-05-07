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

    int passiveAnswer;

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        try {
            String[] msgContent= messageContent(msg);
            String switchUsername=msgContent[1];
            List<Actioncard> cards = UtilityMethods.findPlayer(session, lobby).getCards();
            Actioncard currentCard = null;

            int cardid=getIdOfCard(msgContent);

            for(Actioncard c : cards){
                if(c.getId() == cardid){

                    cards.remove(c);
                    currentCard=c;
                    break;
                }
            }

            List<Player> players = GameHandler.getPlayersofGame();
            for(Player p : players){
                if(p.getUsername().equals(switchUsername)){
                    p.getCards().add(currentCard);
                    WebSocketSession switchSession= p.getSession();
                    switchSession.sendMessage(new TextMessage(convertToJSONrequest(currentCard, UtilityMethods.findUsernameOfPlayer(session))));
                    break;
                }
            }

        }
        catch(PlayerNotFoundException p){
            Logger.getLogger("global")
                    .info("PLAYER NOT IN LOBBY (SwitchCardDeckHandler)");
        } catch (IOException e) {
            Logger.getLogger("global")
                    .info(e.getMessage() + " (SwitchCardDeckHandler)");
        }

    }
    public String[] messageContent(JsonNode msg){
        String[] infos = new String[4];
        infos[0] = msg.path("type").asText();
        infos[1] = msg.path("switchedWith").asText();
        infos[2] = msg.path("cardGiven").asText();
        infos[3] = msg.path("cardGivenP").asText();
        return infos;
    }

    public String convertToJson(Actioncard card){
        return "{'type':'SWITCH_CARD_PLAYER_RESPONSE', " +
                card.convertToJson() + "}";
    }

    public String convertToJSONrequest(Actioncard card, String username){
        return "{'type':'SWITCH_CARD_PLAYER_RESPONSE', 'switchedWith':'"+username+"', " +
                card.convertToJson() + "}";
    }

    public int getIdOfCard(String[] message){

        if(message[2].equals("null")){
            passiveAnswer =1;
            return Integer.parseInt(message[3]);
        }
        else{
            passiveAnswer =0;
            return Integer.parseInt(message[2]);
        }

    }
}
