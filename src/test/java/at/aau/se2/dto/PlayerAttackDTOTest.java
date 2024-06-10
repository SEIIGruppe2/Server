package at.aau.se2.dto;

import org.junit.jupiter.api.Test;
import org.springframework.web.socket.TextMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerAttackDTOTest {
    @Test
    public void testMakeMessage(){
        PlayerAttackDTO dto = new PlayerAttackDTO("1", 5);
        String result = "{\"type\":\"CARD_ATTACK_MONSTER\",\"monsterId\":\"1\",\"lifepoints\":5}";
        assertEquals(new TextMessage(result), dto.makeMessage());
    }
}
