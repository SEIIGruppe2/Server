package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.dto.MonsterAttackDTO;
import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import java.util.logging.Logger;

import static at.aau.se2.service.MAHService.triggerMonsterAttack;

public class MonsterAttackHandler implements ActionHandler {
    private static final Logger logger = Logger.getLogger(MonsterAttackHandler.class.getName());

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try {
            String messageType = msg.path("type").asText();

            if ("MONSTER_ATTACK".equals(messageType)) {
                // Extract monster and tower IDs from the message
                String monsterId = msg.path("monsterid").asText();
                String towerId = msg.path("towerid").asText();

                // Trigger the monster attack (this is a static call)
                triggerMonsterAttack(monsterId, towerId, lobby);

                // Retrieve monster and tower data from the lobby to construct the DTO
                var monster = lobby.getGameState().getMonsters().get(Integer.parseInt(monsterId));
                var tower = lobby.getGameState().getTowers().get(Integer.parseInt(towerId));

                // Create the MonsterAttackDTO based on the results of the attack
                String attackStatus = (monster.getLifepoints() > 0) ? "success" : "failed";
                MonsterAttackDTO dto = new MonsterAttackDTO(
                        monsterId,
                        monster.getLifepoints(),
                        tower.getLifepoints(),
                        attackStatus
                );

                // Send the DTO as a message to the client
                session.sendMessage(dto.makeMessage());
            }

        } catch (Exception e) {
            logger.severe("Error processing MONSTER_ATTACK message: " + e.getMessage());
        }
    }
}