package com.code_room.interview_service.AudioStreamService.infrastruture.controller;

import com.code_room.interview_service.AudioStreamService.domain.model.SignalMessage;
import com.code_room.interview_service.AudioStreamService.domain.ports.SignalingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SignalingController {

    @Autowired
    private SignalingService signalingService;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/room/{roomId}")
    public void handleSignal(
            @DestinationVariable String roomId,
            @Payload SignalMessage signal
    ) {
        System.out.println("📥 Mensaje recibido de usuario: " + signal.getUserId());
        System.out.println("📨 Reenviando señal a /topic/room/" + roomId);
        System.out.println("🧾 Payload: " + signal.getPayload());



        messagingTemplate.convertAndSend("/topic/room/" + roomId, signal);
    }
}
