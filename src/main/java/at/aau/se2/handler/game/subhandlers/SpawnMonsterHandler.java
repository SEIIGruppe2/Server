package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.model.Monster;
import at.aau.se2.model.monsters.Bullrog;
import at.aau.se2.model.monsters.Slime;
import at.aau.se2.model.monsters.Sphinx;
import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.security.SecureRandom;
import java.util.logging.Logger;

public class SpawnMonsterHandler implements ActionHandler {
    private final SecureRandom rn;
    @Getter
    private static int monsterId = 0;

    public static void increaseMonsterId(){
        monsterId++;
    }
    public SpawnMonsterHandler(SecureRandom rn){
        this.rn = rn;
    }
    @Override
    public void handleMessage(WebSocketSession session, JsonNode msg, Lobby lobby) {
        try {
            Monster monster = spawnRandomMonster(lobby);
            session.sendMessage(new TextMessage(convertToJson(monster)));
        }catch (Exception e){
            Logger.getLogger("global")
                    .info("SPAWN_MONSTER: " + e.getMessage());
        }
    }

    public Monster spawnRandomMonster(Lobby lobby){
        int monstertype = rn.nextInt(1,3);

        Monster monster = switch(monstertype){
            case 1 -> new Slime(rn.nextInt(0,3), 0, monsterId);
            case 2 -> new Sphinx(rn.nextInt(0,3), 0, monsterId);
            default -> new Bullrog(rn.nextInt(0,3), 0, monsterId);
        };
        increaseMonsterId();
        lobby.getGameState().getMonsters().add(monster);
        return monster;
    }

    public String convertToJson(Monster monster) {
        return "{ 'type':'SPAWN_MONSTER', 'monster': " + monster.convertToJson() + "}";
    }
}
