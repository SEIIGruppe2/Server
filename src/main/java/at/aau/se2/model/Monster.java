package at.aau.se2.model;

import lombok.Getter;
import lombok.Setter;
@Getter
public abstract class Monster extends Tower{
    protected final int id;
    protected String name;
    @Setter
    protected int zone;
    @Setter
    protected int ring;
    public Monster(int zone, int ring, int id){
        this.zone = zone % 4; // 0-3
        this.ring = ring % 4; // 0-3
        this.id = id;
    }
    public int doesDmg(Tower tower){
        if(tower.lifepoints >= 1) {
            tower.takeDamage(1);
            return 0;
        }
        return -1;
    }
}
