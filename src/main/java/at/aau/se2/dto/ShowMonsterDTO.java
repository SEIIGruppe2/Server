package at.aau.se2.dto;

import at.aau.se2.model.Monster;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

import java.util.List;

import static at.aau.se2.utils.UtilityMethods.logi;

public class ShowMonsterDTO extends ParentDTO{
    private List<String>monsters;
    public ShowMonsterDTO(List<String> monsterid){
        super("SHOW_MONSTERS");
        this.monsters=monsterid;

    }
    @Override
    public TextMessage makeMessage() {
        ObjectNode node = this.getMapper().createObjectNode();
        node.put("type", getType());
        node.put("monstersid", listToJsonArray());
        logi(node.toString());
        return new TextMessage(node.toString());
    }

    private String listToJsonArray(){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        String arr = "[";
        for(String m : monsters){
            if(monsters.get(monsters.size()-1).equals(m))
                builder.append("'").append(m).append("']}");
            else
                builder.append("'").append(m).append("',");
        }
        return builder.toString();
    }
}
