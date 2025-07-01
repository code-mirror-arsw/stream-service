package com.code_room.interview_service.AudioStreamService.domain.usecases;

import com.code_room.interview_service.AudioStreamService.domain.model.Room;
import com.code_room.interview_service.AudioStreamService.domain.ports.RoomService;
import com.code_room.interview_service.AudioStreamService.domain.ports.SignalingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignalingServiceImpl implements SignalingService{

    @Autowired
    private RoomService roomService;

    @Override
    public boolean isUserAllowed(String roomId, String userId) {
        Room room = roomService.getRoom(roomId);
        if (room == null) return false;

        return room.getInterviewerId().equals(userId)
                || room.getParticipants().contains(userId);
    }
}
