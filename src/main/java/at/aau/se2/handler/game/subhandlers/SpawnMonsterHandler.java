package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.dto.SpawnMonsterDTO;
import at.aau.se2.model.Monster;
import at.aau.se2.service.SMHService;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import java.security.SecureRandom;

import static at.aau.se2.utils.UtilityMethods.logi;

public class SpawnMonsterHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try {
            int zone = msg.path("zone").asInt();
            logi("Received zone: " + zone);
            Monster monster = SMHService.spawnRandomMonster(zone, lobby, new SecureRandom());
            SpawnMonsterDTO dto = new SpawnMonsterDTO(monster);

            for (Player player : lobby.getPlayers()) {
                if (player.getSession().isOpen()) {
                    player.getSession().sendMessage(dto.makeMessage());
                }
            }
        } catch (Exception e) {
            logi("SPAWN_MONSTER: " + e.getMessage());
        }
    }
}