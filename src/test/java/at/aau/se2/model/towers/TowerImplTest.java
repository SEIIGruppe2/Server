package at.aau.se2.model.towers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TowerImplTest {
    TowerImpl tower;
    @BeforeEach
    public void setupTests(){
        tower = new TowerImpl(1);
    }

    @Test
    public void testSetup(){
        assertEquals(10, tower.getLifepoints());
    }

    @Test
    public void testTakeDamage(){
        assertEquals(0, tower.takeDamage(1));
        assertEquals(9, tower.getLifepoints());
        assertEquals(-1, tower.takeDamage(9));
        assertEquals(0, tower.getLifepoints());
        assertEquals(1, tower.getId());
    }

    @Test
    public void testConvertToJson(){
        String res = "{ 'id': '1','lifepoints':'10'}";
        assertEquals(res, tower.convertToJson());
    }


    @Test
    public void testSetterMethod(){
        tower.setLifepoints(10);
        assertEquals(10, tower.getLifepoints());
    }


}
