package at.aau.se2.handler.game.subhandlers;


import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.WebSocketSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


public class RequestUsernamesForSwitchHandlerTest {


    private RequestUsernamesForSwitchHandler rufs;


    @BeforeEach
    public void setup(){
        rufs = new RequestUsernamesForSwitchHandler();
    }

    @Test
    public void testConvertToJson(){

        String test = "{ 'type': 'REQUEST_USERNAMES_SWITCH', 'usernames': ['no users found','123','456']}";
        rufs.handleMessage(mock(WebSocketSession.class), mock(JsonNode.class), mock(Lobby.class));
        rufs.usernames.add("123");
        rufs.usernames.add("456");

        assertEquals(test,rufs.convertToJson());


    }

}



