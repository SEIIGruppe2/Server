package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.socket.WebSocketSession;

import java.util.logging.Logger;

import static at.aau.se2.service.MAHService.triggerMonsterAttack;

public class MonsterAttackHandler implements ActionHandler {
    private static final Logger logger = Logger.getLogger(MonsterAttackHandler.class.getName());

    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try {
            String messageType = msg.path("type").asText();

            if ("MONSTER_ATTACK".equals(messageType)) {
                // Extract monster and tower IDs from the message
                String monsterId = msg.path("monsterid").asText();
                String towerId = msg.path("towerid").asText();

                // Trigger the monster attack (this is a static call)
                triggerMonsterAttack(monsterId, towerId, lobby);