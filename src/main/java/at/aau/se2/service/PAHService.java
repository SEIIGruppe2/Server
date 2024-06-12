package at.aau.se2.service;

import at.aau.se2.model.Actioncard;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class PAHService {
    /**
     * Reads information from the given JSON message and extracts specific fields.
     * The extracted information includes the monster ID and card ID.
     *
     * @param msg the JsonNode representing the JSON message
     * @return a String array containing the monster ID and card ID
     */
    public static String[] readInfosFromMessage(JsonNode msg){
        String[] arr = new String[2];
        arr[0] = msg.path("monsterid").asText();
        arr[1] = msg.path("cardid").asText();
        return arr;
    }

    /**
     * Retrieves an Actioncard from a list of cards based on the specified card ID.
     *
     * @param id the ID of the card to be retrieved
     * @param cards the list of Actioncards to search through
     * @return the Actioncard with the specified ID, or null if no such card is found
     */
    public static Actioncard getCard(int id, List<Actioncard> cards){
        for(Actioncard card : cards){
            if(card.getId() == id) return card;
        }
        return null;
    }
}
