package at.aau.se2.model.monsters;

import at.aau.se2.model.towers.TowerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BullrogTest {
    Bullrog bullrog;
    TowerImpl tower, tower2;
    @BeforeEach
    public void setupTests(){
        bullrog = new Bullrog(2,1, 1);
        tower = mock(TowerImpl.class);
        tower2 = mock(TowerImpl.class);
        when(tower.getLifepoints()).thenReturn(2);
        when(tower2.getLifepoints()).thenReturn(0);
    }

    @Test
    public void testSetup(){
        assertEquals(3, bullrog.getLifepoints());
        assertEquals(2, bullrog.getZone());
        bullrog.setZone(3);
        assertEquals(3, bullrog.getZone());
        assertEquals(1, bullrog.getRing());
        bullrog.setRing(2);
        assertEquals(2, bullrog.getRing());
        assertEquals("Bullrog", bullrog.getName());
    }

    @Test
    public void testTakeDamage(){
        assertEquals(0, bullrog.takeDamage(1));
        assertEquals(2, bullrog.getLifepoints());
        assertEquals(-1, bullrog.takeDamage(2));
        assertEquals(0, bullrog.getLifepoints());
    }

    @Test
    public void testDoesDamage(){
        assertEquals(0, bullrog.doesDmg(tower));
        assertEquals(-1, bullrog.doesDmg(tower2));
    }

    @Test
    public void testConvertToJson(){
        String res = "{ 'id': '1', 'zone': '2', 'ring': '1', " +
                "'name': 'Bullrog', 'lifepoints': '3'}";
        assertEquals(res, bullrog.convertToJson());
    }
}
