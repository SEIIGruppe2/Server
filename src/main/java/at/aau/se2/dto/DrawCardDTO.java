package at.aau.se2.dto;

import at.aau.se2.model.Actioncard;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

public class DrawCardDTO extends ParentDTO{
    private Actioncard card;

    public DrawCardDTO(Actioncard card){
        super("DRAW_CARD");
        this.card = card;
    }

    @Override
    public TextMessage makeMessage(){
        ObjectNode node = this.getMapper().createObjectNode();
        node.put("type", this.getType());
        node.put("id", card.getId());
        node.put("name", card.getName());
        node.put("zone", card.getZone());
        return new TextMessage(node.toString());
    }
}
