package at.aau.se2.service;

import at.aau.se2.utils.Player;

import static at.aau.se2.utils.UtilityMethods.logs;

public class ACCService {
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
