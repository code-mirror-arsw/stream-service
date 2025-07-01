package com.code_room.interview_service.AudioStreamService.domain.usecases;

import com.code_room.interview_service.AudioStreamService.domain.model.Room;
import com.code_room.interview_service.AudioStreamService.domain.ports.RoomService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RoomServiceImpl implements RoomService{

    private final Map<String, Room> rooms = new ConcurrentHashMap<>();

    @Override
    public String createRoom(String interviewerId, List<String> participants) {
        String roomId = String.valueOf(UUID.randomUUID());

        if (rooms.containsKey(roomId)){
            new RuntimeException("the room has already been created");
        }

        Room room = new Room();
        room.setRoomId(roomId);
        room.setInterviewerId(interviewerId);
        room.setParticipants(participants);
        room.setCreationTime(Instant.now());

        rooms.put(roomId, room);
        return roomId;
    }

    @Override
    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }

    @Override
    public List<String> getUsersInRoom(String roomId) {
        Room room = getRoom(roomId);
        if (room == null) return Collections.emptyList();

        List<String> users = new ArrayList<>(room.getParticipants());
        users.add(room.getInterviewerId());
        return users;
    }

    @Override
    public boolean isUserAllowed(String roomId, String userId) {
        Room room = getRoom(roomId);
        if (room == null) return false;

        return room.getInterviewerId().equals(userId) || room.getParticipants().contains(userId);
    }

    @Override
    public void deleteRoom(String roomId) {
        rooms.remove(roomId);
    }
}