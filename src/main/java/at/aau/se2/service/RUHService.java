package at.aau.se2.service;

import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.utils.Player;

import static at.aau.se2.utils.UtilityMethods.checkUsername;
import static at.aau.se2.utils.UtilityMethods.logi;

public class RUHService {
    public static String addUsername(String username, Player player){
        if(!checkUsername(username)){
            GameHandler.getUsernames().add(username);
            player.setUsername(username);
            logi("Username wurde zum Player hinzugefügt!");
            return "accepted";
        }
        return "denied";
    }
}
