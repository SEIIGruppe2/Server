package at.aau.se2.utils;

import at.aau.se2.model.characters.Archer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlayerTest {
    private Player player;
    private Lobby lobby;
    private WebSocketSession session;

    @BeforeEach
    public void setup(){
        session = mock(WebSocketSession.class);
        lobby = mock(Lobby.class);
        when(session.getId()).thenReturn("123");
        player = new Player(session, lobby);
    }

    @Test
    public void testGetLobby(){
        assertEquals(this.lobby, player.getLobby());
    }

    @Test
    public void testGetSession(){
        assertEquals(this.session, player.getSession());
    }

    @Test
    public void testGetSetPoints(){
        assertEquals(0, player.getPoints());
        player.setPoints(1);
        assertEquals(1, player.getPoints());
        player.setPoints(4);
        assertEquals(5, player.getPoints());
    }

    @Test
    public void testGetSetUsername(){
        assertEquals("", player.getUsername());
        assertFalse(player.isUsernameSet());
        player.setUsername("tester");
        assertTrue(player.isUsernameSet());
        assertEquals("tester", player.getUsername());
        player.setUsername("tester2");
        assertEquals("tester", player.getUsername());
    }

    @Test
    public void testGetPlayerID(){

        assertEquals("123", player.getPlayerID());
    }

    @Test
    public void testGetCards(){
        assertTrue(player.getCards().isEmpty());
        player.getCards().add(mock(Archer.class));
        assertFalse(player.getCards().isEmpty());
    }

    @Test
    public void testConstructor(){
        assertEquals("", player.getUsername());
        assertFalse(player.isUsernameSet());
        assertEquals("123", player.getPlayerID());
        assertEquals(this.session, player.getSession());
        assertEquals(this.lobby, player.getLobby());
        assertEquals(0, player.getPoints());
        assertTrue(player.getCards().isEmpty());
    }

}
