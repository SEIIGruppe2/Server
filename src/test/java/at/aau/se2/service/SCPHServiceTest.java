package at.aau.se2.service;

import at.aau.se2.exceptions.CardNotFoundException;
import at.aau.se2.exceptions.PlayerNotFoundException;
import at.aau.se2.handler.game.GameHandler;
import at.aau.se2.model.Actioncard;
import at.aau.se2.model.characters.Archer;
import at.aau.se2.model.characters.Fighter;
import at.aau.se2.utils.Player;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

import static at.aau.se2.service.SCPHService.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SCPHServiceTest {
    private MockedStatic<GameHandler> gh;
    private WebSocketSession ses;
    private Player player;
    private Actioncard card;
    private List<Actioncard> cards;

    @BeforeEach
    public void setup(){
        ses = mock(WebSocketSession.class);

        player = mock(Player.class);
        when(player.getUsername()).thenReturn("user");
        when(player.getSession()).thenReturn(ses);

        List<Player> players = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            players.add(mock(Player.class));
            when(players.get(i).getSession()).thenReturn(mock(WebSocketSession.class));
            when(players.get(i).getUsername()).thenReturn("null");
        }
        players.add(player);

        gh = mockStatic(GameHandler.class);
        gh.when(GameHandler::getPlayers).thenReturn(players);

        card = mock(Actioncard.class);
        when(card.getId()).thenReturn(2);
        cards = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            cards.add(mock(Actioncard.class));
            when(cards.get(i).getId()).thenReturn(0);
        }
        cards.add(card);
    }

    @AfterEach
    public void cleanup(){
        gh.close();
    }
    @Test
    public void testFindUsernameOfPlayer() throws PlayerNotFoundException {
        assertEquals("user", findUsernameOfPlayer(ses));
        verify(player).getSession();
    }

    @Test
    public void testFindUsernameOfPlayerException(){
        assertThrows(PlayerNotFoundException.class, () ->{
            findUsernameOfPlayer(mock(WebSocketSession.class));
        });
        verify(player).getSession();
    }

    @Test
    public void testFindPlayerByUsername() throws PlayerNotFoundException {
        assertEquals(player, findPlayerByUsername("user"));
        verify(player).getUsername();
    }

    @Test
    public void testFindPlayerByUsernameException(){
        assertThrows(PlayerNotFoundException.class, () -> {
           findPlayerByUsername("nan");
        });
        verify(player).getUsername();
    }

    @Test
    public void testCardRemoval() throws CardNotFoundException {
        assertEquals(card, cardRemoval(2, cards));
        verify(card).getId();
    }

    @Test
    public void testCardRemovalException(){
        assertThrows(CardNotFoundException.class, () -> {
            cardRemoval(1, cards);
        });
        verify(card).getId();
    }

    @Test
    public void testMessageContent(){
        String[] infos = new String[4];
        infos[0] = "SWITCH_CARD_WITH_PLAYER";
        infos[1] = "username";
        infos[2] = "33";
        infos[3] = "null";
        JSONObject json = new JSONObject();

        json.put("type","SWITCH_CARD_WITH_PLAYER");
        json.put("switchedWith","username");
        json.put("cardGiven","33");
        json.put("cardGivenP","null");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.valueToTree(json);
        assertArrayEquals(infos,getMessageContent(node));
    }

    @Test
    public void testGetCardId(){
        String[] message = new String[4];
        message[0]="SWITCH_CARD_PLAYER_RESPONSE";
        message[1]="username";
        message[2]="null";
        message[3]="1";
        assertEquals(1, getCardId(message));
        message[2]="3";
        message[3]="null";
        assertEquals(3, getCardId(message));
    }

    @Test
    public void testConvertToJson(){
        Archer actioncardtwo = new Archer(2,6);
        String jsontwo= "{'type':'SWITCH_CARD_PLAYER_RESPONSE', " +
                actioncardtwo.convertToJson() + "}";
        assertEquals(jsontwo, convertToJson(actioncardtwo));
    }
    @Test
    public void testConverTOJSONrequest(){
        Fighter actioncard = new Fighter(2,5);
        String json= "{'type':'SWITCH_CARD_PLAYER_RESPONSE', 'switchedWith':'username', "+
                actioncard.convertToJson() + "}";
        assertEquals(json, convertToJSONrequest(actioncard, "username"));
    }


    @Test
    void testSetPassiveSwitchWithNull() {
        String[] content = {"type", "switchedWith", "null", "cardGivenP"};
        SCPHService.setPassiveSwitch(content);
        assertEquals(1, SCPHService.passiveSwitch);
    }

    @Test
    void testSetPassiveSwitchWithNonNull() {
        String[] content = {"type", "switchedWith", "cardGiven", "cardGivenP"};
        SCPHService.setPassiveSwitch(content);
        assertEquals(0, SCPHService.passiveSwitch);
    }

}
