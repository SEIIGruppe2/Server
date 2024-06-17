package at.aau.se2.service;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class SHMHService {

    public static String readInfosFromMessage(JsonNode msg){
        String arr =  msg.path("cardid").asText();
        return arr;
    }

    public static Actioncard getCard(int id, List<Actioncard> cards){
        for(Actioncard card : cards){
            if(card.getId() == id) return card;
        }
        return null;
    }

    public static int getRing(Actioncard c){
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

    public static List<String> addSearchedMonstersToList(List<Monster> monsters, int searchedring, int searchedzone){
        List<String>monsterids = new ArrayList<>();
        if(searchedring==4){
            for(Monster m:monsters){
                if(m.getZone()==searchedzone){
                    monsterids.add(String.valueOf(m.getId()));
                }
            }
        } else {
            for(Monster m:monsters){
                if(m.getZone()==searchedzone&&m.getRing()==searchedring){
                    monsterids.add(String.valueOf(m.getId()));
                }
            }
        }
        if(monsterids.isEmpty()){
            monsterids.add("-1");
        }
    return monsterids;
    }
}
