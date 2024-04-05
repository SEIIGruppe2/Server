package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.gameutils.Lobby;
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
import java.util.Random;

@Data
public class DrawCardHandler implements ActionHandler {
    private WebSocketSession session;
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        // generate a new card and send it to client
        try {
            session.sendMessage(new TextMessage(convertCardToJson(drawRandomCard(lobby))));
        }catch(IOException i){
            System.out.println(i.getMessage());
        }
    }

    public String convertCardToJson(Actioncard card){
        StringBuilder builder = new StringBuilder();
        builder.append("{ 'type' : 'DRAW_CARD', 'name': '")
                .append(card.getName())
                .append("'}");
        return builder.toString();
    }

    public Actioncard drawRandomCard(Lobby lobby){
        SecureRandom rn = new SecureRandom();
        int i = lobby.getGameState().getIndexMinimumCardAmount();
        Actioncard card = switch(i){
            case 1 -> new Archer(rn.nextInt(1,4));
            case 2 -> new Fighter(rn.nextInt(1,4));
            case 3 -> new Knight(rn.nextInt(1,4));
            default -> new Hero(rn.nextInt(1,4));
        };
        lobby.getGameState().increaseCardAmount(i);
        return card;
    }
}
