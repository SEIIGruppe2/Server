package at.aau.se2.model.characters;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;

public class Archer extends Actioncard {
    public Archer(int zone){
        super(zone);
        this.name = "Bogenschütze";
    }

    @Override
    public int doesDmg(Monster monster){
        if(monster.getZone() == this.zone && monster.getRing() == 1) {
            monster.takeDamage(dmg);
            return 0;
        }
        return -1;
    }
}
