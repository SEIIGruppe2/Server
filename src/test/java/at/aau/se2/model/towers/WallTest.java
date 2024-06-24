package at.aau.se2.model.towers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WallTest {
    Wall wall;
    @BeforeEach
    public void setupTests(){
        wall = new Wall(1);
    }

    @Test
    void testSetup(){
        assertEquals(2, wall.getLifepoints());
    }

    @Test
    void testTakeDamage(){
        assertEquals(0, wall.takeDamage(1));
        assertEquals(1, wall.getLifepoints());
        assertEquals(-1, wall.takeDamage(1));
        assertEquals(0, wall.getLifepoints());
    }
}
