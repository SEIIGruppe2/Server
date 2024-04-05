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
    private int[] cardTypeAmount;
    public GameState(){
        actioncards = new ArrayList<>();
        monsters = new ArrayList<>();
        towers = new ArrayList<>();
        cardTypeAmount = new int[4];
        this.round = 0;
    }
    public void increaseRound(){
        this.round++;
    }

    public void increaseCardAmount(int i){
        cardTypeAmount[i] += 1;
    }

    public int getIndexMinimumCardAmount(){
        int min = Integer.MAX_VALUE;
        int index = 0;
        for(int i = 0; i < this.cardTypeAmount.length; i++){
            if(this.cardTypeAmount[i] < min){
                min = this.cardTypeAmount[i];
                index = i;
            }
        }
        return index;
    }
}
