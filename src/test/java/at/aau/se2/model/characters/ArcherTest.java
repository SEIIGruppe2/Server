package at.aau.se2.model.characters;

import at.aau.se2.model.Monster;
import at.aau.se2.model.monsters.Bullrog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ArcherTest {
    Archer archer;
    Monster monster, monster1, monster2, monster3;
    @BeforeEach
    public void setupTests(){
        archer = new Archer(2,1);
        monster = mock(Bullrog.class);
        monster1 = mock(Bullrog.class);
        monster2 = mock(Bullrog.class);
        monster3 = mock(Bullrog.class);

        when(monster.getZone()).thenReturn(2);
        when(monster.getRing()).thenReturn(1);

        when(monster1.getZone()).thenReturn(2);
        when(monster1.getRing()).thenReturn(2);

        when(monster2.getZone()).thenReturn(1);
        when(monster2.getRing()).thenReturn(2);

        when(monster3.getZone()).thenReturn(1);
        when(monster3.getRing()).thenReturn(1);
    }

    @Test
    public void testSetup(){
        assertEquals("Bogenschütze", archer.getName());
        assertEquals(2, archer.getZone());
        assertEquals(1, archer.getDmg());
        assertEquals(1, archer.getId());
    }

    @Test
    public void testDoesDmg(){
        assertEquals(0, archer.doesDmg(monster));
        archer.setZone(1);
        assertEquals(0, archer.doesDmg(monster3));
    }

    @Test
    public void testConvertToJson(){
        String result = "'id': '" +
                1 +
                "', 'name': '" +
                "Bogenschütze" +
                "', 'zone': '" +
                "2" +
                "'";
        assertEquals(result, archer.convertToJson());
    }
}
