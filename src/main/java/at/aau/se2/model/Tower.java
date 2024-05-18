package at.aau.se2.model;

import at.aau.se2.utils.JsonSerializable;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Tower  implements JsonSerializable {
    protected final int id;
    @Setter
    protected int lifepoints;
    protected Tower(int id){
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
