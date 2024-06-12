package at.aau.se2.model.characters;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;

/**
 * The Fighter class represents a Fighter action card in the game.
 * It extends the Actioncard class and provides specific initialization for the name attribute.
 */
public final class Fighter extends Actioncard {
    /**
     * Constructs a new Fighter object with the specified zone and ID.
     * Sets the name of the Fighter to "Schwertkämpfer".
     *
     * @param zone the zone to which the Fighter belongs
     * @param id the unique ID of the Fighter
     */
    public Fighter(int zone, int id){
        super(zone, id);
        this.name = "Schwertkämpfer";
    }

    /**
     * Deals damage to the specified monster.
     * The amount of damage dealt is determined by the Fighter's doesDmg method.
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
