package at.aau.se2.service;

import at.aau.se2.exceptions.CardNotFoundException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.model.Actioncard;
import at.aau.se2.utils.Player;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

public class SCPHService {

    public static int passiveSwitch=0;

    /**
     * Finds the username of the player associated with the given WebSocket session.
     *
     * @param session the WebSocket session of the player
     * @return the username of the player
     * @throws PlayerNotFoundException if the player associated with the session is not found
     */
    public static String findUsernameOfPlayer(WebSocketSession session) throws PlayerNotFoundException {
        for(Player a: GameHandler.getPlayers()){
            if(a.getSession().equals(session)){
                return a.getUsername();

            }
        }
        throw new PlayerNotFoundException();
    }

    /**
     * Finds the player associated with the given username.
     *
     * @param username the username of the player
     * @return the Player object associated with the given username
     * @throws PlayerNotFoundException if the player with the specified username is not found
     */
    public static Player findPlayerByUsername(String username) throws PlayerNotFoundException {
        for(Player p: GameHandler.getPlayers()){
            if(p.getUsername().equals(username)){
                return p;
            }
        }
        throw new PlayerNotFoundException();
    }

    /**
     * Removes a card with the specified ID from the given list of cards.
     *
     * @param id the ID of the card to be removed
     * @param cards the list of Actioncards to search through
     * @return the removed Actioncard
     * @throws CardNotFoundException if no card with the specified ID is found in the list
     */
    public static Actioncard cardRemoval(int id, List<Actioncard> cards) throws CardNotFoundException {
        for(Actioncard c : cards){
            if(c.getId() == id){
                cards.remove(c);
                return c;
            }
        }
        throw new CardNotFoundException("SwitchCardPlayerHandler handleMessage Method: Card not found!");
    }

    /**
     * Extracts specific fields from the given JSON node.
     * The extracted fields include type, switchedWith, cardGiven, and cardGivenP.
     *
     * @param node the JsonNode representing the JSON message
     * @return a String array containing the extracted fields
     */
    public static String[] getMessageContent(JsonNode node){
        String[] content = new String[4];
        content[0] = node.path("type").asText();
        content[1] = node.path("switchedWith").asText();
        content[2] = node.path("cardGiven").asText();
        content[3] = node.path("cardGivenP").asText();
        return content;
    }

    /**
     * Gets the card ID from the specified message content.
     *
     * @param content the message content as a String array
     * @return the card ID as an integer
     */
    public static int getCardId(String[] content){
        return (content[2].equals("null")) ?
                Integer.parseInt(content[3])
                : Integer.parseInt(content[2]);
    }

    /**
     * Sets the passive switch flag based on the specified message content.
     *
     * @param content the message content as a String array
     */
    public static void setPassiveSwitch(String[] content){
        if(content[2].equals("null")){
            passiveSwitch=1;
        }else{
            passiveSwitch=0;
        }
    }

    /**
     * Converts the given Actioncard object to a JSON string representation for player card switching.
     *
     * @param card the Actioncard object to be converted to JSON
     * @return a JSON string representing the Actioncard object for the switch card player response
     */
    public static String convertToJson(Actioncard card){

        return "{'type':'SWITCH_CARD_PLAYER_RESPONSE', " +
                card.convertToJson() + "}";

    }

    /**
     * Converts the given Actioncard object and username to a JSON string representation for a switch card player request.
     *
     * @param card the Actioncard object to be converted to JSON
     * @param username the username associated with the request
     * @return a JSON string representing the switch card player request
     */
    public static String convertToJSONrequest(Actioncard card, String username){
        if(passiveSwitch==0) {
            return "{'type':'SWITCH_CARD_PLAYER_RESPONSE1', 'switchedWith':'" + username + "', " +
                    card.convertToJson() + "}";
        }
        return "{'type':'SWITCH_CARD_PLAYER_RESPONSE', 'switchedWith':'" + username + "', " +
                card.convertToJson() + "}";
    }
}
