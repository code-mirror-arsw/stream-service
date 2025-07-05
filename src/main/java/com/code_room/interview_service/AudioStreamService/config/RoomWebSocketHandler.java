package com.code_room.interview_service.AudioStreamService.config;

import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RoomWebSocketHandler extends BinaryWebSocketHandler {

    private final Map<String, Set<WebSocketSession>> rooms = new ConcurrentHashMap<>();

    private String roomId(WebSocketSession s) {
        String p = s.getUri().getPath();          // /yjs/{roomId}
        return p.substring(p.lastIndexOf('/') + 1);
    }

    @Override                 /* ---- CONNECT ---- */
    public void afterConnectionEstablished(WebSocketSession s) {
        rooms.computeIfAbsent(roomId(s), k -> ConcurrentHashMap.newKeySet()).add(s);
        System.out.printf("🟢 WS CONNECT %s room=%s%n", s.getId(), roomId(s));
    }

    @Override                 /* ---- MESSAGE ---- */
    protected void handleBinaryMessage(WebSocketSession sender, BinaryMessage msg) {
        String id = roomId(sender);
        rooms.getOrDefault(id, Set.of()).forEach(client -> {
            if (client.isOpen()) {
                try { client.sendMessage(msg); } catch (Exception ignored) {}
            }
        });
    }

    @Override                 /* ---- CLOSE ---- */
    public void afterConnectionClosed(WebSocketSession s, CloseStatus st) {
        rooms.getOrDefault(roomId(s), Set.of()).remove(s);
        System.out.printf("🔴 WS CLOSE  %s room=%s%n", s.getId(), roomId(s));
    }
}
