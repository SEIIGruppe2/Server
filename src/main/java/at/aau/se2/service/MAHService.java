package at.aau.se2.service;

import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import at.aau.se2.model.Monster;
import at.aau.se2.model.Tower;

import java.util.List;

import static at.aau.se2.utils.UtilityMethods.*;

/**
 * Service class for handling monster attacks in the game.
 * This class provides methods to validate messages, validate entities, and trigger monster attacks.
 */
public class MAHService {

    /**
     * Private constructor to prevent instantiation.
     */
    private MAHService(){
        // Prevents instantiation
    }

    /**
     * Checks if the given message is of type "MONSTER_ATTACK".
     *
     * @param msg the JSON message to check
     * @return true if the message type is "MONSTER_ATTACK", false otherwise
     */
    public static boolean isMessageTypeValid(JsonNode msg) {
        return "MONSTER_ATTACK".equals(msg.path("type").asText());
    }

    /**
     * Validates the monster and tower IDs against the entities in the lobby.
     *
     * @param monsterId the ID of the monster as a string
     * @param towerId the ID of the tower as a string
     * @param lobby the lobby containing the game state
     * @return true if both IDs are valid, false otherwise
     */
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

    /**
     * Triggers an attack by the specified monster on the specified tower in the given lobby.
     *
     * @param monsterId the ID of the monster as a string
     * @param towerId the ID of the tower as a string
     * @param lobby the lobby containing the game state
     */
    public static void triggerMonsterAttack(String monsterId, String towerId, Lobby lobby) {
        try {
            Monster monster = findMonsterById(lobby.getGameState().getMonsters(), Integer.parseInt(monsterId));
            Tower tower = findTowerById(lobby.getGameState().getTowers(), Integer.parseInt(towerId));

            if (tower != null && monster != null) {
                int attackResult = monster.doesDmg(tower);
                if (attackResult == 0) {
                    monster.takeDamage(1);
                    logi("MonsterID " + monsterId + " damaged Tower. MonsterHP: " + monster.getLifepoints() + " TowerHP: " + tower.getLifepoints());
                } else {
                    logs("Tower could not be damaged. No damage taken by Monster.");
                }
            } else {
                logd("Invalid MonsterID or invalid TowerID.");
            }
        } catch (NumberFormatException e) {
            logs("Error parsing IDs: " + e.getMessage());
        }
    }

    /**
     * Finds a monster by its ID from a list of monsters.
     *
     * @param monsters the list of monsters
     * @param id the ID of the monster to find
     * @return the monster with the specified ID, or null if not found
     */
    static Monster findMonsterById(List<Monster> monsters, int id) {
        return monsters.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    /**
     * Finds a tower by its ID from a list of towers.
     *
     * @param towers the list of towers
     * @param id the ID of the tower to find
     * @return the tower with the specified ID, or null if not found
     */
    static Tower findTowerById(List<Tower> towers, int id) {
        return towers.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
}
