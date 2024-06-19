package at.aau.se2.service;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;
import at.aau.se2.model.characters.Archer;
import at.aau.se2.model.characters.Fighter;
import at.aau.se2.model.characters.Hero;
import at.aau.se2.model.characters.Knight;
import at.aau.se2.model.monsters.Bullrog;
import at.aau.se2.model.monsters.Slime;
import at.aau.se2.model.monsters.Sphinx;
import at.aau.se2.utils.GameState;
import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static at.aau.se2.service.SHMHService.addSearchedMonstersToList;
import static at.aau.se2.service.SHMHService.readInfosFromMessage;
import static at.aau.se2.service.SMHService.increaseMonsterId;
import static at.aau.se2.service.SMHService.spawnRandomMonster;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class SHMHServiceTest {
    private Lobby lobby;

    @Test
    void testreadInfosFromMessage() {
        JSONObject json = new JSONObject();
        json.put("cardid", 1);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(json);
        assertEquals("1",readInfosFromMessage(node));

    }

    @Test
    void getCard() {
        Archer a = new Archer(2,3);
        Fighter f = new Fighter(1,4);
        Hero h = new Hero(3,5);
        Knight k = new Knight(4,6);
        List<Actioncard> cards = new ArrayList<>();
        cards.add(a);
        cards.add(f);
        cards.add(h);
        cards.add(k);
        assertEquals(h, SHMHService.getCard(5,cards));
    }

    @Test
    void getRing() {
        Archer a = new Archer(2,3);
        assertEquals(1,SHMHService.getRing(a));
        Fighter f = new Fighter(1,4);
        assertEquals(3,SHMHService.getRing(f));
        Hero h = new Hero(3,5);
        assertEquals(4,SHMHService.getRing(h));
        Knight k = new Knight(4,6);
        assertEquals(2,SHMHService.getRing(k));



    }

    @Test
    void testaddSearchedMonstersToList() {
        Bullrog a = new Bullrog(1,1,1);
        Slime b = new Slime(2,2,2);
        Sphinx c = new Sphinx(3,3,3);
        Bullrog d = new Bullrog(1,2,4);
        List<Monster> monsters = new ArrayList<>();
        monsters.add(a);
        monsters.add(b);
        monsters.add(c);
        monsters.add(d);
        List<String> result = new ArrayList<>();
        result.add("2");
        assertEquals(result, addSearchedMonstersToList(monsters, 2,2));
        assertEquals(result, addSearchedMonstersToList(monsters, 4,2));
}
}
