package at.aau.se2.handler.game.subhandlers;



public class DrawCardHandlerTest {
    /*private Lobby lobby;
    private SecureRandom rn;
    private GameState gs;

    private DrawCardHandler dch;

    @BeforeEach
    public void setup(){
        this.lobby = mock(Lobby.class);
        this.rn = mock(SecureRandom.class);
        this.gs = mock(GameState.class);
        this.dch = new DrawCardHandler(rn);
    }

    @Test
    public void testDrawRandomCard(){
        when(lobby.getGameState()).thenReturn(gs);
        when(gs.getIndexMinimumCardAmount()).thenReturn(0);
        when(rn.nextInt(anyInt(), anyInt())).thenReturn(1);
        assertEquals(Archer.class, dch.drawRandomCard(lobby).getClass());
        when(gs.getIndexMinimumCardAmount()).thenReturn(1);
        assertEquals(Fighter.class, dch.drawRandomCard(lobby).getClass());
        when(gs.getIndexMinimumCardAmount()).thenReturn(2);
        assertEquals(Knight.class, dch.drawRandomCard(lobby).getClass());
        when(gs.getIndexMinimumCardAmount()).thenReturn(3);
        assertEquals(Hero.class, dch.drawRandomCard(lobby).getClass());
        verify(gs, times(4)).getIndexMinimumCardAmount();
    }

    @Test
    public void testConvertToJson(){
        String exp = "{ 'type' : 'DRAW_CARD', 'id': '1', 'name': 'Bogenschütze', 'zone': '1'}";
        assertEquals(exp, dch.convertToJson(new Archer(1,1)));
    }*/
}