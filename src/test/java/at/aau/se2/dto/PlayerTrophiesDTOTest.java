package at.aau.se2.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.web.socket.TextMessage;

class PlayerTrophiesDTOTest {

    @Test
    void testConstructor() {
        String playerName = "testPlayer";
        int points = 1;

        PlayerTrophiesDTO dto = new PlayerTrophiesDTO(playerName, points);

        assertEquals("PLAYER_TROPHIES", dto.getType());
        assertEquals("testPlayer", playerName);
        assertEquals(1, points);
    }

    @Test
    void testMakeMessage() {
        String playerName = "testPlayer";
        int points = 1;

        PlayerTrophiesDTO dto = new PlayerTrophiesDTO(playerName, points);
        TextMessage message = dto.makeMessage();

        assertNotNull(message);
        String payload = message.getPayload();
        assertNotNull(payload);


        assertEquals("{\"type\":\"PLAYER_TROPHIES\",\"playerName\":\"testPlayer\",\"points\":1}", payload);
    }
}