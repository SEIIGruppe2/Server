package at.aau.se2.utils;

import at.aau.se2.model.Monster;
import at.aau.se2.model.Tower;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GameState implements JsonSerializable{
    private List<Monster> monsters;
    private List<Tower> towers;
    private int round;
    private int[] cardTypeAmount;
    public GameState(){
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
    public void decreaseCardAmount(int i){cardTypeAmount[i] -= 1;}

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

    public String convertToJson(){
        StringBuilder builder = new StringBuilder();

        builder.append("{ 'type':'GAME_STATE_UPDATE', 'monsters': [");
        if(!monsters.isEmpty()) {
            for (int i = 0; i < monsters.size() - 1; i++) {
                builder.append(monsters.get(i).convertToJson())
                        .append(",");

                builder.append(monsters.get(monsters.size() - 1).convertToJson())
                        .append("],");
            }
        }
        else {
            builder.append("],");
        }
        if(!towers.isEmpty()) {
            for (int i = 0; i < towers.size() - 1; i++) {
                builder.append(towers.get(i).convertToJson())
                        .append(",");
            }
            builder.append(towers.get(towers.size() - 1).convertToJson())
                    .append("],");
        }
        builder.append("],");
        builder.append("'round':'")
                .append(this.round)
                .append("'}");

        return builder.toString();
    }
}
