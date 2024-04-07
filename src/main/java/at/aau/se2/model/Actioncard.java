package at.aau.se2.model;

import at.aau.se2.utils.JsonSerializable;
import lombok.Getter;
@Getter
public abstract class Actioncard implements JsonSerializable {
    // TODO Discuss if dmg and zonePlayed should be byte
    protected String name;
    protected final int dmg = 1;
    protected int zone;
    protected final int id;
    public Actioncard(int zone, int id){
        this.zone = zone % 4;
        this.id = id;
    }
    public abstract int doesDmg(Monster monster);
    public void setZone(int zone){
        // Modulo 4 als Sicherstellung das nur 0-3 zugewiesen werden kann
        this.zone = zone % 4;
    }

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
