package at.aau.se2.handler.game.subhandlers;

public class SwitchCardDeckHandlerTest {

    /*private SwitchCardDeckHandler test;

    private SecureRandom secureRandom;


    @BeforeEach
    public void setup(){

        this.secureRandom = mock(SecureRandom.class);
        test = new SwitchCardDeckHandler();
    }

    @Test
    public void testconvertToJson(){

        Knight actioncard = new Knight(2,5);
        String json= "{'type':'SWITCH_CARD_DECK_RESPONSE', "+
                actioncard.convertToJson() + "}";
        assertEquals(json,test.convertToJson(actioncard));
    }


    @Test
    public void readInfosFromMessage(){
       Knight actioncard = new Knight(1, 23);
        JSONObject json = new JSONObject();
        json.put("cardid",String.valueOf(actioncard.getId()));
        json.put("type","SWITCH_CARD_DECK");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(json);
        String[] result = new String[2];
        result[0] = "SWITCH_CARD_DECK";
        result[1] = "23";

        Assert.assertArrayEquals(result,test.readInfosFromMessage(node));

    }*/


}
