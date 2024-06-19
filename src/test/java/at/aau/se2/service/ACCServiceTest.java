package at.aau.se2.service;

import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;

public class ACCServiceTest {

    private Player cheater;
    private Player accusator;

    @BeforeEach
    public void setUp() {
        WebSocketSession session = mock(WebSocketSession.class);
        Lobby lobby = mock(Lobby.class);
        cheater = new Player(session, lobby);
        accusator = new Player(session, lobby);
    }

    @Test
    public void testCheckCheaterTrueWithEnoughPoints() {
        cheater.setPoints(10);
        cheater.setCheating(true);
        cheater.setCheatingRoundsLeft(3);

        ACCService.checkCheater(cheater, accusator);

        assertEquals(5, cheater.getPoints());
        assertEquals(0, cheater.getCheatingRoundsLeft());
        assertFalse(cheater.isCheating());
    }

    @Test
    public void testCheckCheaterTrueWithNotEnoughPoints() {
        cheater.setPoints(3);
        cheater.setCheating(true);
        cheater.setCheatingRoundsLeft(3);

        ACCService.checkCheater(cheater, accusator);

        assertEquals(0, cheater.getPoints());
        assertEquals(0, cheater.getCheatingRoundsLeft());
        assertFalse(cheater.isCheating());
    }

    @Test
    public void testCheckCheaterFalseWithEnoughPoints() {
        accusator.setPoints(10);
        cheater.setCheating(false);

        ACCService.checkCheater(cheater, accusator);

        assertEquals(5, accusator.getPoints());
        assertFalse(cheater.isCheating());
    }

    @Test
    public void testCheckCheaterFalseWithNotEnoughPoints() {
        accusator.setPoints(3);
        cheater.setCheating(false);

        ACCService.checkCheater(cheater, accusator);

        assertEquals(0, accusator.getPoints());
        assertFalse(cheater.isCheating());
    }
}