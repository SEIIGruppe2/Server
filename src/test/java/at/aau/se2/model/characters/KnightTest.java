package at.aau.se2.model.characters;

import at.aau.se2.model.Monster;
import at.aau.se2.model.monsters.Bullrog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class KnightTest {
    Knight knight;
    Monster monster, monster1, monster2, monster3;
    @BeforeEach
    public void setupTests(){
        knight = new Knight(2,1);
        monster = new Bullrog(2, 2, 1);
        monster = mock(Bullrog.class);
        monster1 = mock(Bullrog.class);
        monster2 = mock(Bullrog.class);
        monster3 = mock(Bullrog.class);

        when(monster.getZone()).thenReturn(2);
        when(monster.getRing()).thenReturn(2);

        when(monster1.getZone()).thenReturn(2);
        when(monster1.getRing()).thenReturn(3);

        when(monster2.getZone()).thenReturn(1);
        when(monster2.getRing()).thenReturn(3);

        when(monster3.getZone()).thenReturn(1);
        when(monster3.getRing()).thenReturn(2);
    }

    @Test
    void testSetup(){
        assertEquals("Ritter", knight.getName());
        assertEquals(2, knight.getZone());
        assertEquals(1, knight.getDmg());
    }

    @Test
    void testDoesDmg(){
        assertEquals(0, knight.doesDmg(monster));
        monster.setRing(3);
        monster.setZone(1);
        knight.setZone(1);
        monster.setRing(2);
        assertEquals(0, knight.doesDmg(monster3));
    }
}
