package at.aau.se2.model.monsters;

import at.aau.se2.model.Monster;

public class Slime extends Monster {
    public Slime(int zone, int ring){
        super(zone, ring);
        this.name = "Schleim";
        this.lifepoints = 1;
    }
}
