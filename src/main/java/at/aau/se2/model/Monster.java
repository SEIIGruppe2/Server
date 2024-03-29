package at.aau.se2.model;

import lombok.Getter;
import lombok.Setter;
@Getter
public abstract class Monster extends Tower{
    protected String name;
    @Setter
    protected int zone;
    @Setter
    protected int ring;
    public Monster(int zone, int ring){
        this.zone = zone % 4; // 0-3
        this.ring = ring % 4; // 0-3
    }
    public int doesDmg(Tower tower){
        if(tower.lifepoints >= 1) {
            tower.takeDamage(1);
            return 0;
        }
        return -1;
    }
}
