package at.aau.se2.dto;

import at.aau.se2.model.monsters.Slime;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.TextMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpawnMonsterDTOTest {

    @Test
    void testConvertToJson(){
        Slime slime = new Slime(1,1,1);
        String expected = "{\"type\":\"SPAWN_MONSTER\",\"monster\":\"{ 'id': '1', 'zone': '1', 'ring': '1', 'name': 'Schleim', 'lifepoints': '1'}\"}";
        SpawnMonsterDTO dto = new SpawnMonsterDTO(slime);
        assertEquals(new TextMessage(expected), dto.makeMessage());
    }
}
