package at.aau.se2.model.towers;

import at.aau.se2.model.Tower;

public class Wall extends Tower {
    public Wall(int id){
        super(id);
        this.lifepoints = 2;
    }
}
