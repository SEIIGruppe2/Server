package at.aau.se2.dto;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

/**
 * A Data Transfer Object (DTO) that represents the trophy points of a player.
 * This class extends the ParentDTO class and is used to encapsulate the details of a player's trophies.
 */
public class PlayerTrophiesDTO extends ParentDTO {
    private final String playerName;
    private final int points;

    /**
     * Constructs a new PlayerTrophiesDTO with the specified player name and points.
     *
     * @param playerName the name of the player
     * @param points the trophy points of the player
     */
    public PlayerTrophiesDTO(String playerName, int points) {
        super("PLAYER_TROPHIES");
        this.playerName = playerName;
        this.points = points;
    }

    /**
     * Creates a WebSocket TextMessage containing the details of the player's trophy points.
     * This method serializes the trophy details into a JSON object and encapsulates it in a TextMessage.
     *
     * @return a TextMessage containing the JSON representation of the player's trophy points
     */
    @Override
    public TextMessage makeMessage() {
        ObjectNode node = this.getMapper().createObjectNode();
        node.set("type", JsonNodeFactory.instance.textNode(this.getType()));
        node.set("playerName", JsonNodeFactory.instance.textNode(this.playerName));
        node.set("points", JsonNodeFactory.instance.numberNode(this.points));
        return new TextMessage(node.toString());
    }
}
