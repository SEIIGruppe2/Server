package at.aau.se2.model.monsters;

import at.aau.se2.model.towers.TowerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SphinxTest {
    Sphinx sphinx;
    TowerImpl tower;
    @BeforeEach
    public void setupTests(){
        sphinx = new Sphinx(2,1, 1);
        tower = new TowerImpl(1);
    }

    @Test
    public void testSetup(){
        assertEquals(2, sphinx.getLifepoints());
        assertEquals(2, sphinx.getZone());
        assertEquals(1, sphinx.getRing());
        assertEquals("Sphinx", sphinx.getName());
    }

    @Test
    public void testTakeDamage(){
        assertEquals(0, sphinx.takeDamage(1));
        assertEquals(1, sphinx.getLifepoints());
        assertEquals(-1, sphinx.takeDamage(1));
        assertEquals(0, sphinx.getLifepoints());
    }

    @Test
    public void testDoesDamage(){
        assertEquals(0, sphinx.doesDmg(tower));
        sphinx.doesDmg(tower);
        sphinx.doesDmg(tower);
        assertEquals(-1, sphinx.doesDmg(tower));
    }
}
