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

    int passiveanswer;

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        try {
            String[] textfrommessage= inhaltvonnachricht(msg);
            System.out.println("String handle message ausgeführt");
            String usernametoswitchwith=textfrommessage[1];
            List<Actioncard> cards = UtilityMethods.findPlayer(session, lobby).getCards();
            Actioncard currentcard = null;

            int cardid=getidofcard(textfrommessage);

            for(Actioncard c : cards){
                if(c.getId() == cardid){

                    cards.remove(c);
                    currentcard=c;
                    break;
                }
            }


            List<Player> players = GameHandler.getPlayers();
            for(Player p : players){
                if(p.getUsername().equals(usernametoswitchwith)){
                    p.getCards().add(currentcard);
                    WebSocketSession sessionofusertoswitchwith= p.getSession();
                    sessionofusertoswitchwith.sendMessage(new TextMessage(convertToJSONrequest(currentcard, UtilityMethods.findusernameofPlayer(session))));
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
    public String[]  inhaltvonnachricht(JsonNode msg){
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

    public int getidofcard(String[] message){

        if(message[2].equals("null")){
            passiveanswer=1;
            return Integer.parseInt(message[3]);
        }
        else{
            passiveanswer=0;
            return Integer.parseInt(message[2]);
        }

    }
}
