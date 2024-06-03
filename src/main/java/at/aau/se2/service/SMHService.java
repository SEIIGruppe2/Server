package at.aau.se2.service;

import at.aau.se2.model.Monster;
import at.aau.se2.model.monsters.Bullrog;
import at.aau.se2.model.monsters.Slime;
import at.aau.se2.model.monsters.Sphinx;
import at.aau.se2.utils.Lobby;
import lombok.Getter;

import java.security.SecureRandom;

public class SMHService {
    @Getter
    private static int monsterId = 0;

    public static void increaseMonsterId(){
        monsterId++;
    }
    public static Monster spawnRandomMonster(int zone, Lobby lobby, SecureRandom rn){
        int monstertype = rn.nextInt(1,4);

        Monster monster = switch(monstertype){
            case 1 -> new Slime(zone, 0, monsterId);
            case 2 -> new Sphinx(zone, 0, monsterId);
            case 3 -> new Bullrog(zone, 0, monsterId);
            default -> throw new IllegalStateException("Unexpected value: " + monstertype);
        };
        increaseMonsterId();
        lobby.getGameState().getMonsters().add(monster);
        return monster;
    }
}
