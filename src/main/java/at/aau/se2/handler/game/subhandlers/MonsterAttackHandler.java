package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.dto.MonsterAttackDTO;
import at.aau.se2.model.towers.TowerImpl;
import at.aau.se2.service.MAHService;
import at.aau.se2.utils.Lobby;
import org.springframework.web.socket.WebSocketSession;
import com.fasterxml.jackson.databind.JsonNode;

import static at.aau.se2.utils.UtilityMethods.logd;
import static at.aau.se2.utils.UtilityMethods.logs;

public class MonsterAttackHandler implements ActionHandler {

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        lobby.getGameState().getTowers().add(new TowerImpl(0));

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

                    // Send message to client
                    session.sendMessage(dto.makeMessage());
                } else {
                    logd("Invalid entities provided for MONSTER_ATTACK.");
                }
            }
        } catch (Exception e) {
            logs("Error processing MONSTER_ATTACK message: " + e.getMessage());
        }
    }
}
