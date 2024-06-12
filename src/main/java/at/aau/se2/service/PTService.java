package at.aau.se2.service;

import at.aau.se2.utils.Player;

import static at.aau.se2.utils.UtilityMethods.logi;
import static at.aau.se2.utils.UtilityMethods.logs;

/**
 * Service class for updating player points based on the monster defeated.
 * This class provides a method to update the player's points according to the monster's name.
 * <p>
 * This class cannot be instantiated because it contains only static methods.
 */
public class PTService {

    /**
     * Private constructor to prevent instantiation.
     */
    private PTService() {
        // Prevents instantiation
    }

    /**
     * Updates the points of the given player based on the name of the monster defeated.
     * Different monsters provide different point values.
     *
     * @param player the player whose points will be updated
     * @param monsterName the name of the monster defeated
     */
    public static void updatePlayerPoints(Player player, String monsterName) {
        int pointsToAdd = 0;
        switch (monsterName) {
            case "Schleim":
                pointsToAdd = 1;
                break;
            case "Sphinx":
                pointsToAdd = 2;
                break;
            case "Bullrog":
                pointsToAdd = 3;
                break;
            default:
                logi("Unknown monster name: " + monsterName); //DEBUG
        }
        player.setPoints(pointsToAdd);
        logs("Points:" + player.getPoints());
    }
}
