package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.UtilityMethods;
import at.aau.se2.model.Actioncard;
import at.aau.se2.model.characters.Archer;
import at.aau.se2.model.characters.Fighter;
import at.aau.se2.model.characters.Hero;
import at.aau.se2.model.characters.Knight;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.security.SecureRandom;

@Data
public class DrawCardHandler implements ActionHandler {
    private WebSocketSession session;
    private static int cardId = 0;

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        // generate a new card and send it to client
        try {
            Actioncard card = drawRandomCard(lobby);
            UtilityMethods.findPlayer(session, lobby).getCards().add(card);
            session.sendMessage(new TextMessage(convertToJson(card)));
        }
        catch(PlayerNotFoundException p){
            System.out.println(p.getMessage() + "PLAYER NOT IN LOBBY");
        }
        catch(IOException i){
            System.out.println(i.getMessage());
        }
    }

    public String convertToJson(Actioncard card){
        return "{ 'type' : 'DRAW_CARD', 'id': '" +
                card.getId() +
                "', 'name': '" +
                card.getName() +
                "', 'zone': '" +
                card.getZone() +
                "'}";
    }

    public Actioncard drawRandomCard(Lobby lobby){
        SecureRandom rn = new SecureRandom();
        int i = lobby.getGameState().getIndexMinimumCardAmount();
        Actioncard card = switch(i){
            case 0 -> new Archer(rn.nextInt(1,4), cardId++);
            case 1 -> new Fighter(rn.nextInt(1,4), cardId++);
            case 2 -> new Knight(rn.nextInt(1,4), cardId++);
            default -> new Hero(rn.nextInt(1,4), cardId++);
        };
        lobby.getGameState().increaseCardAmount(i);
        return card;
    }
}
