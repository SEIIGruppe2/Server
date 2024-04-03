package at.aau.se2.model.monsters;

import at.aau.se2.model.towers.TowerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlimeTest {
    Slime slime;
    TowerImpl tower;
    @BeforeEach
    public void setupTests(){
        slime = new Slime(2,1, 1);
        tower = new TowerImpl();
    }

    @Test
    public void testSetup(){
        assertEquals(1, slime.getLifepoints());
        assertEquals(2, slime.getZone());
        assertEquals(1, slime.getRing());
        assertEquals("Schleim", slime.getName());
    }

    @Test
    public void testTakeDamage(){
        assertEquals(-1, slime.takeDamage(1));
        assertEquals(0, slime.getLifepoints());
    }

    @Test
    public void testDoesDamage(){
        assertEquals(0, slime.doesDmg(tower));
        slime.doesDmg(tower);
        slime.doesDmg(tower);
        assertEquals(-1, slime.doesDmg(tower));
    }
}
