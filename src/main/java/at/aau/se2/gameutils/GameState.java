package at.aau.se2.gameutils;

import at.aau.se2.model.Actioncard;
import at.aau.se2.model.Monster;
import at.aau.se2.model.Tower;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameState {
    private List<Actioncard> actioncards;
    private List<Monster> monsters;
    private List<Tower> towers;
    private int round;
    public GameState(){
        actioncards = new ArrayList<>();
        monsters = new ArrayList<>();
        towers = new ArrayList<>();
        this.round = 0;
    }
    public void increaseRound(){
        this.round++;
    }
}
