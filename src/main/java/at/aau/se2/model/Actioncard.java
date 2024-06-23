package at.aau.se2.model;

import at.aau.se2.utils.JsonSerializable;
import lombok.Getter;

/**
 * The Actioncard class represents a generic action card in the game.
 * It provides common functionality and attributes for all action cards.
 * This class is abstract and implements the JsonSerializable interface.
 */
@Getter
public abstract class Actioncard implements JsonSerializable {
    protected String name;
    protected final int dmg = 1;
    protected int zone;
    protected final int id;

    /**
     * Constructs a new Actioncard object with the specified zone and ID.
     *
     * @param zone the zone to which the action card belongs
     * @param id the ID of the action card
     */
    protected Actioncard(int zone, int id){
        this.zone = zone;
        this.id = id;
    }

    /**
     * Calculates the damage dealt by the action card to the specified monster.
     * Subclasses must override this method to provide specific damage calculation logic.
     *
     * @param monster the monster to which the damage is dealt
     * @return the amount of damage dealt by the action card
     */
    public abstract int doesDmg(Monster monster);

    /**
     * Sets the zone of the action card.
     * This method ensures that only values from 0 to 3 can be assigned as the zone.
     *
     * @param zone the zone to which the action card belongs
     */
    public void setZone(int zone){
        // Modulo 4 als Sicherstellung das nur 0-3 zugewiesen werden kann
        this.zone = zone;
    }

    /**
     * Converts the action card object to a JSON string representation.
     *
     * @return a JSON string representing the action card object
     */
    @Override
    public String convertToJson(){
        return "'id': '" +
                id +
                "', 'name': '" +
                name +
                "', 'zone': '" +
                zone +
                "'";
    }
}
