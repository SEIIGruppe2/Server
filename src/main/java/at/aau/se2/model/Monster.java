package at.aau.se2.model;

import lombok.Getter;
import lombok.Setter;
@Getter
public abstract class Monster extends Tower{
    protected String name;
    @Setter
    protected int zone;
    @Setter
    protected int ring;
    public Monster(int zone, int ring, int id){
        super(id);
        this.zone = zone % 4; // 0-3
        this.ring = ring % 4; // 0-3
    }
    public int doesDmg(Tower tower){
        if(tower.lifepoints >= 1) {
            tower.takeDamage(1);
            return 0;
        }
        return -1;
    }
    @Override
    public String convertToJson(){
        StringBuilder builder = new StringBuilder();
        builder.append("{ 'id': '")
                .append(this.id)
                .append("', ")
                .append("'zone': '")
                .append(this.zone)
                .append("', ")
                .append("'ring': '")
                .append(this.ring)
                .append("', ")
                .append("'name': '")
                .append(this.name)
                .append("', ")
                .append("'lifepoints': '")
                .append(this.lifepoints)
                .append("'}");
        return builder.toString();
    }
}
