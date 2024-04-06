package at.aau.se2.model.characters;

import at.aau.se2.model.Monster;
import at.aau.se2.model.monsters.Bullrog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FighterTest {
    Fighter fighter;
    Monster monster;
    @BeforeEach
    public void setupTests(){
        fighter = new Fighter(2,1);
        monster = new Bullrog(2, 3, 1);
    }

    @Test
    public void testSetup(){
        assertEquals("Schwertkämpfer", fighter.getName());
        assertEquals(2, fighter.getZone());
        assertEquals(1, fighter.getDmg());
    }

    @Test
    public void testDoesDmg(){
        assertEquals(0, fighter.doesDmg(monster));
        monster.setRing(2);
        assertEquals(-1, fighter.doesDmg(monster));
        monster.setZone(1);
        assertEquals(-1, fighter.doesDmg(monster));
        fighter.setZone(1);
        monster.setRing(3);
        assertEquals(0, fighter.doesDmg(monster));
    }
}
