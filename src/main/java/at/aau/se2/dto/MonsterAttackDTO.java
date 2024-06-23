package at.aau.se2.dto;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

/**
 * A Data Transfer Object (DTO) that represents the state of an attack between a monster and a tower.
 * This class extends the ParentDTO class and is used to encapsulate the details of a monster attack.
 */
public class MonsterAttackDTO extends ParentDTO {
    private final String monsterId;
    private final String towerId;
    private final int monsterHp;
    private final int towerHp;
    private final String attackStatus;

    /**
     * Constructs a new MonsterAttackDTO with the specified details of the attack.
     *
     * @param monsterId the unique identifier of the monster
     * @param towerId the unique identifier of the tower
     * @param monsterHp the current health points of the monster
     * @param towerHp the current health points of the tower
     * @param attackStatus the status of the attack (e.g., "SUCCESS", "FAILURE")
     */
    public MonsterAttackDTO(String monsterId, String towerId, int monsterHp, int towerHp, String attackStatus) {
        super("MONSTER_ATTACK");
        this.monsterId = monsterId;
        this.towerId = towerId;
        this.monsterHp = monsterHp;
        this.towerHp = towerHp;
        this.attackStatus = attackStatus;
    }

    /**
     * Creates a WebSocket TextMessage containing the details of the monster attack.
     * This method serializes the attack details into a JSON object and encapsulates it in a TextMessage.
     *
     * @return a TextMessage containing the JSON representation of the monster attack details
     */
    @Override
    public TextMessage makeMessage() {
        ObjectNode node = this.getMapper().createObjectNode();
        node.set("type", JsonNodeFactory.instance.textNode(this.getType()));
        node.set("monsterId", JsonNodeFactory.instance.textNode(this.monsterId));
        node.set("towerId", JsonNodeFactory.instance.textNode(this.towerId));
        node.set("monsterHp", JsonNodeFactory.instance.numberNode(this.monsterHp));
        node.set("towerHp", JsonNodeFactory.instance.numberNode(this.towerHp));
        node.set("attackStatus", JsonNodeFactory.instance.textNode(this.attackStatus));
        return new TextMessage(node.toString());
    }
}
