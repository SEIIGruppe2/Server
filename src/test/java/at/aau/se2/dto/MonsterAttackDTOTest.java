package at.aau.se2.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.socket.TextMessage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MonsterAttackDTOTest {
    private MonsterAttackDTO dto;

    @BeforeEach
    protected void setup() {
        dto = new MonsterAttackDTO("monster1", "tower1",10, 5, "success");
    }

    @Test
    protected void testMakeMessage() {
        String expectedJson = "{\"type\":\"MONSTER_ATTACK\",\"monsterId\":\"monster1\",\"towerId\":\"tower1\",\"monsterHp\":10,\"towerHp\":5,\"attackStatus\":\"success\"}";
        TextMessage expectedMessage = new TextMessage(expectedJson);
        assertEquals(expectedMessage, dto.makeMessage());
    }
}