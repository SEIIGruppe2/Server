package at.aau.se2.service;

import at.aau.se2.model.Monster;
import at.aau.se2.model.Tower;
import at.aau.se2.utils.Lobby;

import java.util.List;
import java.util.logging.Logger;

import static at.aau.se2.utils.UtilityMethods.*;

public class MAHService {
    private static final Logger logger = Logger.getLogger(MAHService.class.getName());

    public static void triggerMonsterAttack(String monsterId, String towerId, Lobby lobby) {
        try {
            Monster monster = findMonsterById(lobby.getGameState().getMonsters(), Integer.parseInt(monsterId));
            Tower tower = findTowerById(lobby.getGameState().getTowers(), Integer.parseInt(towerId));

            if (tower != null && monster != null) {
                int attackResult = monster.doesDmg(tower);
                if (attackResult == 0) {
                    monster.takeDamage(1);

                    logi("Monster " + monsterId + " attacked Tower " + towerId + " and booth took 1 damage.\nTower HP: " + tower.getLifepoints() + ",\nMonster HP: " + monster.getLifepoints());
                } else {
                    logd("Tower could not be damaged. No damage taken by Monster.");
                }
            } else {
                logs("Invalid MonsterID or invalid TowerID.");
            }
        } catch (NumberFormatException e) {
            logs("Error parsing IDs: " + e.getMessage());
        }
    }
    // Helper methods to find a monster or tower by ID from a list
    static Monster findMonsterById(List<Monster> monsters, int id) {
        return monsters.stream().filter(m -> m.getId() == id).findFirst().orElse(null);
    }

    static Tower findTowerById(List<Tower> towers, int id) {
        return towers.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }
}