package at.aau.se2.dto;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

import java.util.List;

import static at.aau.se2.utils.UtilityMethods.logi;

public class RequestUsernamesForSwitchDTO extends ParentDTO{
    private List<String> usernames;
    public RequestUsernamesForSwitchDTO(List<String> usernames){
        super("REQUEST_USERNAMES_SWITCH");
        this.usernames = usernames;
    }

    @Override
    public TextMessage makeMessage() {
        ObjectNode node = this.getMapper().createObjectNode();
        node.put("type", getType());
        node.put("usernames", listToJsonArray());
        logi(node.toString());
        return new TextMessage(node.toString());
    }

    private String listToJsonArray(){
        String arr = "[";
        for(String s : usernames){
            if(s.equals(usernames.get(usernames.size()-1))){
                arr += "'" + s + "']";
                break;
            }
            arr += "'" + s + "',";
        }
        return arr;
    }
}
