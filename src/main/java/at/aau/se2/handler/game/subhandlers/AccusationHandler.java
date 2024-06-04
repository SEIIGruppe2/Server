package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.dto.PlayerTrophiesDTO;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import static at.aau.se2.service.ACCService.checkCheater;
import static at.aau.se2.service.SCPHService.findPlayerByUsername;
import static at.aau.se2.utils.UtilityMethods.logs;

public class AccusationHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try {
            String cheaterName = msg.path("cheaterName").asText();
            String accusatorName = msg.path("accusatorName").asText();

            Player cheater = findPlayerByUsername(cheaterName);
            Player accusator = findPlayerByUsername(accusatorName);

            checkCheater(cheater, accusator);
            PlayerTrophiesDTO cheaterDTO = new PlayerTrophiesDTO(
                    cheaterName,
                    cheater.getPoints()
            );
            PlayerTrophiesDTO accusatorDTO = new PlayerTrophiesDTO(
                    accusatorName,
                    accusator.getPoints()
            );
            for (Player players : lobby.getPlayers()) {
                try {
                    synchronized (players.getSession()) {
                        players.getSession().sendMessage(cheaterDTO.makeMessage());
                        players.getSession().sendMessage(accusatorDTO.makeMessage());
                    }
                } catch (Exception e) {
                    logs("Failed to send message to player " + players.getUsername() + ": " + e.getMessage());
                }
            }
        } catch (PlayerNotFoundException e) {
            logs("Error processing ACCUSE message: " + e.getMessage());
        }
    }
}