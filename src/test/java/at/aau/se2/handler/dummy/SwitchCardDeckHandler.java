package at.aau.se2.handler.dummy;
import at.aau.se2.model.Actioncard;
import at.aau.se2.model.characters.Knight;
import at.aau.se2.utils.GameState;
import at.aau.se2.utils.Lobby;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SwitchCardDeckHandler {
    private Lobby lobby;
    private at.aau.se2.handler.game.subhandlers.SwitchCardDeckHandler test;

    @BeforeEach
    public void setup(){
        lobby = mock(Lobby.class);
        when(lobby.getGameState()).thenReturn(new GameState());
        //TODO:check if switchcardhandler needs something
        //rn = mock(SecureRandom.class);
        test = new at.aau.se2.handler.game.subhandlers.SwitchCardDeckHandler();
    }

    @Test
    public void readInfosFromMessage(){
       Knight actioncard = new Knight(1, 23);
       //TODO make jsonmsg as an JSON object
       String jsonmsg = "{ 'type':'SWITCH_CARD_DECK', 'id': '"+actioncard.getId()+"'}";

        String[] result = new String[2];
        result[0] = "SWITCH_CARD_DECK";
        result[1] = "23";
       //assertEquals(result, test.readInfosFromMessage(jsonmsg));
    }

}
