package at.aau.se2.model.monsters;

import at.aau.se2.model.Monster;

/**
 * The Slime class represents a Slime monster in the game.
 * It extends the Monster class and provides specific initialization for the name and lifepoints attributes.
 */
public final class Slime extends Monster {

    /**
     * Constructs a new Slime object with the specified zone, ring, and ID.
     * Sets the name of the Slime to "Slime" and the lifepoints to the default value of 1.
     *
     * @param zone the zone to which the Slime belongs
     * @param ring the ring in which the Slime is located
     * @param id the unique ID of the Slime
     */
    public Slime(int zone, int ring, int id){
        super(zone, ring, id);
        this.name = "Schleim";
        this.lifepoints = 1;
    }
}
