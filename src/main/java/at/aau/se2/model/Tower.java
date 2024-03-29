package at.aau.se2.model;

import lombok.Getter;

@Getter
public abstract class Tower {
    protected int lifepoints;
    public int takeDamage(int dmg){
        this.lifepoints -= dmg;
        return (lifepoints > 0) ? 0 : -1;
    }
}
