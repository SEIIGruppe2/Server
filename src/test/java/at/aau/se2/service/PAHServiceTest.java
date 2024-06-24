package at.aau.se2.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PAHServiceTest {
    @Test
    void testReadInfosFromMessage(){
        JSONObject json = new JSONObject();
        json.put("monsterid", 1);
        json.put("cardid", 1);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(json);
        String[] result = {"1", "1"};
        assertEquals(result[0], PAHService.readInfosFromMessage(node)[0]);
        assertEquals(result[1], PAHService.readInfosFromMessage(node)[1]);
    }
}
