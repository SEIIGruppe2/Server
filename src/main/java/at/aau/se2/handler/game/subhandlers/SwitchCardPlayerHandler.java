package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.exceptions.CardNotFoundException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.model.Actioncard;
import at.aau.se2.service.SCPHService;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

import static at.aau.se2.service.SCPHService.*;
import static at.aau.se2.utils.UtilityMethods.logi;

public class SwitchCardPlayerHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        try {
            String[] msgContent = getMessageContent(msg);
            String switchUsername = msgContent[1];
            List<Actioncard> cards = UtilityMethods.findPlayer(session, lobby).getCards();


            Actioncard currentCard = cardRemoval(getCardId(msgContent), cards);
            setPassiveSwitch(msgContent);

            Player switchPlayer = findPlayerByUsername(switchUsername);
            switchPlayer.getCards().add(currentCard);
            System.out.println(SCPHService.convertToJSONrequest(currentCard,findUsernameOfPlayer(session)));
            switchPlayer.getSession()
                    .sendMessage(
                            new TextMessage(
                                    SCPHService.convertToJSONrequest(
                                            currentCard,
                                            findUsernameOfPlayer(session
                                            )
                                    )
                            )
                    );
        }
        catch(CardNotFoundException c){
            logi(this.getClass() + ": Card not found");
        }
        catch(PlayerNotFoundException p){
            logi("PLAYER NOT IN LOBBY (SwitchCardDeckHandler)");
        } catch (IOException e) {
            logi(e.getMessage() + " (SwitchCardDeckHandler)");
        }

    }
}
