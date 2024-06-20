package at.aau.se2.dto;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

/**
 * The RegisterUsernameDTO class represents a data transfer object (DTO) for registering a username.
 * It extends the ParentDTO class and encapsulates the response to the username registration attempt.
 */
public class RegisterUsernameDTO extends ParentDTO{
    private final String res;

    /**
     * Constructs a new RegisterUsernameDTO object with the specified response.
     *
     * @param res the response to the username registration attempt
     */
    public RegisterUsernameDTO(String res){
        super("REGISTER_USERNAME");
        this.res = res;
    }

    /**
     * Creates a TextMessage containing the information about the username registration attempt response.
     *
     * @return a TextMessage representing the response to the username registration attempt
     */
    @Override
    public TextMessage makeMessage(){
        ObjectNode node = this.getMapper().createObjectNode();
        node.set("type", JsonNodeFactory.instance.textNode(this.getType()));
        node.set("response", JsonNodeFactory.instance.textNode(this.res));
        return new TextMessage(node.toString());
    }
}
