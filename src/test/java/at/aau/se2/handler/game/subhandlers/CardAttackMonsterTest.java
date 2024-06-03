package at.aau.se2.handler.game.subhandlers;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CardAttackMonsterTest {
   /* @InjectMocks
    private CardAttackMonster cardAttackMonster;

    @Mock
    private Lobby lobby;

    @Mock
    private GameState gameState;

    @Mock
    private WebSocketSession session;

    @Mock
    private JsonNode msg;

    @Mock
    private Player player;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ObjectNode messageNode;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        cardAttackMonster = new CardAttackMonster();
        setObjectMapper(cardAttackMonster, objectMapper);
    }

    private void setObjectMapper(CardAttackMonster instance, ObjectMapper objectMapper) {
        try {
            Field field = CardAttackMonster.class.getDeclaredField("objectMapper");
            field.setAccessible(true);
            field.set(instance, objectMapper);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setPrivateField(Object instance, String fieldName, Object value) {
        try {
            Field field = instance.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Object invokePrivateMethod(Object instance, String methodName, Class<?>[] parameterTypes, Object... args) {
        try {
            Method method = instance.getClass().getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method.invoke(instance, args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testHandleMessage_PlayerNotFoundException() throws IOException {
        when(msg.path("cardid")).thenReturn(mock(JsonNode.class));
        when(msg.path("cardid").asText()).thenReturn("1");
        when(msg.path("monsterid")).thenReturn(mock(JsonNode.class));
        when(msg.path("monsterid").asText()).thenReturn("1");


        try (MockedStatic<UtilityMethods> utilities = mockStatic(UtilityMethods.class)) {
            utilities.when(() -> UtilityMethods.findPlayer(session, lobby)).thenThrow(new PlayerNotFoundException());

            cardAttackMonster.handleMessage(session, msg, lobby);

            verify(session, never()).sendMessage(any(TextMessage.class));
        }
    }

    @Test
    void testHandleMessage_IOExcetion() throws IOException, PlayerNotFoundException {
        when(msg.path("cardid")).thenReturn(mock(JsonNode.class));
        when(msg.path("cardid").asText()).thenReturn("1");
        when(msg.path("monsterid")).thenReturn(mock(JsonNode.class));
        when(msg.path("monsterid").asText()).thenReturn("1");

        try (MockedStatic<UtilityMethods> utilities = mockStatic(UtilityMethods.class)) {
            utilities.when(() -> UtilityMethods.findPlayer(session, lobby)).thenReturn(player);


            List<Actioncard> cards = new ArrayList<>(Collections.singletonList(new Archer(0, 1)));
            when(player.getCards()).thenReturn(cards);

            when(lobby.getGameState()).thenReturn(gameState);


            Monster mockMonster = mock(Bullrog.class);
            when(mockMonster.getId()).thenReturn(1);
            when(mockMonster.getLifepoints()).thenReturn(3);
            List<Monster> monsterList = new ArrayList<>(Collections.singletonList(mockMonster));
            when(gameState.getMonsters()).thenReturn(monsterList);


            ObjectNode mockMessageNode = JsonNodeFactory.instance.objectNode();
            when(objectMapper.createObjectNode()).thenReturn(mockMessageNode);

            cardAttackMonster.handleMessage(session, msg, lobby);


            verify(mockMonster).setLifepoints(anyInt());
        }
    }

    @Test
    void testRemoveCard() {
        Actioncard card1 = new Archer(0, 1);
        Actioncard card2 = new Archer(0, 2);
        List<Actioncard> cards = new ArrayList<>(Arrays.asList(card1, card2));  // Use ArrayList to allow modification

        invokePrivateMethod(cardAttackMonster, "removeCard", new Class<?>[]{int.class, List.class}, 1, cards);

        assertEquals(1, cards.size());
        assertEquals(2, cards.get(0).getId());
    }

    @Test
    void testFindMonster() {
        Monster monster1 = new Bullrog(0, 0, 1);
        Monster monster2 = new Bullrog(0, 0, 2);
        List<Monster> monsters = Arrays.asList(monster1, monster2);

        Monster result = (Monster) invokePrivateMethod(cardAttackMonster, "findmonster", new Class<?>[]{int.class, List.class}, 2, monsters);

        assertEquals(monster2, result);
    }

    @Test
    void testDecreaseLifePoints() {
        Monster monster = new Bullrog(0, 0, 1);
        monster.setLifepoints(3);

        int newLifePoints = (int) invokePrivateMethod(cardAttackMonster, "decreaseLifePoints", new Class<?>[]{Monster.class}, monster);

        assertEquals(2, newLifePoints);
        assertEquals(2, monster.getLifepoints());
    }

    @Test
    void testUpdateOtherUser() throws IOException {
        Player player1 = mock(Player.class);
        Player player2 = mock(Player.class);
        WebSocketSession session1 = mock(WebSocketSession.class);
        WebSocketSession session2 = mock(WebSocketSession.class);

        when(lobby.getPlayers()).thenReturn(Arrays.asList(player1, player2));
        when(player1.getSession()).thenReturn(session1);
        when(player2.getSession()).thenReturn(session2);
        when(objectMapper.createObjectNode()).thenReturn(messageNode);

        setPrivateField(cardAttackMonster, "monsterId", "1");
        setPrivateField(cardAttackMonster, "lifepoints", "2");

        invokePrivateMethod(cardAttackMonster, "updateOtherUser", new Class<?>[]{Lobby.class}, lobby);

        verify(session1).sendMessage(any(TextMessage.class));
        verify(session2).sendMessage(any(TextMessage.class));
    }*/
}
