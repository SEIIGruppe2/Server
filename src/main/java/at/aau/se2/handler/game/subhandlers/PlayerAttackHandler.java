package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.dto.PlayerAttackDTO;
import at.aau.se2.exceptions.CardCannotAttackException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;
import at.aau.se2.model.characters.Archer;
import at.aau.se2.model.characters.Fighter;
import at.aau.se2.model.characters.Hero;
import at.aau.se2.model.characters.Knight;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Arrays;

import static at.aau.se2.service.PAHService.getCard;
import static at.aau.se2.service.PAHService.readInfosFromMessage;
import static at.aau.se2.service.PTService.updatePlayerPoints;
import static at.aau.se2.utils.UtilityMethods.logi;
import static at.aau.se2.utils.UtilityMethods.logs;


public class PlayerAttackHandler implements ActionHandler {
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby){
        String[] m = readInfosFromMessage(msg);
        try{
            logi(Arrays.toString(m));
            Player player = UtilityMethods.findPlayer(session, lobby);
            Actioncard card = getCard(Integer.parseInt(m[1]), player.getCards());
            logi(card.convertToJson());
            Monster monster = lobby
                                .getGameState()
                                .getMonsters()
                                .get(Integer.parseInt(m[0]));
            logi(monster.convertToJson());
            if(card.doesDmg(monster) == 0){
                if(player.isCheatToggleOn()){
                    card.doesDmg(monster);
                    player.setCheating(true);
                    player.setCheatingRoundsLeft(4);
                }
                player.getCards().remove(card);
            }
            else {
                throw new CardCannotAttackException();
            }

            if(monster.getLifepoints() <= 0){
                logi("MONSTER DEAD, ADD POINTS"); //DEBUG
                updatePlayerPoints(player, monster.getName());
                lobby.getGameState().getMonsters().remove(monster);
            }

            if(card instanceof Archer)
                lobby.getGameState().decreaseCardAmount(0);
            else if(card instanceof Fighter)
                lobby.getGameState().decreaseCardAmount(1);
            else if(card instanceof Knight)
                lobby.getGameState().decreaseCardAmount(2);
            else if(card instanceof Hero)
                lobby.getGameState().decreaseCardAmount(3);

            PlayerAttackDTO dto = new PlayerAttackDTO("" + monster.getId(), monster.getLifepoints());
            for(Player p : lobby.getPlayers()){
                p.getSession().sendMessage(dto.makeMessage());
            }
        }
        catch(CardCannotAttackException c){
            logs("Card could not attack the monster (PlayerAttackHandler)");
        }
        catch(PlayerNotFoundException p){
            logi("PLAYER NOT IN LOBBY (PlayerAttackHandler)");
        } catch (IOException e) {
            logi("Failed to send CARD_ATTACK Message");
        }
    }
}
