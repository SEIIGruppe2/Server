// File path: at/aau/se2/service/MAHService.java

package at.aau.se2.service;

import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import at.aau.se2.model.Monster;
import at.aau.se2.model.Tower;

import java.util.List;

import static at.aau.se2.utils.UtilityMethods.logs;

public class MAHService {

    public static boolean isMessageTypeValid(JsonNode msg) {
        return "MONSTER_ATTACK".equals(msg.path("type").asText());
    }

    public static boolean validateEntities(String monsterId, String towerId, Lobby lobby) {
        try {
            int mId = Integer.parseInt(monsterId);
            int tId = Integer.parseInt(towerId);
            return (mId >= 0 && mId <= lobby.getGameState().getMonsters().size()) &&
                    (tId >= 0 && tId <= lobby.getGameState().getTowers().size());
        } catch (NumberFormatException e) {
            logs("Invalid number format for entity IDs. " + e.getMessage());
            return false;
        }
    }

    public static void triggerMonsterAttack(String monsterId, String towerId, Lobby lobby) {
        try {
            Monster monster = findMonsterById(lobby.getGameState().getMonsters(), Integer.parseInt(monsterId));
            Tower tower = findTowerById(lobby.getGameState().getTowers(), Integer.parseInt(towerId));

            if (tower != null && monster != null) {
                int attackResult = monster.doesDmg(tower);
                if (attackResult == 0) {
                    monster.takeDamage(1);
                } else {
                    logs("Tower could not be damaged. No damage taken by Monster.");
                }
            } else {
                logs("Invalid MonsterID or invalid TowerID.");
            }
        } catch (NumberFormatException e) {
            logs("Error parsing IDs: " + e.getMessage());
        }
    }

    // Helper methods to find monsters or towers by ID from a list
    static Monster findMonsterById(List<Monster> monsters, int id) {
        return monsters.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    static Tower findTowerById(List<Tower> towers, int id) {
        return towers.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
}
