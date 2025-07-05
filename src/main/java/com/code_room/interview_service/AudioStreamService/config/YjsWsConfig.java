package com.code_room.interview_service.AudioStreamService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.List;

@Configuration
@EnableWebSocket
public class YjsWsConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        WebSocketHandler handler = new RoomWebSocketHandler();

        DefaultHandshakeHandler hsHandler = new DefaultHandshakeHandler() {
            @Override
            protected String selectProtocol(List<String> requested, WebSocketHandler wsh) {
                return requested.isEmpty() ? null : requested.get(0); // "binary"
            }
        };

        registry.addHandler(handler, "/yjs/{roomId}")
                 .setAllowedOriginPatterns("http://localhost:5173", "http://localhost:5000")
                .setAllowedOriginPatterns("*")                 // ← comodín para pruebas
                .setHandshakeHandler(hsHandler);
    }
}
