package at.aau.se2.model.characters;

import at.aau.se2.model.Monster;
import at.aau.se2.model.monsters.Bullrog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HeroTest {
    Hero hero;
    Monster monster;
    @BeforeEach
    public void setupTests(){
        hero = new Hero(2,1);
        monster = new Bullrog(2, 1, 1);
    }

    @Test
    public void testSetup(){
        assertEquals("Held", hero.getName());
        assertEquals(2, hero.getZone());
        assertEquals(1, hero.getDmg());
    }

    @Test
    public void testDoesDmg(){
        assertEquals(0, hero.doesDmg(monster));
        monster.setRing(2);
        assertEquals(0, hero.doesDmg(monster));
        monster.setRing(0);
        assertEquals(-1, hero.doesDmg(monster));
        monster.setZone(1);
        assertEquals(-1, hero.doesDmg(monster));
        hero.setZone(1);
        monster.setRing(1);
        assertEquals(0, hero.doesDmg(monster));
        monster.setRing(4);
        assertEquals(-1, hero.doesDmg(monster));
    }
}
