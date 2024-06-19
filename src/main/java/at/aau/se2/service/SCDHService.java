package at.aau.se2.service;

import at.aau.se2.exceptions.CardNotFoundException;
import at.aau.se2.model.Actioncard;
import com.fasterxml.jackson.databind.JsonNode;

import java.security.SecureRandom;
import java.util.List;

public class SCDHService extends DCHService{
    /**
     * Constructs a new SCDHService with the specified SecureRandom instance.
     *
     * @param rn the SecureRandom instance to be used for generating random values
     */
    public SCDHService(SecureRandom rn) {
        super(rn);
    }

    /**
     * Reads the card ID from the given JSON message.
     *
     * @param msg the JsonNode representing the JSON message
     * @return the card ID as a String
     */
    public static String readCardId(JsonNode msg){
        return msg.path("cardid").asText();
    }

    /**
     * Converts the given Actioncard object to a JSON string representation.
     *
     * @param card the Actioncard object to be converted to JSON
     * @return a JSON string representing the Actioncard object
     */
    public static String convertToJson(Actioncard card){
        return "{'type':'SWITCH_CARD_DECK_RESPONSE', " +
                card.convertToJson() + "}";
    }

    /**
     * Adds a card to the list of cards at the position of the card with the specified ID.
     * If a card with the given ID is found, the new card is added at its position.
     *
     * @param id the ID of the card to be replaced
     * @param card the Actioncard to be added
     * @param cards the list of Actioncards to which the card will be added
     * @return the added Actioncard
     * @throws CardNotFoundException if no card with the specified ID is found in the list
     */
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
