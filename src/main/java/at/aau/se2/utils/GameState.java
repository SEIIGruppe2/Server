package at.aau.se2.utils;

import at.aau.se2.model.Monster;
import at.aau.se2.model.Tower;
import at.aau.se2.model.towers.TowerImpl;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

import static at.aau.se2.utils.UtilityMethods.logi;

/**
 * The GameState class represents the state of the game and implements the JsonSerializable interface.
 * It provides methods to access and manipulate the game state, and it can be converted to JSON format.
 */
@Getter
public class GameState implements JsonSerializable{
    private final List<Monster> monsters;
    private final List<Tower> towers;
    private int round;
    private final int[] cardTypeAmount;

    /**
     * Constructs a new GameState object. Initializes the lists for monsters and towers,
     * sets up the initial tower, initializes the array for card type amounts,
     * and sets the round counter to zero.
     */
    public GameState(){
        monsters = new ArrayList<>();
        towers = new ArrayList<>();
        towers.add(new TowerImpl(0));
        cardTypeAmount = new int[4];
        this.round = 0;
    }

    /**
     * Increases the round counter by one.
     */
    public void increaseRound(){
        this.round++;
    }

    /**
     * Increases the count of a specific card type.
     *
     * @param i the index of the card type
     */
    public void increaseCardAmount(int i){
        cardTypeAmount[i] += 1;
    }

    /**
     * Decreases the count of a specific card type.
     *
     * @param i the index of the card type
     */
    public void decreaseCardAmount(int i){
        cardTypeAmount[i] -= 1;
    }

    /**
     * Finds the index of the card type with the minimum count.
     *
     * @return the index of the card type with the minimum count
     */
    public int getIndexMinimumCardAmount(){
        logi("getIndexMinimumCardAmount 1");
        int min = Integer.MAX_VALUE;
        logi("getIndexMinimumCardAmount 2");
        int index = 0;
        logi("getIndexMinimumCardAmount 3");
        for(int i = 0; i < this.cardTypeAmount.length; i++){
            logi("getIndexMinimumCardAmount 4");
            if(this.cardTypeAmount[i] < min){
                logi("getIndexMinimumCardAmount 5");
                min = this.cardTypeAmount[i];
                logi("getIndexMinimumCardAmount 6");
                index = i;
            }
        }
        logi("getIndexMinimumCardAmount 7");
        return index;
    }

    /**
     * Converts the GameState object to a JSON string representation.
     *
     * @return a JSON string representing the GameState object
     */
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

    /**
     * Builds the JSON representation of a list of Tower or Monster objects and appends it to the StringBuilder.
     *
     * @param builder the StringBuilder to which the JSON representation will be appended
     * @param list the list of Tower or Monster objects to be converted to JSON
     */
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
