package at.aau.se2.utils;

import at.aau.se2.model.Monster;
import at.aau.se2.model.Tower;
import at.aau.se2.model.towers.TowerImpl;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GameState implements JsonSerializable{
    private final List<Monster> monsters;
    private final List<Tower> towers;
    private int round;
    private final int[] cardTypeAmount;
    public GameState(){
        monsters = new ArrayList<>();
        towers = new ArrayList<>();
        towers.add(new TowerImpl(0));
        cardTypeAmount = new int[4];
        this.round = 0;
    }
    public void increaseRound(){
        this.round++;
    }

    public void increaseCardAmount(int i){
        cardTypeAmount[i] += 1;
    }
    public void decreaseCardAmount(int i){
        cardTypeAmount[i] -= 1;
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

    public String convertToJson(){
        StringBuilder builder = new StringBuilder();

        builder.append("{ 'type':'GAME_STATE_UPDATE', 'monsters': [");
        buildListJson(builder, monsters);
        builder.append("'towers':[");
        buildListJson(builder, towers);
        builder.append("'round':'")
                .append(this.round)
                .append("'}");

        return builder.toString();
    }

    public void buildListJson(StringBuilder builder, List<? extends Tower> list){
        if(!list.isEmpty()){
            for(int i = 0; i < list.size() - 1; i++){
                builder.append(list.get(i).convertToJson())
                        .append(",");
            }
            builder.append(list.get(list.size()-1).convertToJson())
                    .append("],");
        }
        else{
            builder.append("],");
        }
    }
}
