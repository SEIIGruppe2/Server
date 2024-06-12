package at.aau.se2.dto;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

/**
 * The PlayerAttackDTO class represents a data transfer object (DTO) for a player attacking a monster.
 * It extends the ParentDTO class and encapsulates the information about the attack.
 */
public class PlayerAttackDTO extends ParentDTO{
    private final String monsterId;
    private final int lp;

    /**
     * Constructs a new PlayerAttackDTO object with the specified monster ID and remaining life points.
     *
     * @param monsterId the ID of the monster being attacked
     * @param lp the remaining life points of the monster after the attack
     */
    public PlayerAttackDTO(String monsterId, int lp) {
        super("CARD_ATTACK_MONSTER");
        this.monsterId = monsterId;
        this.lp = lp;
    }

    /**
     * Creates a TextMessage containing the information about the player's attack.
     *
     * @return a TextMessage representing the attack information
     */
    @Override
    public TextMessage makeMessage() {
        ObjectNode node = this.getMapper().createObjectNode();
        node.put("type", this.getType());
        node.put("monsterid", this.monsterId);
        node.put("lifepoints", this.lp);
        return new TextMessage(node.toString());
    }
}
