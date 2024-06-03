package at.aau.se2.service;

import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class PTServiceTest {

    private Player player;

    @BeforeEach
    public void setUp() {
        WebSocketSession session = mock(WebSocketSession.class);
        Lobby lobby = mock(Lobby.class);
        player = new Player(session, lobby);
    }

    @Test
    public void testUpdatePlayerPointsSchleim() {
        PTService.updatePlayerPoints(player, "Schleim");
        assertEquals(1, player.getPoints());
    }

    @Test
    public void testUpdatePlayerPointsSphinx() {
        PTService.updatePlayerPoints(player, "Sphinx");
        assertEquals(2, player.getPoints());
    }

    @Test
    public void testUpdatePlayerPointsBullrog() {
        PTService.updatePlayerPoints(player, "Bullrog");
        assertEquals(3, player.getPoints());
    }

    @Test
    public void testUpdatePlayerPointsUnknownMonster() {
        PTService.updatePlayerPoints(player, "UnknownMonster");
        assertEquals(0, player.getPoints());
    }
}