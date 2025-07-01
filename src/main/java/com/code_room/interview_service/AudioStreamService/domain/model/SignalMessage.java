package com.code_room.interview_service.AudioStreamService.domain.model;

import com.code_room.interview_service.AudioStreamService.domain.enums.Payload;
import lombok.Data;

@Data
public class SignalMessage {
    private String userId;   // (cliente que habla)
    private String roomId;
    private Object payload;
}

