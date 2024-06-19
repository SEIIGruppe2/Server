package at.aau.se2.model.characters;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;

/**
 * The Knight class represents a Knight action card in the game.
 * It extends the Actioncard class and provides specific initialization for the name attribute.
 */
public final class Knight extends Actioncard {
    /**
     * Constructs a new Knight object with the specified zone and ID.
     * Sets the name of the Knight to "Ritter".
     *
     * @param zone the zone to which the Knight belongs
     * @param id the unique ID of the Knight
     */
    public Knight(int zone, int id){
        super(zone, id);
        this.name = "Ritter";
    }

    /**
     * Deals damage to the specified monster.
     * The amount of damage dealt is determined by the Knight's doesDmg method.
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
