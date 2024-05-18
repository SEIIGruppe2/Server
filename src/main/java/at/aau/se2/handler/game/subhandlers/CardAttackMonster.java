package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.exceptions.CardNotFoundException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import at.aau.se2.utils.UtilityMethods;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static at.aau.se2.utils.UtilityMethods.findLobby;
import static at.aau.se2.utils.UtilityMethods.logi;

public class CardAttackMonster implements ActionHandler {
    private static final Logger logger = Logger.getLogger(GameRoundHandler.class.getName());
    private String monsterId;
    private String lifepoints;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try{
            String cardid = msg.path("cardid").asText();
            this.monsterId = msg.path("monsterid").asText();
            removeCard(Integer.parseInt(cardid), UtilityMethods.findPlayer(session, lobby).getCards());
            List<Monster> monsters =lobby.getGameState().getMonsters();
            Monster m = findmonster(Integer.parseInt(monsterId), monsters);
            this.lifepoints = String.valueOf(decreaseLifePoints(m));
            if(lifepoints.equals("-1")){
                monsters.remove(m);
            }
            updateOtherUser(lobby);


        }

        catch(PlayerNotFoundException p){
            logi("PLAYER NOT IN LOBBY (SwitchCardDeckHandler)");
        } catch (IOException e) {
            logger.severe("Failed to send cardattackmessage: " + e.getMessage());
        }
    }

    private void removeCard(int id, List<Actioncard> cards) {
        for (Actioncard c : cards) {
            if (c.getId() == id) {
                cards.remove(c);
            }
        }
    }
    private Monster findmonster(int id, List<Monster> monsters){
        for(Monster m: monsters){
            if(m.getId()==id){
                return m;
            }
        }
    return null;
    }
    private int decreaseLifePoints(Monster m){
        int currentlifepoints = m.getLifepoints();
        if(currentlifepoints == 1){

            return -1;
        }else{
            m.setLifepoints(currentlifepoints--);
            return currentlifepoints--;
        }
    }

    public void updateOtherUser(Lobby lobby) throws IOException {
        List<Player> players=lobby.getPlayers();
        ObjectNode messageNode = objectMapper.createObjectNode();

        messageNode.put("type", "CARD_ATTACK_MONSTER");
        messageNode.put("monsterid", monsterId);
        messageNode.put("lifepoints", lifepoints);
        try {
            for (Player a : players) {
                a.getSession().sendMessage(new TextMessage(messageNode.toString()));
            }
        }catch (IOException e) {
            logger.severe("Failed to send cardattackmessage: " + e.getMessage());
        }
    }


}