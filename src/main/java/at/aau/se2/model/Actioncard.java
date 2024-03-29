package at.aau.se2.model;

import lombok.Getter;
@Getter
public abstract class Actioncard {
    // TODO Discuss if dmg and zonePlayed should be byte
    protected String name;
    protected final int dmg = 1;
    protected int zone;
    public Actioncard(int zone){
        this.zone = zone % 4;
    }
    public abstract int doesDmg(Monster monster);
    public void setZone(int zone){
        // Modulo 4 als Sicherstellung das nur 0-3 zugewiesen werden kann
        this.zone = zone % 4;
    }
}
