package at.aau.se2.model.characters;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;

public final class Hero extends Actioncard {
    public Hero(int zone, int id){
        super(zone, id);
        this.name = "Held";
    }
    @Override
    public int doesDmg(Monster monster){
        monster.takeDamage(dmg);
        return 0;
    }
}
