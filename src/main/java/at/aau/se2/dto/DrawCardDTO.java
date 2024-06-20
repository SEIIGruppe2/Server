package at.aau.se2.dto;

import at.aau.se2.model.Actioncard;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

/**
 * The DrawCardDTO class represents a data transfer object (DTO) for drawing a card action.
 * It extends the ParentDTO class and encapsulates the information about the card being drawn.
 */
public class DrawCardDTO extends ParentDTO{
    private final Actioncard card;

    /**
     * Constructs a new DrawCardDTO object with the specified action card.
     *
     * @param card the action card being drawn
     */
    public DrawCardDTO(Actioncard card){
        super("DRAW_CARD");
        this.card = card;
    }

    /**
     * Creates a TextMessage containing the information about the drawn card.
     *
     * @return a TextMessage representing the drawn card information
     */
    @Override
    public TextMessage makeMessage(){
        ObjectNode node = this.getMapper().createObjectNode();
//        node.put("type", this.getType());
//        node.put("id", card.getId());
//        node.put("name", card.getName());
//        node.put("zone", card.getZone());
        node.set("type", JsonNodeFactory.instance.textNode(this.getType()));
        node.set("id", JsonNodeFactory.instance.numberNode(card.getId()));
        node.set("name", JsonNodeFactory.instance.textNode(card.getName()));
        node.set("zone", JsonNodeFactory.instance.numberNode(card.getZone()));
        return new TextMessage(node.toString());
    }
}
