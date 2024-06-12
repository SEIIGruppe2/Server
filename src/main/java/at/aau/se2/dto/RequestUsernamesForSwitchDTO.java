package at.aau.se2.dto;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

import java.util.List;

import static at.aau.se2.utils.UtilityMethods.logi;

/**
 * The RequestUsernamesForSwitchDTO class represents a data transfer object (DTO) for requesting usernames for card switching.
 * It extends the ParentDTO class and encapsulates the list of usernames to be requested.
 */
public class RequestUsernamesForSwitchDTO extends ParentDTO{
    private List<String> usernames;

    /**
     * Constructs a new RequestUsernamesForSwitchDTO object with the specified list of usernames.
     *
     * @param usernames the list of usernames to be requested for card switching
     */
    public RequestUsernamesForSwitchDTO(List<String> usernames){
        super("REQUEST_USERNAMES_SWITCH");
        this.usernames = usernames;
    }

    /**
     * Creates a TextMessage containing the information about the requested usernames for card switching.
     *
     * @return a TextMessage representing the requested usernames for card switching
     */
    @Override
    public TextMessage makeMessage() {
        ObjectNode node = this.getMapper().createObjectNode();
        node.put("type", getType());
        node.put("usernames", listToJsonArray());
        logi(node.toString());
        return new TextMessage(node.toString());
    }

    /**
     * Converts the list of usernames to a JSON array string.
     *
     * @return a JSON array string representing the list of usernames
     */
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
