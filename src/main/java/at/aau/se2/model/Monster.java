package at.aau.se2.model;

import at.aau.se2.utils.JsonSerializable;
import lombok.Getter;
import lombok.Setter;

/**
 * The Monster class represents a generic monster in the game, extending the Tower class.
 * It provides common functionality and attributes for all monsters.
 * This class is abstract and implements the JsonSerializable interface.
 */
@Getter
public abstract class Monster extends Tower implements JsonSerializable {
    protected String name;
    @Setter
    protected int zone;
    @Setter
    protected int ring;

    /**
     * Constructs a new Monster object with the specified zone, ring, and ID.
     *
     * @param zone the zone to which the monster belongs
     * @param ring the ring in which the monster is located
     * @param id the ID of the monster
     */
    protected Monster(int zone, int ring, int id){
        super(id);
        this.zone = zone;
        this.ring = ring;
    }

    /**
     * Deals damage to the specified tower.
     * If the tower has at least 1 lifepoint, it takes 1 damage.
     *
     * @param tower the tower to which the damage is dealt
     * @return 0 if damage is successfully dealt, -1 if the tower has 0 lifepoints
     */
    public int doesDmg(Tower tower){
        if(tower.getLifepoints() >= 1) {
            tower.takeDamage(1);
            return 0;
        }
        return -1;
    }

    /**
     * Converts the monster object to a JSON string representation.
     *
     * @return a JSON string representing the monster object
     */
    @Override
    public String convertToJson(){
        StringBuilder builder = new StringBuilder();
        builder.append("{")
                .append("\"id\":")
                .append(this.id)
                .append(", ")
                .append("\"zone\":")
                .append(this.zone)
                .append(", ")
                .append("\"ring\":")
                .append(this.ring)
                .append(", ")
                .append("\"name\":\"")
                .append(this.name)
                .append("\", ")
                .append("\"lifepoints\":")
                .append(this.lifepoints)
                .append("}");
        return builder.toString();
    }
}
