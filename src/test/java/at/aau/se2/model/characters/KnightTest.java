package at.aau.se2.model.characters;

import at.aau.se2.model.Monster;
import at.aau.se2.model.monsters.Bullrog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnightTest {
    Knight knight;
    Monster monster;
    @BeforeEach
    public void setupTests(){
        knight = new Knight(2);
        monster = new Bullrog(2, 2, 1);
    }

    @Test
    public void testSetup(){
        assertEquals("Ritter", knight.getName());
        assertEquals(2, knight.getZone());
        assertEquals(1, knight.getDmg());
    }

    @Test
    public void testDoesDmg(){
        assertEquals(0, knight.doesDmg(monster));
        monster.setRing(3);
        assertEquals(-1, knight.doesDmg(monster));
        monster.setZone(1);
        assertEquals(-1, knight.doesDmg(monster));
        knight.setZone(1);
        monster.setRing(2);
        assertEquals(0, knight.doesDmg(monster));
    }
}
