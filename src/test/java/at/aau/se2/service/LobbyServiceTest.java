package at.aau.se2.service;

import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static at.aau.se2.service.LobbyService.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class LobbyServiceTest {
    private List<WebSocketSession> conOrder;
    private List<Player> players;

    @BeforeEach
    public void setup(){
        this.conOrder = new ArrayList<>();
        this.players = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            conOrder.add(mock(WebSocketSession.class));
        }
    }

    @Test
    public void testCreateLobby(){
        assertEquals(Lobby.class, createLobby().getClass());
    }

    @Test
    public void testMovePlayerToLobby() throws IOException {
        assertTrue(players.isEmpty());
        movePlayerToLobby(mock(WebSocketSession.class), mock(Lobby.class), players);
        assertFalse(players.isEmpty());
    }

    @Test
    public void testMakeLobby() throws IOException{
        assertTrue(players.isEmpty());
        assertEquals(4, makeLobby(conOrder, 0, players));
        assertFalse(players.isEmpty());
        assertEquals(4, players.size());
    }
}
