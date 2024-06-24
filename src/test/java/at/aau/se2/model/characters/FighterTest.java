package at.aau.se2.model.characters;

import at.aau.se2.model.Monster;
import at.aau.se2.model.monsters.Bullrog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FighterTest {
    Fighter fighter;
    Monster monster, monster1, monster2, monster3;
    @BeforeEach
    public void setupTests(){
        fighter = new Fighter(2,1);
        monster = mock(Bullrog.class);
        monster1 = mock(Bullrog.class);
        monster2 = mock(Bullrog.class);
        monster3 = mock(Bullrog.class);

        when(monster.getZone()).thenReturn(2);
        when(monster.getRing()).thenReturn(3);

        when(monster1.getZone()).thenReturn(2);
        when(monster1.getRing()).thenReturn(2);

        when(monster2.getZone()).thenReturn(1);
        when(monster2.getRing()).thenReturn(2);

        when(monster3.getZone()).thenReturn(1);
        when(monster3.getRing()).thenReturn(3);
    }

    @Test
    void testSetup(){
        assertEquals("Schwertkämpfer", fighter.getName());
        assertEquals(2, fighter.getZone());
        assertEquals(1, fighter.getDmg());
    }

    @Test
    void testDoesDmg(){
        assertEquals(0, fighter.doesDmg(monster));
        fighter.setZone(1);
        assertEquals(0, fighter.doesDmg(monster3));
    }
}
