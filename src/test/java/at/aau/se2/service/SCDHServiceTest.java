package at.aau.se2.service;

import at.aau.se2.exceptions.CardNotFoundException;
import at.aau.se2.model.Actioncard;
import at.aau.se2.model.characters.Knight;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import static at.aau.se2.service.SCDHService.addCard;
import static at.aau.se2.service.SCDHService.convertToJson;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SCDHServiceTest {
    private SCDHService test;

    private SecureRandom secureRandom;
    private List<Actioncard> cards;
    private Actioncard card;

    @BeforeEach
    public void setup(){
        this.secureRandom = mock(SecureRandom.class);
        test = new SCDHService(secureRandom);

        cards = new ArrayList<>();
        cards.add(mock(Actioncard.class));
        when(cards.get(0).getId()).thenReturn(123);
        card = mock(Actioncard.class);
        when(card.getId()).thenReturn(2);
    }

    @Test
    public void testAddCard() throws CardNotFoundException {
        Actioncard card1 = addCard(123, card, cards);
        assertEquals(card, card1);
        assertEquals(2, card1.getId());
        assertEquals(card, cards.get(0));
    }

    @Test
    public void testAddCardException(){
        assertThrows(CardNotFoundException.class, () -> {
            addCard(3, card, cards);
        });
    }
    @Test
    public void testReadCardId(){
        Knight actioncard = new Knight(1, 23);
        JSONObject json = new JSONObject();
        json.put("cardid",String.valueOf(actioncard.getId()));
        json.put("type","SWITCH_CARD_DECK");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(json);
        String[] result = new String[2];
        result[0] = "SWITCH_CARD_DECK";
        result[1] = "23";

        assertEquals("23", SCDHService.readCardId(node));

    }

    @Test
    public void testConvertToJson(){

        Knight actioncard = new Knight(2,5);
        String json= "{'type':'SWITCH_CARD_DECK_RESPONSE', "+
                actioncard.convertToJson() + "}";
        assertEquals(json,convertToJson(actioncard));
    }
}
