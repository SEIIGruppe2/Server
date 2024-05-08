package at.aau.se2.service;

import at.aau.se2.exceptions.CardNotFoundException;
import at.aau.se2.model.Actioncard;
import com.fasterxml.jackson.databind.JsonNode;

import java.security.SecureRandom;
import java.util.List;

public class SCDHService extends DCHService{
    public SCDHService(SecureRandom rn) {
        super(rn);
    }

    public static String readCardId(JsonNode msg){
        return msg.path("cardid").asText();
    }

    public static String convertToJson(Actioncard card){
        return "{'type':'SWITCH_CARD_DECK_RESPONSE', " +
                card.convertToJson() + "}";
    }

    public static Actioncard addCard(int id, Actioncard card, List<Actioncard> cards) throws CardNotFoundException {
        for(Actioncard c : cards){
            if(c.getId() == id){
                cards.add(cards.indexOf(c), card);
                return card;
            }
        }
        throw new CardNotFoundException("SCDHService addCard");
    }
}
