package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.dto.RegisterUsernameDTO;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static at.aau.se2.service.RUHService.addUsername;
import static at.aau.se2.utils.UtilityMethods.findPlayer;
import static at.aau.se2.utils.UtilityMethods.logi;

public class RegisterUsernameHandler implements ActionHandler{
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try {
            String username = msg.path("username").asText();
            RegisterUsernameDTO dto = new RegisterUsernameDTO(
                    addUsername(
                            username,
                            findPlayer(
                                    session,
                                    GameHandler.getPlayers()
                            )
                    )
            );
            session.sendMessage(dto.makeMessage());
        }
        catch (PlayerNotFoundException e) {
            logi("PLAYER NOT FOUND (RegisterUsernameHandler)");
        }
        catch(IOException i){
            logi(i.getMessage() + "(RegisterUsernameHandler)");
        }
    }
}
