package at.aau.se2.handler;

import at.aau.se2.handler.dummy.DummyHandlerImpl;
import at.aau.se2.handler.game.GameHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry reg){
        reg.addHandler(new DummyHandlerImpl(),"/dummy").setAllowedOrigins("*");
        reg.addHandler(GameHandler.getInstance(), "/game").setAllowedOrigins("*");
    }
}
