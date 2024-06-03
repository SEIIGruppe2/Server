package at.aau.se2.service;

import at.aau.se2.model.Actioncard;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class PAHService {
    public static String[] readInfosFromMessage(JsonNode msg){
        String[] arr = new String[2];
        arr[0] = msg.path("monsterid").asText();
        arr[1] = msg.path("cardid").asText();
        return arr;
    }

    public static Actioncard getCard(int id, List<Actioncard> cards){
        for(Actioncard card : cards){
            if(card.getId() == id) return card;
        }
        return null;
    }
}
