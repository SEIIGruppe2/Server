package at.aau.se2.handler.dummy;

import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.handler.game.subhandlers.RequestUsernamesForSwitchHandler;
import at.aau.se2.handler.game.subhandlers.SwitchCardDeckHandler;
import at.aau.se2.utils.GameState;
import at.aau.se2.utils.Lobby;
import at.aau.se2.utils.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketExtension;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.Principal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestUsernamesForSwitchHandlerTest {


    private RequestUsernamesForSwitchHandler rufs;


    @BeforeEach
    public void setup(){
        rufs = new RequestUsernamesForSwitchHandler();
    }

    @Test
    public void testConvertToJson(){

        String test = "{ 'type': 'REQUEST_USERNAMES_SWITCH', 'usernames': ['123','456']}";

        rufs.usernames.add("123");
        rufs.usernames.add("456");

        assertEquals(test,rufs.convertToJson());


    }

}



