package at.aau.se2.model.monsters;

import at.aau.se2.model.towers.TowerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SlimeTest {
    Slime slime;
    TowerImpl tower, tower2;
    @BeforeEach
    public void setupTests(){
        slime = new Slime(2,1, 1);
        tower = mock(TowerImpl.class);
        tower2 = mock(TowerImpl.class);
        when(tower.getLifepoints()).thenReturn(2);
        when(tower2.getLifepoints()).thenReturn(0);
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
        assertEquals(-1, slime.doesDmg(tower2));
    }
}
