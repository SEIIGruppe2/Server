package at.aau.se2.utils;

import at.aau.se2.exceptions.LobbyNotFoundException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UtilityMethodsTest {

    private final static List<Lobby> lobbies = new ArrayList<>();
    private final static List<Player> players = new ArrayList<>();
    private final static List<WebSocketSession> sessions = new ArrayList<>();

    @BeforeAll
    public static void setup(){
        int j = -1;
        for(int i = 0; i < 12; i++){
            sessions.add(mock(WebSocketSession.class));
            if(i % 4 == 0){
                lobbies.add(mock(Lobby.class));
                j++;
            }
            players.add(mock(Player.class));
            when(players.get(i).getSession()).thenReturn(sessions.get(i));
            when(players.get(i).getLobby()).thenReturn(lobbies.get(j));
            GameHandler.getUsernames().add("User"+i);
        }
        when(lobbies.get(0).getPlayers()).thenReturn(players.subList(0, 3));
        when(lobbies.get(1).getPlayers()).thenReturn(players.subList(4, 7));
        when(lobbies.get(2).getPlayers()).thenReturn(players.subList(8, 11));
    }
    @Test
    void testFindPlayerInLobby() throws PlayerNotFoundException {
        assertEquals(players.get(1), UtilityMethods.findPlayer(sessions.get(1), lobbies.get(0)));
        assertEquals(players.get(6), UtilityMethods.findPlayer(sessions.get(6), lobbies.get(1)));
        assertEquals(players.get(9), UtilityMethods.findPlayer(sessions.get(9), lobbies.get(2)));
    }

    @Test
    void testFindPlayerInLobbyExceptionThrown(){
        assertThrows(PlayerNotFoundException.class, () -> UtilityMethods.findPlayer(sessions.get(1), lobbies.get(1)));
        assertThrows(PlayerNotFoundException.class, () -> UtilityMethods.findPlayer(mock(WebSocketSession.class), lobbies.get(0)));
    }

    @Test
    void testFindPlayerOnServer() throws PlayerNotFoundException{
        for(int i = 0; i < 12; i++){
            assertEquals(players.get(i), UtilityMethods.findPlayer(sessions.get(i), players));
        }
    }

    @Test
    void testFindPlayerOnServerExceptionThrown(){
        assertThrows(PlayerNotFoundException.class, () -> UtilityMethods.findPlayer(mock(WebSocketSession.class), players));
    }

    @Test
    void testFindLobby() throws LobbyNotFoundException {
        for(int i = 0; i < 3; i++){
            assertEquals(lobbies.get(i), UtilityMethods.findLobby(sessions.get(3*(i+1)), players));
        }
    }

    @Test
    void testFindLobbyExceptionThrown(){
        assertThrows(LobbyNotFoundException.class, () -> UtilityMethods.findLobby(mock(WebSocketSession.class), players));
    }

    @Test
    void testCheckUsername(){
        assertTrue(UtilityMethods.checkUsername("User1"));
        assertFalse(UtilityMethods.checkUsername("username"));
    }
}
