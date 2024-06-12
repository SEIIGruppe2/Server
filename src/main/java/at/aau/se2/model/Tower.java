package at.aau.se2.model;

import at.aau.se2.utils.JsonSerializable;
import lombok.Getter;
import lombok.Setter;

/**
 * The Tower class represents a generic tower in the game.
 * It provides common functionality and attributes for all towers.
 * This class is abstract and implements the JsonSerializable interface.
 */
@Getter
public abstract class Tower  implements JsonSerializable {
    protected final int id;
    @Setter
    protected int lifepoints;

    /**
     * Constructs a new Tower object with the specified ID.
     *
     * @param id the unique ID of the tower
     */
    protected Tower(int id){
        this.id = id;
    }

    /**
     * Inflicts damage to the tower by reducing its lifepoints.
     *
     * @param dmg the amount of damage to be inflicted
     * @return 0 if the tower still has lifepoints after taking damage, -1 if the tower's lifepoints reach 0 or below
     */
    public int takeDamage(int dmg){
        this.lifepoints -= dmg;
        return (lifepoints > 0) ? 0 : -1;
    }


    /**
     * Converts the tower object to a JSON string representation.
     *
     * @return a JSON string representing the tower object
     */
    public String convertToJson(){
        StringBuilder builder = new StringBuilder();
        builder.append("{ 'id': '")
                .append(this.id)
                .append("',")
                .append("'lifepoints':'")
                .append(this.lifepoints)
                .append("'}");
        return builder.toString();
    }
}
