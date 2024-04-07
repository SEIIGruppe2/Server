package at.aau.se2.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class LobbyTest {
    @Test
    public void testConstructor(){
        GameState state = new GameState();
        Lobby lobby = new Lobby(state);
        assertEquals(state, lobby.getGameState());
        assertTrue(lobby.getPlayers().isEmpty());
        Player player = mock(Player.class);
        lobby.getPlayers().add(player);
        assertFalse(lobby.getPlayers().isEmpty());
    }
}
