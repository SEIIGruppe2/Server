package at.aau.se2.service;

import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class ACCServiceTest {

    private Player cheater;
    private Player accusator;

    @BeforeEach
    public void setUp() {
        WebSocketSession session = mock(WebSocketSession.class);
        Lobby lobby = mock(Lobby.class);
        cheater = new Player (session, lobby);
        accusator = new Player(session, lobby);
    }