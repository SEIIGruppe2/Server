package at.aau.se2.service;

import at.aau.se2.utils.Player;

import static at.aau.se2.utils.UtilityMethods.logs;

/**
 * Service class for handling accusations of cheating.
 * This class provides a method to check if a player is cheating and apply penalties accordingly.
 */
public class ACCService {

    /**
     * Checks if the accused player is cheating and applies the appropriate penalty.
     * If the accused player is found cheating, they receive a penalty. If not, the accuser receives a penalty.
     *
     * @param cheater the player accused of cheating
     * @param accusator the player who made the accusation
     */
    public static void checkCheater(Player cheater, Player accusator) {
        int penalty = 5;
        if(cheater.isCheating()) {
            int points = cheater.getPoints();
            if (points >= penalty) {
                cheater.setPoints(-penalty);
            } else {
                cheater.setPoints(-points);
            }
            cheater.setCheatingRoundsLeft(0);
            cheater.setCheating(false);
            logs("Cheater: guilty + penalty received");
        } else {
            int points = accusator.getPoints();
            if (points >= penalty) {
                accusator.setPoints(-penalty);
            } else {
                accusator.setPoints(-points);
            }
            logs("Cheater: not guilty + Accusator penalty received");
        }
    }
}
