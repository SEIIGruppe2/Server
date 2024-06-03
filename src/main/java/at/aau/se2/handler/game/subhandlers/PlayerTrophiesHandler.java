package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.dto.PlayerTrophiesDTO;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static at.aau.se2.utils.UtilityMethods.logs;

public class PlayerTrophiesHandler implements ActionHandler {

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try {
            Player player = UtilityMethods.findPlayer(session, lobby);
            String playerName = player.getUsername();
            int points = player.getPoints();
            logs(playerName);
            logs(String.valueOf(points));
            PlayerTrophiesDTO dto = new PlayerTrophiesDTO(
                    playerName,
                    points
            );
            for (Player players : lobby.getPlayers()) {
                try {
                    synchronized(players.getSession()) {
                        players.getSession().sendMessage(dto.makeMessage());
                    }

                } catch (IOException e) {
                    logs("Failed to send message to player " + players.getUsername() + ": " + e.getMessage());
                }
            }
            logs("TROPHIES DONE");

        } catch (Exception e) {
            logs("Error processing PLAYER_TROPHIES message: " + e.getMessage());
        }
    }
}