package at.aau.se2.service;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.characters.Archer;
import at.aau.se2.model.characters.Fighter;
import at.aau.se2.model.characters.Hero;
import at.aau.se2.model.characters.Knight;
import at.aau.se2.utils.Lobby;

import java.security.SecureRandom;

public class DCHService {
    private static int cardId = 0;
    private static SecureRandom rn = new SecureRandom();

    public DCHService(SecureRandom rn){
        DCHService.rn = rn;
    }
    public static String convertToJson(Actioncard card){
        return "{ 'type' : 'DRAW_CARD', " +
                card.convertToJson() + "}";
    }

    public static Actioncard drawRandomCard(Lobby lobby){
        int i = lobby.getGameState().getIndexMinimumCardAmount();
        Actioncard card = switch(i){
            case 0 -> new Archer(rn.nextInt(0,4), cardId++);
            case 1 -> new Fighter(rn.nextInt(0,4), cardId++);
            case 2 -> new Knight(rn.nextInt(0,4), cardId++);
            default -> new Hero(rn.nextInt(0,4), cardId++);
        };
        lobby.getGameState().increaseCardAmount(i);
        return card;
    }
}
