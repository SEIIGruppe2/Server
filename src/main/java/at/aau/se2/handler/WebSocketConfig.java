package at.aau.se2.handler;

import at.aau.se2.handler.dummy.DummyHandlerImpl;
import at.aau.se2.handler.game.GameHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry reg){
        reg.addHandler(new DummyHandlerImpl(),"/dummy").setAllowedOrigins("*");
        reg.addHandler(new GameHandler(), "/game").setAllowedOrigins("*");
    }
}
