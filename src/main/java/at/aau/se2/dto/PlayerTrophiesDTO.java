package at.aau.se2.dto;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.socket.TextMessage;

public class PlayerTrophiesDTO extends ParentDTO {
    private final String playerName;
    private final int points;

    public PlayerTrophiesDTO(String playerName, int points) {
        super("PLAYER_TROPHIES");
        this.playerName = playerName;
        this.points = points;
    }

    @Override
    public TextMessage makeMessage() {
        ObjectNode node = this.getMapper().createObjectNode();
        node.put("type", this.getType());
        node.put("playerName", playerName);
        node.put("points", points);
        return new TextMessage(node.toString());
    }
}