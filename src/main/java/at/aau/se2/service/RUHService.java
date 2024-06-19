package at.aau.se2.service;

import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.utils.Player;

import static at.aau.se2.utils.UtilityMethods.checkUsername;
import static at.aau.se2.utils.UtilityMethods.logi;

public class RUHService {
    /**
     * Adds a username to the specified player if the username is not already in use.
     * If the username is valid and not already taken, it is added to the global list of usernames,
     * and the player's username is set to the specified value.
     *
     * @param username the username to be added
     * @param player the Player object to which the username will be added
     * @return "accepted" if the username was successfully added, "denied" otherwise
     */
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
