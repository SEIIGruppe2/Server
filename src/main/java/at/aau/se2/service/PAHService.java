package at.aau.se2.service;

import com.fasterxml.jackson.databind.JsonNode;

public class PAHService {
    public static String[] readInfosFromMessage(JsonNode msg){
        String[] arr = new String[2];
        arr[0] = msg.path("monsterid").asText();
        arr[1] = msg.path("cardid").asText();
        return arr;
    }
}
