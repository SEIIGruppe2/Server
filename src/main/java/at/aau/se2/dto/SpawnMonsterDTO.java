package at.aau.se2.dto;

import at.aau.se2.model.Monster;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

public class SpawnMonsterDTO extends ParentDTO{
    private final Monster monster;
    public SpawnMonsterDTO(Monster monster){
        super("SPAWN_MONSTER");
        this.monster = monster;
    }
    @Override
    public TextMessage makeMessage() {
        ObjectNode node = this.getMapper().createObjectNode();
        node.put("type", this.getType());
        node.put("monster", this.monster.convertToJson());
        return new TextMessage(node.toString());
    }
}
