package at.aau.se2.service;

import at.aau.se2.model.Monster;
import at.aau.se2.model.Tower;
import at.aau.se2.model.monsters.Bullrog;
import at.aau.se2.model.monsters.Slime;
import at.aau.se2.model.monsters.Sphinx;
import at.aau.se2.model.towers.TowerImpl;
import at.aau.se2.model.towers.Wall;
import at.aau.se2.utils.GameState;
import at.aau.se2.utils.Lobby;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class MAHServiceTest {

    @Mock
    private Lobby lobby;
    @Mock
    private GameState gameState;

    @BeforeEach
    protected void setup() {
        MockitoAnnotations.openMocks(this);
        when(lobby.getGameState()).thenReturn(gameState);
    }

    @Test
    void testTriggerMonsterAttack_Slime_Tower() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Slime(1, 1, 1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new TowerImpl(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(0, monster.getLifepoints());
        assertEquals(9, tower.getLifepoints());
    }

    @Test
    void testTriggerMonsterAttack_Sphinx_Tower() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Sphinx(1, 1, 1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new TowerImpl(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(1, monster.getLifepoints());
        assertEquals(9, tower.getLifepoints());
    }

    @Test
    void testTriggerMonsterAttack_Bullrog_Tower() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Bullrog(1, 1, 1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new TowerImpl(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(2, monster.getLifepoints());
        assertEquals(9, tower.getLifepoints());
    }

    @Test
    void testTriggerMonsterAttack_Slime_Wall() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Slime(1, 1, 1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new Wall(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(0, monster.getLifepoints());
        assertEquals(1, tower.getLifepoints());
    }

    @Test
    void testTriggerMonsterAttack_Sphinx_Wall() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Sphinx(1, 1, 1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new Wall(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(1, monster.getLifepoints());
        assertEquals(1, tower.getLifepoints());
    }

    @Test
    void testTriggerMonsterAttack_Bullrog_Wall() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Bullrog(1, 1, 1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new Wall(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(2, monster.getLifepoints());
        assertEquals(1, tower.getLifepoints());
    }

    @Test
    void testTriggerMonsterAttack_MonsterOrTowerNotFound() {
        List<Monster> monsters = new ArrayList<>();
        List<Tower> towers = new ArrayList<>();

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertTrue(monsters.isEmpty());
        assertTrue(towers.isEmpty());
    }

    @Test
    void testTriggerMonsterAttack_ErrorParsingIds() {
        MAHService.triggerMonsterAttack("abc", "def", lobby);
    }

    @Test
    void testTriggerMonsterAttack_TowerCannotBeDamaged() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = mock(Monster.class);
        when(monster.getId()).thenReturn(1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = mock(Tower.class);
        when(tower.getId()).thenReturn(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        when(monster.doesDmg(tower)).thenReturn(-1);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        verify(monster, times(1)).doesDmg(tower);
        verify(tower, never()).takeDamage(anyInt());
        verify(monster, never()).takeDamage(anyInt());

    }


    @Test
    void testIsMessageTypeValid_ValidType() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree("{\"type\":\"MONSTER_ATTACK\"}");
        assertTrue(MAHService.isMessageTypeValid(jsonNode));
    }

    @Test
    void testIsMessageTypeValid_InvalidType() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree("{\"type\":\"INVALID_TYPE\"}");
        assertFalse(MAHService.isMessageTypeValid(jsonNode));
    }

    @Test
    void testValidateEntities_ValidEntities() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Slime(1, 1, 1));
        monsters.add(new Bullrog(2, 1, 1));

        List<Tower> towers = new ArrayList<>();
        towers.add(new TowerImpl(1));
        towers.add(new Wall(2));

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        assertTrue(MAHService.validateEntities("1", "1", lobby));
    }

    @Test
    void testValidateEntities_InvalidEntities() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Slime(1, 1, 1));

        List<Tower> towers = new ArrayList<>();
        towers.add(new TowerImpl(1));

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        assertFalse(MAHService.validateEntities("2", "2", lobby));
    }

    @Test
    void testFindMonsterById_Found() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Slime(1, 1, 1);
        monsters.add(monster);

        assertEquals(monster, MAHService.findMonsterById(monsters, 1));
    }

    @Test
    void testFindMonsterById_NotFound() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Slime(1, 1, 1);
        monsters.add(monster);

        assertNull(MAHService.findMonsterById(monsters, 2));
    }

    @Test
    void testFindTowerById_Found() {
        List<Tower> towers = new ArrayList<>();
        Tower tower = new TowerImpl(1);
        towers.add(tower);

        assertEquals(tower, MAHService.findTowerById(towers, 1));
    }

    @Test
    void testFindTowerById_NotFound() {
        List<Tower> towers = new ArrayList<>();
        Tower tower = new TowerImpl(1);
        towers.add(tower);

        assertNull(MAHService.findTowerById(towers, 2));
    }

    @Test
    void testTriggerMonsterAttack_TowerNull() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Slime(1, 1, 1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(1, monster.getLifepoints());
        assertTrue(towers.isEmpty());
    }

    @Test
    void testTriggerMonsterAttack_MonsterNull() {
        List<Monster> monsters = new ArrayList<>();

        List<Tower> towers = new ArrayList<>();
        Tower tower = new TowerImpl(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertTrue(monsters.isEmpty());
        assertEquals(10, tower.getLifepoints());
    }

    @Test
    void testValidateEntities_InvalidNumberFormatForMonsterId() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Slime(1, 1, 1));

        List<Tower> towers = new ArrayList<>();
        towers.add(new TowerImpl(1));

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        // Pass non-integer value for monsterId
        assertFalse(MAHService.validateEntities("one", "1", lobby));
    }

    @Test
    void testValidateEntities_InvalidNumberFormatForTowerId() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Slime(1, 1, 1));

        List<Tower> towers = new ArrayList<>();
        towers.add(new TowerImpl(1));

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        // Pass non-integer value for towerId
        assertFalse(MAHService.validateEntities("1", "one", lobby));
    }
}
