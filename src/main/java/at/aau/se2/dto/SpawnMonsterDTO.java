package at.aau.se2.dto;

import at.aau.se2.model.Monster;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

public class SpawnMonsterDTO extends ParentDTO {
    private final Monster monster;

    public SpawnMonsterDTO(Monster monster) {
        super("SPAWN_MONSTER");
        this.monster = monster;
    }

    @Override
    public TextMessage makeMessage() {
        ObjectNode node = this.getMapper().createObjectNode();
        node.put("type", this.getType());
        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode monsterNode = mapper.readTree(this.monster.convertToJson());
            node.set("monster", monsterNode);

        } catch (JsonProcessingException e)
        {
            throw new RuntimeException("Fehler beim Erstellen des Monster-JSON", e);
        }


        return new TextMessage(node.toString());
    }
}
