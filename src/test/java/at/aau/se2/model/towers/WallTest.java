package at.aau.se2.model.towers;

import at.aau.se2.model.Monster;
import at.aau.se2.model.characters.Archer;
import at.aau.se2.model.monsters.Bullrog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WallTest {
    Wall wall;
    @BeforeEach
    public void setupTests(){
        wall = new Wall();
    }

    @Test
    public void testSetup(){
        assertEquals(2, wall.getLifepoints());
    }

    @Test
    public void testTakeDamage(){
        assertEquals(0, wall.takeDamage(1));
        assertEquals(1, wall.getLifepoints());
        assertEquals(-1, wall.takeDamage(1));
        assertEquals(0, wall.getLifepoints());
    }
}
