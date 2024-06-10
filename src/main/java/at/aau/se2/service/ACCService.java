package at.aau.se2.service;

import at.aau.se2.utils.Player;

import static at.aau.se2.utils.UtilityMethods.logs;

public class ACCService {
    public static void checkCheater(Player cheater, Player accusator) {
        if(cheater.isCheating()) {
            int points = cheater.getPoints();
            cheater.setPoints(-points); //Vorerst alle Punkte entfernen von Cheater. Balancing erfolgt später.
            cheater.setCheatingRoundsLeft(0);
            cheater.setCheating(false);
            logs("Cheater: guilty + penalty received");
        }
        else{
            int points = accusator.getPoints();
            accusator.setPoints(-points); //Vorerst alle Punkte entfernen von Accuser. Balancing erfolgt später.
            logs("Cheater: not guilty + Accusator penalty received");
        }
    }

}