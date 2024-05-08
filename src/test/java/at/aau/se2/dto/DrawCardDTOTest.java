package at.aau.se2.dto;

import at.aau.se2.model.Actioncard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.TextMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DrawCardDTOTest {
    private DrawCardDTO dto;
    private Actioncard card;

    @BeforeEach
    public void setup(){
        card = mock(Actioncard.class);
        when(card.getId()).thenReturn(1);
        when(card.getName()).thenReturn("Test");
        when(card.getZone()).thenReturn(1);
        dto = new DrawCardDTO(card);
    }

    @Test
    public void testMakeMessage(){
        String msg = "{\"type\":\"DRAW_CARD\",\"id\":1,\"name\":\"Test\",\"zone\":1}";
        TextMessage exp = new TextMessage(msg);
        assertEquals(exp, dto.makeMessage());
    }
}
