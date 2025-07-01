package com.code_room.interview_service.AudioStreamService.infrastruture.controller;


import com.code_room.interview_service.AudioStreamService.domain.ports.RoomService;
import com.code_room.interview_service.AudioStreamService.infrastruture.controller.dto.RoomRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/room")
@RequiredArgsConstructor
public class RoomController {

    @Autowired
    private  RoomService roomService;

    @PostMapping("/start")
    public ResponseEntity<?> StartRoom(@RequestBody RoomRequest request) {
        try {
            String roomId = roomService.createRoom(request.getInterviewerId(),request.getParticipants());
            return ResponseEntity.ok(Map.of("message","room successfully created","roomId",roomId));
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body
                    ((Map.of("message","error when creating the room"
                    ,"error", e.getMessage())));

        }
    }


}
