package at.aau.se2.model.towers;

import at.aau.se2.model.Tower;

public class TowerImpl extends Tower {
    public TowerImpl(int id){
        super(id);
        this.lifepoints = 10;
    }
}

