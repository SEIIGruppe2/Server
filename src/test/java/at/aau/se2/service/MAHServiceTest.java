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
    protected void testTriggerMonsterAttack_Slime_Tower() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Slime(1,1,1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new TowerImpl(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(0, monster.getLifepoints());  // Assuming initial lifepoints are > 1
        assertEquals(2, tower.getLifepoints());   // Assuming tower takes 1 damage
    }

    @Test
    protected void testTriggerMonsterAttack_Sphinx_Tower() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Sphinx(1,1,1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new TowerImpl(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(1, monster.getLifepoints());  // Assuming initial lifepoints are > 1
        assertEquals(2, tower.getLifepoints());   // Assuming tower takes 1 damage
    }
    @Test
    protected void testTriggerMonsterAttack_Bullrog_Tower() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Bullrog(1,1,1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new TowerImpl(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(2, monster.getLifepoints());  // Assuming initial lifepoints are > 1
        assertEquals(2, tower.getLifepoints());   // Assuming tower takes 1 damage
    }
    @Test
    protected void testTriggerMonsterAttack_Slime_Wall() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Slime(1,1,1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new Wall(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(0, monster.getLifepoints());  // Assuming initial lifepoints are > 1
        assertEquals(1, tower.getLifepoints());   // Assuming tower takes 1 damage
    }
    @Test
    protected void testTriggerMonsterAttack_Sphinx_Wall() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Sphinx(1,1,1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new Wall(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(1, monster.getLifepoints());  // Assuming initial lifepoints are > 1
        assertEquals(1, tower.getLifepoints());   // Assuming tower takes 1 damage
    }
    @Test
    protected void testTriggerMonsterAttack_Bullrog_Wall() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = new Bullrog(1,1,1);
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = new Wall(1);
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertEquals(2, monster.getLifepoints());  // Assuming initial lifepoints are > 1
        assertEquals(1, tower.getLifepoints());   // Assuming tower takes 1 damage
    }

    @Test
    protected void testTriggerMonsterAttack_MonsterOrTowerNotFound() {
        List<Monster> monsters = new ArrayList<>();
        List<Tower> towers = new ArrayList<>();

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        MAHService.triggerMonsterAttack("1", "1", lobby);

        assertTrue(monsters.isEmpty());
        assertTrue(towers.isEmpty());
    }

    @Test
    protected void testTriggerMonsterAttack_ErrorParsingIds() {
        MAHService.triggerMonsterAttack("abc", "def", lobby);
    }

    @Test
    protected void testTriggerMonsterAttack_TowerCannotBeDamaged() {
        List<Monster> monsters = new ArrayList<>();
        Monster monster = mock(Monster.class);
        when(monster.getId()).thenReturn(1);  // Set the monster ID to match the test case
        monsters.add(monster);

        List<Tower> towers = new ArrayList<>();
        Tower tower = mock(Tower.class);
        when(tower.getId()).thenReturn(1);  // Set the tower ID to match the test case
        towers.add(tower);

        when(gameState.getMonsters()).thenReturn(monsters);
        when(gameState.getTowers()).thenReturn(towers);

        when(monster.doesDmg(tower)).thenReturn(-1);  // No damage to the tower

        MAHService.triggerMonsterAttack("1", "1", lobby);

        verify(monster, times(1)).doesDmg(tower);
        verify(tower, never()).takeDamage(anyInt());
        verify(monster, never()).takeDamage(anyInt());

    }
}