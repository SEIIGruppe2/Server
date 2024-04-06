package at.aau.se2.model.monsters;

import at.aau.se2.model.towers.TowerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BullrogTest {
    Bullrog bullrog;
    TowerImpl tower;
    @BeforeEach
    public void setupTests(){
        bullrog = new Bullrog(2,1, 1);
        tower = new TowerImpl(1);
    }

    @Test
    public void testSetup(){
        assertEquals(3, bullrog.getLifepoints());
        assertEquals(2, bullrog.getZone());
        assertEquals(1, bullrog.getRing());
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
        bullrog.doesDmg(tower);
        bullrog.doesDmg(tower);
        assertEquals(-1, bullrog.doesDmg(tower));
    }
}
