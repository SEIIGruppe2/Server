package at.aau.se2.model.characters;

import at.aau.se2.model.Monster;
import at.aau.se2.model.monsters.Bullrog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HeroTest {
    Hero hero;
    Monster monster, monster1, monster2, monster3, monster4, monster5;
    @BeforeEach
    public void setupTests(){
        hero = new Hero(2,1);
        monster = new Bullrog(2, 1, 1);
        monster = mock(Bullrog.class);
        monster1 = mock(Bullrog.class);
        monster2 = mock(Bullrog.class);
        monster3 = mock(Bullrog.class);
        monster4 = mock(Bullrog.class);
        monster5 = mock(Bullrog.class);

        when(monster.getZone()).thenReturn(2);
        when(monster.getRing()).thenReturn(1);

        when(monster1.getZone()).thenReturn(2);
        when(monster1.getRing()).thenReturn(2);

        when(monster2.getZone()).thenReturn(2);
        when(monster2.getRing()).thenReturn(0);

        when(monster3.getZone()).thenReturn(1);
        when(monster3.getRing()).thenReturn(0);

        when(monster4.getZone()).thenReturn(1);
        when(monster4.getRing()).thenReturn(1);

        when(monster5.getZone()).thenReturn(1);
        when(monster5.getRing()).thenReturn(4);
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

        assertEquals(0, hero.doesDmg(monster1));
        hero.setZone(1);
        assertEquals(0, hero.doesDmg(monster4));

        assertEquals(-1, hero.doesDmg(monster5));
    }
}
