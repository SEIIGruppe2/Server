package at.aau.se2.service;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.characters.Archer;
import at.aau.se2.model.characters.Fighter;
import at.aau.se2.model.characters.Hero;
import at.aau.se2.model.characters.Knight;
import at.aau.se2.utils.Lobby;

import java.security.SecureRandom;

import static at.aau.se2.utils.UtilityMethods.logi;

public class DCHService {
    private static int cardId = 0;
    private static SecureRandom rn = new SecureRandom();

    /**
     * Constructs a new DCHService with the specified SecureRandom instance.
     *
     * @param rn the SecureRandom instance to be used for generating random values
     */
    public DCHService(SecureRandom rn){
        DCHService.rn = rn;
    }

    /**
     * Converts the given Actioncard object to a JSON string representation.
     *
     * @param card the Actioncard object to be converted to JSON
     * @return a JSON string representing the Actioncard object
     */
    public static String convertToJson(Actioncard card){
        return "{ 'type' : 'DRAW_CARD', " +
                card.convertToJson() + "}";
    }

    /**
     * Draws a random Actioncard based on the current game state of the specified lobby.
     * The type of card drawn depends on the index of the minimum card amount in the lobby's game state.
     * The card drawn can be an Archer, Fighter, Knight, or Hero.
     *
     * @param lobby the Lobby object for which to draw the random card
     * @return the drawn Actioncard object
     */
    public static Actioncard drawRandomCard(Lobby lobby){
        logi("drawRandomCard is called!");
        int i = lobby.getGameState().getIndexMinimumCardAmount();
        logi("getIndexMinimumCardAmount was called!");
        Actioncard card = switch(i){
            case 0 -> new Archer(rn.nextInt(1,5), cardId++);
            case 1 -> new Fighter(rn.nextInt(1,5), cardId++);
            case 2 -> new Knight(rn.nextInt(1,5), cardId++);
            default -> new Hero(rn.nextInt(1,5), cardId++);
        };
        logi("increaseCardAmount of GameState will be called!");
        lobby.getGameState().increaseCardAmount(i);
        logi("Card will be returned!");
        return card;
    }
}
