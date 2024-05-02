package at.aau.se2.handler.dummy;


import at.aau.se2.handler.game.subhandlers.RequestUsernamesForSwitchHandler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


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



