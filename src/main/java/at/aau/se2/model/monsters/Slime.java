package at.aau.se2.model.monsters;

import at.aau.se2.model.Monster;

public final class Slime extends Monster {
    public Slime(int zone, int ring, int id){
        super(zone, ring, id);
        this.name = "Schleim";
        this.lifepoints = 1;
    }
}
