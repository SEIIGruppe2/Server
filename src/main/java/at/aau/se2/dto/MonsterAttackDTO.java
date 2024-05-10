package at.aau.se2.dto;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

public class MonsterAttackDTO extends ParentDTO {
    private final String monsterId;
    private final int monsterHp;
    private final int towerHp;
    private final String attackStatus;

    public MonsterAttackDTO(String monsterId, int monsterHp, int towerHp, String attackStatus) {
        super("MONSTER_ATTACK");
        this.monsterId = monsterId;
        this.monsterHp = monsterHp;
        this.towerHp = towerHp;
        this.attackStatus = attackStatus;
    }

    @Override
    public TextMessage makeMessage() {
        ObjectNode node = this.getMapper().createObjectNode();
        node.put("type", this.getType());
        node.put("monsterId", monsterId);
        node.put("monsterHp", monsterHp);
        node.put("towerHp", towerHp);
        node.put("attackStatus", attackStatus);
        return new TextMessage(node.toString());
    }
}