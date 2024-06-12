package at.aau.se2.model.characters;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;

/**
 * The Archer class represents an Archer action card in the game.
 * It extends the Actioncard class and provides specific initialization for the name attribute.
 */
public final class Archer extends Actioncard {
    /**
     * Constructs a new Archer object with the specified zone and ID.
     * Sets the name of the Archer to "Bogenschütze".
     *
     * @param zone the zone to which the Archer belongs
     * @param id the unique ID of the Archer
     */
    public Archer(int zone, int id){
        super(zone, id);
        this.name = "Bogenschütze";
    }

    /**
     * Deals damage to the specified monster.
     * The amount of damage dealt is determined by the Archer's doesDmg method.
     *
     * @param monster the monster to which the damage is dealt
     * @return 0 indicating successful damage dealing
     */
    @Override
    public int doesDmg(Monster monster){
        monster.takeDamage(dmg);
        return 0;
    }
}
