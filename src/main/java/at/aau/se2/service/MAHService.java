package at.aau.se2.service;

import at.aau.se2.model.Monster;
import at.aau.se2.model.Tower;
import at.aau.se2.utils.Lobby;

import java.util.logging.Logger;

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

                    logger.info("Monster " + monsterId + " attacked Tower and took 1 damage. Tower HP: " + tower.getLifepoints() + ", Monster HP: " + monster.getLifepoints());
                } else {
                    logger.warning("Tower could not be damaged. No damage taken by Monster.");
                }
            } else {
                logger.severe("Invalid Monster ID or no Tower found for attack.");
            }
        } catch (NumberFormatException e) {
            logger.severe("Error parsing IDs: " + e.getMessage());
        }
    }
}