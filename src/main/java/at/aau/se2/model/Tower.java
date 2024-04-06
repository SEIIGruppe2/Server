package at.aau.se2.model;

import at.aau.se2.utils.JsonSerializable;
import lombok.Getter;

@Getter
public abstract class Tower  implements JsonSerializable {
    protected final int id;
    protected int lifepoints;
    public Tower(int id){
        this.id = id;
    }
    public int takeDamage(int dmg){
        this.lifepoints -= dmg;
        return (lifepoints > 0) ? 0 : -1;
    }

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
