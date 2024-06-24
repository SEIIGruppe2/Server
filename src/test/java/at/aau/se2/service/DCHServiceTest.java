package at.aau.se2.service;

import at.aau.se2.model.characters.Archer;
import at.aau.se2.model.characters.Fighter;
import at.aau.se2.model.characters.Hero;
import at.aau.se2.model.characters.Knight;
import at.aau.se2.utils.GameState;
import at.aau.se2.utils.Lobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static at.aau.se2.service.DCHService.convertToJson;
import static at.aau.se2.service.DCHService.drawRandomCard;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class DCHServiceTest {
    private Lobby lobby;
    private SecureRandom rn;
    private GameState gs;


    @BeforeEach
    public void setup(){
        this.lobby = mock(Lobby.class);
        this.rn = mock(SecureRandom.class);
        this.gs = mock(GameState.class);
    }

    @Test
    void testDrawRandomCard(){
        when(lobby.getGameState()).thenReturn(gs);
        when(gs.getIndexMinimumCardAmount()).thenReturn(0);
        when(rn.nextInt(anyInt(), anyInt())).thenReturn(1);
        assertEquals(Archer.class, drawRandomCard(lobby).getClass());
        when(gs.getIndexMinimumCardAmount()).thenReturn(1);
        assertEquals(Fighter.class, drawRandomCard(lobby).getClass());
        when(gs.getIndexMinimumCardAmount()).thenReturn(2);
        assertEquals(Knight.class, drawRandomCard(lobby).getClass());
        when(gs.getIndexMinimumCardAmount()).thenReturn(3);
        assertEquals(Hero.class, drawRandomCard(lobby).getClass());
        verify(gs, times(4)).getIndexMinimumCardAmount();
    }

    @Test
    void testConvertToJson(){
        String exp = "{ 'type' : 'DRAW_CARD', 'id': '1', 'name': 'Bogenschütze', 'zone': '1'}";
        assertEquals(exp, convertToJson(new Archer(1,1)));
    }
}
