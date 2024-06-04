package at.aau.se2.dto;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

public class PlayerAttackDTO extends ParentDTO{
    private final String monsterId;
    private final int lp;
    public PlayerAttackDTO(String monsterId, int lp) {
        super("CARD_ATTACK_MONSTER");
        this.monsterId = monsterId;
        this.lp = lp;
    }

    @Override
    public TextMessage makeMessage() {
        ObjectNode node = this.getMapper().createObjectNode();
        node.put("type", this.getType());
        node.put("monsterid", this.monsterId);
        node.put("lifepoints", this.lp);
        return new TextMessage(node.toString());
    }
}
