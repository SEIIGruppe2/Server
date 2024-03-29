package at.aau.se2.model;

import lombok.Getter;

@Getter
public abstract class Tower {
    int lifepoints;
    abstract int takeDamage(int dmg);
}
