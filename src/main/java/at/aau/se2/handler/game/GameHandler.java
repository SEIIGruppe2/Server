package at.aau.se2.handler.game;

import at.aau.se2.handler.game.subhandlers.ActionHandler;
import at.aau.se2.service.GHService;
import at.aau.se2.utils.Player;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class GameHandler implements WebSocketHandler {
    //region Members
    private final Logger logger;
    private static GameHandler gameHandler;
    @Getter
    private final Map<String, ActionHandler> handlers = new HashMap<>();
    @Getter
    private final List<WebSocketSession> connectionOrder = new ArrayList<>();
    @Getter
    private static final List<Player> players = new ArrayList<>();
    @Getter
    private static final List<String> usernames = new ArrayList<>();
    private final GHService service;
    //endregion

    //region Constructor & Instance
    private GameHandler(){
        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        service = new GHService(this);
    }

    public static GameHandler getInstance(){
        if(gameHandler == null)
            gameHandler = new GameHandler();
        return gameHandler;
    }
    //endregion

    // region Communication Methods
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Connection established; SessionID: " + session.getId());
        service.helpAfterConnectionEstablished(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        JsonNode node = service.getMessage(message);
        String type = node
                        .path("type")
                        .asText()
                        .toUpperCase();
        service.makeRouting(type, session, node);
        logger.info("HandleMessage processed");
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // No Transport Error Handling yet
        throw new Exception();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        logger.info("Connection closed, SessionID: " + session.getId());
        service.helpAfterConnectionClosed(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    //endregion
}
