package at.aau.se2.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestUsernamesForSwitchDTOTest {
    private List<String> names;
    private RequestUsernamesForSwitchDTO dto;
    @BeforeEach
    public void setup(){
        names = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            names.add("test " + i);
        }
        dto = new RequestUsernamesForSwitchDTO(names);
    }

    @Test
    public void testMakeMessage(){
        String msg = "{\"type\":\"REQUEST_USERNAMES_SWITCH\",\"usernames\":\"['test 0','test 1','test 2','test 3','test 4']\"}";
        TextMessage exp = new TextMessage(msg);
        assertEquals(exp, dto.makeMessage());
    }
}
