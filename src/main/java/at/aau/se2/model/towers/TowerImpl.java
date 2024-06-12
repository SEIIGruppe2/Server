package at.aau.se2.model.towers;

import at.aau.se2.model.Tower;

/**
 * The TowerImpl class represents an implementation of a tower in the game.
 * It extends the Tower class and provides specific initialization for the lifepoints attribute.
 */
public class TowerImpl extends Tower {
    /**
     * Constructs a new TowerImpl object with the specified ID.
     * Sets the lifepoints of the tower to the default value of 10.
     *
     * @param id the unique ID of the tower
     */
    public TowerImpl(int id){
        super(id);
        this.lifepoints = 10;
    }
}

