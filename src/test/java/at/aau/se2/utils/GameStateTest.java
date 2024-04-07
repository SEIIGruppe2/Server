package at.aau.se2.utils;

import at.aau.se2.model.monsters.Slime;
import at.aau.se2.model.towers.TowerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameStateTest {

    private GameState gs;
    private Slime monster;
    private TowerImpl tower;
    @BeforeEach
    public void setup(){
        gs = new GameState();
        monster = mock(Slime.class);
        when(monster.convertToJson()).thenReturn("testString Monster");
        tower = mock(TowerImpl.class);
        when(tower.convertToJson()).thenReturn("testString Tower");
    }

    @Test
    public void testIncreaseRound(){
        assertEquals(0, gs.getRound());
        gs.increaseRound();
        assertEquals(1, gs.getRound());
    }

    @Test
    public void testIncreaseCardAmount(){
        int loop = 0;
        for(int i : gs.getCardTypeAmount()){
            assertEquals(0, i);
            gs.increaseCardAmount(loop);
            assertEquals(gs.getCardTypeAmount()[loop++], 1);
        }
    }

    @Test
    public void testDecreaseCardAmount(){
        int loop = 0;
        for(int i = 0; i < 4; i++){
            gs.increaseCardAmount(i);
        }
        for(int i : gs.getCardTypeAmount()){
            assertEquals(1, i);
            gs.decreaseCardAmount(loop);
            assertEquals(0, gs.getCardTypeAmount()[loop++]);
        }
    }


    public void setupMinTest(){
        for(int i = 0; i < 4; i++){
            gs.increaseCardAmount(i);
        }
        gs.increaseCardAmount(3);
        gs.increaseCardAmount(2);
        gs.increaseCardAmount(1);
    }
    @Test
    public void testGetIndexMinimumCardAmount(){
        setupMinTest();
        assertEquals(0, gs.getIndexMinimumCardAmount());
        gs.increaseCardAmount(0);
        gs.decreaseCardAmount(1);
        assertEquals(1, gs.getIndexMinimumCardAmount());
    }

    @Test
    public void testConvertToJson(){
        String resultJson = "{ 'type':'GAME_STATE_UPDATE', 'monsters': [" +
                    "],'towers':[],'round':'0'}";
        assertEquals(resultJson, gs.convertToJson());
        gs.getMonsters().add(monster);
        gs.getTowers().add(tower);

        resultJson = "{ 'type':'GAME_STATE_UPDATE', 'monsters': [" +
                "testString Monster],'towers':[testString Tower],'round':'0'}";
        assertEquals(resultJson, gs.convertToJson());
        gs.getMonsters().add(monster);
        gs.getTowers().add(tower);
        resultJson = "{ 'type':'GAME_STATE_UPDATE', 'monsters': [" +
                "testString Monster,testString Monster],'towers':" +
                "[testString Tower,testString Tower],'round':'0'}";
        assertEquals(resultJson, gs.convertToJson());
    }


}
