package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.dto.ShowMonsterDTO;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static at.aau.se2.service.SHMHService.*;
import static at.aau.se2.utils.UtilityMethods.logi;

public class ShowMonstersHandler implements ActionHandler {

   @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
       try {

           String cardid = readInfosFromMessage(msg);
           List<Monster> monsters = lobby.getGameState().getMonsters();
           Player player = UtilityMethods.findPlayer(session, lobby.getPlayers());
           int searchedring = getRing(getCard(Integer.parseInt(cardid), player.getCards()));
           int searchedzone = getCard(Integer.parseInt(cardid), player.getCards()).getZone();
           logi("Monsters" + searchedring + searchedzone);
           List<String> monsterids = addSearchedMonstersToList(monsters, searchedring, searchedzone);
           logi("Monsters" + monsterids.size());
           ShowMonsterDTO showMonsterDTO = new ShowMonsterDTO(monsterids);
           session.sendMessage(showMonsterDTO.makeMessage());

       } catch (PlayerNotFoundException e) {
           logi("PLAYER NOT FOUND (ShowMonstersHandler)");
       } catch (IOException i) {
           Logger.getLogger("global")
                   .info(i.getMessage() + "(ShowMonstersHandler)");
       }
   }







}
