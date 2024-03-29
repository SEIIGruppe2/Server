package at.aau.se2.model.monsters;

import at.aau.se2.model.Monster;
public class Sphinx extends Monster{
    public Sphinx(int zone, int ring){
        super(zone, ring);
        this.name = "Sphinx";
        this.lifepoints = 2;
    }
}
