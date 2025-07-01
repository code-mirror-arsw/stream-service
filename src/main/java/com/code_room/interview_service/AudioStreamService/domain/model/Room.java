package com.code_room.interview_service.AudioStreamService.domain.model;

import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class Room {
    private String roomId;
    private String interviewerId;
    private List<String> participants;
    private Instant creationTime;
}

