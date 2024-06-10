package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.dto.DrawCardDTO;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.model.Actioncard;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static at.aau.se2.service.DCHService.drawRandomCard;
import static at.aau.se2.utils.UtilityMethods.logi;
import static at.aau.se2.utils.UtilityMethods.logs;

@Data
public class DrawCardHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        try {
            Actioncard card = drawRandomCard(lobby);
            UtilityMethods.findPlayer(session, lobby).getCards().add(card);
            DrawCardDTO dto = new DrawCardDTO(card);
            logi(card.convertToJson());
            session.sendMessage(dto.makeMessage());
        }
        catch(PlayerNotFoundException p){
            logs("PLAYER NOT IN LOBBY (DrawCardHandler)");
        }
        catch(IOException i){
            logi(i.getMessage() + " (DrawCardHandler)");
        }
    }
}
