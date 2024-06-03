package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import static at.aau.se2.utils.UtilityMethods.logs;

public class CheatModeHandler implements ActionHandler {

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try {
            Player player = UtilityMethods.findPlayer(session,lobby);

            boolean cheatMode = msg.path("cheatMode").asBoolean();
            player.setCheatToggleOn(cheatMode);
            logs("Cheat-Mode set to " + cheatMode);

        } catch (Exception e) {
            logs("Error processing CHEAT message: " + e.getMessage());
        }
    }
}