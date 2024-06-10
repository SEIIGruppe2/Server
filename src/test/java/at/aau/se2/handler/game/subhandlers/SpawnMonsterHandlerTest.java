package at.aau.se2.handler.game.subhandlers;

public class SpawnMonsterHandlerTest {
    /*private Lobby lobby;

    private int zone;
    private SecureRandom rn;
    private SpawnMonsterHandler sph;
    @BeforeEach
    public void setup(){
        lobby = mock(Lobby.class);
        when(lobby.getGameState()).thenReturn(new GameState());
        rn = mock(SecureRandom.class);
        sph = new SpawnMonsterHandler(rn);
    }
    @Test
    public void testSpawnRandomMonster(){
        when(rn.nextInt(1,3)).thenReturn(1);
        assertEquals(Slime.class, sph.spawnRandomMonster(1,lobby).getClass());
        when(rn.nextInt(1,3)).thenReturn(2);
        assertEquals(Sphinx.class, sph.spawnRandomMonster(2,lobby).getClass());
        when(rn.nextInt(1,3)).thenReturn(3);
        assertEquals(Bullrog.class, sph.spawnRandomMonster(3,lobby).getClass());
        verify(lobby, times(3)).getGameState();
        verify(rn, times(3)).nextInt(1,3);
    }

    @Test
    public void testIncreaseMonsterId(){
        assertEquals(0, SpawnMonsterHandler.getMonsterId());
        SpawnMonsterHandler.increaseMonsterId();
        assertEquals(1, SpawnMonsterHandler.getMonsterId());
    }*/
}
