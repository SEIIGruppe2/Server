package at.aau.se2.model.characters;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;

public final class Knight extends Actioncard {
    public Knight(int zone){
        super(zone);
        this.name = "Ritter";
    }
    @Override
    public int doesDmg(Monster monster){
        if(monster.getZone() == this.zone && monster.getRing() == 2) {
            monster.takeDamage(dmg);
            return 0;
        }
        return -1;
    }
}
