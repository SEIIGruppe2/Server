package at.aau.se2.model.monsters;

import at.aau.se2.model.Monster;

/**
 * The Sphinx class represents a Sphinx monster in the game.
 * It extends the Monster class and provides specific initialization for the name and lifepoints attributes.
 */
public class Sphinx extends Monster{

    /**
     * Constructs a new Sphinx object with the specified zone, ring, and ID.
     * Sets the name of the Sphinx to "Sphinx" and the lifepoints to the default value of 2.
     *
     * @param zone the zone to which the Sphinx belongs
     * @param ring the ring in which the Sphinx is located
     * @param id the unique ID of the Sphinx
     */
    public Sphinx(int zone, int ring, int id){
        super(zone, ring, id);
        this.name = "Sphinx";
        this.lifepoints = 2;
    }
}
