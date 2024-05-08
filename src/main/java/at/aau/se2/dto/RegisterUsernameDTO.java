package at.aau.se2.dto;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

public class RegisterUsernameDTO extends ParentDTO{
    private final String res;
    public RegisterUsernameDTO(String res){
        super("REGISTER_USERNAME");
        this.res = res;
    }

    @Override
    public TextMessage makeMessage(){
        ObjectNode node = this.getMapper().createObjectNode();
        node.put("type", this.getType());
        node.put("response", res);
        return new TextMessage(node.toString());
    }
}
