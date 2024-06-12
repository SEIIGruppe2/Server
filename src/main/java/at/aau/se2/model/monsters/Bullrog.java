package at.aau.se2.model.monsters;

import at.aau.se2.model.Monster;

/**
 * The Bullrog class represents a Bullrog monster in the game.
 * It extends the Monster class and provides specific initialization for the name and lifepoints attributes.
 */
public final class Bullrog extends Monster {

    /**
     * Constructs a new Bullrog object with the specified zone, ring, and ID.
     * Sets the name of the Bullrog to "Bullrog" and the lifepoints to the default value of 3.
     *
     * @param zone the zone to which the Bullrog belongs
     * @param ring the ring in which the Bullrog is located
     * @param id the unique ID of the Bullrog
     */
    public Bullrog(int zone, int ring, int id){
        super(zone, ring, id);
        this.name = "Bullrog";
        this.lifepoints = 3;
    }
}
