package at.aau.se2.dto;


import org.springframework.web.socket.TextMessage;

import java.util.List;



public class ShowMonsterDTO extends ParentDTO{
    private List<String>monsters;
    public ShowMonsterDTO(List<String> monsterid){
        super("SHOW_MONSTERS");
        this.monsters=monsterid;

    }
    @Override
    public TextMessage makeMessage() {

        StringBuilder builder = new StringBuilder();
        builder.append("{ 'type': 'SHOW_MONSTERS', 'monstersid': [");
        builder.append(listToJsonArray());

        return new TextMessage(builder.toString());
    }

    private String listToJsonArray(){
        StringBuilder monster = new StringBuilder();
        for(String m : monsters){
            if(monsters.get(monsters.size()-1).equals(m))
                monster.append("'").append(m).append("']}");
            else
                monster.append("'").append(m).append("',");
        }

        return monster.toString();
    }
}
