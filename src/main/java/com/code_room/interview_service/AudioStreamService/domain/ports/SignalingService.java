package com.code_room.interview_service.AudioStreamService.domain.ports;

public interface SignalingService {
    boolean isUserAllowed(String roomId, String userId);
}
