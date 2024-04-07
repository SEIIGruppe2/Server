package at.aau.se2.model.monsters;

import at.aau.se2.model.Monster;

public final class Bullrog extends Monster {
    public Bullrog(int zone, int ring, int id){
        super(zone, ring, id);
        this.name = "Bullrog";
        this.lifepoints = 3;
    }
}
