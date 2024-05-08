package at.aau.se2.dto;

import org.junit.jupiter.api.Test;
import org.springframework.web.socket.TextMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterUsernameDTOTest {
    private RegisterUsernameDTO dto;

    @Test
    public void testMakeMessage(){
        dto = new RegisterUsernameDTO("accepted");
        String msg = "{\"type\":\"REGISTER_USERNAME\",\"response\":\"accepted\"}";
        TextMessage exp = new TextMessage(msg);
        assertEquals(exp, dto.makeMessage());
        msg = "{\"type\":\"REGISTER_USERNAME\",\"response\":\"denied\"}";
        exp = new TextMessage(msg);
        dto = new RegisterUsernameDTO("denied");
        assertEquals(exp, dto.makeMessage());
    }
}
