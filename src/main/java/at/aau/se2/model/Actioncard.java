package at.aau.se2.model;

import lombok.Getter;
@Getter
public abstract class Actioncard {
    // TODO Discuss if dmg and zonePlayed should be byte
    protected String name;
    protected final int dmg = 1;
    protected int zonePlayed;
    public void doesDmg(Monster monster){
        monster.takeDamage(dmg);
    }
    public void setZonePlayed(int zone){
        // Modulo 4 als Sicherstellung das nur 0-3 zugewiesen werden kann
        this.zonePlayed = zone % 4;
    }
}
