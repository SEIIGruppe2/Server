package at.aau.se2.dto;

public class MonsterAttackDTO extends ParentDTO {
    private final String monsterId;
    private final int monsterHp;
    private final int towerHp;
    private final String attackStatus;

    public MonsterAttackDTO(String monsterId, int monsterHp, int towerHp, String attackStatus) {
        super("MONSTER_ATTACK");
        this.monsterId = monsterId;
        this.monsterHp = monsterHp;
        this.towerHp = towerHp;
        this.attackStatus = attackStatus;
    }
}