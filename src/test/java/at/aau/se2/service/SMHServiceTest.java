package at.aau.se2.service;

import at.aau.se2.model.monsters.Bullrog;
import at.aau.se2.model.monsters.Slime;
import at.aau.se2.model.monsters.Sphinx;
import at.aau.se2.utils.GameState;
import at.aau.se2.utils.Lobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static at.aau.se2.service.SMHService.increaseMonsterId;
import static at.aau.se2.service.SMHService.spawnRandomMonster;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SMHServiceTest {
    private Lobby lobby;
    private SecureRandom rn;
    @BeforeEach
    public void setup(){
        lobby = mock(Lobby.class);
        when(lobby.getGameState()).thenReturn(new GameState());
        rn = mock(SecureRandom.class);
    }
    @Test
    public void testSpawnRandomMonster(){
        when(rn.nextInt(1,4)).thenReturn(1);
        assertEquals(Slime.class, spawnRandomMonster(1,lobby, rn).getClass());
        when(rn.nextInt(1,4)).thenReturn(2);
        assertEquals(Sphinx.class, spawnRandomMonster(2,lobby, rn).getClass());
        when(rn.nextInt(1,4)).thenReturn(3);
        assertEquals(Bullrog.class, spawnRandomMonster(3,lobby, rn).getClass());
        verify(lobby, times(3)).getGameState();
        verify(rn, times(3)).nextInt(1,4);
    }

    @Test
    public void testIncreaseMonsterId(){
        assertEquals(0, SMHService.getMonsterId());
        increaseMonsterId();
        assertEquals(1, SMHService.getMonsterId());
    }
}
