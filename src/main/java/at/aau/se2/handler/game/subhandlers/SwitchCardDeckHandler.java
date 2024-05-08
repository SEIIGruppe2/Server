package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.exceptions.CardNotFoundException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.model.Actioncard;
import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static at.aau.se2.service.SCDHService.*;
import static at.aau.se2.utils.UtilityMethods.findPlayer;
import static at.aau.se2.utils.UtilityMethods.logi;

public class SwitchCardDeckHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        try {
            Actioncard newCard = addCard(
                    Integer.parseInt(readCardId(msg)),
                    drawRandomCard(lobby),
                    findPlayer(session, lobby).getCards()
            );
            session.sendMessage(new TextMessage(convertToJson(newCard)));

        }
        catch (CardNotFoundException e) {
            logi("CARD NOT FOUND (SwitchCardDeckHandler) " + e.getMessage());
        }
        catch(PlayerNotFoundException p){
            logi("PLAYER NOT IN LOBBY (SwitchCardDeckHandler)");
        }
        catch(IOException i){
            logi(i.getMessage() + " (SwitchCardDeckHandler)");
        }

    }

}
