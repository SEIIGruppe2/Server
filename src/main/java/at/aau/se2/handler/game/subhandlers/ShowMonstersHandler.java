package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static at.aau.se2.utils.UtilityMethods.logi;

public class ShowMonstersHandler implements ActionHandler {
    public ArrayList<String> monsterids;
    int searchedring;
    int searchedzone;
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try{
            monsterids = new ArrayList<>();
            String cardid = msg.path("cardid").asText();
            List<Monster> monsters= lobby.getGameState().getMonsters();
            List<Actioncard> cards = UtilityMethods.findPlayer(session,lobby.getPlayers()).getCards();
            for(Actioncard c:cards)
            if(c.getId() == Integer.parseInt(cardid)){
               searchedring = getRing(c);
               searchedzone= c.getZone();
            }
            addSearchedMonstersToList(monsters);
            session.sendMessage(new TextMessage(convertToJSON()));

        }catch (PlayerNotFoundException e) {
            logi("PLAYER NOT FOUND (ShowMonstersHandler)");
        }catch(IOException i){
            Logger.getLogger("global")
                    .info(i.getMessage() + "(ShowMonstersHandler)");
        }


    }


    private String convertToJSON() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ 'type': 'SHOW_MONSTERS', 'monstersid: [");

        for(String m : monsterids){
            if(monsterids.get(monsterids.size()-1).equals(m))
                builder.append("'").append(m).append("']}");
            else
                builder.append("'").append(m).append("',");
        }
        return builder.toString();
    }

    private int getRing(Actioncard c){
        switch (c.getName()){
            case "Bogenschütze":
                return 1;
            case "Ritter":
                return 2;
            case "Schwertkämpfer":
                return 3;
            case "Held":
                return 4;
            default:
                return 0;
        }
    }

    private void addSearchedMonstersToList(List<Monster> monsters){
        if(searchedring==4){
            for(Monster m:monsters){
                if(m.getZone()==searchedzone){
                    monsterids.add(String.valueOf(m.getId()));

                }
            }
        } else {
            for(Monster m:monsters){
                if(m.getZone()-1==searchedzone&&m.getRing()==searchedring){
                    monsterids.add(String.valueOf(m.getId()));

                }
            }
        }
    }

}
