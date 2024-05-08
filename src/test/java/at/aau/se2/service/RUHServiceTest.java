package at.aau.se2.service;

import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.utils.Player;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static at.aau.se2.service.RUHService.addUsername;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class RUHServiceTest {
    private static Player player;
    private static MockedStatic<GameHandler> gh;

    @BeforeAll
    public static void setup(){
        player = mock(Player.class);
        gh = mockStatic(GameHandler.class);
        List<String> usernames = new ArrayList<>();
        gh.when(GameHandler::getUsernames).thenReturn(usernames);
    }

    @Test
    public void testAddUsername(){
        assertEquals("accepted", addUsername("test", player));
        assertEquals("denied", addUsername("test", player));
    }

    @AfterAll
    public static void cleanup(){
        gh.close();
    }
}
