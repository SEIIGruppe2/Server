package at.aau.se2.service;
import at.aau.se2.utils.Player;

import static at.aau.se2.utils.UtilityMethods.logi;
import static at.aau.se2.utils.UtilityMethods.logs;

public class PTService {
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
