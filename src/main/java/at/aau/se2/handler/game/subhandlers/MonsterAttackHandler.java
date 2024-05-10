package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.dto.MonsterAttackDTO;
import at.aau.se2.model.Tower;
import at.aau.se2.model.towers.TowerImpl;
import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;
import static at.aau.se2.service.MAHService.triggerMonsterAttack;
import static at.aau.se2.utils.UtilityMethods.logd;
import static at.aau.se2.utils.UtilityMethods.logs;

public class MonsterAttackHandler implements ActionHandler {


    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        lobby.getGameState().getTowers().add(new TowerImpl(0));
        lobby.getGameState().getTowers().add(new TowerImpl(1));

        try {
            if (isMessageTypeValid(msg)) {
                String monsterId = msg.path("monsterid").asText();
                String towerId = msg.path("towerid").asText();

                if (validateEntities(monsterId, towerId, lobby)) {
                    processMonsterAttack(monsterId, towerId, lobby, session);
                } else {
                    logd("Invalid entities provided for MONSTER_ATTACK.");
                }
            }
        } catch (Exception e) {
            logs("Error processing MONSTER_ATTACK message: " + e.getMessage());
        }
    }

    private boolean isMessageTypeValid(JsonNode msg) {
        return "MONSTER_ATTACK".equals(msg.path("type").asText());
    }

    private boolean validateEntities(String monsterId, String towerId, Lobby lobby) {
        try {
            int mId = Integer.parseInt(monsterId);
            int tId = Integer.parseInt(towerId);
            return (mId >= 0 && mId <= lobby.getGameState().getMonsters().size()) && //Eventuell anpassen die Bedingungen
                    (tId >= 0 && tId <= lobby.getGameState().getTowers().size()); //Kommt auf die Implementierung an
        } catch (NumberFormatException e) {
            logs("Invalid number format for entity IDs. " + e.getMessage());
            return false;
        }
    }

    private void processMonsterAttack(String monsterId, String towerId, Lobby lobby, WebSocketSession session) {
        triggerMonsterAttack(monsterId, towerId, lobby);
        MonsterAttackDTO dto = buildAttackDTO(monsterId, towerId, lobby);
        sendMessage(session, dto);
    }
    private void sendMessage(WebSocketSession session, MonsterAttackDTO dto) {
        try {
            session.sendMessage(dto.makeMessage());
        } catch (Exception e) {
            logs("Error sending message to client: " + e.getMessage());
        }
    }

    private MonsterAttackDTO buildAttackDTO(String monsterId, String towerId, Lobby lobby) {
        var monster = lobby.getGameState().getMonsters().get(Integer.parseInt(monsterId)); //hier auch aufpassen, wie werden die listen behandelt
        var tower = lobby.getGameState().getTowers().get(Integer.parseInt(towerId));        //wenn ein objekt zerstört oder getötet wird?
        String attackStatus = (monster.getLifepoints() > 0) ? "success" : "failed";
        return new MonsterAttackDTO(
                monsterId,
                towerId,
                monster.getLifepoints(),
                tower.getLifepoints(),
                attackStatus
        );
    }
}