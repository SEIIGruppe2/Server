package at.aau.se2.model.towers;

import at.aau.se2.model.Tower;

/**
 * The Wall class represents a wall tower in the game.
 * It extends the Tower class and provides specific initialization for the lifepoints attribute.
 */
public class Wall extends Tower {

    /**
     * Constructs a new Wall object with the specified ID.
     * Sets the lifepoints of the wall tower to the default value of 2.
     *
     * @param id the unique ID of the wall tower
     */
    public Wall(int id){
        super(id);
        this.lifepoints = 2;
    }
}
