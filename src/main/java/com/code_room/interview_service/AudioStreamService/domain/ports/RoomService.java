package com.code_room.interview_service.AudioStreamService.domain.ports;

import com.code_room.interview_service.AudioStreamService.domain.model.Room;

import java.util.List;

public interface RoomService {
    String createRoom(String interviewerId, List<String> participants);

    Room getRoom(String roomId);

    List<String> getUsersInRoom(String roomId);

    boolean isUserAllowed(String roomId, String userId);

    void deleteRoom(String roomId);
}
