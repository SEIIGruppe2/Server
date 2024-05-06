package at.aau.se2.handler.game.subhandlers;

import at.aau.se2.model.characters.Archer;
import at.aau.se2.model.characters.Fighter;
import at.aau.se2.model.characters.Knight;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SwitchCardPlayerHandlerTest {
    private SwitchCardPlayerHandler scph;


    @BeforeEach
    public void setup(){
        scph = new SwitchCardPlayerHandler();
    }


    @Test
    public void testGetIdOfCard(){
        String[] message = new String[4];
        message[0]="SWITCH_CARD_PLAYER_RESPONSE";
        message[1]="usename";
        message[2]="null";
        message[3]="1";
        assertEquals(1,scph.getIdOfCard(message));
        message[2]="3";
        message[3]="null";
        assertEquals(3,scph.getIdOfCard(message));
    }

    @Test
    public void testConverTOJSONrequest(){
        Fighter actioncard = new Fighter(2,5);
        String json= "{'type':'SWITCH_CARD_PLAYER_RESPONSE', 'switchedWith':'username', "+
                actioncard.convertToJson() + "}";
        assertEquals(json,scph.convertToJSONrequest(actioncard, "username"));
    }

    @Test
    public void testConvertToJson(){
        Archer actioncardtwo = new Archer(2,6);
        String jsontwo= "{'type':'SWITCH_CARD_PLAYER_RESPONSE', " +
                actioncardtwo.convertToJson() + "}";
        assertEquals(jsontwo,scph.convertToJson(actioncardtwo));
    }

    @Test
    public void testMessageContent(){
        String[] infos = new String[4];
        infos[0] = "SWITCH_CARD_WITH_PLAYER";
        infos[1] = "username";
        infos[2] = "33";
        infos[3] = "null";
        Knight actioncard = new Knight(4, 33);
        JSONObject json = new JSONObject();

        json.put("type","SWITCH_CARD_WITH_PLAYER");
        json.put("switchedWith","username");
        json.put("cardGiven","33");
        json.put("cardGivenP","null");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(json);
        assertArrayEquals(infos,scph.messageContent(node));
    }

}