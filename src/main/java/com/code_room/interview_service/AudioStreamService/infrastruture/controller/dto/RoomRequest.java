package com.code_room.interview_service.AudioStreamService.infrastruture.controller.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomRequest {
    private String interviewerId;
    private List<String> participants;
}
