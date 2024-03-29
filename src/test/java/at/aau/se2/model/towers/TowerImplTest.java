package at.aau.se2.model.towers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TowerImplTest {
    TowerImpl tower;
    @BeforeEach
    public void setupTests(){
        tower = new TowerImpl();
    }

    @Test
    public void testSetup(){
        assertEquals(3, tower.getLifepoints());
    }

    @Test
    public void testTakeDamage(){
        assertEquals(0, tower.takeDamage(1));
        assertEquals(2, tower.getLifepoints());
        assertEquals(-1, tower.takeDamage(2));
        assertEquals(0, tower.getLifepoints());
    }
}
