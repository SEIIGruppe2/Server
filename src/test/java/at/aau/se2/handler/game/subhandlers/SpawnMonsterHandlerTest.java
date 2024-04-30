package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.model.monsters.Bullrog;
import at.aau.se2.model.monsters.Slime;
import at.aau.se2.model.monsters.Sphinx;
import at.aau.se2.utils.GameState;
import at.aau.se2.utils.Lobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SpawnMonsterHandlerTest {
    private Lobby lobby;

    private int zone;
    private SecureRandom rn;
    private SpawnMonsterHandler sph;
    @BeforeEach
    public void setup(){
        lobby = mock(Lobby.class);
        when(lobby.getGameState()).thenReturn(new GameState());
        rn = mock(SecureRandom.class);
        sph = new SpawnMonsterHandler(rn);
    }
    @Test
    public void testSpawnRandomMonster(){
        when(rn.nextInt(1,3)).thenReturn(1);
        assertEquals(Slime.class, sph.spawnRandomMonster(1,lobby).getClass());
        when(rn.nextInt(1,3)).thenReturn(2);
        assertEquals(Sphinx.class, sph.spawnRandomMonster(2,lobby).getClass());
        when(rn.nextInt(1,3)).thenReturn(3);
        assertEquals(Bullrog.class, sph.spawnRandomMonster(3,lobby).getClass());
        verify(lobby, times(3)).getGameState();
        verify(rn, times(3)).nextInt(1,3);
    }

    @Test
    public void testIncreaseMonsterId(){
        assertEquals(0, SpawnMonsterHandler.getMonsterId());
        SpawnMonsterHandler.increaseMonsterId();
        assertEquals(1, SpawnMonsterHandler.getMonsterId());
    }

    @Test
    public void testConvertToJson(){
        Slime slime = new Slime(1,1,1);
        String expected = "{ 'type':'SPAWN_MONSTER', 'monster': " + slime.convertToJson() + "}";
        assertEquals(expected, sph.convertToJson(slime));
    }
}
