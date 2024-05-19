package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.dto.MonsterAttackDTO;
import at.aau.se2.service.MAHService;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import org.springframework.web.socket.WebSocketSession;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import static at.aau.se2.utils.UtilityMethods.logd;
import static at.aau.se2.utils.UtilityMethods.logs;

public class MonsterAttackHandler implements ActionHandler {

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {

        try {
            // Validate message type using MAHService
            if (MAHService.isMessageTypeValid(msg)) {
                String monsterId = msg.path("monsterid").asText();
                String towerId = msg.path("towerid").asText();

                // Validate entities using MAHService
                if (MAHService.validateEntities(monsterId, towerId, lobby)) {
                    // Process monster attack via MAHService
                    MAHService.triggerMonsterAttack(monsterId, towerId, lobby);

                    // Build DTO directly
                    var monster = lobby.getGameState().getMonsters().get(Integer.parseInt(monsterId));//hier auch aufpassen, wie werden die listen behandelt
                    var tower = lobby.getGameState().getTowers().get(Integer.parseInt(towerId));//wenn ein objekt zerstört oder getötet wird?
                    String attackStatus = (monster.getLifepoints() > 0) ? "success" : "failed";
                    MonsterAttackDTO dto = new MonsterAttackDTO(
                            monsterId,
                            towerId,
                            monster.getLifepoints(),
                            tower.getLifepoints(),
                            attackStatus
                    );
                    for (Player player : lobby.getPlayers()) {
                        try {
                            synchronized(player.getSession()) {
                                player.getSession().sendMessage(dto.makeMessage());
                            }
                        } catch (IOException e) {
                            logs("Failed to send message to player " + player.getUsername() + ": " + e.getMessage());
                        }
                    }


                } else {
                    logd("Invalid entities provided for MONSTER_ATTACK.");
                }
            }
        } catch (Exception e) {
            logs("Error processing MONSTER_ATTACK message: " + e.getMessage());
        }
    }
}
