package at.aau.se2.model.characters;

import at.aau.se2.model.Monster;
import at.aau.se2.model.monsters.Bullrog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArcherTest {
    Archer archer;
    Monster monster;
    @BeforeEach
    public void setupTests(){
        archer = new Archer(2);
        monster = new Bullrog(2, 1, 1);
    }

    @Test
    public void testSetup(){
        assertEquals("Bogenschütze", archer.getName());
        assertEquals(2, archer.getZone());
        assertEquals(1, archer.getDmg());
    }

    @Test
    public void testDoesDmg(){
        assertEquals(0, archer.doesDmg(monster));
        monster.setRing(2);
        assertEquals(-1, archer.doesDmg(monster));
        monster.setZone(1);
        assertEquals(-1, archer.doesDmg(monster));
        archer.setZone(1);
        monster.setRing(1);
        assertEquals(0, archer.doesDmg(monster));
    }
}
