package at.aau.se2.model.characters;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;

public final class Fighter extends Actioncard {
    public Fighter(int zone, int id){
        super(zone, id);
        this.name = "Schwertkämpfer";
    }

    @Override
    public int doesDmg(Monster monster){
        monster.takeDamage(dmg);
        return 0;
    }
}
