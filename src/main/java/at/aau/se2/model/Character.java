package at.aau.se2.model;

import lombok.Getter;
@Getter
public class Character {
    String name;
    final int dmg = 1;

    public void doesDmg(Monster monster){
        monster.takeDamage(dmg);
    }
}
